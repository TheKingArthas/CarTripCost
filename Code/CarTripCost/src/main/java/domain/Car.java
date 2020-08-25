package domain;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class Car {

    private String licensePlate;
    private double tankCapacity;
    private double efficiency;
    private CarCategory category;

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
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
