package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class Model implements IModel {
    private String mode;
    private Canvas canvas;
    private Shape figure;
    GraphicsContext gc;

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
        gc = canvas.getGraphicsContext2D();

        if (mode.equals("Ellipse")){
            gc.fillOval(x,y,10.,10.);
            //drawShape(new Ellipse(x,y,10.0,10.0));
        }else if (mode.equals("Rectangle")){
            //drawShape(new Rectangle(x,y,0.0,0.0));
            gc.fillRect(x,y,0.,0.);
        }else if (mode.equals("Line")){
            //drawShape(new Line(x,y,x,y));
            gc.strokeLine(x,y,x,y);
        }
    }

    /**
     * dessine une figure
     *
     * @param s
     */
    @Override
    public void drawShape(Shape s) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        currentShape(s);
    }

    /**
     * Ajoute une référence vers le pane de dessin
     *
     * @param c
     */
    @Override
    public void addCanvas(Canvas c) {
        canvas = c;
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
        if (mode.equals("Ellipse")){

            gc.fillOval(x,y,10.,10.);
            //drawShape(new Ellipse(x,y,10.0,10.0));
        }else if (mode.equals("Rectangle")){
            //drawShape(new Rectangle(x,y,0.0,0.0));
            gc.fillRect(x,y,0.,0.);
        }else if (mode.equals("Line")){
            //drawShape(new Line(x,y,x,y));
            gc.strokeLine(x,y,x,y);
        }
    }
}
