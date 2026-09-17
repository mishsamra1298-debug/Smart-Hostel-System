package model;

import java.time.LocalDateTime;

public class Complaint {
    private int complaintId;
    private int studentId;
    private Integer workerId;
    private String category;
    private String description;
    private String location;
    private Priority priority;
    private ComplaintStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;

    public Complaint(int complaintId, int studentId, String category,
                     String description, String location) {

        this.complaintId = complaintId;
        this.studentId = studentId;
        this.category = category;
        this.description = description;
        this.location = location;
        this.priority = calculatePriority(category);
        this.status = ComplaintStatus.SUBMITTED;
        this.createdAt = LocalDateTime.now();
    }

    private Priority calculatePriority(String category) {

        if (category.equalsIgnoreCase("Electrical")
                || category.equalsIgnoreCase("Water Leakage")) {
            return Priority.HIGH;
        }

        if (category.equalsIgnoreCase("AC")
                || category.equalsIgnoreCase("Fan")
                || category.equalsIgnoreCase("WiFi")) {
            return Priority.MEDIUM;
        }

        return Priority.LOW;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public int getStudentId() {
        return studentId;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public Priority getPriority() {
        return priority;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getResolvedAt() {
        return resolvedAt;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
        this.status = ComplaintStatus.ASSIGNED;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;

        if (status == ComplaintStatus.RESOLVED) {
            this.resolvedAt = LocalDateTime.now();
        }
    }

    public void displayComplaint() {
        System.out.println("\n----- Complaint Details -----");
        System.out.println("Complaint ID : " + complaintId);
        System.out.println("Student ID   : " + studentId);
        System.out.println("Category     : " + category);
        System.out.println("Description  : " + description);
        System.out.println("Location     : " + location);
        System.out.println("Priority     : " + priority);
        System.out.println("Status       : " + status);
        System.out.println("Created At   : " + createdAt);

        if (workerId != null) {
            System.out.println("Worker ID    : " + workerId);
        }

        if (resolvedAt != null) {
            System.out.println("Resolved At  : " + resolvedAt);
        }

        System.out.println("-----------------------------");
    }
}
