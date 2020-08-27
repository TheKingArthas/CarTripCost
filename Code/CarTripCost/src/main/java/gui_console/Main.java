/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_console;

import domain.Car;
import domain.CarCategory;
import domain.FuelType;
import java.util.Scanner;
import permanence.DataManagement;
import permanence.TempDB;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class Main {

    private static TempDB tempDB;

    private static void cleanScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }

    private static void space() {
        for (int i = 0; i < 3; i++) {
            System.out.println("");
        }
    }

    private static int selectOption() {
        System.out.print("Please select an option:");

        Scanner scan = new Scanner(System.in);
        int selecction = scan.nextInt();

        return selecction;
    }

    private static char confirmSubMenu() {
        char confirmation;
        Scanner scan = new Scanner(System.in);

        System.out.println("Yes (Y) | No (N) | Back to main menu (M)");

        confirmation = scan.nextLine().charAt(0);
        confirmation = java.lang.Character.toUpperCase(confirmation);

        return confirmation;
    }

    private static FuelType fuelTypeSelector() {
        Scanner scan = new Scanner(System.in);
        int selection = 0;
        FuelType selectedFuelType;

        while (selection < 1 || 3 < selection) {
            System.out.println("(1) Gasoline | (2) Premium | (3) Super");
            selection = scan.nextInt();
        }

        if (selection == 1) {
            selectedFuelType = FuelType.GASOLINE;
        } else if (selection == 2) {
            selectedFuelType = FuelType.PREMIUM;
        } else {
            selectedFuelType = FuelType.SUPER;
        }

        return selectedFuelType;
    }

    private static void mainMenu() {
        boolean exitProgram = false;

        while (!exitProgram) {
            cleanScreen();
            System.out.println("<<<CAR-TRIP-PRICE>>>");
            space();
            System.out.println("1) New travel");
            System.out.println("2) Car management");
            System.out.println("3) Fuel management");
            System.out.println("4) Toll management");
            space();
            System.out.println("0) Exit");

            space();
            int selection = selectOption();

            switch (selection) {
                case 2:
                    carManagement();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }

    private static void carManagement() {
        boolean systemHasCars = !tempDB.cars.isEmpty();
        String errorMessage = "";

        while (true) {
            cleanScreen();
            System.out.println("<<<CAR-TRIP-PRICE>>>");
            System.out.println("<<<Car management>>>");
            System.out.println(errorMessage);
            space();
            System.out.println("1) Add new car");
            if (systemHasCars) {
                System.out.println("2) List cars");
            }
            space();
            System.out.println("0) Go back");

            space();
            int selection = selectOption();

            if (selection == 1 || selection == 0 || (systemHasCars && (selection == 2))) {
                switch (selection) {
                    case 1:
                        addNewCar();
                        break;
                    case 2:
                        listCars();
                        break;
                    case 3:
                        break;
                    case 0:
                        mainMenu();
                        break;
                }
            } else {
                errorMessage = "\"" + selection + "\" isn't a valid option. Please try again.";
            }
        }
    }

    private static void addNewCar() {
        Scanner scan = new Scanner(System.in);

        String errorMessage = "";
        char confirmation;
        boolean completedForm = false;
        boolean confirmed = false;

        String licensePlate = "";
        String brand = "";
        String model = "";
        FuelType fuelType;
        double tankCapacity = 0;
        double efficiency = 0;
        CarCategory category;

        while (!confirmed) {
            cleanScreen();
            System.out.println("<<<CAR-TRIP-PRICE>>>");
            System.out.println("<<<Add car>>>");
            System.out.println(errorMessage);

            if (!completedForm) {
                space();
                System.out.print("1) License plate: ");
                licensePlate = scan.nextLine();
                System.out.println("");
                System.out.print("2) Brand: ");
                brand = scan.nextLine();
                System.out.println("");
                System.out.print("3) Model: ");
                model = scan.nextLine();
                System.out.println("");
                System.out.println("4) Fuel type: ");
                fuelType = fuelTypeSelector();
                System.out.println("");
                System.out.print("5) Tank capacity (L): ");
                tankCapacity = scan.nextDouble();
                System.out.println("");
                System.out.print("6) Efficiency (Km/L): ");
                efficiency = scan.nextDouble();
                completedForm = true;
            }

            space();
            System.out.println("Are these values correct?");
            confirmation = confirmSubMenu();

            switch (confirmation) {
                case 'Y':
                    Car newCar = new Car();
                    newCar.setLicensePlate(licensePlate);
                    newCar.setBrand(brand);
                    newCar.setModel(model);
                    newCar.setTankCapacity(tankCapacity);
                    newCar.setEfficiency(efficiency);
                    DataManagement.addCar(newCar);

                    confirmed = true;

                    System.out.print("Car successfully added. Press enter to continue.");
                    scan.nextLine();
                    scan.nextLine();
                    break;
                case 'N':
                    errorMessage = "";
                    completedForm = false;
                    scan.nextLine();
                    break;
                case 'M':
                    confirmed = true;
                    break;
                default:
                    errorMessage = "\"" + confirmation + "\" isn't a valid option. Please try again.";
                    break;
            }
        }
        mainMenu();
    }

    private static void listCars() {
        Scanner scan = new Scanner(System.in);
        cleanScreen();

        System.out.println("LICENSE PLATE | BRAND | MODEL |");
        for (Car c : tempDB.cars) {
            System.out.println("| " + c.getLicensePlate() + " | " + c.getBrand() + " | " + c.getModel() + " |");
        }
        space();
        System.out.print("Press enter to continue.");
        scan.nextLine();
    }

    public static void main(String[] args) {
        cleanScreen();
        tempDB = TempDB.getInstance();
        mainMenu();
    }
}
