package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Random;

import static sample.SortingAlgorithms.*;

public class Main extends Application implements EventHandler<ActionEvent> {

    static int screenSizeX = 1000;
    static int screenSizeY = 500;
    RectangleObject[] myRectangles;
    RectangleObject rectangle;
    Label textLabel;
    TextField textField;
    Button initializeArray;
    Button resetArray;
    public static Button bubbleSort;
    public static Button insertionSort;
    public static Button selectionSort;

    Group group;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        group = new Group();

        // Number of elements input button
        textLabel = new Label("Number of Elements:");
        textLabel.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        group.getChildren().add(textLabel);
        textLabel.setLayoutX(25);
        textLabel.setLayoutY(403);

        // Number of elements input field
        textField = new TextField();
        textField.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        group.getChildren().add(textField);
        textField.setLayoutX(145);
        textField.setLayoutY(400);

        // Submit and initialize number of elements button
        initializeArray = new Button();
        initializeArray.setText("Initialize elements");
        group.getChildren().add(initializeArray);
        initializeArray.setLayoutX(300);
        initializeArray.setLayoutY(400);
        initializeArray.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        initializeArray.setOnAction(this);

        // Bubble Sort Button
        bubbleSort = new Button();
        bubbleSort.setText("Bubble Sort");
        group.getChildren().add(bubbleSort);
        bubbleSort.setLayoutX(25);
        bubbleSort.setLayoutY(450);
        bubbleSort.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        bubbleSort.setOnAction(this);

        // Insertion Sort Button
        insertionSort = new Button();
        insertionSort.setText("Insertion Sort");
        group.getChildren().add(insertionSort);
        insertionSort.setLayoutX(125);
        insertionSort.setLayoutY(450);
        insertionSort.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        insertionSort.setOnAction(this);

        // Selection Sort Button
        selectionSort = new Button();
        selectionSort.setText("Selection Sort");
        group.getChildren().add(selectionSort);
        selectionSort.setLayoutX(225);
        selectionSort.setLayoutY(450);
        selectionSort.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        selectionSort.setOnAction(this);

        // Reset same array button
        resetArray = new Button();
        resetArray.setText("New array");
        group.getChildren().add(resetArray);
        resetArray.setLayoutX(625);
        resetArray.setLayoutY(450);
        resetArray.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        resetArray.setOnAction(this);

        // Set title
        stage.setTitle("Data Structure and Algorithm Visualization");
        // Create scene adding everything in group
        Scene scene = new Scene(group, screenSizeX, screenSizeY);
        // Set scene to scene
        stage.setScene(scene);
        // Prevent window from being resized
        stage.setResizable(false);
        // Show the screen
        stage.show();
    }

    public void initializeArray(int numRectangles) {

        bubbleSort.setDisable(false);
        insertionSort.setDisable(false);
        selectionSort.setDisable(false);

        int width = screenSizeX / numRectangles; // Fix some more

        myRectangles = new RectangleObject[numRectangles];




        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                initializeArray.setDisable(true);

                int x = 0;
                for (int i = 0; i < myRectangles.length; i++) {
                    Random rand = new Random();
                    int length = rand.nextInt(300 - 10) + 10;
                    int y = 0;
                    rectangle = new RectangleObject(length, x, y, width);
                    x += width;
                    myRectangles[i] = rectangle;
                    group.getChildren().add(myRectangles[i].rectangle);
                }

            }
        });
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        if (actionEvent.getSource() == resetArray) {

            Platform.runLater(new Runnable() {

                @Override
                public void run() {

                    for (int i = 0; i < myRectangles.length; i++) {
                        group.getChildren().remove(myRectangles[i].rectangle);
                        group.getChildren().remove(myRectangles);
                        group.getChildren().remove(rectangle);

                    }
                    initializeArray.setDisable(false);
                    resetArray.setDisable(false);


                }

            });
        }

        if (actionEvent.getSource() == initializeArray) {

            new Thread() {
                public void run() {
                    String stringNumberOfRects = textField.getText();
                    int numberOfRects = Integer.parseInt(stringNumberOfRects);
                    initializeArray(numberOfRects);
                }
            }.start();
        }

        if (actionEvent.getSource() == bubbleSort) {

            new Thread() {
                public void run() {
                    try {
                        BubbleSort(myRectangles);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        if (actionEvent.getSource() == insertionSort) {
            new Thread() {
                public void run() {
                    try {
                        InsertionSort(myRectangles);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        if (actionEvent.getSource() == selectionSort) {
            new Thread() {
                public void run() {
                    try {
                        SelectionSort(myRectangles);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
