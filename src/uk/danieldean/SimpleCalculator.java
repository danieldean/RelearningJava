package uk.danieldean;

/*
 * SimpleCalculator
 *
 * Copyright (c) 2020 Daniel Dean <dd@danieldean.uk>.
 */

import javax.swing.*;

public class SimpleCalculator {

    public static void main(String[] args) {

        // Ask for two numbers and an operator.
        int a = toInt(JOptionPane.showInputDialog(null, "Enter a number."));
        String operator = JOptionPane.showInputDialog(null, "What would you like to do?" + "\n" + "Enter: +, -, * or /");
        int b = toInt(JOptionPane.showInputDialog(null, "Enter another number."));

        // Determine operation and calculate.
        switch(operator) {
            case "+":
                a += b;
                break;
            case "-":
                a -= b;
                break;
            case "*":
                a *= b;
                break;
            case "/":
                a /= b;
                break;
            default:
                JOptionPane.showMessageDialog(null, "You did not enter a valid option.");
                return;
        }

        // Show the result.
        JOptionPane.showMessageDialog(null, "The result is: " + a);

    }

    private static int toInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch(NumberFormatException e){
            // Return zero if an error occurred.
            return 0;
        }
    }

}
