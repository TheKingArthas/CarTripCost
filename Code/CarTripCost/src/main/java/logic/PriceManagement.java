package logic;

import domain.Car;
import domain.CarCategory;
import domain.CarCategoryPrice;
import domain.Fuel;
import domain.FuelType;
import domain.Travel;
import java.util.Date;
import permanence.DataManagement;
import permanence.TempDB;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class PriceManagement {

    public static boolean hasCars() {
        TempDB tempDB = TempDB.getInstance();

        return !tempDB.cars.isEmpty();
    }

    public static boolean hasTolls() {
        TempDB tempDB = TempDB.getInstance();

        return !tempDB.tolls.isEmpty();
    }

    public static double getCarCategoryPrice(CarCategory carCategory) {
        TempDB tempDB = TempDB.getInstance();
        CarCategoryPrice mostUpToDateCarCategoryPrice = tempDB.historicalCarCategoriesPrices.get(0);
        for (CarCategoryPrice ccp : tempDB.historicalCarCategoriesPrices) {
            if ((ccp.getCategory().equals(carCategory)) && (ccp.getUpdateDate().after(mostUpToDateCarCategoryPrice.getUpdateDate()))) {
                mostUpToDateCarCategoryPrice = ccp;
            }
        }
        double mostUpToDatePrice = mostUpToDateCarCategoryPrice.getPrice();
        return mostUpToDatePrice;
    }

    public static double getFullFuelTankPrice(Car car) {
        double tankCapacity = car.getTankCapacity();
        double fuelPricePerLitre = PriceManagement.getFuelPrice(car.getFuelType());
        return tankCapacity * fuelPricePerLitre;
    }

    public static double getFuelPrice(FuelType fuelType) {
        TempDB tempDB = TempDB.getInstance();
        Fuel mostUpToDateFuel = tempDB.historicalFuelPrices.get(0);
        double mostUpToDatePrice = mostUpToDateFuel.getPrice();
        for (Fuel f : tempDB.historicalFuelPrices) {
            if ((f.getType().equals(fuelType)) && (f.getPriceUpdateDate().after(mostUpToDateFuel.getPriceUpdateDate()))) {
                mostUpToDateFuel = f;
            }
        }
        mostUpToDatePrice = mostUpToDateFuel.getPrice();
        return mostUpToDatePrice;
    }

    public static void updateCarCategoryPrice(CarCategory carCategory, double expectedPrice) {
        TempDB tempDB = TempDB.getInstance();
        CarCategoryPrice carCategoryPrice = new CarCategoryPrice();
        carCategoryPrice.setCategory(carCategory);
        carCategoryPrice.setPrice(expectedPrice);
        carCategoryPrice.setUpdateDate(new Date());
        tempDB.historicalCarCategoriesPrices.add(carCategoryPrice);
    }

    public static double getTravelTollsTotalCost(Travel travel) {
        int tollsQuantity = travel.getTolls().size();
        double perTollCost = getCarCategoryPrice(travel.getCar().getCategory());

        return perTollCost * tollsQuantity;
    }

    public static double getTravelTotalCost(Travel travel) {
        double distanceToTravel = travel.getDistance();
        double fullTankPrice = getFullFuelTankPrice(travel.getCar());
        double carMaxDistance = travel.getCar().getMaxDistance();
        double tollsTotalCost = getTravelTollsTotalCost(travel);

        return ((distanceToTravel * fullTankPrice / carMaxDistance) + tollsTotalCost);
    }

    public static double getTravelPerPassengerPrice(Travel travel) {
        double travelTotalCost = getTravelTotalCost(travel);
        int passengersQuantity = travel.getPassengersQuantity();

        return travelTotalCost / passengersQuantity;
    }

    public static void updateFuelTypePrice(FuelType fuelType, double price) {
        Fuel newFuel = new Fuel();
        newFuel.setType(fuelType);
        newFuel.setPrice(price);
        newFuel.setPriceUpdateDate(new Date());

        DataManagement.addFuel(newFuel);
    }
}
