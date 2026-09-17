import exception.ComplaintNotFoundException;
import exception.InvalidInputException;
import model.Admin;
import model.Complaint;
import model.ComplaintStatus;
import model.Student;
import model.User;
import model.Worker;
import service.AnalyticsService;
import service.ComplaintService;
import service.UserService;
import thread.NotificationThread;
import util.FileUtil;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final UserService userService = new UserService();
    private static final ComplaintService complaintService =
            new ComplaintService();

    private static final AnalyticsService analyticsService =
            new AnalyticsService(complaintService);

    private static int nextUserId = 4;
    private static int nextComplaintId = 1;

    public static void main(String[] args) {

        loadSampleUsers();

        System.out.println("======================================");
        System.out.println("   SMART HOSTEL COMPLAINT SYSTEM");
        System.out.println("======================================");

        boolean running = true;

        while (running) {

            displayMainMenu();

            int choice = readInt("Enter your choice: ");

            try {

                switch (choice) {

                    case 1:
                        studentMenu();
                        break;

                    case 2:
                        workerMenu();
                        break;

                    case 3:
                        adminMenu();
                        break;

                    case 4:
                        registerStudent();
                        break;

                    case 5:
                        FileUtil.saveComplaints(
                                complaintService.getComplaints()
                        );
                        break;

                    case 6:
                        FileUtil.displaySavedData();
                        break;

                    case 7:
                        startNotificationThread();
                        break;

                    case 8:
                        System.out.println(
                                "\nThank you for using Smart Hostel System!"
                        );
                        running = false;
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please try again."
                        );
                }

            } catch (Exception e) {

                System.out.println(
                        "\nError: " + e.getMessage()
                );
            }
        }

        scanner.close();
    }

    // ============================
    // MAIN MENU
    // ============================

    private static void displayMainMenu() {

        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Student Login");
        System.out.println("2. Worker Login");
        System.out.println("3. Admin Login");
        System.out.println("4. Register New Student");
        System.out.println("5. Save Complaints to File");
        System.out.println("6. View Saved Complaint File");
        System.out.println("7. Start Notification Thread");
        System.out.println("8. Exit");
        System.out.println("===============================");
    }

    // ============================
    // SAMPLE USERS
    // ============================

    private static void loadSampleUsers() {

        try {

            Student student = new Student(
                    1,
                    "Rahul Sharma",
                    "rahul@student.com",
                    "9876543210",
                    "1234",
                    "Aryabhata Hostel",
                    "A-101"
            );

            Worker worker = new Worker(
                    2,
                    "Amit Kumar",
                    "amit@worker.com",
                    "9876543211",
                    "1234",
                    "Electrical"
            );

            Admin admin = new Admin(
                    3,
                    "Hostel Admin",
                    "admin@hostel.com",
                    "9876543212",
                    "admin123"
            );

            userService.registerUser(student);
            userService.registerUser(worker);
            userService.registerUser(admin);

        } catch (InvalidInputException e) {

            System.out.println(
                    "Error loading sample users: "
                            + e.getMessage()
            );
        }
    }

    // ============================
    // STUDENT MENU
    // ============================

    private static void studentMenu() {

        System.out.println("\n========== STUDENT LOGIN ==========");

        String email = readString("Email: ");
        String password = readString("Password: ");

        User user = userService.login(email, password);

        if (!(user instanceof Student)) {

            System.out.println(
                    "Invalid student credentials."
            );
            return;
        }

        Student student = (Student) user;

        System.out.println(
                "\nWelcome, " + student.getName() + "!"
        );

        boolean loggedIn = true;

        while (loggedIn) {

            System.out.println("\n------- STUDENT MENU -------");
            System.out.println("1. View Profile");
            System.out.println("2. Submit Complaint");
            System.out.println("3. View All Complaints");
            System.out.println("4. Search Complaint");
            System.out.println("5. Cancel Complaint");
            System.out.println("6. Logout");

            int choice = readInt("Enter choice: ");

            try {

                switch (choice) {

                    case 1:
                        student.displayUser();
                        break;

                    case 2:
                        submitComplaint(student);
                        break;

                    case 3:
                        complaintService.displayAllComplaints();
                        break;

                    case 4:
                        searchComplaint();
                        break;

                    case 5:
                        cancelComplaint();
                        break;

                    case 6:
                        loggedIn = false;
                        System.out.println("Logged out.");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (Exception e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );
            }
        }
    }

    // ============================
    // SUBMIT COMPLAINT
    // ============================

    private static void submitComplaint(Student student)
            throws InvalidInputException {

        System.out.println("\n====== SUBMIT COMPLAINT ======");

        System.out.println("Categories:");
        System.out.println("1. Electrical");
        System.out.println("2. Water Leakage");
        System.out.println("3. AC");
        System.out.println("4. Fan");
        System.out.println("5. WiFi");
        System.out.println("6. Other");

        int categoryChoice =
                readInt("Choose category: ");

        String category;

        switch (categoryChoice) {

            case 1:
                category = "Electrical";
                break;

            case 2:
                category = "Water Leakage";
                break;

            case 3:
                category = "AC";
                break;

            case 4:
                category = "Fan";
                break;

            case 5:
                category = "WiFi";
                break;

            case 6:
                category = "Other";
                break;

            default:
                throw new InvalidInputException(
                        "Invalid category."
                );
        }

        String description =
                readString("Enter complaint description: ");

        String location =
                readString("Enter location: ");

        Complaint complaint = new Complaint(
                nextComplaintId++,
                student.getId(),
                category,
                description,
                location
        );

        complaintService.addComplaint(complaint);

        System.out.println(
                "Automatically assigned priority: "
                        + complaint.getPriority()
        );
    }

    // ============================
    // WORKER MENU
    // ============================

    private static void workerMenu() {

        System.out.println("\n========== WORKER LOGIN ==========");

        String email = readString("Email: ");
        String password = readString("Password: ");

        User user = userService.login(email, password);

        if (!(user instanceof Worker)) {

            System.out.println(
                    "Invalid worker credentials."
            );
            return;
        }

        Worker worker = (Worker) user;

        System.out.println(
                "\nWelcome, " + worker.getName() + "!"
        );

        boolean loggedIn = true;

        while (loggedIn) {

            System.out.println("\n------- WORKER MENU -------");
            System.out.println("1. View Assigned Complaints");
            System.out.println("2. Accept Complaint");
            System.out.println("3. Mark Complaint In Progress");
            System.out.println("4. Mark Complaint Resolved");
            System.out.println("5. Logout");

            int choice = readInt("Enter choice: ");

            try {

                switch (choice) {

                    case 1:
                        complaintService.displayAllComplaints();
                        break;

                    case 2:
                        assignWorker(worker);
                        break;

                    case 3:
                        updateComplaintStatus(
                                ComplaintStatus.IN_PROGRESS
                        );
                        break;

                    case 4:
                        updateComplaintStatus(
                                ComplaintStatus.RESOLVED
                        );
                        break;

                    case 5:
                        loggedIn = false;
                        System.out.println("Logged out.");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (Exception e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );
            }
        }
    }

    // ============================
    // ASSIGN WORKER
    // ============================

    private static void assignWorker(Worker worker)
            throws ComplaintNotFoundException,
            InvalidInputException {

        int complaintId =
                readInt("Enter complaint ID: ");

        complaintService.assignWorker(
                complaintId,
                worker.getId()
        );
    }

    // ============================
    // ADMIN MENU
    // ============================

    private static void adminMenu() {

        System.out.println("\n========== ADMIN LOGIN ==========");

        String email = readString("Email: ");
        String password = readString("Password: ");

        User user = userService.login(email, password);

        if (!(user instanceof Admin)) {

            System.out.println(
                    "Invalid admin credentials."
            );
            return;
        }

        Admin admin = (Admin) user;

        System.out.println(
                "\nWelcome, " + admin.getName() + "!"
        );

        boolean loggedIn = true;

        while (loggedIn) {

            System.out.println("\n------- ADMIN MENU -------");
            System.out.println("1. View All Users");
            System.out.println("2. View All Complaints");
            System.out.println("3. Assign Worker");
            System.out.println("4. Update Complaint Status");
            System.out.println("5. View Analytics");
            System.out.println("6. Search Complaint");
            System.out.println("7. Logout");

            int choice = readInt("Enter choice: ");

            try {

                switch (choice) {

                    case 1:
                        userService.displayUsers();
                        break;

                    case 2:
                        complaintService.displayAllComplaints();
                        break;

                    case 3:
                        int complaintId =
                                readInt("Complaint ID: ");

                        int workerId =
                                readInt("Worker ID: ");

                        complaintService.assignWorker(
                                complaintId,
                                workerId
                        );
                        break;

                    case 4:
                        updateComplaintStatusFromAdmin();
                        break;

                    case 5:
                        analyticsService.displayStatistics();
                        break;

                    case 6:
                        searchComplaint();
                        break;

                    case 7:
                        loggedIn = false;
                        System.out.println("Logged out.");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (Exception e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );
            }
        }
    }

    // ============================
    // ADMIN STATUS UPDATE
    // ============================

    private static void updateComplaintStatusFromAdmin()
            throws ComplaintNotFoundException,
            InvalidInputException {

        int complaintId =
                readInt("Complaint ID: ");

        System.out.println("\nChoose status:");
        System.out.println("1. SUBMITTED");
        System.out.println("2. ASSIGNED");
        System.out.println("3. IN_PROGRESS");
        System.out.println("4. RESOLVED");
        System.out.println("5. CANCELLED");

        int choice =
                readInt("Enter status: ");

        ComplaintStatus status;

        switch (choice) {

            case 1:
                status = ComplaintStatus.SUBMITTED;
                break;

            case 2:
                status = ComplaintStatus.ASSIGNED;
                break;

            case 3:
                status = ComplaintStatus.IN_PROGRESS;
                break;

            case 4:
                status = ComplaintStatus.RESOLVED;
                break;

            case 5:
                status = ComplaintStatus.CANCELLED;
                break;

            default:
                throw new InvalidInputException(
                        "Invalid status."
                );
        }

        complaintService.updateStatus(
                complaintId,
                status
        );
    }

    // ============================
    // STATUS UPDATE
    // ============================

    private static void updateComplaintStatus(
            ComplaintStatus status)
            throws ComplaintNotFoundException,
            InvalidInputException {

        int complaintId =
                readInt("Enter complaint ID: ");

        complaintService.updateStatus(
                complaintId,
                status
        );
    }

    // ============================
    // SEARCH COMPLAINT
    // ============================

    private static void searchComplaint()
            throws ComplaintNotFoundException {

        int complaintId =
                readInt("Enter complaint ID: ");

        Complaint complaint =
                complaintService.searchComplaint(
                        complaintId
                );

        complaint.displayComplaint();
    }

    // ============================
    // CANCEL COMPLAINT
    // ============================

    private static void cancelComplaint()
            throws ComplaintNotFoundException {

        int complaintId =
                readInt("Enter complaint ID: ");

        complaintService.cancelComplaint(
                complaintId
        );
    }

    // ============================
    // REGISTER STUDENT
    // ============================

    private static void registerStudent()
            throws InvalidInputException {

        System.out.println("\n====== STUDENT REGISTRATION ======");

        String name =
                readString("Name: ");

        String email =
                readString("Email: ");

        String phone =
                readString("Phone: ");

        String password =
                readString("Password: ");

        String hostel =
                readString("Hostel name: ");

        String room =
                readString("Room number: ");

        Student student = new Student(
                nextUserId++,
                name,
                email,
                phone,
                password,
                hostel,
                room
        );

        userService.registerUser(student);

        System.out.println(
                "Your Student ID is: "
                        + student.getId()
        );
    }

    // ============================
    // NOTIFICATION THREAD
    // ============================

    private static void startNotificationThread() {

        if (complaintService.getComplaints().isEmpty()) {

            System.out.println(
                    "No complaints available for notification."
            );

            return;
        }

        NotificationThread notificationThread =
                new NotificationThread(
                        complaintService.getComplaints()
                );

        notificationThread.start();

        System.out.println(
                "Notification thread started in background."
        );
    }

    // ============================
    // INPUT METHODS
    // ============================

    private static String readString(String message) {

        System.out.print(message);

        String input = scanner.nextLine().trim();

        return input;
    }

    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                int value =
                        Integer.parseInt(
                                scanner.nextLine().trim()
                        );

                return value;

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }
}