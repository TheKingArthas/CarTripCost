package permanence;

import domain.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class DataManagement {

    public static boolean hasCars() {
        TempDB tempDB = TempDB.getInstance();
        return !tempDB.cars.isEmpty();
    }

    public static void addCar(Car car) {
        TempDB tempDB = TempDB.getInstance();

        tempDB.cars.add(car);
    }

    public static ArrayList<Car> getCars() {
        TempDB tempDB = TempDB.getInstance();
        return tempDB.cars;
    }

    public static Car getCarByLicensePlate(String licensePlate) {
        TempDB tempDB = TempDB.getInstance();
        Car requestedCar = null;

        for (Car c : tempDB.cars) {
            if (c.getLicensePlate().equals(licensePlate)) {
                requestedCar = c;
            }
        }

        return requestedCar;
    }

    public static boolean hasTolls() {
        TempDB tempDB = TempDB.getInstance();
        return !tempDB.tolls.isEmpty();
    }

    public static void addToll(Toll toll) {
        TempDB tempDB = TempDB.getInstance();

        tempDB.tolls.add(toll);
    }

    public static ArrayList<Toll> getTolls() {
        TempDB tempDB = TempDB.getInstance();

        return tempDB.tolls;
    }

    public static Toll getTollByCoordinates(Point coordinates) {
        TempDB tempDB = TempDB.getInstance();
        Toll requestedToll = null;

        for (Toll t : tempDB.tolls) {
            if (t.getCoordinates().equals(coordinates)) {
                requestedToll = t;
            }
        }

        return requestedToll;
    }

    public static Point getTollCoordinates(String name) {
        TempDB tempDB = TempDB.getInstance();
        Point requestedCoordinates = null;

        for (Toll t : tempDB.tolls) {
            if (t.getName().equals(name)) {
                requestedCoordinates = t.getCoordinates();
            }
        }

        return requestedCoordinates;
    }

    public static ArrayList<CarCategoryPrice> getHistoricalCarCategoriesPrices() {
        TempDB tempDB = TempDB.getInstance();

        return tempDB.historicalCarCategoriesPrices;
    }

    public static void updateCarCategoryPrice(CarCategory carCategory, double expectedPrice) {
        TempDB tempDB = TempDB.getInstance();

        CarCategoryPrice carCategoryPrice = new CarCategoryPrice();
        carCategoryPrice.setCategory(carCategory);
        carCategoryPrice.setPrice(expectedPrice);
        carCategoryPrice.setUpdateDate(new Date());

        tempDB.historicalCarCategoriesPrices.add(carCategoryPrice);
    }

    public static boolean hasFuels() {
        TempDB tempDB = TempDB.getInstance();
        return !tempDB.historicalFuelPrices.isEmpty();
    }

    public static void addFuel(Fuel newFuel) {
        TempDB tempDB = TempDB.getInstance();
        tempDB.historicalFuelPrices.add(newFuel);
    }

    public static ArrayList<Fuel> getHistoricalFuelPrices() {
        TempDB tempDB = TempDB.getInstance();

        return tempDB.historicalFuelPrices;
    }

    public static void addTravel(Travel travel) {
        TempDB tempDB = TempDB.getInstance();

        tempDB.travels.add(travel);
    }

    public static void addTollToTravel(Travel travel, Toll toll) {
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
    public static void deleteAllDataBases() {
        TempDB.deleteAllDataBases();
    }
}
