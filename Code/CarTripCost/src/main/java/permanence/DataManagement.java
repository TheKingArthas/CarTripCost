package permanence;

import domain.*;

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
        System.out.println(newFuel);
        tempDB.historicalFuelPrices.add(newFuel);
    }

    public static double getFuelCost(FuelType fuelType) {
        TempDB tempDB = TempDB.getInstance();
        Fuel mostUpToDateFuel = tempDB.historicalFuelPrices.get(0);
        System.out.println(tempDB.historicalFuelPrices.get(0));
        double mostUpToDateCost = mostUpToDateFuel.getCost();
        System.out.println(fuelType);
        System.out.println(mostUpToDateFuel);
        System.out.println(mostUpToDateCost);
        for (Fuel f : tempDB.historicalFuelPrices) {
            if ((f.getType().equals(fuelType)) && (f.getCostUpdateDate().after(mostUpToDateFuel.getCostUpdateDate()))) {
                mostUpToDateFuel = f;
            }
        }

        mostUpToDateCost = mostUpToDateFuel.getCost();

        return mostUpToDateCost;
    }
}
