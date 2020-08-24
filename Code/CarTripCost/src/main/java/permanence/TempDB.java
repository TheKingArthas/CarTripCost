package permanence;

import domain.*;
import java.util.ArrayList;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class TempDB {

    private static TempDB tempDB = null;
    public ArrayList<Car> cars;
    public ArrayList<Fuel> historicalFuelPrices;
    public ArrayList<Toll> tolls;

    private TempDB() {
        cars = new ArrayList<Car>();
        historicalFuelPrices = new ArrayList<Fuel>();
        tolls = new ArrayList<Toll>();
    }

    public static TempDB getInstance() {
        if (tempDB == null) {
            tempDB = new TempDB();
        }
        return tempDB;
    }
}
