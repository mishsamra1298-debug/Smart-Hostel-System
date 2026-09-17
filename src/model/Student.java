package model;

public class Student extends User {
    private String hostelName;
    private String roomNumber;

    public Student(int id, String name, String email, String phone,
                   String password, String hostelName, String roomNumber) {

        super(id, name, email, phone, password);
        this.hostelName = hostelName;
        this.roomNumber = roomNumber;
    }

    public String getHostelName() {
        return hostelName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public void displayUser() {
        super.displayUser();
        System.out.println("Hostel: " + hostelName);
        System.out.println("Room: " + roomNumber);
    }
}
