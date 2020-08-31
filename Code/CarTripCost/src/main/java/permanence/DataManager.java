package permanence;

import domain.*;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
abstract class Datamanager {

    abstract boolean hasCars();

    abstract void addCar(Car car);

    abstract ArrayList<Car> getCars();

    abstract Car getCarByLicensePlate(String licensePlate);

    abstract boolean hasTolls();

    abstract void addToll(Toll toll);

    abstract ArrayList<Toll> getTolls();

    abstract Toll getTollByCoordinates(Point coordinates);

    abstract Point getTollCoordinates(String name);

    abstract ArrayList<CarCategoryPrice> getHistoricalCarCategoriesPrices();

    abstract void updateCarCategoryPrice(CarCategory carCategory, double expectedPrice);

    abstract boolean hasFuels();

    abstract void addFuel(Fuel newFuel);

    abstract ArrayList<Fuel> getHistoricalFuelPrices();

    abstract void addTravel(Travel travel);

    abstract void addTollToTravel(Travel travel, Toll toll);

    /**
     * WARNING!: This method will wipe all data. This procedure cannot be
     * undone.
     */
    abstract void deleteAllDataBases();
}
