package logic;

import domain.*;
import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import permanence.DataManagement;
import permanence.DataManagementTest;
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

    @Before
    public void setUp() {
        newCar = new Car();
        licensePlate = "ABC1234";
        newCar.setLicensePlate(licensePlate);
        fuelTankCapacity = 40.00;
        newCar.setTankCapacity(fuelTankCapacity);
        carEfficiency = 16;
        newCar.setEfficiency(carEfficiency);

        newFuel = new Fuel();
        price = 12.34;
        newFuel.setType(FuelType.GASOLINE);
        newFuel.setPrice(price);
        newFuel.setPriceUpdateDate(new Date());

        DataManagement.addCar(newCar);
        DataManagement.addFuel(newFuel);
    }

    @After
    public void dataBaseCleanUp() {
        TempDB tempDB = TempDB.getInstance();

        tempDB.cars.clear();
        tempDB.historicalFuelPrices.clear();
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
        double obtainedPrice = PriceManagement.getFullFuelTankPrice(newCar, FuelType.GASOLINE);
        assertEquals(expectedPrice, obtainedPrice, 0.009);
        /*"0.009" was the selected delta to avoid differences
        when rounding prices.*/
    }

}
