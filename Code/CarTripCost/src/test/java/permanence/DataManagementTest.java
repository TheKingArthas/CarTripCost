package permanence;

import domain.Car;
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
}
