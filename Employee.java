public class Employee {

    private int id;
    private String name;
    private String role;
    private String salary;
    private String contactNumber;

    public Employee(int id, String name, String role, String salary, String contactNumber) {

        this.id = id;
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.contactNumber = contactNumber;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
