package sample;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Random;

public class Vertex {

    public Circle vertex;
    private double orgSceneX, orgSceneY;
    private ArrayList<Edge> edges;
    private int number;


    public Vertex(double x, double y, int number) {

        Color[] colors = new Color[]{Color.LIGHTBLUE, Color.LIGHTPINK, Color.PINK, Color.AZURE, Color.SNOW, Color.LIGHTYELLOW, Color.LINEN, Color.LAVENDERBLUSH};
        vertex = new Circle();
        vertex.setRadius(20);

        vertex.setCenterY(x);
        vertex.setCenterX(y);

        vertex.setFill(getRandomColor(colors));

        this.number = number;
        this.edges = new ArrayList<Edge>();

        enableDragging();

    }

    public static Paint getRandomColor(Color[] array) {
        int randColor = new Random().nextInt(array.length);
        return array[randColor];
    }

    public int getNumber() {
        return number;
    }

    public void addEdge(Vertex endVertex) {
        this.edges.add(new Edge(this, endVertex));
    }

    private void enableDragging() {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                vertex.setOnMousePressed((t) ->

                {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();

                    Circle c = (Circle) (t.getSource());
                    c.toFront();
                });


                vertex.setOnMouseDragged((t) ->

                {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;

                    Circle c = (Circle) (t.getSource());

                    c.setCenterX(c.getCenterX() + offsetX);
                    c.setCenterY(c.getCenterY() + offsetY);

                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                });




            }
        });
    }
}
