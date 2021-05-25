package sample;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Graph {

    ArrayList<Vertex> vertices;

    public Vertex getVertices(int i) {
        return vertices.get(i);
    }

    public Graph () {
        this.vertices = new ArrayList<Vertex>();
    }

    public Vertex addVertex(double x, double y, int data) {
        Vertex newVertex = new Vertex(x, y, data);
        this.vertices.add(newVertex);
        return newVertex;
    }

    public void addEdge(Vertex vertex1, Vertex vertex2) {

        vertex1.addEdge(vertex2);
        vertex2.addEdge(vertex1);

    }

    public Line connectVertexWithLine(Vertex vertex1, Vertex vertex2) {

        DoubleProperty startX = new SimpleDoubleProperty(vertex1.vertex.getCenterX());
        DoubleProperty startY = new SimpleDoubleProperty(vertex1.vertex.getCenterY());
        DoubleProperty endX = new SimpleDoubleProperty(vertex2.vertex.getCenterX());
        DoubleProperty endY = new SimpleDoubleProperty(vertex2.vertex.getCenterY());

        return new BoundLine(startX, startY, endX, endY);

    }

}

