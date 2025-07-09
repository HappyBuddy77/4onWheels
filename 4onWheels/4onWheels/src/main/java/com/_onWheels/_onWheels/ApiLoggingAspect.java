package com._onWheels._onWheels;

import com._onWheels._onWheels.ApiLog;
import com._onWheels._onWheels.ApiLogService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Aspect
@Component
public class ApiLoggingAspect {

    private static final AtomicLong counter = new AtomicLong(1);

    private static final Map<String, String> SERVICE_MAP = new HashMap<>();

    static {
        SERVICE_MAP.put("/auth", "Auth");
        SERVICE_MAP.put("/orders", "OrderService");
        SERVICE_MAP.put("/payments", "Payment");
        SERVICE_MAP.put("/admin", "Admin");
    }

    private final ApiLogService logService;

    @Autowired
    public ApiLoggingAspect(ApiLogService logService) {
        this.logService = logService;
    }

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object logApiActivity(ProceedingJoinPoint joinPoint) throws Throwable {
        long id = counter.getAndIncrement();
        Instant timestamp = Instant.now();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String path = request.getRequestURI();
        String service = resolveService(path);
        String environment = "Production";

        int status = 200;
        String severity = "INFO";
        String message = "";

        Object result;
        try {
            result = joinPoint.proceed();
            message = joinPoint.getSignature().getName() + " executed successfully";
        } catch (Exception ex) {
            status = 500;
            severity = "ERROR";
            message = ex.getMessage();
            throw ex;
        } finally {
            ApiLog log = new ApiLog();
            log.setTimestamp(timestamp);
            log.setEnvironment(environment);
            log.setService(service);
            log.setStatus(status);
            log.setSeverity(severity);
            log.setMessage(message);

            logService.save(log);
        }

        return result;
    }

    private String resolveService(String path) {
        for (Map.Entry<String, String> entry : SERVICE_MAP.entrySet()) {
            if (path.startsWith(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "UnknownService";
    }
}
