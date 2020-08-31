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
public class OnMemoryDataManagerTest {

    Car newCar;
    String licensePlate;
    double fuelTankCapacity;
    Fuel newFuel;
    double price;
    double carEfficiency;
    Point origin;
    Point destiny;
    Travel newTravel;
    OnMemoryDataManager dataManager;

    @Before
    public void setUp() {
        dataManager = new OnMemoryDataManager();

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

        newTravel = new Travel();
        origin = new Point(10, 20);
        destiny = new Point(30, 40);
        newTravel.setOrigin(origin);
        newTravel.setDestiny(destiny);

        dataManager.addCar(newCar);
        dataManager.addFuel(newFuel);
        dataManager.addTravel(newTravel);

    }

    @After
    public void dataBaseCleanUp() {
        TempDB tempDB = TempDB.getInstance();

        tempDB.cars.clear();
        tempDB.historicalFuelPrices.clear();
        tempDB.tolls.clear();
        tempDB.historicalCarCategoriesPrices.clear();
        tempDB.travels.clear();
    }

    @Test
    public void testAddCar() {
        assertTrue(TempDB.getInstance().cars.contains(newCar));
    }

    @Test
    public void testGetCarByLicensePlate() {
        Car obtainedCar = dataManager.getCarByLicensePlate(licensePlate);

        assertEquals(newCar, obtainedCar);
    }

    @Test
    public void testAddFuel() {
        assertTrue(TempDB.getInstance().historicalFuelPrices.contains(newFuel));
    }

    @Test
    public void testGetCarMaxDistance() {
        double expectedDistance = fuelTankCapacity * carEfficiency;
        double obtainedDistance = newCar.getMaxDistance();

        assertEquals(expectedDistance, obtainedDistance, 0.009);
    }

    @Test
    public void testGetTollByCoordinates() {
        Toll expectedToll = new Toll();
        Point tollCoordinates = new Point(10, 20);
        expectedToll.setCoordinates(tollCoordinates);

        dataManager.addToll(expectedToll);

        Toll obtainedToll = dataManager.getTollByCoordinates(tollCoordinates);

        assertEquals(expectedToll, obtainedToll);
    }

    @Test
    public void testGetTollCoordinatesByName() {
        Toll expectedToll = new Toll();
        Point tollCoordinates = new Point(10, 20);
        String tollName = "Toll01";
        expectedToll.setName(tollName);
        expectedToll.setCoordinates(tollCoordinates);

        dataManager.addToll(expectedToll);

        Point obtainedCoordinates = dataManager.getTollCoordinates(tollName);

        assertEquals(tollCoordinates, obtainedCoordinates);
    }

    @Test
    public void testGetTravelDistance() {
        double expectedDistance = origin.distance(destiny);
        double obtainedDistance = newTravel.getDistance();

        assertEquals(expectedDistance, obtainedDistance, 0.009);
    }

    @Test
    public void testAddTollToTravel() {
        Toll newToll = new Toll();

        newTravel.addToll(newToll);

        assertTrue(newTravel.getTolls().contains(newToll));
    }
}
