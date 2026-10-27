package com.azure.dashboard.service;

import com.azure.dashboard.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service layer providing simulated Azure cloud data for all dashboard views.
 */
@Service
public class AzureDataService {

    // ─────────────────────────────────────────────────────────────
    // Dashboard / Home
    // ─────────────────────────────────────────────────────────────

    public Map<String, Object> getDashboardSummary() {
        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("totalResources", 142);
        summary.put("activeAlerts", 7);
        summary.put("monthlyCost", "$4,283.50");
        summary.put("secureScore", 78);
        summary.put("subscription", "Enterprise Dev/Test");
        summary.put("tenantId", "a1b2c3d4-e5f6-7890-abcd-ef1234567890");
        summary.put("region", "East US");
        summary.put("resourceGroups", 12);
        summary.put("runningVMs", 18);
        summary.put("storageAccounts", 9);
        summary.put("webApps", 6);
        summary.put("sqlDatabases", 5);
        return summary;
    }

    public List<Map<String, String>> getRecentActivity() {
        List<Map<String, String>> activity = new ArrayList<>();

        activity.add(makeActivity("VM Scale Set updated", "prod-vmss-eastus", "2 minutes ago", "success", "Compute"));
        activity.add(makeActivity("Alert fired: High CPU", "api-server-01", "14 minutes ago", "warning", "Monitor"));
        activity.add(makeActivity("New resource deployed", "staging-sql-db", "1 hour ago", "success", "Database"));
        activity.add(makeActivity("Policy compliance scan", "rg-production", "2 hours ago", "info", "Security"));
        activity.add(makeActivity("Backup completed", "blob-storage-prod", "3 hours ago", "success", "Storage"));
        activity.add(makeActivity("Cost budget 80% reached", "dev-resource-group", "5 hours ago", "warning", "Cost"));
        activity.add(makeActivity("Runbook executed", "cleanup-old-snapshots", "6 hours ago", "success", "Automation"));
        activity.add(makeActivity("SSL cert expiring soon", "webapp-frontend", "Yesterday", "danger", "Security"));

        return activity;
    }

    private Map<String, String> makeActivity(String action, String resource, String time,
                                              String type, String category) {
        Map<String, String> m = new LinkedHashMap<>();
        m.put("action", action);
        m.put("resource", resource);
        m.put("time", time);
        m.put("type", type);
        m.put("category", category);
        return m;
    }

    // ─────────────────────────────────────────────────────────────
    // Azure Monitor
    // ─────────────────────────────────────────────────────────────

    public List<MonitorAlert> getAlerts() {
        List<MonitorAlert> alerts = new ArrayList<>();
        alerts.add(new MonitorAlert("ALT-001", "High CPU Usage", "Sev2", "Fired",
                "api-server-01", "2024-05-15 09:14", "CPU exceeded 90% for 5 minutes", "CPU > 90%"));
        alerts.add(new MonitorAlert("ALT-002", "Memory Pressure", "Sev3", "Fired",
                "db-server-prod", "2024-05-15 08:30", "Available memory below 512MB", "Memory < 512MB"));
        alerts.add(new MonitorAlert("ALT-003", "Disk Space Warning", "Sev3", "Resolved",
                "file-server-01", "2024-05-14 22:00", "Disk usage exceeded 85%", "Disk > 85%"));
        alerts.add(new MonitorAlert("ALT-004", "HTTP 5xx Spike", "Sev1", "Fired",
                "webapp-prod", "2024-05-15 10:01", "500 errors rate > 5% over 10min", "5xx Rate > 5%"));
        alerts.add(new MonitorAlert("ALT-005", "Slow DB Queries", "Sev4", "Fired",
                "sql-prod-eastus", "2024-05-15 07:45", "Query duration exceeds 5 seconds", "QueryDuration > 5s"));
        alerts.add(new MonitorAlert("ALT-006", "Network Latency", "Sev2", "Resolved",
                "vnet-prod-hub", "2024-05-14 18:20", "RTT exceeded 200ms threshold", "RTT > 200ms"));
        alerts.add(new MonitorAlert("ALT-007", "Autoscale Triggered", "Sev4", "Resolved",
                "prod-vmss-eastus", "2024-05-15 06:00", "Scale out event triggered", "CPU > 70%"));
        return alerts;
    }

    public Map<String, Object> getMetrics() {
        Map<String, Object> metrics = new LinkedHashMap<>();
        metrics.put("cpuPercent", 67);
        metrics.put("memoryPercent", 54);
        metrics.put("diskReadMBps", 142.3);
        metrics.put("diskWriteMBps", 89.7);
        metrics.put("networkInMbps", 324.5);
        metrics.put("networkOutMbps", 187.2);
        metrics.put("requestsPerMin", 4820);
        metrics.put("avgResponseMs", 143);
        metrics.put("errorRate", 0.8);
        metrics.put("availabilityPercent", 99.94);

        // Chart data as JSON strings
        metrics.put("cpuChartData", "[42,55,61,78,82,67,59,63,70,67,72,65]");
        metrics.put("memChartData", "[50,52,53,55,58,54,51,54,56,54,55,54]");
        metrics.put("reqChartData", "[3200,3800,4200,4600,5100,4900,4700,4820,5000,4820,4600,4820]");
        metrics.put("chartLabels", "[\"10:00\",\"10:05\",\"10:10\",\"10:15\",\"10:20\",\"10:25\",\"10:30\",\"10:35\",\"10:40\",\"10:45\",\"10:50\",\"10:55\"]");

        return metrics;
    }

    // ─────────────────────────────────────────────────────────────
    // Cost Management
    // ─────────────────────────────────────────────────────────────

    public List<CostEntry> getCostEntries() {
        List<CostEntry> costs = new ArrayList<>();
        costs.add(new CostEntry("Virtual Machines", "rg-production", 1420.50, 1310.20, 1580.00, 1500.00, "USD", "up"));
        costs.add(new CostEntry("Azure SQL Database", "rg-production", 820.30, 790.10, 850.00, 900.00, "USD", "up"));
        costs.add(new CostEntry("Blob Storage", "rg-storage", 345.80, 360.50, 340.00, 400.00, "USD", "down"));
        costs.add(new CostEntry("App Service", "rg-web", 280.40, 265.00, 295.00, 350.00, "USD", "up"));
        costs.add(new CostEntry("Azure Kubernetes", "rg-containers", 612.70, 580.40, 650.00, 700.00, "USD", "up"));
        costs.add(new CostEntry("Virtual Network", "rg-networking", 98.20, 95.10, 100.00, 150.00, "USD", "up"));
        costs.add(new CostEntry("Azure Monitor", "rg-monitoring", 145.60, 138.90, 155.00, 200.00, "USD", "up"));
        costs.add(new CostEntry("Key Vault", "rg-security", 42.30, 40.10, 45.00, 60.00, "USD", "up"));
        costs.add(new CostEntry("Backup", "rg-production", 88.50, 82.30, 92.00, 100.00, "USD", "up"));
        costs.add(new CostEntry("Azure DNS", "rg-networking", 22.10, 21.80, 23.00, 30.00, "USD", "up"));
        return costs;
    }

    public Map<String, Object> getCostSummary() {
        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("totalCurrentMonth", "$4,283.50");
        summary.put("totalPreviousMonth", "$3,984.40");
        summary.put("forecastedMonth", "$4,730.00");
        summary.put("totalBudget", "$5,390.00");
        summary.put("budgetUsedPercent", 79);
        summary.put("savingsRecommended", "$342.80");
        summary.put("costTrend", "+7.5%");
        summary.put("unusedResources", 6);
        summary.put("costChartData", "[3200,3500,3100,3800,3984,4283]");
        summary.put("costChartLabels", "[\"Dec\",\"Jan\",\"Feb\",\"Mar\",\"Apr\",\"May\"]");
        summary.put("serviceBreakdown", "[1420,820,345,280,613,98,146,42,88,22]");
        summary.put("serviceLabels", "[\"VMs\",\"SQL\",\"Storage\",\"App Svc\",\"AKS\",\"VNet\",\"Monitor\",\"Key Vault\",\"Backup\",\"DNS\"]");
        return summary;
    }

    // ─────────────────────────────────────────────────────────────
    // Resource Manager
    // ─────────────────────────────────────────────────────────────

    public List<AzureResource> getResources() {
        List<AzureResource> resources = new ArrayList<>();

        resources.add(new AzureResource("RES-001", "api-server-01", "Virtual Machine", "rg-production", "East US", "Running", "Enterprise Dev/Test", "env:prod"));
        resources.add(new AzureResource("RES-002", "api-server-02", "Virtual Machine", "rg-production", "East US", "Running", "Enterprise Dev/Test", "env:prod"));
        resources.add(new AzureResource("RES-003", "sql-prod-eastus", "SQL Database", "rg-production", "East US", "Online", "Enterprise Dev/Test", "env:prod"));
        resources.add(new AzureResource("RES-004", "blob-storage-prod", "Storage Account", "rg-storage", "West US", "Available", "Enterprise Dev/Test", "tier:hot"));
        resources.add(new AzureResource("RES-005", "webapp-frontend", "App Service", "rg-web", "East US 2", "Running", "Enterprise Dev/Test", "env:prod"));
        resources.add(new AzureResource("RES-006", "aks-prod-cluster", "Kubernetes Service", "rg-containers", "East US", "Running", "Enterprise Dev/Test", "env:prod"));
        resources.add(new AzureResource("RES-007", "prod-vmss-eastus", "VM Scale Set", "rg-production", "East US", "Running", "Enterprise Dev/Test", "env:prod"));
        resources.add(new AzureResource("RES-008", "keyvault-prod", "Key Vault", "rg-security", "East US", "Active", "Enterprise Dev/Test", "criticality:high"));
        resources.add(new AzureResource("RES-009", "vnet-prod-hub", "Virtual Network", "rg-networking", "East US", "Available", "Enterprise Dev/Test", "tier:hub"));
        resources.add(new AzureResource("RES-010", "backup-vault-01", "Recovery Services Vault", "rg-production", "East US", "Active", "Enterprise Dev/Test", "env:prod"));
        resources.add(new AzureResource("RES-011", "staging-vm-01", "Virtual Machine", "rg-staging", "West US", "Deallocated", "Enterprise Dev/Test", "env:staging"));
        resources.add(new AzureResource("RES-012", "dev-sql-db", "SQL Database", "rg-dev", "Central US", "Online", "Enterprise Dev/Test", "env:dev"));
        resources.add(new AzureResource("RES-013", "cdn-profile-prod", "CDN Profile", "rg-web", "Global", "Active", "Enterprise Dev/Test", "env:prod"));
        resources.add(new AzureResource("RES-014", "monitor-workspace", "Log Analytics Workspace", "rg-monitoring", "East US", "Active", "Enterprise Dev/Test", "purpose:monitoring"));
        resources.add(new AzureResource("RES-015", "loadbalancer-ext", "Load Balancer", "rg-networking", "East US", "Active", "Enterprise Dev/Test", "tier:external"));

        return resources;
    }

    public List<String> getResourceGroups() {
        return Arrays.asList("rg-production", "rg-storage", "rg-web", "rg-containers",
                "rg-networking", "rg-security", "rg-monitoring", "rg-staging",
                "rg-dev", "rg-backup", "rg-shared", "rg-testing");
    }

    // ─────────────────────────────────────────────────────────────
    // Automation
    // ─────────────────────────────────────────────────────────────

    public List<AutomationTask> getAutomationTasks() {
        List<AutomationTask> tasks = new ArrayList<>();

        tasks.add(new AutomationTask("AUT-001", "Daily VM Backup", "Runbook", "Completed",
                "Daily 02:00 UTC", "2024-05-15 02:00", "2024-05-16 02:00",
                "4m 32s", "Backs up all production VMs daily", "Backup-VMs.ps1"));
        tasks.add(new AutomationTask("AUT-002", "Scale Down Off-Hours", "Runbook", "Completed",
                "Weekdays 20:00 UTC", "2024-05-14 20:00", "2024-05-15 20:00",
                "1m 12s", "Deallocates non-critical dev VMs after hours", "Scale-Down-Dev.ps1"));
        tasks.add(new AutomationTask("AUT-003", "Cleanup Old Snapshots", "Runbook", "Completed",
                "Weekly Sunday 03:00", "2024-05-12 03:00", "2024-05-19 03:00",
                "8m 45s", "Removes disk snapshots older than 30 days", "Cleanup-Snapshots.ps1"));
        tasks.add(new AutomationTask("AUT-004", "Security Patch Update", "Update Management", "Running",
                "Monthly 1st Sunday", "2024-05-05 04:00", "2024-06-02 04:00",
                "32m 10s (ongoing)", "Applies Windows/Linux security patches", "Patch-Update.ps1"));
        tasks.add(new AutomationTask("AUT-005", "Cost Report Generation", "Runbook", "Completed",
                "Monthly 1st 08:00", "2024-05-01 08:00", "2024-06-01 08:00",
                "2m 55s", "Generates and emails monthly cost report", "Cost-Report.ps1"));
        tasks.add(new AutomationTask("AUT-006", "DB Index Optimization", "Runbook", "Failed",
                "Weekly Saturday 01:00", "2024-05-11 01:00", "2024-05-18 01:00",
                "0m 45s", "Rebuilds SQL Server indexes for performance", "Optimize-DBIndex.ps1"));
        tasks.add(new AutomationTask("AUT-007", "SSL Cert Check", "Runbook", "Completed",
                "Daily 06:00 UTC", "2024-05-15 06:00", "2024-05-16 06:00",
                "0m 28s", "Checks SSL certificate expiry on all web apps", "Check-SSLCerts.ps1"));

        return tasks;
    }

    // ─────────────────────────────────────────────────────────────
    // Security Center
    // ─────────────────────────────────────────────────────────────

    public List<SecurityFinding> getSecurityFindings() {
        List<SecurityFinding> findings = new ArrayList<>();

        findings.add(new SecurityFinding("SEC-001", "MFA Not Enabled for Admin Accounts", "High", "Active",
                "Identity", "Azure AD", "Multi-factor authentication is not enabled for 3 admin accounts.",
                "Enable MFA for all privileged accounts in Azure AD.", "2024-05-10 08:00", 8));
        findings.add(new SecurityFinding("SEC-002", "Storage Account HTTP Access", "Medium", "Active",
                "Data", "blob-storage-prod", "Storage account allows unencrypted HTTP traffic.",
                "Enforce HTTPS-only traffic on the storage account.", "2024-05-12 10:30", 6));
        findings.add(new SecurityFinding("SEC-003", "VM with Open RDP Port", "High", "Active",
                "Network", "staging-vm-01", "RDP port 3389 is exposed to the internet.",
                "Restrict RDP access using NSG rules or Azure Bastion.", "2024-05-13 14:15", 9));
        findings.add(new SecurityFinding("SEC-004", "SQL Server without TDE", "Medium", "Resolved",
                "Data", "dev-sql-db", "Transparent Data Encryption is not enabled on database.",
                "Enable TDE via Azure Portal or T-SQL command.", "2024-05-08 09:00", 5));
        findings.add(new SecurityFinding("SEC-005", "Outdated VM OS Version", "Low", "Active",
                "Compute", "api-server-02", "Operating system is not on latest security patch level.",
                "Apply latest OS security patches via Update Management.", "2024-05-14 11:00", 3));
        findings.add(new SecurityFinding("SEC-006", "Key Vault Access Logging Disabled", "Medium", "Active",
                "Security", "keyvault-prod", "Diagnostic logging is not enabled for Key Vault.",
                "Enable audit logging in Key Vault diagnostic settings.", "2024-05-11 16:00", 6));
        findings.add(new SecurityFinding("SEC-007", "NSG with Any-Any Rule", "High", "Active",
                "Network", "vnet-prod-hub", "Network Security Group has overly permissive inbound rule.",
                "Replace Any-Any rules with specific port/IP allow rules.", "2024-05-13 07:30", 8));

        return findings;
    }

    public Map<String, Object> getSecuritySummary() {
        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("secureScore", 78);
        summary.put("maxScore", 100);
        summary.put("highSeverity", 3);
        summary.put("mediumSeverity", 3);
        summary.put("lowSeverity", 1);
        summary.put("resolvedFindings", 12);
        summary.put("complianceScore", 84);
        summary.put("threatsDetected", 2);
        summary.put("resourcesProtected", 131);
        summary.put("resourcesAtRisk", 11);
        return summary;
    }
}
