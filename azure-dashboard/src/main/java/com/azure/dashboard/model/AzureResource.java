package com.azure.dashboard.model;

/**
 * Represents an Azure Resource in the Resource Manager view.
 */
public class AzureResource {

    private String id;
    private String name;
    private String type;
    private String resourceGroup;
    private String location;
    private String status;
    private String subscription;
    private String tags;

    public AzureResource() {}

    public AzureResource(String id, String name, String type, String resourceGroup,
                         String location, String status, String subscription, String tags) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.resourceGroup = resourceGroup;
        this.location = location;
        this.status = status;
        this.subscription = subscription;
        this.tags = tags;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getResourceGroup() { return resourceGroup; }
    public void setResourceGroup(String resourceGroup) { this.resourceGroup = resourceGroup; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getSubscription() { return subscription; }
    public void setSubscription(String subscription) { this.subscription = subscription; }

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }
}
