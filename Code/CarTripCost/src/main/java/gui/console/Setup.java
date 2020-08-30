/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.console;

import domain.CarCategory;
import domain.FuelType;
import java.util.Scanner;
import logic.PriceManagement;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class Setup {

    public static void start() {
        Scanner scan = new Scanner(System.in);

        System.out.println("<<<CAR-TRIP-PRICE>>>");
        System.out.println("<<<Setup>>>");
        System.out.println("");
        System.out.println("Welcome! Please enter the following prices before starting using the program:");
        System.out.println("");
        System.out.println("<<<Fuel prices>>>");
        System.out.print(FuelType.GASOLINE + ": $");
        PriceManagement.updateFuelTypePrice(FuelType.GASOLINE, scan.nextDouble());
        System.out.print(FuelType.PREMIUM + ": $");
        PriceManagement.updateFuelTypePrice(FuelType.PREMIUM, scan.nextDouble());
        System.out.print(FuelType.SUPER + ": $");
        PriceManagement.updateFuelTypePrice(FuelType.SUPER, scan.nextDouble());
        System.out.println("");
        System.out.println("");
        System.out.println("");

        System.out.println("<<<Toll cars categories prices>>>");
        System.out.print(CarCategory.CAT_01 + " (" + CarCategory.CAT_01.description + ": $");
        PriceManagement.updateCarCategoryPrice(CarCategory.CAT_01, scan.nextDouble());
        System.out.print(CarCategory.CAT_02 + " (" + CarCategory.CAT_02.description + ": $");
        PriceManagement.updateCarCategoryPrice(CarCategory.CAT_02, scan.nextDouble());
        System.out.print(CarCategory.CAT_03 + " (" + CarCategory.CAT_03.description + ": $");
        PriceManagement.updateCarCategoryPrice(CarCategory.CAT_03, scan.nextDouble());
        System.out.print(CarCategory.CAT_04 + " (" + CarCategory.CAT_04.description + ": $");
        PriceManagement.updateCarCategoryPrice(CarCategory.CAT_04, scan.nextDouble());
        System.out.print(CarCategory.CAT_05 + " (" + CarCategory.CAT_05.description + ": $");
        PriceManagement.updateCarCategoryPrice(CarCategory.CAT_05, scan.nextDouble());
        System.out.print(CarCategory.CAT_06 + " (" + CarCategory.CAT_06.description + ": $");
        PriceManagement.updateCarCategoryPrice(CarCategory.CAT_06, scan.nextDouble());
        System.out.print(CarCategory.CAT_07 + " (" + CarCategory.CAT_07.description + ": $");
        PriceManagement.updateCarCategoryPrice(CarCategory.CAT_07, scan.nextDouble());

        System.out.println("Thanks! Please press enter to continue.");
        scan.nextLine();

    }
}
