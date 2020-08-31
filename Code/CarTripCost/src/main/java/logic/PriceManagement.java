package logic;

import domain.Car;
import domain.CarCategory;
import domain.CarCategoryPrice;
import domain.Fuel;
import domain.FuelType;
import domain.Travel;
import java.util.ArrayList;
import java.util.Date;
import permanence.OnMemoryDataManager;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class PriceManagement {

    public static double getCarCategoryPrice(CarCategory carCategory) {
        OnMemoryDataManager dataManager = new OnMemoryDataManager();

        ArrayList<CarCategoryPrice> historicalCarCategoriesPrices = dataManager.getHistoricalCarCategoriesPrices();

        CarCategoryPrice mostUpToDateCarCategoryPrice = historicalCarCategoriesPrices.get(0);
        for (CarCategoryPrice ccp : dataManager.getHistoricalCarCategoriesPrices()) {
            if ((ccp.getCategory().equals(carCategory)) && (ccp.getUpdateDate().after(mostUpToDateCarCategoryPrice.getUpdateDate()))) {
                mostUpToDateCarCategoryPrice = ccp;
            }
        }
        double mostUpToDatePrice = mostUpToDateCarCategoryPrice.getPrice();
        return mostUpToDatePrice;
    }

    public static double getFullFuelTankPrice(Car car) {
        OnMemoryDataManager dataManager = new OnMemoryDataManager();

        double tankCapacity = car.getTankCapacity();
        double fuelPricePerLitre = PriceManagement.getFuelPrice(car.getFuelType());
        return tankCapacity * fuelPricePerLitre;
    }

    public static double getFuelPrice(FuelType fuelType) {
        OnMemoryDataManager dataManager = new OnMemoryDataManager();

        ArrayList<Fuel> historicalFuelPrices = dataManager.getHistoricalFuelPrices();

        Fuel mostUpToDateFuel = historicalFuelPrices.get(0);
        double mostUpToDatePrice = mostUpToDateFuel.getPrice();
        for (Fuel f : historicalFuelPrices) {
            if ((f.getType().equals(fuelType)) && (f.getPriceUpdateDate().after(mostUpToDateFuel.getPriceUpdateDate()))) {
                mostUpToDateFuel = f;
            }
        }
        mostUpToDatePrice = mostUpToDateFuel.getPrice();
        return mostUpToDatePrice;
    }

    public static void updateCarCategoryPrice(CarCategory carCategory, double expectedPrice) {
        OnMemoryDataManager dataManager = new OnMemoryDataManager();

        dataManager.updateCarCategoryPrice(carCategory, expectedPrice);
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
        OnMemoryDataManager dataManager = new OnMemoryDataManager();

        Fuel newFuel = new Fuel();
        newFuel.setType(fuelType);
        newFuel.setPrice(price);
        newFuel.setPriceUpdateDate(new Date());

        dataManager.addFuel(newFuel);
    }
}
