package com.azure.dashboard.model;

/**
 * Represents a Security Center finding / recommendation.
 */
public class SecurityFinding {

    private String id;
    private String title;
    private String severity;
    private String status;
    private String category;
    private String resource;
    private String description;
    private String remediation;
    private String detectedTime;
    private int score;

    public SecurityFinding() {}

    public SecurityFinding(String id, String title, String severity, String status,
                           String category, String resource, String description,
                           String remediation, String detectedTime, int score) {
        this.id = id;
        this.title = title;
        this.severity = severity;
        this.status = status;
        this.category = category;
        this.resource = resource;
        this.description = description;
        this.remediation = remediation;
        this.detectedTime = detectedTime;
        this.score = score;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getResource() { return resource; }
    public void setResource(String resource) { this.resource = resource; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRemediation() { return remediation; }
    public void setRemediation(String remediation) { this.remediation = remediation; }

    public String getDetectedTime() { return detectedTime; }
    public void setDetectedTime(String detectedTime) { this.detectedTime = detectedTime; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}
