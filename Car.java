import java.sql.SQLException;

public class Car {
    private int id;
    private String brand;
    private String model;
    private int price;

    public Car(String brand, String model, int price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        if(!new CarService().carExists(brand, model, price)) {
            new CarService().addCar(brand, model, price);
        }
        this.id = new CarService().getId(brand, model);
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public int getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    public void setPrice(int price) {
        if(new CarService().changePrice(getId(), price) > 0) {
            System.out.println("Price updated!");
        }
        this.price = price;
    }
}
