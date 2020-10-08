package uk.danieldean;

/*
 * BubbleSort
 *
 * Copyright (c) 2020 Daniel Dean <dd@danieldean.uk>.
 *
 * Licensed under The MIT License a copy of which you should have
 * received. If not, see:
 *
 * http://opensource.org/licenses/MIT
 */

/** Basic sorting of ints using the bubble sort algorithm.
 *
 * Based on reading of the Wikipedia entry:
 *
 * <url>https://en.wikipedia.org/wiki/Bubble_sort</url>
 *
 * @author Daniel Dean <dd@danieldean.uk>
 */
public class BubbleSort {

    /** Random test of the bubble sort.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {

        // Generate an array of random length containing random ints.
        int[] list = randomInts();

        // Print the array of random ints as is.
        System.out.println(toString(list));

        // Print the sorted array of ints.
        System.out.println(toString(sortInts(list)));

    }

    /** Sort an array of ints into descending order using the bubble sort algorithm.
     *
     * @param list Array of ints to sort.
     * @return Sorted array of ints.
     */
    public static int[] sortInts(int[] list) {

        int temp;
        int swapped;
        int n = list.length;

        while(n > 0) {
            swapped = 0;
            for (int i = 0; i < n - 1; i++) {
                if (list[i] < list[i + 1]) {
                    temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    swapped = i + 1;
                }
            }
            n = swapped;
        }

        return list;

    }

    private static String toString(int[] list) {
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < list.length; i++) {
            sb.append(list[i]);
            if(i < list.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static int[] randomInts() {
        int[] list = new int[(int) (Math.random() * 19 + 1)];
        for(int i = 0; i < list.length; i++) {
            list[i] = (int) (Math.random() * 100 - 50);
        }
        return list;
    }

}
