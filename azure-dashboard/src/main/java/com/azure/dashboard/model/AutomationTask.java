package com.azure.dashboard.model;

/**
 * Represents an Azure Automation Runbook/Task.
 */
public class AutomationTask {

    private String id;
    private String name;
    private String type;
    private String status;
    private String schedule;
    private String lastRun;
    private String nextRun;
    private String duration;
    private String description;
    private String runbook;

    public AutomationTask() {}

    public AutomationTask(String id, String name, String type, String status,
                          String schedule, String lastRun, String nextRun,
                          String duration, String description, String runbook) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.schedule = schedule;
        this.lastRun = lastRun;
        this.nextRun = nextRun;
        this.duration = duration;
        this.description = description;
        this.runbook = runbook;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }

    public String getLastRun() { return lastRun; }
    public void setLastRun(String lastRun) { this.lastRun = lastRun; }

    public String getNextRun() { return nextRun; }
    public void setNextRun(String nextRun) { this.nextRun = nextRun; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRunbook() { return runbook; }
    public void setRunbook(String runbook) { this.runbook = runbook; }
}
