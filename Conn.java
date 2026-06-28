import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/CarDealership";
    private static final String username = "root";

    //Enter your DataBase password here!
    private static final String password = "***********";
    static Connection connection = null;


    public static Connection establishConnection() {

        //Connecting DATABASE Drivers
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }


        try {
        if(connection == null || connection.isClosed()) {

                connection = DriverManager.getConnection(url, username, password);
                return connection;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

    public static boolean verifyPass(String truncatePass) {
        return truncatePass.equals(password);
    }
}
