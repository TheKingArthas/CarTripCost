package permanence;

import domain.*;
import java.awt.Point;
import java.util.Calendar;
import java.util.Date;
import org.apache.xerces.impl.dv.xs.FullDVFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class DataManagementTest {

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
    public void testAddCar() {
        assertTrue(TempDB.getInstance().cars.contains(newCar));
    }

    @Test
    public void testGetCarByLicensePlate() {
        Car obtainedCar = DataManagement.getCarByLicensePlate(licensePlate);

        assertEquals(newCar, obtainedCar);
    }

    @Test
    public void testAddFuel() {
        assertTrue(TempDB.getInstance().historicalFuelPrices.contains(newFuel));
    }

    @Test
    public void testGetFuelGasolineCurrentPrice() {
        double obtainedPrice = DataManagement.getFuelPrice(FuelType.GASOLINE);

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

        double obtainedPrice = DataManagement.getFuelPrice(FuelType.GASOLINE);

        assertEquals(newPrice, obtainedPrice, 0.009);
        /*"0.009" was the selected delta to avoid differences 
        when rounding prices.*/
    }

    @Test
    public void testGetCarFullTankPrice() {
        double expectedPrice = fuelTankCapacity * price;
        double obtainedPrice = DataManagement.getFullFuelTankPrice(newCar, FuelType.GASOLINE);

        assertEquals(expectedPrice, obtainedPrice, 0.009);
        /*"0.009" was the selected delta to avoid differences 
        when rounding prices.*/
    }

    @Test
    public void testGetCarMaxDistance() {
        double expectedDistance = fuelTankCapacity * carEfficiency;
        double obtainedDistance = newCar.getCarMaxDistance();

        assertEquals(expectedDistance, obtainedDistance, 0.009);
    }

    @Test
    public void testGetTollByCoordinates() {
        Toll expectedToll = new Toll();
        Point tollCoordinates = new Point(10, 20);
        expectedToll.setCoordinates(tollCoordinates);

        DataManagement.addToll(expectedToll);

        Toll obtainedToll = DataManagement.getTollByCoordinates(tollCoordinates);

        assertEquals(expectedToll, obtainedToll);
    }

    @Test
    public void testGetTollCoordinatesByName() {
        Toll expectedToll = new Toll();
        Point tollCoordinates = new Point(10, 20);
        String tollName = "Toll01";
        expectedToll.setName(tollName);
        expectedToll.setCoordinates(tollCoordinates);

        DataManagement.addToll(expectedToll);

        Point obtainedCoordinates = DataManagement.getTollCoordinates(tollName);

        assertEquals(tollCoordinates, obtainedCoordinates);
    }

    @Test
    public void testUpdateCarCategoryPrice() {
        double expectedPrice = 100.00;
        DataManagement.updateCarCategoryPrice(CarCategory.CAT_01, expectedPrice);

        double obtainedPrice = DataManagement.getCarCategoryPrice(CarCategory.CAT_01);

        assertEquals(expectedPrice, obtainedPrice, 0.009);
        /*"0.009" was the selected delta to avoid differences 
        when rounding prices.*/
    }
}
