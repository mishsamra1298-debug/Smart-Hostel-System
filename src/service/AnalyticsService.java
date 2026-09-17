package service;

import model.ComplaintStatus;

public class AnalyticsService {

    private final ComplaintService complaintService;

    public AnalyticsService(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    // Display complaint statistics
    public void displayStatistics() {

        int total = complaintService.getTotalComplaints();

        int submitted =
                complaintService.countByStatus(
                        ComplaintStatus.SUBMITTED);

        int assigned =
                complaintService.countByStatus(
                        ComplaintStatus.ASSIGNED);

        int inProgress =
                complaintService.countByStatus(
                        ComplaintStatus.IN_PROGRESS);

        int resolved =
                complaintService.countByStatus(
                        ComplaintStatus.RESOLVED);

        int cancelled =
                complaintService.countByStatus(
                        ComplaintStatus.CANCELLED);

        System.out.println("\n===== COMPLAINT STATISTICS =====");

        System.out.println("Total Complaints : " + total);
        System.out.println("Submitted       : " + submitted);
        System.out.println("Assigned        : " + assigned);
        System.out.println("In Progress     : " + inProgress);
        System.out.println("Resolved        : " + resolved);
        System.out.println("Cancelled       : " + cancelled);

        if (total > 0) {

            double resolutionRate =
                    (resolved * 100.0) / total;

            System.out.printf(
                    "Resolution Rate : %.2f%%\n",
                    resolutionRate
            );
        }

        System.out.println("===============================");
    }
}
