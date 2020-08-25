package domain;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class Car {

    private String licensePlate;
    private String brand;
    private String model;
    private double tankCapacity;
    private double efficiency;
    private CarCategory category;

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    public CarCategory getCategory() {
        return category;
    }

    public void setCategory(CarCategory category) {
        this.category = category;
    }

    public double getCarMaxDistance() {
        double tankCapacity = this.getTankCapacity();
        double carEfficiency = this.getEfficiency();

        return tankCapacity * carEfficiency;
    }

    public Car() {
    }
}
