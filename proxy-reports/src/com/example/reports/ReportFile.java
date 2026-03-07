package com.example.reports;

/**
 * Original concrete loader — kept for reference.
 * In the refactored design, clients use ReportProxy instead of this class directly.
 */
public class ReportFile {

    private final String reportId;
    private final String title;
    private final String classification;

    public ReportFile(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    public void display(User user) {
        String content = loadFromDisk();
        System.out.println("REPORT -> id=" + reportId
                + " title=" + title
                + " classification=" + classification
                + " openedBy=" + user.getName());
        System.out.println("CONTENT: " + content);
    }

    private String loadFromDisk() {
        System.out.println("[disk] loading report " + reportId + " ...");
        try { Thread.sleep(120); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return "Internal report body for " + title;
    }
}
