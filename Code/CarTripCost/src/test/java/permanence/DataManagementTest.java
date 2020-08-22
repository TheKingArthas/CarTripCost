package permanence;

import domain.*;
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

    @Before
    public void setUp() {
        newCar = new Car();
    }

    @After
    public void dataBaseCleanUp() {
        TempDB tempDB = TempDB.getInstance();

        tempDB.cars.clear();
        tempDB.historicalFuelPrices.clear();
    }

    @Test
    public void testAddCar() {
        newCar = new Car();

        DataManagement.addCar(newCar);

        assertTrue(TempDB.getInstance().cars.contains(newCar));
    }

    @Test
    public void testGetCarByLicensePlate() {
        newCar = new Car();
        String licensePlate = "ABC1234";
        newCar.setLicensePlate(licensePlate);

        DataManagement.addCar(newCar);

        Car obtainedCar = DataManagement.getCarByLicensePlate(licensePlate);

        assertEquals(newCar, obtainedCar);
    }

    @Test
    public void testAddFuel() {
        Fuel newFuel = new Fuel();

        DataManagement.addFuel(newFuel);

        assertTrue(TempDB.getInstance().historicalFuelPrices.contains(newFuel));
    }

    @Test
    public void testGetFuelGasolineCurrentCost() {
        Fuel newFuel = new Fuel();
        double cost = 12.34;
        newFuel.setType(FuelType.GASOLINE);
        newFuel.setCost(cost);
        newFuel.setCostUpdateDate(new Date());
        DataManagement.addFuel(newFuel);

        double obtainedCost = DataManagement.getFuelCost(FuelType.GASOLINE);

        assertEquals(cost, obtainedCost, 0.009);
        /*"0.009" was the selected delta to avoid differences 
        when rounding prices.*/
    }
}
