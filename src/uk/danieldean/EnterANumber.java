package uk.danieldean;

/*
 * EnterANumber
 *
 * Copyright (c) 2020 Daniel Dean <dd@danieldean.uk>.
 */

import javax.swing.*;

public class EnterANumber {

    public static void main(String[] main) {

        // Running total and intermediate string before conversion to integer.
        int total = 0;
        String toConvert;

        while(true) {

            // Request a number.
            toConvert = JOptionPane.showInputDialog(null, "Enter a number or press cancel to finish.");

            if(toConvert == null || toConvert.equals("")) {
                // Exit the loop.
                break;
            } else {
                // Assumes a number has been entered. Will throw an error if not.
                total += Integer.parseInt(toConvert);
            }

        }

        // Show the result.
        JOptionPane.showMessageDialog(null, "The total is: " + total);

    }

}
