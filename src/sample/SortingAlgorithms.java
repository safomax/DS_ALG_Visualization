package sample;

import javafx.scene.paint.Paint;

import static sample.Main.*;

public class SortingAlgorithms {

    public static void BubbleSort(RectangleObject[] myRectangles) throws InterruptedException {

        bubbleSort.setDisable(true);
        insertionSort.setDisable(true);
        selectionSort.setDisable(true);
        quickSort.setDisable(true);
        mergeSort.setDisable(true);


        for (int i = 0; i < myRectangles.length; i++) {

            for (int j = 0; j < myRectangles.length - i - 1; j++) {

                if (myRectangles[j].rectangle.getHeight() > myRectangles[j + 1].rectangle.getHeight()) {

                    double temp = myRectangles[j].rectangle.getHeight();
                    Paint tempColor =  myRectangles[j].rectangle.getFill();

                    myRectangles[j].rectangle.setHeight(myRectangles[j + 1].rectangle.getHeight());
                    myRectangles[j].rectangle.setFill(myRectangles[j + 1].rectangle.getFill());

                    myRectangles[j + 1].rectangle.setHeight(temp);
                    myRectangles[j + 1].rectangle.setFill(tempColor);
                }
                Thread.sleep(20);
            }
        }

        bubbleSort.setDisable(false);
        insertionSort.setDisable(false);
        selectionSort.setDisable(false);
        quickSort.setDisable(false);
        mergeSort.setDisable(false);

    }

    public static void InsertionSort(RectangleObject[] myRectangles) throws InterruptedException {

        bubbleSort.setDisable(true);
        insertionSort.setDisable(true);
        selectionSort.setDisable(true);
        quickSort.setDisable(true);
        mergeSort.setDisable(true);

        for (int i = 1; i < myRectangles.length; i++) {

            int j = i;

            while (j > 0 && myRectangles[j - 1].rectangle.getHeight() > myRectangles[j].rectangle.getHeight()) {

                double temp = myRectangles[j].rectangle.getHeight();
                Paint tempColor =  myRectangles[j].rectangle.getFill();

                myRectangles[j].rectangle.setHeight(myRectangles[j - 1].rectangle.getHeight());
                myRectangles[j].rectangle.setFill(myRectangles[j - 1].rectangle.getFill());

                myRectangles[j - 1].rectangle.setHeight(temp);
                myRectangles[j - 1].rectangle.setFill(tempColor);

                j = j - 1;

                Thread.sleep(20);
            }
        }

        bubbleSort.setDisable(false);
        insertionSort.setDisable(false);
        selectionSort.setDisable(false);
        quickSort.setDisable(false);
        mergeSort.setDisable(false);
    }

    public static void SelectionSort(RectangleObject[] myRectangles) throws InterruptedException {

        bubbleSort.setDisable(true);
        insertionSort.setDisable(true);
        selectionSort.setDisable(true);
        quickSort.setDisable(true);
        mergeSort.setDisable(true);

        for (int i = 0; i < myRectangles.length; i++) {

            int currentMin = i;

            for (int j = i + 1; j < myRectangles.length; j++) {

                if (myRectangles[j].rectangle.getHeight() < myRectangles[currentMin].rectangle.getHeight()) {

                    currentMin = j; // Now it is the current minimum

                }
                Thread.sleep(20);
            }

            double temp = myRectangles[currentMin].rectangle.getHeight();
            Paint tempColor = myRectangles[currentMin].rectangle.getFill();

            myRectangles[currentMin].rectangle.setHeight(myRectangles[i].rectangle.getHeight());
            myRectangles[currentMin].rectangle.setFill(myRectangles[i].rectangle.getFill());

            myRectangles[i].rectangle.setHeight(temp);
            myRectangles[i].rectangle.setFill(tempColor);

        }

        bubbleSort.setDisable(false);
        insertionSort.setDisable(false);
        selectionSort.setDisable(false);
        quickSort.setDisable(false);
        mergeSort.setDisable(false);
    }
}


class QuickSort {

    public static int partition(RectangleObject[] myRectangles, int low, int high) throws InterruptedException {

        bubbleSort.setDisable(true);
        insertionSort.setDisable(true);
        selectionSort.setDisable(true);
        quickSort.setDisable(true);
        mergeSort.setDisable(true);

        double pivot = myRectangles[high].rectangle.getHeight();
        int i = (low - 1);

        for (int j = low; j < high; j++) {

            if (myRectangles[j].rectangle.getHeight() <= pivot) {
                i++;
                double temp = myRectangles[i].rectangle.getHeight();
                myRectangles[i].rectangle.setHeight(myRectangles[j].rectangle.getHeight());
                myRectangles[j].rectangle.setHeight(temp);
            }
            Thread.sleep(20);
        }
        double temp = myRectangles[i + 1].rectangle.getHeight();
        myRectangles[i + 1].rectangle.setHeight(pivot);
        myRectangles[high].rectangle.setHeight(temp);

        return (i + 1);
    }

    public static void sort(RectangleObject[] myRectangles, int low, int high) throws InterruptedException {

        bubbleSort.setDisable(false);
        insertionSort.setDisable(false);
        selectionSort.setDisable(false);
        quickSort.setDisable(false);
        mergeSort.setDisable(false);

        if (low < high) {

            int pi = partition(myRectangles, low, high);
            sort(myRectangles, low, pi - 1);
            sort(myRectangles, pi + 1, high);
        }
    }
}

class MergeSort {

    public static void merge(RectangleObject[] myRectangles, int low, int mid, int high) {

        bubbleSort.setDisable(true);
        insertionSort.setDisable(true);
        selectionSort.setDisable(true);
        quickSort.setDisable(true);
        mergeSort.setDisable(true);

        int n1 = mid - low + 1;
        int n2 = high - mid;

        RectangleObject[] L = new RectangleObject[n1];
        RectangleObject[] R = new RectangleObject[n2];

        for (int i = 0; i < n1; ++i) {
            L[i] = myRectangles[low + i];
        }

        for (int j = 0; j < n2; ++j) {
            R[j] = myRectangles[mid + 1 + j];
        }

        int i = 0, j = 0;

        int k = low;
        while (i < n1 && j < n2) {
            if (L[i].rectangle.getHeight() <= R[j].rectangle.getHeight()) {

                myRectangles[k] = L[i];

                i++;

            } else {
                myRectangles[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            myRectangles[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            myRectangles[k] = R[j];
            j++;
            k++;
        }
    }

    public static void sort(RectangleObject[] myRectangles, int low, int high) {

        bubbleSort.setDisable(false);
        insertionSort.setDisable(false);
        selectionSort.setDisable(false);
        quickSort.setDisable(false);
        mergeSort.setDisable(false);

        if (low < high) {
            // Find the middle point
            int m = low + (high - low) / 2;

            // Sort first and second halves
            sort(myRectangles, low, m);
            sort(myRectangles, m + 1, high);

            // Merge the sorted halves
            merge(myRectangles, low, m, high);
        }
    }
}








