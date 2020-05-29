package sample;

import javafx.scene.layout.Pane;
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
     * Ajoute une référence vers le pane de dessin
     * @param p
     */
    public void addPane(Pane p);

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
