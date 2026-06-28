import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServiceClass {

    public void retrieveCarData(HashMap<Integer,Car> cars) {
        try (ResultSet result = new CarService().retrieve()) {
            while(result.next()) {
                int id = result.getInt("id");
                String brand = result.getString("brand");
                String model = result.getString("model");
                int price = result.getInt("price");

                cars.put(id, new Car(brand, model, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void exit() {
        System.out.print("Exiting");
        try {
            for(int i = 0; i < 3; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sellCar(HashMap<Integer,Car> cars, Scanner sc) {
        if(cars.isEmpty()) {
            System.out.println("No Cars found!");
            return;
        }
        System.out.print("Enter Car ID to sell: ");
        int id = sc.nextInt();
        cars.remove(id);
        int result = new CarService().removeCar(id);
        if(result > 0) {
            System.out.println("Car Removed from Data Successfully!");
        } else {
            System.out.println("Couldn't find Car with ID " + id + "!");
        }
    }

    public void addCar(HashMap<Integer,Car> cars, Scanner sc) {
        System.out.println("Enter Car's Credentials:");
        System.out.print("Car's Brand: ");
        String brand = sc.nextLine();
        System.out.print("Car's Model: ");
        String model = sc.nextLine();
        System.out.print("Car's Price: ");
        int price = sc.nextInt();

        int result = new CarService().addCar(brand, model, price);
        if(result <= 0) {
            System.out.println("Operation Unsuccessful!");
            return;
        }
        int id = new CarService().getId(brand, model);
        cars.putIfAbsent(id, new CarService().getCar(id));
        System.out.println("Car Added Successfully!");

    }

    public void viewCars(HashMap<Integer,Car> cars) {
        if(cars.isEmpty()) {
            System.out.println("No cars found!");
            return;
        }
        System.out.println();
        for(Map.Entry<Integer, Car> map : cars.entrySet()) {
            System.out.println(
                    "ID: " + map.getKey()
                    + "\nBrand: " + map.getValue().getBrand()
                    + "\nModel: " + map.getValue().getModel()
                    + "\nPrice: ₹" + map.getValue().getPrice()

                    + "\n"
            );
        }
    }

    public void changePrice(HashMap<Integer,Car> cars, Scanner sc) {
        System.out.print("Enter Car's ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Price: ");
        int price = sc.nextInt();

        int result = new CarService().changePrice(id, price);

        if(result > 0) {
            cars.get(id).setPrice(price);
            System.out.println("Price Changed Successfully!");
        } else {
            System.out.println("Couldn't find Car with ID " + id + "!");
        }
    }

    public void truncateCarDB(HashMap<Integer, Car> cars, Scanner sc) {
        System.out.print("Enter DataBase password to Truncate: ");
        String truncatePass = sc.nextLine();

        new CarService().truncateCarDB(truncatePass);
        cars.clear();

        System.out.println("Car DataBase Truncated!");
    }

    public void deleteCar(HashMap<Integer,Car> cars, Scanner sc) {
        if(cars.isEmpty()) {
            System.out.println("No Cars found!");
            return;
        }

        System.out.print("Enter Car ID to delete: ");
        int id = sc.nextInt();
        cars.remove(id);
        int result = new CarService().removeCar(id);
        if(result > 0) {
            System.out.println("Car Removed from Data Successfully!");
        } else {
            System.out.println("Couldn't find Car with ID " + id + "!");
        }
    }

    public void addEmployee(HashMap<Integer,Employee> employees, Scanner sc) {
        System.out.println("Enter Employee's Credentials:");
        System.out.print("Enter Employee's ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        if(new EmployeeService().employeeExists(id)) {
            System.out.println("Employee with ID " + id + " already exists!");
            return;
        }
        System.out.print("Enter Employee's Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Employee's Role: ");
        String role = sc.nextLine();
        System.out.print("Enter Employee's Salary: ₹");
        String salary = sc.nextLine();
        System.out.print("Enter Employee's Contact Number: +91 ");
        String contactNumber = sc.next();

        int result = new EmployeeService().addEmployee(id, name, role, salary, contactNumber);
        if(result <= 0) {
            System.out.println("Operation Unsuccessful!");
            return;
        }
        employees.putIfAbsent(id, new EmployeeService().getEmployee(id));
    }

    public void removeEmployee(HashMap<Integer,Employee> employees, Scanner sc) {
        if(employees.isEmpty()) {
            System.out.println("No Employees Found!");
            return;
        }

        System.out.print("Enter Employee's ID: ");
        int id = sc.nextInt();

        if(!new EmployeeService().employeeExists(id)) {
            System.out.println("No Employee Found with ID " + id +"!");
            return;
        }

        int result = new EmployeeService().removeEmployee(id);

        if(result <= 0) {
            System.out.println("Operation Unsuccessful!");
            return;
        }

        employees.remove(id);

        System.out.println("Employee Removed Successfully!");
    }

    public void changeEmployeeId(HashMap<Integer,Employee> employees, Scanner sc) {
        if(employees.isEmpty()) {
            System.out.println("No Employees Found!");
            return;
        }

        System.out.print("Enter Employee's Current ID: ");
        int id = sc.nextInt();

        if(!new EmployeeService().employeeExists(id)) {
            System.out.println("No Employee Found with ID " + id + "!");
            return;
        }

        System.out.print("Enter New ID: ");
        int newId = sc.nextInt();

        if(new EmployeeService().employeeExists(newId)) {
            System.out.println("Employee with ID " + newId + " already exists!");
            return;
        }

        int result = new EmployeeService().changeId(id, newId);
        if(result <= 0) {
            System.out.println("Operation Unsuccessful!");
            return;
        }

        employees.putIfAbsent(newId, employees.get(id));
        employees.get(newId).setId(newId);
        employees.remove(id);

        System.out.println("ID changed Successfully!");

    }

    public void changeEmployeeSalary(HashMap<Integer,Employee> employees, Scanner sc) {
        if(employees.isEmpty()) {
            System.out.println("No Employees Found!");
            return;
        }

        System.out.print("Enter Employee's ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if(!new EmployeeService().employeeExists(id)) {
            System.out.println("No Employee Exists with ID " + id + "!");
            return;
        }

        System.out.print("Enter New Salary: ₹");
        String salary = sc.nextLine();

        int result = new EmployeeService().changeSalary(id, salary);

        if(result <= 0) {
            System.out.println("Operation Unsuccessful!");
            return;
        }

        employees.get(id).setSalary(salary);
        System.out.println("Salary changed Successfully!");
    }

    public void changeEmployeeContact(HashMap<Integer,Employee> employees, Scanner sc) {
        if(employees.isEmpty()) {
            System.out.println("No Employees Found!");
            return;
        }

        System.out.print("Enter Employee's ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if(!new EmployeeService().employeeExists(id)) {
            System.out.println("No Employee Exists with ID " + id + "!");
            return;
        }

        System.out.print("Enter New Contact: ");
        String contactNumber = sc.next();

        int result = new EmployeeService().changeContactNumber(id, contactNumber);

        if(result <= 0) {
            System.out.println("Operation Unsuccessful!");
            return;
        }

        employees.get(id).setContactNumber(contactNumber);
        System.out.println("Contact Number changed Successfully!");

    }

    public void truncateEmployeeDB(HashMap<Integer,Employee> employees, Scanner sc) {
        System.out.print("Enter DataBase password to Truncate: ");
        String truncatePass = sc.nextLine();

        new EmployeeService().truncateEmployeeDB(truncatePass);
        employees.clear();

        System.out.println("Employee DataBase Truncated!");
    }

    public void changeEmployeeRole(HashMap<Integer,Employee> employees, Scanner sc) {
        if(employees.isEmpty()) {
            System.out.println("No Employees Found!");
            return;
        }

        System.out.print("Enter Employee's ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if(!new EmployeeService().employeeExists(id)) {
            System.out.println("No Employee Exists with ID " + id + "!");
            return;
        }

        System.out.print("Enter New Role: ");
        String role = sc.nextLine();

        int result = new EmployeeService().changeRole(id, role);

        if(result <= 0) {
            System.out.println("Operation Unsuccessful!");
            return;
        }

        employees.get(id).setRole(role);
        System.out.println("Role changed Successfully!");

    }

    public void retrieveEmployeeData(HashMap<Integer,Employee> employees) {
        try (ResultSet result = new EmployeeService().retrieve()) {
            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String role = result.getString("role");
                String salary = result.getString("salary");
                String contactNumber = result.getString("contactNumber");

                employees.put(id, new Employee(id, name, role, salary, contactNumber));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewEmployees(HashMap<Integer,Employee> employees) {
        if(employees.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }

        System.out.println();
        for(Map.Entry<Integer, Employee> map : employees.entrySet()) {
            System.out.println(
                    "Id: " + map.getKey()
                    + "\nName: " + map.getValue().getName()
                    + "\nRole: " + map.getValue().getRole()
                    + "\nSalary: ₹" + map.getValue().getSalary()
                    + "\nContact: " + map.getValue().getContactNumber()

                    + "\n"
            );
        }
    }

    public void viewEmployeeByID(HashMap<Integer,Employee> employees, Scanner sc) {
        if(employees.isEmpty()) {
            System.out.println("No Employees Found!");
            return;
        }

        System.out.print("Enter Employee's ID: ");
        int id = sc.nextInt();
        if(!new EmployeeService().employeeExists(id)) {
            System.out.println("Employee with ID " + id + " doesn't exist!");
            return;
        }

        Employee employee = employees.get(id);
        System.out.println(
                "ID: " + employee.getId() +
                "\nName: " + employee.getName() +
                "\nRole: " + employee.getRole() +
                "\nSalary: ₹" + employee.getSalary() +
                "\nContact Number: " + employee.getContactNumber()
        );
    }
}
