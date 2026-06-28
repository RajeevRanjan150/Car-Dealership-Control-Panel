import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeService {

    public boolean employeeExists(int id) {
        try {
            String query = "SELECT * FROM employees WHERE id=?";
            Connection connection = Conn.establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if(result.next()) return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ResultSet retrieve() {
        try {
            String query = "SELECT * FROM employees;";
            Connection connection = Conn.establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet result = preparedStatement.executeQuery();

            return result;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int addEmployee(int id, String name, String role, String salary, String contactNumber) {
        try {
            String query = "INSERT INTO employees (id, name, role, salary, contactNumber) VALUES (?, ?, ?, ?, ?)";
            Connection connection = Conn.establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, role);
            preparedStatement.setString(4, salary);
            preparedStatement.setString(5, contactNumber);

            return preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public Employee getEmployee(int id) {
        try {
            String query = "SELECT * FROM employees WHERE id=?";
            PreparedStatement preparedStatement = Conn.establishConnection().prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();
            while(result.next()) {
                Employee employee = new Employee(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("role"),
                        result.getString("salary"),
                        result.getString("contactNumber")
                );

                return employee;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int changeId(int id, int newId) {
        try {
            String query = "UPDATE employees SET id=? WHERE id=?";
            Connection connection = Conn.establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, newId);
            preparedStatement.setInt(2, id);

            return preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int changeSalary(int id, String salary) {
        try {
            String query = "UPDATE employees SET salary=? WHERE id=?";
            Connection connection = Conn.establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, salary);
            preparedStatement.setInt(2, id);

            return preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int changeContactNumber(int id, String contactNumber) {
        try {
            String query = "UPDATE employees SET contactNumber=? WHERE id=?";
            Connection connection = Conn.establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, contactNumber);
            preparedStatement.setInt(2, id);

            return preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int changeRole(int id, String role) {
        try {
            String query = "UPDATE employees SET role=? WHERE id=?";
            Connection connection = Conn.establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, role);
            preparedStatement.setInt(2, id);

            return preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void truncateEmployeeDB(String truncatePass) {
        try{
            if(Conn.verifyPass(truncatePass)) {
                String query = "TRUNCATE TABLE employees";
                Connection connection = Conn.establishConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int removeEmployee(int id) {
        try {
            String query = "DELETE FROM employees WHERE id=?";
            Connection connection = Conn.establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
