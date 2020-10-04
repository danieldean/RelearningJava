package uk.danieldean;

/*
 * HelloName
 *
 * Copyright (c) 2020 Daniel Dean <dd@danieldean.uk>.
 */

import javax.swing.*;

public class HelloName {

    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog(null,"What is your name?");
        JOptionPane.showMessageDialog(null,"Hello " + name + "!");
    }

}
