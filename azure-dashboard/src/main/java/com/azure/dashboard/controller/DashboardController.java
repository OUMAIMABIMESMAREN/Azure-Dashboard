package com.azure.dashboard.controller;

import com.azure.dashboard.service.AzureDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Main controller handling all dashboard page routes.
 */
@Controller
public class DashboardController {

    private final AzureDataService dataService;

    public DashboardController(AzureDataService dataService) {
        this.dataService = dataService;
    }

    // ─────────────────────────────────────────────────────────────
    // Home / Azure Portal Dashboard
    // ─────────────────────────────────────────────────────────────

    @GetMapping({"/", "/dashboard"})
    public String home(Model model) {
        model.addAttribute("page", "dashboard");
        model.addAttribute("pageTitle", "Home | Microsoft Azure");
        model.addAttribute("summary", dataService.getDashboardSummary());
        model.addAttribute("recentActivity", dataService.getRecentActivity());
        return "pages/dashboard";
    }

    // ─────────────────────────────────────────────────────────────
    // Azure Monitor
    // ─────────────────────────────────────────────────────────────

    @GetMapping("/monitor")
    public String monitor(Model model) {
        model.addAttribute("page", "monitor");
        model.addAttribute("pageTitle", "Azure Monitor | Microsoft Azure");
        model.addAttribute("alerts", dataService.getAlerts());
        model.addAttribute("metrics", dataService.getMetrics());
        return "pages/monitor";
    }

    // ─────────────────────────────────────────────────────────────
    // Cost Management
    // ─────────────────────────────────────────────────────────────

    @GetMapping("/cost")
    public String costManagement(Model model) {
        model.addAttribute("page", "cost");
        model.addAttribute("pageTitle", "Cost Management | Microsoft Azure");
        model.addAttribute("costEntries", dataService.getCostEntries());
        model.addAttribute("costSummary", dataService.getCostSummary());
        return "pages/cost";
    }

    // ─────────────────────────────────────────────────────────────
    // Resource Manager
    // ─────────────────────────────────────────────────────────────

    @GetMapping("/resources")
    public String resources(Model model,
                            @RequestParam(required = false) String filter,
                            @RequestParam(required = false) String group) {
        model.addAttribute("page", "resources");
        model.addAttribute("pageTitle", "Resource Manager | Microsoft Azure");
        model.addAttribute("resources", dataService.getResources());
        model.addAttribute("resourceGroups", dataService.getResourceGroups());
        model.addAttribute("filterType", filter);
        model.addAttribute("filterGroup", group);
        return "pages/resources";
    }

    @GetMapping("/resources/create")
    public String createResourceForm(Model model) {
        model.addAttribute("page", "resources");
        model.addAttribute("pageTitle", "Create Resource | Microsoft Azure");
        model.addAttribute("resourceGroups", dataService.getResourceGroups());
        return "pages/create-resource";
    }

    @GetMapping("/resources/create/submit")
    public String createResourceSubmit(@RequestParam String resourceName,
                                       @RequestParam String resourceType,
                                       @RequestParam String resourceGroup,
                                       @RequestParam String region,
                                       RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("successMessage",
                "Resource '" + resourceName + "' (" + resourceType + ") is being deployed to " + region + ". This may take a few minutes.");
        return "redirect:/resources";
    }

    // ─────────────────────────────────────────────────────────────
    // Automation
    // ─────────────────────────────────────────────────────────────

    @GetMapping("/automation")
    public String automation(Model model) {
        model.addAttribute("page", "automation");
        model.addAttribute("pageTitle", "Azure Automation | Microsoft Azure");
        model.addAttribute("tasks", dataService.getAutomationTasks());
        return "pages/automation";
    }

    @GetMapping("/automation/run")
    public String runAutomation(@RequestParam String taskName,
                                RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("successMessage",
                "Runbook '" + taskName + "' has been triggered successfully. Job queued.");
        return "redirect:/automation";
    }

    @GetMapping("/automation/create")
    public String createRunbookForm(Model model) {
        model.addAttribute("page", "automation");
        model.addAttribute("pageTitle", "New Runbook | Microsoft Azure");
        return "pages/create-runbook";
    }

    @GetMapping("/automation/create/submit")
    public String createRunbookSubmit(@RequestParam String runbookName,
                                      @RequestParam String runbookType,
                                      @RequestParam String schedule,
                                      RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("successMessage",
                "Runbook '" + runbookName + "' created and scheduled: " + schedule);
        return "redirect:/automation";
    }

    // ─────────────────────────────────────────────────────────────
    // Security Center
    // ─────────────────────────────────────────────────────────────

    @GetMapping("/security")
    public String security(Model model) {
        model.addAttribute("page", "security");
        model.addAttribute("pageTitle", "Microsoft Defender for Cloud | Microsoft Azure");
        model.addAttribute("findings", dataService.getSecurityFindings());
        model.addAttribute("securitySummary", dataService.getSecuritySummary());
        return "pages/security";
    }

    @GetMapping("/security/remediate")
    public String remediateFinding(@RequestParam String findingId,
                                   @RequestParam String findingTitle,
                                   RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("successMessage",
                "Remediation initiated for: '" + findingTitle + "'. Finding ID: " + findingId);
        return "redirect:/security";
    }
}
