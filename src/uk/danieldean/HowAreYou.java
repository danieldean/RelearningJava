package uk.danieldean;

/*
 * HowAreYou
 *
 * Copyright (c) 2020 Daniel Dean <dd@danieldean.uk>.
 */

import javax.swing.*;

public class HowAreYou {

    public static void main(String[] args) {

        // Ask for name and if 'you had a good day'.
        String name = JOptionPane.showInputDialog(null, "What is your name?");
        int response = JOptionPane.showConfirmDialog(null, "Did you have a good day?");

        // Check response. Yes returns zero so anything greater is bad.
        if(response > 0) {
            JOptionPane.showMessageDialog(null, "Oh that is terrible " + name + ".");
        } else {
            JOptionPane.showMessageDialog(null, "Good to hear " + name + ".");
        }

    }

}
