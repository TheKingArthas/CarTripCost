package domain;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class Travel {

    private Point origin;
    private Point destiny;
    private double distance;
    private ArrayList<Toll> tolls;
    private Car car;
    private int passengersQuantity;

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Point getDestiny() {
        return destiny;
    }

    public void setDestiny(Point destiny) {
        this.destiny = destiny;
    }

    public double getDistance() {
        return Math.abs(origin.distance(destiny));
    }

    public ArrayList<Toll> getTolls() {
        return tolls;
    }

    public void addToll(Toll toll) {
        this.tolls.add(toll);
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getPassengersQuantity() {
        return passengersQuantity;
    }

    public void setPassengersQuantity(int passengersQuantity) {
        this.passengersQuantity = passengersQuantity;
    }

    public Travel() {
        tolls = new ArrayList<Toll>();
    }
}
