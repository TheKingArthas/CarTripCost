package permanence;

import domain.*;
import java.awt.Point;
import java.util.Date;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class DataManagement {

    public static void addCar(Car car) {
        TempDB tempDB = TempDB.getInstance();

        tempDB.cars.add(car);
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

    public static void addFuel(Fuel newFuel) {
        TempDB tempDB = TempDB.getInstance();
        tempDB.historicalFuelPrices.add(newFuel);
    }

    public static double getFuelCost(FuelType fuelType) {
        TempDB tempDB = TempDB.getInstance();
        Fuel mostUpToDateFuel = tempDB.historicalFuelPrices.get(0);

        double mostUpToDateCost = mostUpToDateFuel.getCost();

        for (Fuel f : tempDB.historicalFuelPrices) {
            if ((f.getType().equals(fuelType)) && (f.getCostUpdateDate().after(mostUpToDateFuel.getCostUpdateDate()))) {
                mostUpToDateFuel = f;
            }
        }

        mostUpToDateCost = mostUpToDateFuel.getCost();

        return mostUpToDateCost;
    }

    public static double getFullFuelTankCost(Car car, FuelType fuelType) {
        double tankCapacity = car.getTankCapacity();
        double fuelCostPerLitre = DataManagement.getFuelCost(fuelType);

        return tankCapacity * fuelCostPerLitre;
    }

    public static void addToll(Toll toll) {
        TempDB tempDB = TempDB.getInstance();

        tempDB.tolls.add(toll);
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

    public static void updateCarCategoryPrice(CarCategory carCategory, double expectedCost) {
        TempDB tempDB = TempDB.getInstance();

        CarCategoryPrice carCategoryPrice = new CarCategoryPrice();
        carCategoryPrice.setCategory(carCategory);
        carCategoryPrice.setPrice(expectedCost);
        carCategoryPrice.setUpdateDate(new Date());

        tempDB.historicalCarCateogriesPrices.add(carCategoryPrice);
    }

    public static double getCarCategoryPrice(CarCategory carCategory) {
        TempDB tempDB = TempDB.getInstance();
        CarCategoryPrice mostUpToDateCarCategoryPrice = tempDB.historicalCarCateogriesPrices.get(0);

        for (CarCategoryPrice ccp : tempDB.historicalCarCateogriesPrices) {

            if ((ccp.getCategory().equals(carCategory)) && (ccp.getUpdateDate().after(mostUpToDateCarCategoryPrice.getUpdateDate()))) {
                mostUpToDateCarCategoryPrice = ccp;
            }
        }

        double mostUpToDatePrice = mostUpToDateCarCategoryPrice.getPrice();

        return mostUpToDatePrice;
    }
}
