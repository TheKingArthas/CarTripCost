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
    public ArrayList<CarCategoryPrice> historicalCarCategoriesPrices;
    public ArrayList<Travel> travels;

    private TempDB() {
        cars = new ArrayList<Car>();
        historicalFuelPrices = new ArrayList<Fuel>();
        tolls = new ArrayList<Toll>();
        historicalCarCategoriesPrices = new ArrayList<CarCategoryPrice>();
        travels = new ArrayList<Travel>();
    }

    protected static TempDB getInstance() {
        if (tempDB == null) {
            tempDB = new TempDB();
        }
        return tempDB;
    }

    /**
     * WARNING!: This method will wipe all data. This procedure cannot be
     * undone.
     */
    protected static void deleteAllDataBases() {
        TempDB tempDB = TempDB.getInstance();

        tempDB.cars.clear();
        tempDB.historicalFuelPrices.clear();
        tempDB.tolls.clear();
        tempDB.historicalCarCategoriesPrices.clear();
        tempDB.travels.clear();
    }
}
