package websearch;

import java.util.Arrays;

public class SelectionSort {

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int indexLowest(int[] array, int start) {
        int lowIndex = start;

        for (int i = start; i < array.length; i++) {
            if (array[i] < array[lowIndex]) {
                lowIndex = i;
            }
        }

        return lowIndex;
    }

    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int j = indexLowest(array, i);
            swap(array, i, j);
        }
    }

    public static void main(String[] args) {
        int[] array = {9, 1, 4, 6, 2, 7, 3, 8, 5};
        System.out.println("before sorting: " + Arrays.toString(array));

        selectionSort(array);

        System.out.println("after sorting: " + Arrays.toString(array));
    }
}
