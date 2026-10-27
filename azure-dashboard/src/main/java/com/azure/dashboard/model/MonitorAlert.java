package com.azure.dashboard.model;

/**
 * Represents an Azure Monitor Alert.
 */
public class MonitorAlert {

    private String id;
    private String name;
    private String severity;
    private String status;
    private String resource;
    private String firedTime;
    private String description;
    private String condition;

    public MonitorAlert() {}

    public MonitorAlert(String id, String name, String severity, String status,
                        String resource, String firedTime, String description, String condition) {
        this.id = id;
        this.name = name;
        this.severity = severity;
        this.status = status;
        this.resource = resource;
        this.firedTime = firedTime;
        this.description = description;
        this.condition = condition;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getResource() { return resource; }
    public void setResource(String resource) { this.resource = resource; }

    public String getFiredTime() { return firedTime; }
    public void setFiredTime(String firedTime) { this.firedTime = firedTime; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
}
