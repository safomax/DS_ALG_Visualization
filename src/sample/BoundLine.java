package sample;

import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class BoundLine extends Line {

   public BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {

       startXProperty().bind(startX);
        startYProperty().bind(startY);
        endXProperty().bind(endX);
        endYProperty().bind(endY);


        setStrokeWidth(1);
        setStroke(Color.BLACK.deriveColor(0, 1, 1, 0.5));
        setMouseTransparent(true);
    }
}
