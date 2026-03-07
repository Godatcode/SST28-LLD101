package com.example.reports;

/**
 * Opens reports for a given user.
 * Depends only on the Report interface — works with any implementation (proxy or real).
 */
public class ReportViewer {

    public void open(Report report, User user) {
        report.display(user);
    }
}
