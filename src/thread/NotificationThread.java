package thread;

import model.Complaint;

import java.util.List;

public class NotificationThread extends Thread {

    private final List<Complaint> complaints;

    public NotificationThread(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    @Override
    public void run() {

        System.out.println("\n[Notification Thread Started]");

        for (Complaint complaint : complaints) {

            System.out.println(
                    "[Notification] Complaint #" +
                    complaint.getComplaintId() +
                    " | Status: " +
                    complaint.getStatus()
            );

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(
                        "Notification thread interrupted."
                );
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("[Notification Thread Completed]");
    }
}
