package demo;

import domain.*;
import java.awt.Point;
import logic.PriceManagement;
import permanence.OnMemoryDataManager;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class Demo {

    /**
     * This method load test data into the software.
     */
    public static void loadData() throws InterruptedException {

        OnMemoryDataManager dataManager = new OnMemoryDataManager();

        Car car01 = new Car();
        car01.setLicensePlate("AAA1111");
        car01.setBrand("Dodge");
        car01.setModel("Charger");
        car01.setCategory(CarCategory.CAT_01);
        car01.setFuelType(FuelType.GASOLINE);
        car01.setTankCapacity(30);
        car01.setEfficiency(10);
        dataManager.addCar(car01);

        Car car02 = new Car();
        car02.setLicensePlate("BBB2222");
        car02.setBrand("General Motors");
        car02.setModel("New Look");
        car02.setCategory(CarCategory.CAT_02);
        car02.setFuelType(FuelType.SUPER);
        car02.setTankCapacity(70);
        car02.setEfficiency(8);
        dataManager.addCar(car02);

        Car car03 = new Car();
        car03.setLicensePlate("CCC3333");
        car03.setBrand("Batmobile");
        car03.setModel("Tumbler");
        car03.setCategory(CarCategory.CAT_03);
        car03.setFuelType(FuelType.PREMIUM);
        car03.setTankCapacity(30);
        car03.setEfficiency(22);
        dataManager.addCar(car03);

        Car car04 = new Car();
        car04.setLicensePlate("DDD4444");
        car04.setBrand("General Motors");
        car04.setModel("Old Look");
        car04.setCategory(CarCategory.CAT_04);
        car04.setFuelType(FuelType.GASOLINE);
        car04.setTankCapacity(60);
        car04.setEfficiency(6);
        dataManager.addCar(car04);

        Car car05 = new Car();
        car05.setLicensePlate("EEE5555");
        car05.setBrand("Mack");
        car05.setModel("LR");
        car05.setCategory(CarCategory.CAT_05);
        car05.setFuelType(FuelType.SUPER);
        car05.setTankCapacity(50);
        car05.setEfficiency(18);
        dataManager.addCar(car05);

        Car car06 = new Car();
        car06.setLicensePlate("FFF6666");
        car06.setBrand("Caterpilar");
        car06.setModel("Little monster");
        car06.setCategory(CarCategory.CAT_06);
        car06.setFuelType(FuelType.PREMIUM);
        car06.setTankCapacity(90);
        car06.setEfficiency(4);
        dataManager.addCar(car06);

        Car car07 = new Car();
        car07.setLicensePlate("GGG7777");
        car07.setBrand("Caterpilar");
        car07.setModel("Big monster");
        car07.setCategory(CarCategory.CAT_07);
        car07.setFuelType(FuelType.GASOLINE);
        car07.setTankCapacity(30);
        car07.setEfficiency(10);
        dataManager.addCar(car07);

        PriceManagement.updateFuelTypePrice(FuelType.GASOLINE, 60);
        Thread.sleep(200);
        PriceManagement.updateFuelTypePrice(FuelType.PREMIUM, 57);
        Thread.sleep(200);
        PriceManagement.updateFuelTypePrice(FuelType.SUPER, 55);
        Thread.sleep(200);

        PriceManagement.updateCarCategoryPrice(CarCategory.CAT_01, 125);
        Thread.sleep(200);
        PriceManagement.updateCarCategoryPrice(CarCategory.CAT_02, 130);
        Thread.sleep(200);
        PriceManagement.updateCarCategoryPrice(CarCategory.CAT_03, 190);
        Thread.sleep(200);
        PriceManagement.updateCarCategoryPrice(CarCategory.CAT_04, 195);
        Thread.sleep(200);
        PriceManagement.updateCarCategoryPrice(CarCategory.CAT_05, 200);
        Thread.sleep(200);
        PriceManagement.updateCarCategoryPrice(CarCategory.CAT_06, 205);
        Thread.sleep(200);
        PriceManagement.updateCarCategoryPrice(CarCategory.CAT_07, 385);
        Thread.sleep(200);

        Toll toll01 = new Toll();
        toll01.setName("Route 1 km 23,500 - Santa Lucía");
        toll01.setCoordinates(new Point(10, 20));
        dataManager.addToll(toll01);

        Toll toll02 = new Toll();
        toll02.setName("Route 3 km 284,400 - Mercedes");
        toll02.setCoordinates(new Point(45, 37));
        dataManager.addToll(toll02);

        Toll toll03 = new Toll();
        toll03.setName("Route 5 km 246,350 - Centenario");
        toll03.setCoordinates(new Point(99, 80));
        dataManager.addToll(toll03);
    }
}
