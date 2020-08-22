package permanence;

import domain.*;
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
    Fuel newFuel;
    double cost;

    @Before
    public void setUp() {
        newCar = new Car();
        licensePlate = "ABC1234";
        newCar.setLicensePlate(licensePlate);

        newFuel = new Fuel();
        cost = 12.34;
        newFuel.setType(FuelType.GASOLINE);
        newFuel.setCost(cost);
        newFuel.setCostUpdateDate(new Date());
    }

    @After
    public void dataBaseCleanUp() {
        TempDB tempDB = TempDB.getInstance();

        tempDB.cars.clear();
        tempDB.historicalFuelPrices.clear();
    }

    @Test
    public void testAddCar() {
        DataManagement.addCar(newCar);

        assertTrue(TempDB.getInstance().cars.contains(newCar));
    }

    @Test
    public void testGetCarByLicensePlate() {
        DataManagement.addCar(newCar);

        Car obtainedCar = DataManagement.getCarByLicensePlate(licensePlate);

        assertEquals(newCar, obtainedCar);
    }

    @Test
    public void testAddFuel() {
        DataManagement.addFuel(newFuel);

        assertTrue(TempDB.getInstance().historicalFuelPrices.contains(newFuel));
    }

    @Test
    public void testGetFuelGasolineCurrentCost() {

        DataManagement.addFuel(newFuel);

        double obtainedCost = DataManagement.getFuelCost(FuelType.GASOLINE);

        assertEquals(cost, obtainedCost, 0.009);
        /*"0.009" was the selected delta to avoid differences 
        when rounding prices.*/
    }

    @Test
    public void testGetFuelMostUpToDateCost() {
        Fuel olderFuel = new Fuel();
        double oldCost = 9.87;
        olderFuel.setType(FuelType.GASOLINE);
        olderFuel.setCost(oldCost);
        Date yesterday = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(yesterday);
        c.add(Calendar.DATE, -1);
        yesterday = c.getTime();
        olderFuel.setCostUpdateDate(yesterday);

        double newCost = 12.34;
        newFuel.setType(FuelType.GASOLINE);
        newFuel.setCost(newCost);
        newFuel.setCostUpdateDate(new Date());

        DataManagement.addFuel(newFuel);

        double obtainedCost = DataManagement.getFuelCost(FuelType.GASOLINE);

        assertEquals(newCost, obtainedCost, 0.009);
        /*"0.009" was the selected delta to avoid differences 
        when rounding prices.*/
    }

}
