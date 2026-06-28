import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    private static final String dealerPass = "Dealer123";
    private static final String managerPass = "Manager123";
    private static final String adminPass = "Admin123";

    public static void menu() {
        HashMap<Integer, Car> cars = new HashMap<>();
        HashMap<Integer, Employee> employees = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        ServiceClass helper = new ServiceClass();

        helper.retrieveCarData(cars);
        helper.retrieveEmployeeData(employees);

        System.out.println("====== WELCOME TO CAR DEALERSHIP CONTROL PANEL ======");
        System.out.println("\n\nPlease Verify Your Identity: ");
        System.out.println("1. Dealer");
        System.out.println("2. Manager");
        System.out.println("3. Admin");
        System.out.print("Enter choice(in NO.): ");
        int identity = sc.nextInt();
        System.out.print("Password: ");
        String password = sc.next();

        if(identity == 1 && password.equals(dealerPass)) {
            dealerPanel(cars, sc, helper);
            sc.close();

        } else if(identity == 2 && password.equals(managerPass)) {
            managerPanel(cars, employees, sc, helper);
            sc.close();

        } else if(identity == 3 && password.equals(adminPass)) {
            adminPanel(cars, employees, sc, helper);
            sc.close();

        } else {
            System.out.println("Invalid Password OR Identity!");
            helper.exit();
            sc.close();
            System.exit(0);
        }

        sc.close();
    }

    private static void dealerPanel(HashMap<Integer,Car> cars, Scanner sc, ServiceClass helper) {
        System.out.println("== Dealer's Panel ==");

        while(true) {
            System.out.println("1. Sell Car");
            System.out.println("2. Add Car");
            System.out.println("3. View Cars");
            System.out.println("0. Exit");
            System.out.print("Enter choice(in NO.): ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    helper.sellCar(cars, sc);
                    System.out.println();
                    break;

                case 2:
                    helper.addCar(cars, sc);
                    System.out.println();
                    break;

                case 3:
                    helper.viewCars(cars);
                    System.out.println();
                    break;

                case 0:
                    helper.exit();
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Choice!.. Choose Again!");
            }
        }
    }

    private static void managerPanel(HashMap<Integer,Car> cars, HashMap<Integer, Employee> employees, Scanner sc, ServiceClass helper) {
        System.out.println("== Manager's Panel ==");

        while(true) {
            System.out.println("1. Add Car");
            System.out.println("2. View Cars");
            System.out.println("3. Change Existing Car Price");
            System.out.println("4. Delete Car from DataBase");
            System.out.println("5. Add Employee");
            System.out.println("6. View Employee by ID");
            System.out.println("7. View Employees");
            System.out.println("8. Remove Employee");
            System.out.println("9. Change ID of an Employee");
            System.out.println("10. Change salary of an Employee");
            System.out.println("11. Change contact of an Employee");
            System.out.println("0. Exit");
            System.out.print("Enter choice(in NO.): ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    helper.addCar(cars, sc);
                    System.out.println();
                    break;

                case 2:
                    helper.viewCars(cars);
                    System.out.println();
                    break;

                case 3:
                    helper.changePrice(cars, sc);
                    System.out.println();
                    break;

                case 4:
                    helper.deleteCar(cars, sc);
                    System.out.println();
                    break;

                case 5:
                    helper.addEmployee(employees, sc);
                    System.out.println();
                    break;

                case 6:
                    helper.viewEmployeeByID(employees, sc);
                    System.out.println();
                    break;

                case 7:
                    helper.viewEmployees(employees);
                    System.out.println();
                    break;

                case 8:
                    helper.removeEmployee(employees, sc);
                    System.out.println();
                    break;

                case 9:
                    helper.changeEmployeeId(employees, sc);
                    System.out.println();
                    break;

                case 10:
                    helper.changeEmployeeSalary(employees, sc);
                    System.out.println();
                    break;

                case 11:
                    helper.changeEmployeeContact(employees, sc);
                    System.out.println();
                    break;

                case 0:
                    helper.exit();
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Choice!.. Choose Again!");
            }
        }
    }

    private static void adminPanel(HashMap<Integer,Car> cars, HashMap<Integer, Employee> employees, Scanner sc, ServiceClass helper) {
        System.out.println("== Admin's Panel ==");

        while(true) {
            System.out.println("1. Add Car");
            System.out.println("2. View Cars");
            System.out.println("3. Change Existing Car Price");
            System.out.println("4. Delete Car from DataBase");
            System.out.println("5. Truncate Car DataBase");
            System.out.println("6. View Employee By ID");
            System.out.println("7. View Employees");
            System.out.println("8. Remove Employee");
            System.out.println("9. Change ID of an Employee");
            System.out.println("10. Change salary of an Employee");
            System.out.println("11. Change contact of an Employee");
            System.out.println("12. Change role of an Employee");
            System.out.println("13. Truncate Employee DataBase");
            System.out.println("0. Exit");
            System.out.print("Enter choice(in NO.): ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    helper.addCar(cars, sc);
                    System.out.println();
                    break;

                case 2:
                    helper.viewCars(cars);
                    System.out.println();
                    break;

                case 3:
                    helper.changePrice(cars, sc);
                    System.out.println();
                    break;

                case 4:
                    helper.deleteCar(cars, sc);
                    System.out.println();
                    break;

                case 5:
                    helper.truncateCarDB(cars, sc);
                    System.out.println();
                    break;

                case 6:
                    helper.viewEmployeeByID(employees, sc);
                    System.out.println();
                    break;

                case 7:
                    helper.viewEmployees(employees);
                    System.out.println();
                    break;

                case 8:
                    helper.removeEmployee(employees, sc);
                    System.out.println();
                    break;

                case 9:
                    helper.changeEmployeeId(employees, sc);
                    System.out.println();
                    break;

                case 10:
                    helper.changeEmployeeSalary(employees, sc);
                    System.out.println();
                    break;

                case 11:
                    helper.changeEmployeeContact(employees, sc);
                    System.out.println();
                    break;

                case 12:
                    helper.changeEmployeeRole(employees, sc);
                    System.out.println();
                    break;

                case 13:
                    helper.truncateEmployeeDB(employees, sc);
                    System.out.println();
                    break;

                case 0:
                    helper.exit();
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Choice!.. Choose Again!");
            }
        }
    }
}
