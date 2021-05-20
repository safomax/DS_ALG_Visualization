package sample;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class RectangleObject extends Node {

    Rectangle rectangle;
    int length;

    public RectangleObject(int length, int x, int y, int width) {

        Color[] colors = new Color[]{Color.ALICEBLUE, Color.PALETURQUOISE, Color.PINK, Color.AZURE, Color.GHOSTWHITE, Color.LAVENDER, Color.LINEN, Color.MINTCREAM};
        rectangle = new Rectangle();
        rectangle.setWidth(width);
        this.length = length;
        rectangle.setHeight(length);
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setFill(getRandomColor(colors));
        rectangle.setOpacity(2);
    }

    public static Paint getRandomColor(Color[] array) {
        int randColor = new Random().nextInt(array.length);
        return array[randColor];
    }

}
