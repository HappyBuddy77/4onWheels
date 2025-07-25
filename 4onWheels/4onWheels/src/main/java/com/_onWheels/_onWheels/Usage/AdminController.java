package com._onWheels._onWheels.Usage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com._onWheels._onWheels.Usage.Logging.Health.ApiLog;
import com._onWheels._onWheels.Usage.Logging.Health.ApiLogService;

import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;

@Controller
public class AdminController {

    private final ApiLogService apiLogService;
    
    private static final String super_duper_user = "8a8f0c7e7c12d8a4f53b4e4788a28a8f8bc9f3122c4f1236fdfc5f6c2d7f7263@gmail.com";
    @Autowired
    public AdminController(ApiLogService apiLogService) {
        this.apiLogService = apiLogService;
    }

    @GetMapping("/admin/sales")
    public String sales(Principal principal) {
        if (principal == null || !principal.getName().equals(super_duper_user)) {
            return "redirect:/forbidden";  // Redirect to a forbidden page you create
        }
        return "admin_dashboard/sales";
    }

    @GetMapping("/admin/health")
    public String health(Principal principal) {
        if (principal == null || !principal.getName().equals(super_duper_user)) {
            return "redirect:/forbidden";
        }
        return "admin_dashboard/health";
    }


    // API endpoint for fetching logs
    @PostMapping("/api/getLogs")
    @ResponseBody
    public List<ApiLog> getLogs(@RequestBody FilterRequest payload) {
        String startDate = payload.getStartDate();
        String endDate = payload.getEndDate();

        Instant start = (startDate != null && !startDate.isEmpty())
            ? LocalDate.parse(startDate).atStartOfDay(ZoneOffset.UTC).toInstant()
            : null;

        Instant end = (endDate != null && !endDate.isEmpty())
            ? LocalDate.parse(endDate).atTime(LocalTime.MAX).atZone(ZoneOffset.UTC).toInstant()
            : null;

        return apiLogService.filterLogs(start, end, payload.getServices());
    }


    // DTO for the request payload
    public static class FilterRequest {
        private String startDate;
        private String endDate;
        private List<String> services;

        // Getters and setters
        public String getStartDate() { return startDate; }
        public void setStartDate(String startDate) { this.startDate = startDate; }

        public String getEndDate() { return endDate; }
        public void setEndDate(String endDate) { this.endDate = endDate; }

        public List<String> getServices() { return services; }
        public void setServices(List<String> services) { this.services = services; }
    }
}
