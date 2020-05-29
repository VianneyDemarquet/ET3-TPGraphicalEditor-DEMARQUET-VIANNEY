package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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

    /**
     * change la figure courante avec la figure selectionner
     * @param s figure a selectionner
     */
    public boolean selectShape(Shape s);

    /**
     * Déplace une figure
     * @param x déplacement en x
     * @param y déplacement en y
     */
    public void move(double x, double y);

    /**
     * modifie la couleur d'un objet ou de dessein de base
     * @param c couleur
     */
    public void couleur(Color c);

    /**
     * deselectionne la forme surligner précédament
     */
    public void resetSelect();

    /**
     * suprime la figure selectionné
     */
    public void remove();

    /**
     * clone la figure selectionné
     */
    public void cloneFigure();
}
