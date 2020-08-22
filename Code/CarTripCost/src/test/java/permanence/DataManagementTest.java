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
    
    @Test
    public void testAddCar() {
        Car newCar = new Car();
        
        DataManagement.addCar(newCar);
        
        assertTrue(TempDB.getInstance().cars.contains(newCar));
    }
}
