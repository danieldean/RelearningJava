package uk.danieldean;

/*
 * BetterCalculator
 *
 * Copyright (c) 2020 Daniel Dean <dd@danieldean.uk>.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BetterCalculator extends Frame implements ActionListener {

    // Text area, field and brackets button need to be updatable outside the constructor.
    private final JTextArea jtaHistory;
    private final JTextField jtfCalculation;
    private final JButton jbBracket;

    private BetterCalculator() {

        // Create frame and set exit option.
        JFrame jfBetterCalculator = new JFrame("BetterCalculator");
        jfBetterCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfBetterCalculator.setLayout(new BoxLayout(jfBetterCalculator.getContentPane(), BoxLayout.PAGE_AXIS));

        // Add text area to show calculation history.
        jtaHistory = new JTextArea();
        jtaHistory.setRows(5);
        jtaHistory.setFont(new Font("sans-serif", Font.PLAIN, 24));
        jfBetterCalculator.add(jtaHistory);

        jfBetterCalculator.add(new JSeparator());

        // Add text field for entry and result with enlarged font to top of the frame.
        jtfCalculation = new JTextField();
        jtfCalculation.setBorder(null);
        jtfCalculation.setFont(new Font("sans-serif", Font.PLAIN, 24));
        jfBetterCalculator.add(jtfCalculation);

        // Create a panel using grid layout to hold all the buttons. Add to the centre of the frame.
        JPanel jpButtons = new JPanel(new GridLayout(4, 4, 2, 2));
        JButton jbSeven = new JButton("7");
        jpButtons.add(jbSeven);
        JButton jbEight = new JButton("8");
        jpButtons.add(jbEight);
        JButton jbNine = new JButton("9");
        jpButtons.add(jbNine);
        JButton jbDivide = new JButton("/");
        jpButtons.add(jbDivide);
        JButton jbFour = new JButton("4");
        jpButtons.add(jbFour);
        JButton jbFive = new JButton("5");
        jpButtons.add(jbFive);
        JButton jbSix = new JButton("6");
        jpButtons.add(jbSix);
        JButton jbMultiply = new JButton("*");
        jpButtons.add(jbMultiply);
        JButton jbOne = new JButton("1");
        jpButtons.add(jbOne);
        JButton jbTwo = new JButton("2");
        jpButtons.add(jbTwo);
        JButton jbThree = new JButton("3");
        jpButtons.add(jbThree);
        JButton jbSubtract = new JButton("-");
        jpButtons.add(jbSubtract);
        JButton jbZero = new JButton("0");
        jpButtons.add(jbZero);
        JButton jbDot = new JButton(".");
        jpButtons.add(jbDot);
        jbBracket = new JButton("(");
        jpButtons.add(jbBracket);
        JButton jbAdd = new JButton("+");
        jpButtons.add(jbAdd);
        jfBetterCalculator.add(jpButtons);

        JPanel jpControls = new JPanel(new GridLayout(1,2,2,2));
        jpControls.setBorder(BorderFactory.createEmptyBorder(2,1,0,1));
        JButton jbEquals = new JButton("=");
        jpControls.add(jbEquals);
        JButton jbClear = new JButton("C");
        jpControls.add(jbClear);
        jfBetterCalculator.add(jpControls);

        // Add the action listener to the many buttons.
        jbSeven.addActionListener(this);
        jbEight.addActionListener(this);
        jbNine.addActionListener(this);
        jbDivide.addActionListener(this);
        jbFour.addActionListener(this);
        jbFive.addActionListener(this);
        jbSix.addActionListener(this);
        jbMultiply.addActionListener(this);
        jbOne.addActionListener(this);
        jbTwo.addActionListener(this);
        jbThree.addActionListener(this);
        jbSubtract.addActionListener(this);
        jbZero.addActionListener(this);
        jbDot.addActionListener(this);
        jbBracket.addActionListener(this);
        jbAdd.addActionListener(this);
        jbEquals.addActionListener(this);
        jbClear.addActionListener(this);

        // Size the frame, centre and set visible.
        jfBetterCalculator.setMinimumSize(new Dimension(300,400));
        jfBetterCalculator.pack();
        jfBetterCalculator.setLocationRelativeTo(null);
        jfBetterCalculator.setVisible(true);

    }

    public static void main(String[] args) {
        new BetterCalculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("=")) {
            // Do the calculation and update the text area and field.
            String result = ShuntingYard.evaluateToString(jtfCalculation.getText());
            jtaHistory.append(jtfCalculation.getText() + "=" + result + "\n");
            jtfCalculation.setText(result);
        } else if(e.getActionCommand().equals("C")) {
            // Clear the calculation.
            jtaHistory.setText("");
            jtfCalculation.setText("");
            jbBracket.setText("(");
        } else if(e.getSource() == jbBracket) {
            jtfCalculation.setText(jtfCalculation.getText() + e.getActionCommand());
            if(jbBracket.getText().equals("(")) {
                jbBracket.setText(")");
            } else {
                jbBracket.setText("(");
            }
        } else {
            // Add to the calculation.
            jtfCalculation.setText(jtfCalculation.getText() + e.getActionCommand());
        }
    }

}
