package logic;

import domain.Car;
import domain.CarCategory;
import domain.CarCategoryPrice;
import domain.Fuel;
import domain.FuelType;
import java.util.Date;
import permanence.TempDB;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class PriceManagement {

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

    public static double getFullFuelTankPrice(Car car, FuelType fuelType) {
        double tankCapacity = car.getTankCapacity();
        double fuelPricePerLitre = PriceManagement.getFuelPrice(fuelType);
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
        tempDB.historicalCarCateogriesPrices.add(carCategoryPrice);
    }
    
}
