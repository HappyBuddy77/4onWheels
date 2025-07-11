package com._onWheels._onWheels.Usage.Logging.Health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ApiLogService {

    private final ApiLogRepository logRepository;

    @Autowired
    public ApiLogService(ApiLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void save(ApiLog log) {
        logRepository.save(log);
    }

    public List<ApiLog> filterLogs(Instant start, Instant end, List<String> services) {
    	System.out.println("i am hit");
        if (start != null && end != null && services != null && !services.isEmpty()) {
            return logRepository.findByTimestampBetweenAndServiceIn(start, end, services);
        } else if (start != null && end != null) {
            return logRepository.findByTimestampBetween(start, end);
        } else if (services != null && !services.isEmpty()) {
            return logRepository.findByServiceIn(services);
        } else {
            return logRepository.findAll();
        }
    }
}
