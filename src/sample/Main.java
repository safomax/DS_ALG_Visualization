package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

import static sample.SortingAlgorithms.*;

public class Main extends Application implements EventHandler<ActionEvent> {

    // Buttons for sorting visualizations
    public static Button bubbleSort;
    public static Button insertionSort;
    public static Button selectionSort;
    public static Button quickSort;
    public static Button mergeSort;
    public static Button playWithDS;
    // Buttons for graphing visualizations
    public static Button createGraphButton;
    public static Button insertVerticesButton;
    public static Button insertEdgesButton;
    public static Button removeGraphButton;
    public static Button playWithSorting;
    public static Button confirmEdgeTwoVertices;
    public static Button confirmVerticesButton;
    static int screenSizeX = 1000;
    static int screenSizeY = 500;
    public Group group, group2;
    RectangleObject[] myRectangles;
    RectangleObject rectangle;
    Graph graph;
    Rectangle pane;
    Rectangle pane2;
    Label textLabel;

    TextField textField;
    TextField vertexEdgeOneInput;
    TextField vertexEdgeTwoInput;
    TextField vertexInput;
    Vertex newVertex;

    Text lineLengthText;
    Line line;

    Button initializeArray;
    Button resetArray;
    Stage window;
    Scene scene, scene2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        window = stage;
        // Create groups for each window
        group = new Group();
        group2 = new Group();

        // init UI elements
        initPage1UI();
        initPage2UI();

        // Set title
        stage.setTitle("Sorting Algorithm and Graph Visualization");

        // Create scene adding everything in group
        scene = new Scene(group, screenSizeX, screenSizeY);
        scene2 = new Scene(group2, screenSizeX, screenSizeY);

        // Set scene to scene
        stage.setScene(scene);
        // Prevent window from being resized
        stage.setResizable(false);
        // Show the screen
        stage.show();
    }

    // initialize elements that will be sorted
    public void initializeArray(int numRectangles) {

        bubbleSort.setDisable(false);
        insertionSort.setDisable(false);
        selectionSort.setDisable(false);
        quickSort.setDisable(false);
        mergeSort.setDisable(false);

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

    public void confirmEdges() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String stringVertexEdgeOne = vertexEdgeOneInput.getText();
                String stringVertexEdgeTwo = vertexEdgeTwoInput.getText();

                int vertexEdgeOne = Integer.parseInt(stringVertexEdgeOne);
                int vertexEdgeTwo = Integer.parseInt(stringVertexEdgeTwo);

                int i;
                // Find vertices in arraylist that are of value vertexEdge one
                for (i = 0; i < graph.vertices.size(); i++) {
                    if (vertexEdgeOne == graph.vertices.get(i).getNumber()) {
                        System.out.println("Found " + graph.vertices.get(i).getNumber());
                        break;
                    }
                }

                int j;
                // Find vertices in arraylist that are of value vertexEdge two
                for (j = 0; j < graph.vertices.size(); j++) {
                    if (vertexEdgeTwo == graph.vertices.get(j).getNumber()) {
                        System.out.println("Found " + graph.vertices.get(j).getNumber());
                        break;
                    }
                }

                graph.addEdge(graph.vertices.get(i), graph.vertices.get(j));
                line = graph.connectVertexWithLine(graph.vertices.get(i), graph.vertices.get(j));

                DoubleProperty startX = new SimpleDoubleProperty(graph.vertices.get(i).vertex.getCenterX());
                DoubleProperty startY = new SimpleDoubleProperty(graph.vertices.get(i).vertex.getCenterY());
                DoubleProperty endX = new SimpleDoubleProperty(graph.vertices.get(j).vertex.getCenterX());
                DoubleProperty endY = new SimpleDoubleProperty(graph.vertices.get(j).vertex.getCenterY());

                graph.vertices.get(i).vertex.centerXProperty().bind(startX);
                graph.vertices.get(i).vertex.centerYProperty().bind(startY);

                graph.vertices.get(j).vertex.centerXProperty().bind(endX);
                graph.vertices.get(j).vertex.centerYProperty().bind(endY);

                // Distance formula to calculate line length
                double lineLength = Math.sqrt((line.getEndX() - line.getStartX()) * (line.getEndX() - line.getStartX()) + (line.getEndY() - line.getStartY()) * (line.getEndY() - line.getStartY()));
                lineLength /= 10;
                lineLength = Math.round(lineLength);
                System.out.println("Linelength is " + lineLength);

                double midPointX = (line.getStartX() + line.getEndX()) / 2;
                double midPointY = (line.getStartY() + line.getEndY()) / 2;

                lineLengthText = new Text(String.valueOf(lineLength));
                lineLengthText.setLayoutX(midPointX);
                lineLengthText.setLayoutY(midPointY);


                lineLengthText.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");

                group2.getChildren().add(lineLengthText);
                group2.getChildren().add(line);


                line.toBack();

                // After success
                vertexEdgeOneInput.setText("");
                vertexEdgeTwoInput.setText("");
            }
        });
    }

    // handle button clicks
    @Override
    public void handle(ActionEvent actionEvent) {

        if (actionEvent.getSource() == resetArray) { // Make it so deletes everything

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

        if (actionEvent.getSource() == quickSort) {
            new Thread() {
                public void run() {
                    try {
                        QuickSort.sort(myRectangles, 0, myRectangles.length - 1);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        if (actionEvent.getSource() == mergeSort) {
            new Thread() {
                public void run() {

                    MergeSort.sort(myRectangles, 0, myRectangles.length - 1);

                    for (int i = 0; i < myRectangles.length; i++) {
                        System.out.print(myRectangles[i].rectangle.getHeight() + " ");
                    }
                }
            }.start();
        }

        if (actionEvent.getSource() == createGraphButton) {
            new Thread() {
                public void run() {

                    graph = new Graph();

                    createGraphButton.setDisable(true);
                }
            }.start();
        }

        if (actionEvent.getSource() == removeGraphButton) {

            Platform.runLater(new Runnable() {
                public void run() {

                    // Removes all vertices from GUI. Don't really need this, group2.getzChildrenClear
                    // takes care of it.
                    for (int i = 0; i < graph.vertices.size(); i++) {
                        group2.getChildren().remove(graph.vertices.get(i).vertex);
                    }

                    group2.getChildren().clear();
                    line = null;
                    lineLengthText = null;
                    graph = null;
                    newVertex= null;

                    initPage2UI();
                    createGraphButton.setDisable(false);
                }
            });
        }

        if (actionEvent.getSource() == insertVerticesButton) { // 5/22/2021 -> Dont forget to Assign all nodes with numbers

            Platform.runLater(new Runnable() {
                public void run() {
                    if (confirmVerticesButton.isVisible()) {
                        vertexInput.setVisible(false);
                        confirmVerticesButton.setVisible(false);
                    } else {
                        vertexInput.setVisible(true);
                        confirmVerticesButton.setVisible(true);
                    }
                }
            });
        }

        if (actionEvent.getSource() == confirmVerticesButton) { // Assign vertices with edge

            String stringVertexEdgeOne = vertexInput.getText();

            int vertexInputValue = Integer.parseInt(stringVertexEdgeOne);

            newVertex = graph.addVertex(200, 200, vertexInputValue);

            group2.getChildren().addAll(newVertex.vertex);  // add to group2 for visual

            vertexInput.setText("");
        }

        if (actionEvent.getSource() == insertEdgesButton) { // Assign vertices with edge
            if (confirmEdgeTwoVertices.isVisible()) {
                vertexEdgeOneInput.setVisible(false);
                vertexEdgeTwoInput.setVisible(false);
                confirmEdgeTwoVertices.setVisible(false);
            } else {
                vertexEdgeOneInput.setVisible(true);
                vertexEdgeTwoInput.setVisible(true);
                confirmEdgeTwoVertices.setVisible(true);
            }
        }

        if (actionEvent.getSource() == confirmEdgeTwoVertices) { // Assign vertices with edge

            confirmEdges();
        }
    }

    void initPage1UI() {

        // Create pane for the first window
        pane = new Rectangle();
        pane.setX(10);
        pane.setY(392);
        pane.setWidth(982);
        pane.setHeight(98);
        pane.setArcWidth(30.0);
        pane.setArcHeight(20.0);
        pane.setFill(Color.GHOSTWHITE);
        group.getChildren().add(pane);


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
        initializeArray.setText("Initialize Elements");
        group.getChildren().add(initializeArray);
        initializeArray.setLayoutX(300);
        initializeArray.setLayoutY(400);
        initializeArray.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        initializeArray.setOnAction(this);

        // Bubble Sort Button
        bubbleSort = new Button();
        bubbleSort.applyCss();
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

        // Quick Sort Button
        quickSort = new Button();
        quickSort.setText("Quick Sort");
        group.getChildren().add(quickSort);
        quickSort.setLayoutX(325);
        quickSort.setLayoutY(450);
        quickSort.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        quickSort.setOnAction(this);

        // Merge Sort Button
        mergeSort = new Button();
        mergeSort.setText("Merge Sort");
        group.getChildren().add(mergeSort);
        mergeSort.setLayoutX(425);
        mergeSort.setLayoutY(450);
        mergeSort.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        mergeSort.setOnAction(this);

        // Reset same array button
        resetArray = new Button();
        resetArray.setText("Clear Elements");
        group.getChildren().add(resetArray);
        resetArray.setLayoutX(625);
        resetArray.setLayoutY(450);
        resetArray.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        resetArray.setOnAction(this);

    }

    void initPage2UI() {

        // Create a pane for the second window
        pane2 = new Rectangle();
        pane2.setX(10);
        pane2.setY(360);
        pane2.setWidth(982);
        pane2.setHeight(130);
        pane2.setArcWidth(30.0);
        pane2.setArcHeight(20.0);
        pane2.setFill(Color.GHOSTWHITE);
        group2.getChildren().add(pane2);

        // Change window to data structures
        playWithDS = new Button("Play with Graphs");
        playWithDS.setOnAction(e -> window.setScene(scene2));
        playWithDS.setLayoutX(830);
        playWithDS.setLayoutY(400);
        playWithDS.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        group.getChildren().add(playWithDS);

        // Change window to sorting algorithms
        playWithSorting = new Button("Play with Sorting Algorithms");
        playWithSorting.setOnAction(e -> window.setScene(scene));
        playWithSorting.setLayoutX(815);
        playWithSorting.setLayoutY(370);
        playWithSorting.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        group2.getChildren().add(playWithSorting);

        // Create graph button
        createGraphButton = new Button();
        createGraphButton.setText("Create Graph");
        createGraphButton.setLayoutX(25);
        createGraphButton.setLayoutY(370);
        createGraphButton.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        createGraphButton.setOnAction(this);
        group2.getChildren().add(createGraphButton);

        // Add vertices to graph button
        insertVerticesButton = new Button();
        insertVerticesButton.setText("Add Vertex");
        insertVerticesButton.setLayoutX(125);
        insertVerticesButton.setLayoutY(370);
        insertVerticesButton.setPrefWidth(80);
        insertVerticesButton.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        insertVerticesButton.setOnAction(this);
        group2.getChildren().add(insertVerticesButton);

        vertexInput = new TextField();
        group2.getChildren().add(vertexInput);
        vertexInput.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        vertexInput.setLayoutX(125);
        vertexInput.setLayoutY(400);
        vertexInput.setPrefWidth(80);
        vertexInput.setVisible(false);
        vertexInput.setPromptText("Number");
        vertexInput.getParent().requestFocus();

        confirmVerticesButton = new Button();
        confirmVerticesButton.setText("Confirm");
        confirmVerticesButton.setLayoutX(125);
        confirmVerticesButton.setLayoutY(430);
        confirmVerticesButton.setPrefWidth(80);
        confirmVerticesButton.setVisible(false);
        confirmVerticesButton.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        confirmVerticesButton.setOnAction(this);
        group2.getChildren().add(confirmVerticesButton);

        insertEdgesButton = new Button();
        insertEdgesButton.setText("Insert Edge");
        insertEdgesButton.setLayoutX(225);
        insertEdgesButton.setLayoutY(370);
        insertEdgesButton.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        insertEdgesButton.setOnAction(this);
        group2.getChildren().add(insertEdgesButton);

        vertexEdgeOneInput = new TextField();
        group2.getChildren().add(vertexEdgeOneInput);
        vertexEdgeOneInput.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        vertexEdgeOneInput.setLayoutX(225);
        vertexEdgeOneInput.setLayoutY(400);
        vertexEdgeOneInput.setPrefWidth(80);
        vertexEdgeOneInput.setVisible(false);
        vertexEdgeOneInput.setPromptText("Vertex 1");
        vertexEdgeOneInput.getParent().requestFocus();

        vertexEdgeTwoInput = new TextField();
        group2.getChildren().add(vertexEdgeTwoInput);
        vertexEdgeTwoInput.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        vertexEdgeTwoInput.setLayoutX(225);
        vertexEdgeTwoInput.setLayoutY(430);
        vertexEdgeTwoInput.setPrefWidth(80);
        vertexEdgeTwoInput.setVisible(false);
        vertexEdgeTwoInput.setPromptText("Vertex 2");
        vertexEdgeTwoInput.getParent().requestFocus();

        // Submit two vertices that will gain an edge between them
        confirmEdgeTwoVertices = new Button();
        group2.getChildren().add(confirmEdgeTwoVertices);
        confirmEdgeTwoVertices.setText("Confirm");
        confirmEdgeTwoVertices.setLayoutX(225);
        confirmEdgeTwoVertices.setLayoutY(460);
        confirmEdgeTwoVertices.setPrefWidth(80);
        confirmEdgeTwoVertices.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        confirmEdgeTwoVertices.setOnAction(this);
        confirmEdgeTwoVertices.setVisible(false);

        // Remove entire graph button
        removeGraphButton = new Button();
        removeGraphButton.setText("Rmv Graph");
        removeGraphButton.setLayoutX(25);
        removeGraphButton.setLayoutY(400);
        removeGraphButton.setPrefWidth(88);
        removeGraphButton.setStyle("-fx-font: 12 arial; -fx-base: #ffffff;");
        removeGraphButton.setOnAction(this);
        group2.getChildren().add(removeGraphButton);

    }
}
