package logic;

import domain.*;
import java.awt.Point;
import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import permanence.DataManagement;
import permanence.TempDB;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class PriceManagementTest {

    Car newCar;
    String licensePlate;
    double fuelTankCapacity;
    Fuel newFuel;
    double price;
    double carEfficiency;
    Point origin;
    Point destiny;
    Travel newTravel;

    @Before
    public void setUp() {
        newCar = new Car();
        licensePlate = "ABC1234";
        newCar.setLicensePlate(licensePlate);
        fuelTankCapacity = 40.00;
        newCar.setTankCapacity(fuelTankCapacity);
        carEfficiency = 16;
        newCar.setEfficiency(carEfficiency);
        newCar.setCategory(CarCategory.CAT_01);

        newFuel = new Fuel();
        price = 12.34;
        newFuel.setType(FuelType.GASOLINE);
        newFuel.setPrice(price);
        newFuel.setPriceUpdateDate(new Date());

        newTravel = new Travel();
        origin = new Point(10, 20);
        destiny = new Point(30, 40);
        newTravel.setOrigin(origin);
        newTravel.setDestiny(destiny);
        newTravel.setCar(newCar);

        DataManagement.addCar(newCar);
        DataManagement.addFuel(newFuel);
        DataManagement.addTravel(newTravel);
    }

    @After
    public void dataBaseCleanUp() {
        TempDB tempDB = TempDB.getInstance();

        tempDB.cars.clear();
        tempDB.historicalFuelPrices.clear();
        tempDB.tolls.clear();
        tempDB.historicalCarCateogriesPrices.clear();
        tempDB.travels.clear();
    }

    @Test
    public void testUpdateCarCategoryPrice() {
        double expectedPrice = 100.0;
        PriceManagement.updateCarCategoryPrice(CarCategory.CAT_01, expectedPrice);
        double obtainedPrice = PriceManagement.getCarCategoryPrice(CarCategory.CAT_01);
        assertEquals(expectedPrice, obtainedPrice, 0.009);
        /*"0.009" was the selected delta to avoid differences
        when rounding prices.*/
    }

    @Test
    public void testGetFuelGasolineCurrentPrice() {
        double obtainedPrice = PriceManagement.getFuelPrice(FuelType.GASOLINE);
        assertEquals(price, obtainedPrice, 0.009);
        /*"0.009" was the selected delta to avoid differences
        when rounding prices.*/
    }

    @Test
    public void testGetFuelMostUpToDatePrice() {
        Fuel olderFuel = new Fuel();
        double oldPrice = 9.87;
        olderFuel.setType(FuelType.GASOLINE);
        olderFuel.setPrice(oldPrice);
        Date yesterday = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(yesterday);
        c.add(Calendar.DATE, -1);
        yesterday = c.getTime();
        olderFuel.setPriceUpdateDate(yesterday);
        double newPrice = 12.34;
        newFuel.setType(FuelType.GASOLINE);
        newFuel.setPrice(newPrice);
        newFuel.setPriceUpdateDate(new Date());
        double obtainedPrice = PriceManagement.getFuelPrice(FuelType.GASOLINE);
        assertEquals(newPrice, obtainedPrice, 0.009);
        /*"0.009" was the selected delta to avoid differences
        when rounding prices.*/
    }

    @Test
    public void testGetCarFullTankPrice() {
        double expectedPrice = fuelTankCapacity * price;
        double obtainedPrice = PriceManagement.getFullFuelTankPrice(newCar);
        assertEquals(expectedPrice, obtainedPrice, 0.009);
        /*"0.009" was the selected delta to avoid differences
        when rounding prices.*/
    }

    @Test
    public void testGetTollsTotalCosts() {
        newTravel.addToll(new Toll());
        newTravel.addToll(new Toll());
        newTravel.addToll(new Toll());

        PriceManagement.updateCarCategoryPrice(CarCategory.CAT_01, 100.00);

        double expectedPrice = (PriceManagement.getCarCategoryPrice(newCar.getCategory()) * 3);
        System.out.println(expectedPrice);
        double obtainedPrice = PriceManagement.getTravelTollsTotalCost(newTravel);
        System.out.println(obtainedPrice);

        assertEquals(expectedPrice, obtainedPrice, 0.009);
        /*"0.009" was the selected delta to avoid differences
        when rounding prices.*/
    }

    @Test
    public void testGetTravelTotalCost() {
        newTravel.addToll(new Toll());
        newTravel.addToll(new Toll());
        newTravel.addToll(new Toll());

        PriceManagement.updateCarCategoryPrice(CarCategory.CAT_01, 100.00);

        double distanceToTravel = newTravel.getDistance();
        double fullTankPrice = PriceManagement.getFullFuelTankPrice(newCar);
        double carMaxDistance = newCar.getCarMaxDistance();
        double tollsTotalCost = PriceManagement.getTravelTollsTotalCost(newTravel);

        double expectedPrice = (distanceToTravel * fullTankPrice / carMaxDistance) + tollsTotalCost;
        double obtainedPrice = PriceManagement.getTravelTotalCost(newTravel);

        assertEquals(expectedPrice, obtainedPrice, 0.009);
        /*"0.009" was the selected delta to avoid differences
        when rounding prices.*/
    }
}
