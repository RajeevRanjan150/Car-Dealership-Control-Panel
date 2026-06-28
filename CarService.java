import java.sql.*;

public class CarService {

    public int addCar(String brand, String model, int price) {
        try {
            String query = "INSERT INTO cars (brand, model, price) VALUES (?, ?, ?)";
            Connection connection = Conn.establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, model);
            preparedStatement.setInt(3, price);

            return preparedStatement.executeUpdate();

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public ResultSet retrieve() {
        try {
            String query = "SELECT * FROM cars;";
            Connection connection = Conn.establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet result = preparedStatement.executeQuery();

            return result;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getId(String brand, String model) {
        int id = 0;
        try {
            String query = "SELECT * FROM cars WHERE brand=? AND model=?";
            Connection connection = Conn.establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, model);

            ResultSet set = preparedStatement.executeQuery();

            if(set.next()) {
                id = set.getInt("id");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public int changePrice(int id, int price) {
        try {
            String query = "UPDATE cars SET price=? WHERE id=?";

            Connection connection = Conn.establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, price);
            preparedStatement.setInt(2, id);

            return preparedStatement.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int removeCar(int id) {
        try {
            String query = "DELETE FROM cars WHERE id=?";
            Connection connection = Conn.establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public Car getCar(int id) {
        try {
            String query = "SELECT * FROM cars WHERE id=?";
            Connection connection = Conn.establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                Car car = new Car(
                        result.getString("brand"),
                        result.getString("model"),
                        result.getInt("price")
                );

                return car;
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean carExists(String brand, String model, int price) {
        try {
            String query = "SELECT * FROM cars WHERE brand=? AND model=? AND price=?";
            Connection connection = Conn.establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, model);
            preparedStatement.setInt(3, price);

            ResultSet result = preparedStatement.executeQuery();
            if(result.next()) {
                return true;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void truncateCarDB(String truncatePass) {
        try{
            if(Conn.verifyPass(truncatePass)) {
                String query = "TRUNCATE TABLE cars";
                Connection connection = Conn.establishConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
