/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_console;

import java.util.Scanner;
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
                System.out.println("2) Select car");
                System.out.println("3) Edit current car");
            }
            space();
            System.out.println("0) Go back");

            space();
            int selection = selectOption();

            if (selection == 1 || selection == 0 || (systemHasCars && (selection == 2 || selection == 3))) {
                switch (selection) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 0:
                        mainMenu();
                        break;
                }
            } else {
                errorMessage = "\"" + selection + ")\" isn't a valid option. Please try again.";
            }
        }
    }

    public static void main(String[] args) {
        cleanScreen();
        tempDB = TempDB.getInstance();
        mainMenu();
    }
}
