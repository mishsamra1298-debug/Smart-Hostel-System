package model;

public class Worker extends User {
    private String specialization;

    public Worker(int id, String name, String email, String phone,
                  String password, String specialization) {

        super(id, name, email, phone, password);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String getRole() {
        return "Maintenance Worker";
    }
}
