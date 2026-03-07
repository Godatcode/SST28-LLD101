package com.example.reports;

/**
 * Proxy for a report — handles access control and lazy loading.
 *
 * - Access is checked before anything else. Denied users get no content.
 * - RealReport is created only on the first allowed access (lazy loading).
 * - The same RealReport instance is reused on repeated calls (caching).
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();

    // null until first authorized access
    private RealReport realReport;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("ACCESS DENIED: " + user.getName()
                    + " cannot view \"" + title + "\"");
            return;
        }

        if (realReport == null) {
            realReport = new RealReport(reportId, title, classification);
        }

        realReport.display(user);
    }
}
