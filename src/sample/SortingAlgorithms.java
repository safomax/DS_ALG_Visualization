package sample;


import static sample.Main.*;

public class SortingAlgorithms {

    public static void BubbleSort(RectangleObject[] myRectangles) throws InterruptedException {

        bubbleSort.setDisable(true);
        insertionSort.setDisable(true);
        selectionSort.setDisable(true);

        for (int i = 0; i < myRectangles.length; i++) {

            for (int j = 0; j < myRectangles.length - i - 1; j++) {

                if (myRectangles[j].rectangle.getHeight() > myRectangles[j + 1].rectangle.getHeight()) {

                    double temp = myRectangles[j].rectangle.getHeight();
                    myRectangles[j].rectangle.setHeight(myRectangles[j + 1].rectangle.getHeight());
                    myRectangles[j + 1].rectangle.setHeight(temp);

                    Thread.sleep(10);
                }
            }
        }

        bubbleSort.setDisable(false);
        insertionSort.setDisable(false);
        selectionSort.setDisable(false);

    }

    public static void InsertionSort(RectangleObject[] myRectangles) throws InterruptedException {

        bubbleSort.setDisable(true);
        insertionSort.setDisable(true);
        selectionSort.setDisable(true);

        for (int i = 1; i < myRectangles.length; i++) {

            int j = i;

            while (j > 0 && myRectangles[j - 1].rectangle.getHeight() > myRectangles[j].rectangle.getHeight()) {

                double temp = myRectangles[j].rectangle.getHeight();
                myRectangles[j].rectangle.setHeight(myRectangles[j - 1].rectangle.getHeight());
                myRectangles[j - 1].rectangle.setHeight(temp);
                j = j - 1;

                Thread.sleep(10);
            }
        }

        bubbleSort.setDisable(false);
        insertionSort.setDisable(false);
        selectionSort.setDisable(false);
    }

    public static void SelectionSort(RectangleObject[] myRectangles) throws InterruptedException {

        bubbleSort.setDisable(true);
        insertionSort.setDisable(true);
        selectionSort.setDisable(true);

        for (int i = 0; i < myRectangles.length; i++) {

            int currentMin = i;

            for (int j = i + 1; j < myRectangles.length; j++) {

                if (myRectangles[j].rectangle.getHeight() < myRectangles[currentMin].rectangle.getHeight()) {
                    currentMin = j;
                }
                Thread.sleep(10);
            }

            double temp = myRectangles[currentMin].rectangle.getHeight();
            myRectangles[currentMin].rectangle.setHeight(myRectangles[i].rectangle.getHeight());
            myRectangles[i].rectangle.setHeight(temp);


        }

        bubbleSort.setDisable(false);
        insertionSort.setDisable(false);
        selectionSort.setDisable(false);
    }
}



