package com._onWheels._onWheels;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class ApiLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant timestamp;
    private String environment;
    private String service;
    private int status;
    private String severity;
    private String message;

    // Getters and setters (can use Lombok if desired)
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Instant getTimestamp() { return timestamp; }

    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    public String getEnvironment() { return environment; }

    public void setEnvironment(String environment) { this.environment = environment; }

    public String getService() { return service; }

    public void setService(String service) { this.service = service; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }

    public String getSeverity() { return severity; }

    public void setSeverity(String severity) { this.severity = severity; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }
}
