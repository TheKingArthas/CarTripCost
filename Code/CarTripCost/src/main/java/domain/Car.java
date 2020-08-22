package domain;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class Car {

    private String licensePlate;
    private double tankCapacity;
    private double efficiency;

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

    public double getCarMaxDistance() {
        double tankCapacity = this.getTankCapacity();
        double carEfficiency = this.getEfficiency();

        return tankCapacity * carEfficiency;
    }

    public Car() {
        this.licensePlate = licensePlate;
    }
}
