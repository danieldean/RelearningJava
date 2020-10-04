package uk.danieldean;

/*
 * ShuntingYard
 *
 * Copyright (c) 2020 Daniel Dean <dd@danieldean.uk>.
 *
 * Licensed under The MIT License a copy of which you should have
 * received. If not, see:
 *
 * http://opensource.org/licenses/MIT
 */

import java.util.*;
import java.util.regex.*;

/** A basic implementation of the shunting-yard algorithm.
 *
 * Supports brackets, division, multiplication, subtraction, addition and negation. It expects a properly formatted
 * expression. Based on reading from:
 *
 * <url>https://condor.depaul.edu/ichu/csc415/notes/notes9/Infix.htm</url>
 *
 * And various other inspirations. It may be possible to extend support to other operators with a little additional
 * logic but this is beyond what I required.
 *
 * @author Daniel Dean <dd@danieldean.uk>
 */
public class ShuntingYard {

    /** Convert an infix based expression to postfix.
     *
     * This method can handle negation but will substitute the operator in these cases with <code>-u</code>.
     *
     * @param infix Infix expression.
     * @return Postfix expression.
     */
    public static List<String> toPostfix(String infix) {

        List<String> tokens = findAll(infix,"[+-/*()-]|\\d+\\.?\\d*");
        Stack<String> stack = new Stack<>();
        List<String> postfix = new ArrayList<>();
        boolean numberExpected = true;

        for(String token : tokens) {
            if(isNumber(token)) {
                postfix.add(token);
                numberExpected = false;
            } else if(token.equals("(")) {
                stack.push(token);
            } else if(token.equals(")")) {
                while(!stack.empty() && !stack.peek().equals("(")) {
                    postfix.add(stack.pop());
                }
                stack.pop();
            } else {
                if(numberExpected && token.equals("-")) {
                    token = "-u";
                }
                while (!stack.empty() && getPriority(token) < getPriority(stack.peek()) && !stack.peek().matches("[(|)]")) {
                    postfix.add(stack.pop());
                }
                stack.push(token);
                numberExpected = true;
            }
        }

        while(!stack.empty()) {
            postfix.add(stack.pop());
        }

        return postfix;

    }

    /** Evaluates a postfix based expression.
     *
     * Will treat the operator <code>-u</code> as negation.
     *
     * @param postfix Postfix expression.
     * @return Result of evaluating the expression.
     */
    public static double evaluatePostfix(List<String> postfix) {

        Stack<String> stack = new Stack<>();

        double a;
        double b;

        for(String token : postfix) {
            if(isNumber(token)) {
                stack.push(token);
            } else if(token.equals("-u")) {
                stack.push(String.valueOf(Double.parseDouble(stack.pop()) * -1));
            } else {
                b = Double.parseDouble(stack.pop());
                a = Double.parseDouble(stack.pop());
                switch(token) {
                    case "/": a /= b; break;
                    case "*": a *= b; break;
                    case "-": a -= b; break;
                    case "+":  a += b; break;
                }
                stack.push(String.valueOf(a));
            }
        }

        return Double.parseDouble(stack.pop());

    }

    /** Evaluates an infix based expression
     *
     * @param expression Infix expression.
     * @return Result of evaluating the expression.
     */
    public static double evaluate(String expression) {
        return evaluatePostfix(toPostfix(expression));
    }

    /** Evaluates an infix based expression.
     *
     * Returns a string formatted to be presentable.
     *
     * @param expression Infix expression.
     * @return Result of evaluating the expression.
     */
    public static String evaluateToString(String expression) {
        return toString(evaluatePostfix(toPostfix(expression)));
    }

    /** Finds all occurrences matching a regex.
     *
     * @param string String to search in.
     * @param regex Regex to match.
     * @return Matches split into a list.
     */
    private static List<String> findAll(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        List<String> list = new ArrayList<>();
        for (Matcher matcher = pattern.matcher(string); matcher.find();) {
            list.add(matcher.group());
        }
        return list;
    }

    /** Text a string to see if it is a number.
     *
     * @param string String to test.
     * @return <code>true</code> if the string is a number otherwise <code>false</code>.
     */
    private static boolean isNumber(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    /** Find the priority of an operator.
     *
     * @param operator String containing the operator.
     * @return Priority of the operator.
     */
    private static int getPriority(String operator) {
        switch(operator) {
            case "(": case ")": return 4;
            case "-u": return 3;
            case "/": case "*": return 2;
            case "-": case "+": return 1;
            default: return 0;
        }
    }

    /** Convert a number to a friendly formatted string.
     *
     * Formats to zero decimal places if it will not change the value of the number.
     *
     * @param d Number to convert.
     * @return Result as a string.
     */
    private static String toString(double d) {
        if(d == (int) d) {
            return String.valueOf((int) d);
        } else {
          return String.valueOf(d);
        }
    }

}
