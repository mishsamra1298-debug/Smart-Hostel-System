package util;

import model.Complaint;

import java.io.*;
import java.util.List;

public class FileUtil {

    private static final String FILE_NAME = "complaints.txt";

    // Save complaints to file
    public static void saveComplaints(List<Complaint> complaints) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Complaint complaint : complaints) {

                writer.write(
                        complaint.getComplaintId() + "|" +
                        complaint.getStudentId() + "|" +
                        complaint.getCategory() + "|" +
                        complaint.getDescription() + "|" +
                        complaint.getLocation() + "|" +
                        complaint.getPriority() + "|" +
                        complaint.getStatus()
                );

                writer.newLine();
            }

            System.out.println("Complaints saved to file.");

        } catch (IOException e) {

            System.out.println(
                    "Error while saving complaints: " + e.getMessage()
            );
        }
    }

    // Display saved complaint records
    public static void displaySavedData() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No saved complaint file found.");
            return;
        }

        System.out.println("\n===== SAVED COMPLAINT DATA =====");

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {

            System.out.println(
                    "Error while reading file: " + e.getMessage()
            );
        }

        System.out.println("===============================");
    }
}
