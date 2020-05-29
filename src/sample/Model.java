package sample;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.awt.*;
import java.util.EventListener;

public class Model implements IModel {
    private String mode;
    private Pane pane;
    private Shape figure;

    public Model(){
        super();
        mode = "Select/Mode";
    }

    /**
     * Change le mode
     */
    @Override
    public void changeMode(String m) {
        mode = m;
    }

    /**
     * Ajoute une figure au dessin au coordoné indiqué.
     *
     * @param x coordoné x
     * @param y coordoné y
     */
    @Override
    public void addFigure(double x, double y) {

        if (mode.equals("Ellipse")){
            drawShape(new Ellipse(x,y,10.0,10.0));
        }else if (mode.equals("Rectangle")){
            drawShape(new Rectangle(x,y,0.0,0.0));
        }else if (mode.equals("Line")){
            drawShape(new Line(x,y,x,y));
        }
    }

    /**
     * dessine une figure
     *
     * @param s
     */
    @Override
    public void drawShape(Shape s) {
        pane.getChildren().add(s);
        currentShape(s);
    }

    /**
     * Ajoute une référence vers le pane de dessin
     *
     * @param p
     */
    @Override
    public void addPane(Pane p) {
        pane = p;
    }

    /**
     * Selectionne la figure courante
     *
     * @param s
     */
    @Override
    public void currentShape(Shape s) {
        figure = s;
    }

    /**
     * change la taille de la figure
     *
     * @param x
     * @param y
     */
    @Override
    public void resize(double x, double y) {
        if (mode.equals("Select/Move")){
            return;
        }
        String cl = figure.getClass().getName();
        if (cl.equals("javafx.scene.shape.Ellipse")){
           Ellipse e = (Ellipse) figure;
           e.setRadiusX(x);
           e.setRadiusY(y);
        }else if(cl.equals("javafx.scene.shape.Rectangle")){
            System.out.println(x+" "+y);
            Rectangle r = (Rectangle) figure;
            r.setHeight(y);
            r.setWidth(x);
        }else if(cl.equals("javafx.scene.shape.Line")){
            Line l = (Line) figure;
            l.setEndX(-x);
            l.setEndY(-y);
        }
    }
}
