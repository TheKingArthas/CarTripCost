package domain;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class Car {

    private String licensePlate;
    private double tankCapacity;

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

    public Car() {
        this.licensePlate = licensePlate;
    }

}
