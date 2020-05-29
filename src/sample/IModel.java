package sample;

import javafx.scene.canvas.Canvas;

import javafx.scene.shape.Shape;


public interface IModel {

    /**
     * Change le mode
     */
    public void changeMode(String m);

    /**
     * Ajoute une figure au dessin au coordoné indiqué.
     * @param x coordoné x
     * @param y coordoné y
     */
    public void addFigure(double x, double y);

    /**
     * dessine une figure
     * @param s
     */
    public void drawShape(Shape s);

    /**
     * Ajoute une référence vers le canvas de dessin
     * @param c
     */
    public void addCanvas(Canvas c);

    /**
     * Selectionne la figure courante
     * @param s
     */
    public void currentShape(Shape s);

    /**
     * change la taille de la figure
     * @param x
     * @param y
     */
    public void resize(double x, double y);
}
