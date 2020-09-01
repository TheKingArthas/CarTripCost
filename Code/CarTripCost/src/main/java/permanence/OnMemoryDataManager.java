package permanence;

import domain.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class OnMemoryDataManager extends Datamanager {

    @Override
    public boolean hasCars() {
        TempDB tempDB = TempDB.getInstance();
        return !tempDB.cars.isEmpty();
    }

    @Override
    public void addCar(Car car) {
        TempDB tempDB = TempDB.getInstance();

        tempDB.cars.add(car);
    }

    @Override
    public ArrayList<Car> getCars() {
        TempDB tempDB = TempDB.getInstance();
        return tempDB.cars;
    }

    @Override
    public Car getCarByLicensePlate(String licensePlate) {
        TempDB tempDB = TempDB.getInstance();
        Car requestedCar = null;

        for (Car c : tempDB.cars) {
            if (c.getLicensePlate().equals(licensePlate)) {
                requestedCar = c;
            }
        }

        return requestedCar;
    }

    @Override
    public boolean hasTolls() {
        TempDB tempDB = TempDB.getInstance();
        return !tempDB.tolls.isEmpty();
    }

    @Override
    public void addToll(Toll toll) {
        TempDB tempDB = TempDB.getInstance();

        tempDB.tolls.add(toll);
    }

    @Override
    public ArrayList<Toll> getTolls() {
        TempDB tempDB = TempDB.getInstance();

        return tempDB.tolls;
    }

    @Override
    public Toll getTollByCoordinates(Point coordinates) {
        TempDB tempDB = TempDB.getInstance();
        Toll requestedToll = null;

        for (Toll t : tempDB.tolls) {
            if (t.getCoordinates().equals(coordinates)) {
                requestedToll = t;
            }
        }

        return requestedToll;
    }

    @Override
    public Point getTollCoordinates(String name) {
        TempDB tempDB = TempDB.getInstance();
        Point requestedCoordinates = null;

        for (Toll t : tempDB.tolls) {
            if (t.getName().equals(name)) {
                requestedCoordinates = t.getCoordinates();
            }
        }

        return requestedCoordinates;
    }

    @Override
    public ArrayList<CarCategoryPrice> getHistoricalCarCategoriesPrices() {
        TempDB tempDB = TempDB.getInstance();

        return tempDB.historicalCarCategoriesPrices;
    }

    public void updateCarCategoryPrice(CarCategory carCategory, double newPrice) {
        TempDB tempDB = TempDB.getInstance();

        CarCategoryPrice carCategoryPrice = new CarCategoryPrice();
        carCategoryPrice.setCategory(carCategory);
        carCategoryPrice.setPrice(newPrice);
        carCategoryPrice.setUpdateDate(new Date());

        tempDB.historicalCarCategoriesPrices.add(carCategoryPrice);
    }

    @Override
    public boolean hasFuels() {
        TempDB tempDB = TempDB.getInstance();
        return !tempDB.historicalFuelPrices.isEmpty();
    }

    @Override
    public void addFuel(Fuel newFuel) {
        TempDB tempDB = TempDB.getInstance();
        tempDB.historicalFuelPrices.add(newFuel);
    }

    @Override
    public ArrayList<Fuel> getHistoricalFuelPrices() {
        TempDB tempDB = TempDB.getInstance();

        return tempDB.historicalFuelPrices;
    }

    @Override
    public void addTravel(Travel travel) {
        TempDB tempDB = TempDB.getInstance();

        tempDB.travels.add(travel);
    }

    @Override
    public void addTollToTravel(Travel travel, Toll toll) {
        TempDB tempDB = TempDB.getInstance();

        for (Travel tr : tempDB.travels) {
            if (tr.equals(travel)) {
                tr.addToll(toll);
            }
        }
    }

    /**
     * WARNING!: This method will wipe all data. This procedure cannot be
     * undone.
     */
    @Override
    public void deleteAllDataBases() {
        TempDB.deleteAllDataBases();
    }
}
