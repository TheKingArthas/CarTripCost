package permanence;

import domain.*;
import java.awt.Point;
import java.util.Date;
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
        tempDB.tolls.clear();
        tempDB.historicalCarCateogriesPrices.clear();
        tempDB.travels.clear();
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
    public void testGetTravelDistance() {

        Travel travel = new Travel();

        Point origin = new Point(10, 20);
        Point destiny = new Point(30, 40);
        travel.setOrigin(origin);
        travel.setDestiny(destiny);

        DataManagement.addTravel(travel);

        double expectedDistance = origin.distance(destiny);
        double obtainedDistance = travel.getDistance();

        assertEquals(expectedDistance, obtainedDistance, 0.009);
    }
}
