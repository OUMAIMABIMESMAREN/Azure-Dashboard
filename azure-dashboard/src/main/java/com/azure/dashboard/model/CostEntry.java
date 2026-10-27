package com.azure.dashboard.model;

/**
 * Represents a cost entry in Azure Cost Management.
 */
public class CostEntry {

    private String service;
    private String resourceGroup;
    private double currentMonthCost;
    private double previousMonthCost;
    private double forecastedCost;
    private double budget;
    private String currency;
    private String trend;

    public CostEntry() {}

    public CostEntry(String service, String resourceGroup, double currentMonthCost,
                     double previousMonthCost, double forecastedCost, double budget,
                     String currency, String trend) {
        this.service = service;
        this.resourceGroup = resourceGroup;
        this.currentMonthCost = currentMonthCost;
        this.previousMonthCost = previousMonthCost;
        this.forecastedCost = forecastedCost;
        this.budget = budget;
        this.currency = currency;
        this.trend = trend;
    }

    public String getService() { return service; }
    public void setService(String service) { this.service = service; }

    public String getResourceGroup() { return resourceGroup; }
    public void setResourceGroup(String resourceGroup) { this.resourceGroup = resourceGroup; }

    public double getCurrentMonthCost() { return currentMonthCost; }
    public void setCurrentMonthCost(double currentMonthCost) { this.currentMonthCost = currentMonthCost; }

    public double getPreviousMonthCost() { return previousMonthCost; }
    public void setPreviousMonthCost(double previousMonthCost) { this.previousMonthCost = previousMonthCost; }

    public double getForecastedCost() { return forecastedCost; }
    public void setForecastedCost(double forecastedCost) { this.forecastedCost = forecastedCost; }

    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getTrend() { return trend; }
    public void setTrend(String trend) { this.trend = trend; }

    public double getBudgetUsedPercent() {
        if (budget <= 0) return 0;
        return Math.min(100, (currentMonthCost / budget) * 100);
    }
}
