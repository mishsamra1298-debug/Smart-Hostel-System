package service;

import exception.ComplaintNotFoundException;
import exception.InvalidInputException;
import model.Complaint;
import model.ComplaintStatus;

import java.util.ArrayList;
import java.util.List;

public class ComplaintService {

    private final List<Complaint> complaints = new ArrayList<>();

    // Add a new complaint
    public void addComplaint(Complaint complaint)
            throws InvalidInputException {

        if (complaint == null) {
            throw new InvalidInputException("Complaint cannot be empty.");
        }

        if (complaint.getCategory() == null ||
                complaint.getCategory().trim().isEmpty()) {
            throw new InvalidInputException("Category is required.");
        }

        if (complaint.getDescription() == null ||
                complaint.getDescription().trim().isEmpty()) {
            throw new InvalidInputException("Description is required.");
        }

        if (complaint.getLocation() == null ||
                complaint.getLocation().trim().isEmpty()) {
            throw new InvalidInputException("Location is required.");
        }

        complaints.add(complaint);

        System.out.println("Complaint submitted successfully!");
    }

    // Display all complaints
    public void displayAllComplaints() {

        if (complaints.isEmpty()) {
            System.out.println("No complaints found.");
            return;
        }

        for (Complaint complaint : complaints) {
            complaint.displayComplaint();
        }
    }

    // Search complaint by ID
    public Complaint searchComplaint(int complaintId)
            throws ComplaintNotFoundException {

        for (Complaint complaint : complaints) {

            if (complaint.getComplaintId() == complaintId) {
                return complaint;
            }
        }

        throw new ComplaintNotFoundException(
                "Complaint with ID " + complaintId + " not found."
        );
    }

    // Update complaint status
    public void updateStatus(int complaintId, ComplaintStatus status)
            throws ComplaintNotFoundException, InvalidInputException {

        if (status == null) {
            throw new InvalidInputException("Status cannot be empty.");
        }

        Complaint complaint = searchComplaint(complaintId);

        complaint.setStatus(status);

        System.out.println("Complaint status updated successfully!");
    }

    // Assign worker to complaint
    public void assignWorker(int complaintId, int workerId)
            throws ComplaintNotFoundException, InvalidInputException {

        if (workerId <= 0) {
            throw new InvalidInputException("Invalid worker ID.");
        }

        Complaint complaint = searchComplaint(complaintId);

        complaint.setWorkerId(workerId);

        System.out.println("Worker assigned successfully!");
    }

    // Cancel complaint
    public void cancelComplaint(int complaintId)
            throws ComplaintNotFoundException {

        Complaint complaint = searchComplaint(complaintId);

        complaint.setStatus(ComplaintStatus.CANCELLED);

        System.out.println("Complaint cancelled successfully!");
    }

    // Get total number of complaints
    public int getTotalComplaints() {
        return complaints.size();
    }

    // Count complaints by status
    public int countByStatus(ComplaintStatus status) {

        int count = 0;

        for (Complaint complaint : complaints) {

            if (complaint.getStatus() == status) {
                count++;
            }
        }

        return count;
    }

    // Get all complaints
    public List<Complaint> getComplaints() {
        return complaints;
    }
}