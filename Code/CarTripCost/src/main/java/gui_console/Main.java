/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_console;

import java.util.Scanner;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class Main {

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
        int selection;

        while (!exitProgram) {
            System.out.println("<<<CAR-TRIP-PRICE>>>");
            space();
            System.out.println("1) New travel");
            System.out.println("2) Car management");
            System.out.println("3) Fuel management");
            System.out.println("4) Toll management");
            space();
            System.out.println("0) Exit");

            space();
            selection = selectOption();

            switch (selection) {
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        cleanScreen();
        mainMenu();
    }
}
