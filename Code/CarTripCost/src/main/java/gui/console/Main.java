/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.console;

import domain.Car;
import domain.CarCategory;
import domain.Fuel;
import domain.FuelType;
import domain.Toll;
import java.awt.Point;
import java.util.Date;
import java.util.Scanner;
import logic.PriceManagement;
import permanence.DataManagement;
import permanence.TempDB;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class Main {

    private static TempDB tempDB;

    //////General menu and screen options//////
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

    private static void mainMenu() {
        boolean exitProgram = false;
        String errorMessage = "";

        while (!exitProgram) {
            cleanScreen();
            System.out.println("<<<CAR-TRIP-PRICE>>>");
            System.out.println("<<<Main menu>>>");
            System.out.println(errorMessage);
            space();
            System.out.println("1) New travel");
            System.out.println("2) Car management");
            System.out.println("3) Fuel management");
            System.out.println("4) Toll management");
            space();
            System.out.println("0) Exit");

            space();
            int selection = selectOption();

            if (0 <= selection || selection <= 4) {
                switch (selection) {
                    case 2:
                        carManagement();
                        break;
                    case 3:
                        fuelManagement();
                        break;
                    case 4:
                        tollManagement();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                }
            } else {
                errorMessage = "\"" + selection + "\" isn't a valid option. Please try again.";
            }
        }
    }

    //////Fuel management//////
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

    private static void fuelManagement() {
        boolean systemHasFuels = !tempDB.historicalFuelPrices.isEmpty();
        String errorMessage = "";

        while (true) {
            cleanScreen();
            System.out.println("<<<CAR-TRIP-PRICE>>>");
            System.out.println("<<<Fuel management>>>");
            System.out.println(errorMessage);
            space();
            System.out.println("1) Update fuel price");
            if (systemHasFuels) {
                System.out.println("2) List fuel prices");
            }
            space();
            System.out.println("0) Go back");

            space();
            int selection = selectOption();

            if (selection == 1 || selection == 0 || (systemHasFuels && (selection == 2))) {
                switch (selection) {
                    case 1:
                        updateFuelPrice();
                        break;
                    case 2:
                        listFuelPrices();
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

    private static void updateFuelPrice() {
        Scanner scan = new Scanner(System.in);

        String errorMessage = "";
        char confirmation;
        boolean completedForm = false;
        boolean confirmed = false;

        FuelType fuelType = FuelType.GASOLINE;
        double price = 0;
        Date date = new Date();

        while (!confirmed) {
            cleanScreen();
            System.out.println("<<<CAR-TRIP-PRICE>>>");
            System.out.println("<<<Update fuel type>>>");
            System.out.println(errorMessage);

            if (!completedForm) {
                space();
                System.out.println("1) Fuel type: ");
                fuelType = fuelTypeSelector();
                System.out.println("");
                System.out.print("2) New price: ");
                price = scan.nextDouble();

                completedForm = true;
            }
            cleanScreen();
            System.out.println("1) Fuel type: " + fuelType);
            System.out.println("2) New price: $ " + price);
            space();
            System.out.println("Are these values correct?");
            confirmation = confirmSubMenu();

            switch (confirmation) {
                case 'Y':
                    PriceManagement.updateFuelTypePrice(fuelType, price);

                    confirmed = true;

                    System.out.print("Fuel price successfully updated. Press enter to continue.");
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

    private static void listFuelPrices() {
        Scanner scan = new Scanner(System.in);
        cleanScreen();

        System.out.println("FUEL | COST |");
        System.out.println(FuelType.GASOLINE + " | " + PriceManagement.getFuelPrice(FuelType.GASOLINE));
        System.out.println(FuelType.PREMIUM + " | " + PriceManagement.getFuelPrice(FuelType.PREMIUM));
        System.out.println(FuelType.SUPER + " | " + PriceManagement.getFuelPrice(FuelType.SUPER));
        space();

        System.out.print(
                "Press enter to continue.");
        scan.nextLine();
    }

    //////Car management//////
    private static CarCategory carCategorySelector() {
        Scanner scan = new Scanner(System.in);
        int selection = 0;
        CarCategory selectedCarCategory;

        while (selection < 1 || 7 < selection) {
            System.out.println("(1) " + CarCategory.CAT_01.description);
            System.out.println("(2) " + CarCategory.CAT_02.description);
            System.out.println("(3) " + CarCategory.CAT_03.description);
            System.out.println("(4) " + CarCategory.CAT_04.description);
            System.out.println("(5) " + CarCategory.CAT_05.description);
            System.out.println("(6) " + CarCategory.CAT_06.description);
            System.out.println("(7) " + CarCategory.CAT_07.description);

            selection = scan.nextInt();
        }

        if (selection == 1) {
            selectedCarCategory = CarCategory.CAT_01;
        } else if (selection == 2) {
            selectedCarCategory = CarCategory.CAT_02;
        } else if (selection == 3) {
            selectedCarCategory = CarCategory.CAT_03;
        } else if (selection == 4) {
            selectedCarCategory = CarCategory.CAT_04;
        } else if (selection == 5) {
            selectedCarCategory = CarCategory.CAT_05;
        } else if (selection == 6) {
            selectedCarCategory = CarCategory.CAT_06;
        } else {
            selectedCarCategory = CarCategory.CAT_07;
        }

        return selectedCarCategory;
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
        FuelType fuelType = FuelType.GASOLINE;
        double tankCapacity = 0;
        double efficiency = 0;
        CarCategory category = CarCategory.CAT_01;

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
                System.out.println("");
                System.out.println("7) Car category: ");
                category = carCategorySelector();

                completedForm = true;
            }
            cleanScreen();
            System.out.println("1) License plate: " + licensePlate);
            System.out.println("2) Brand: " + brand);
            System.out.println("3) Model: " + model);
            System.out.println("4) Fuel type: " + fuelType);
            System.out.println("5) Tank capacity " + tankCapacity + "(L)");
            System.out.println("6) Efficiency " + efficiency + "(Km/L): ");
            System.out.println("7) Car category: " + category);
            space();
            System.out.println("Are these values correct?");
            confirmation = confirmSubMenu();

            switch (confirmation) {
                case 'Y':
                    Car newCar = new Car();
                    newCar.setLicensePlate(licensePlate);
                    newCar.setBrand(brand);
                    newCar.setModel(model);
                    newCar.setFuelType(fuelType);
                    newCar.setTankCapacity(tankCapacity);
                    newCar.setEfficiency(efficiency);
                    newCar.setCategory(category);

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

    //////Toll management//////
    private static void tollManagement() {
        boolean systemHasTolls = !tempDB.tolls.isEmpty();
        String errorMessage = "";

        while (true) {
            cleanScreen();
            System.out.println("<<<CAR-TRIP-PRICE>>>");
            System.out.println("<<<Toll management>>>");
            System.out.println(errorMessage);
            space();
            System.out.println("1) Add toll");
            if (systemHasTolls) {
                System.out.println("2) List tolls");
                System.out.println("3) Update categorie price");
            }
            space();
            System.out.println("0) Go back");

            space();
            int selection = selectOption();

            if (selection == 1 || selection == 0 || (systemHasTolls && ((selection == 2) || selection == 3))) {
                switch (selection) {
                    case 1:
                        addToll();
                        break;
                    case 2:
                        listTolls();
                        break;
                    case 3:
                        updateCategoryPrice();
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

    private static void addToll() {
        Scanner scan = new Scanner(System.in);

        String errorMessage = "";
        char confirmation;
        boolean completedForm = false;
        boolean confirmed = false;

        String name = "";
        int x = 0;
        int y = 0;
        Point coordinates = new Point();

        while (!confirmed) {
            cleanScreen();
            System.out.println("<<<CAR-TRIP-PRICE>>>");
            System.out.println("<<<Add toll>>>");
            System.out.println(errorMessage);

            if (!completedForm) {
                space();
                System.out.print("1) Toll name: ");
                name = scan.nextLine();
                System.out.println("");
                System.out.println("2) Toll coordinates: ");
                System.out.print("x: ");
                x = scan.nextInt();
                System.out.print("y: ");
                y = scan.nextInt();
                coordinates = new Point(x, y);

                completedForm = true;
            }
            cleanScreen();
            System.out.println("1) Toll name: " + name);
            System.out.println("2) Coordinates: x: " + x + "| y: " + y);
            space();
            System.out.println("Are these values correct?");
            confirmation = confirmSubMenu();

            switch (confirmation) {
                case 'Y':
                    Toll newToll = new Toll();
                    newToll.setName(name);
                    newToll.setCoordinates(coordinates);

                    DataManagement.addToll(newToll);

                    confirmed = true;

                    System.out.print("Toll successfully added. Press enter to continue.");
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

    private static void listTolls() {
        Scanner scan = new Scanner(System.in);
        cleanScreen();

        System.out.println("NAME | COORDINATES |");
        for (Toll t : tempDB.tolls) {
            System.out.println("| " + t.getName() + " | x: " + t.getCoordinates().x + " | y: " + t.getCoordinates().y);
        }
        space();
        System.out.print("Press enter to continue.");
        scan.nextLine();
    }

    private static void updateCategoryPrice() {
        Scanner scan = new Scanner(System.in);

        String errorMessage = "";
        char confirmation;
        boolean completedForm = false;
        boolean confirmed = false;

        CarCategory category = CarCategory.CAT_01;
        double price = 0;

        while (!confirmed) {
            cleanScreen();
            System.out.println("<<<CAR-TRIP-PRICE>>>");
            System.out.println("<<<Update car categorie toll price>>>");
            System.out.println(errorMessage);

            if (!completedForm) {
                space();
                System.out.println("1) Car category: ");
                category = carCategorySelector();
                System.out.println("");
                System.out.print("2) New price: ");
                price = scan.nextDouble();

                completedForm = true;
            }
            cleanScreen();
            System.out.println("1) Car category: " + category);
            System.out.println("2) New price: $ " + price);
            space();
            System.out.println("Are these values correct?");
            confirmation = confirmSubMenu();

            switch (confirmation) {
                case 'Y':
                    PriceManagement.updateCarCategoryPrice(category, price);

                    confirmed = true;

                    System.out.print("Fuel price successfully updated. Press enter to continue.");
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

}
