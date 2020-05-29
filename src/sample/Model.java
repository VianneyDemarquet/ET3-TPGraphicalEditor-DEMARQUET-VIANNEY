package sample;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class Model implements IModel {
    private String mode;
    private Pane pane;
    private Shape figure;
    private Color couleur;

    public Model(){
        super();
        mode = "Select/Move";
        couleur = Color.BLACK;
    }

    /**
     * Change le mode
     */
    @Override
    public void changeMode(String m) {
        mode = m;
        resetSelect();
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
        s.setFill(couleur);
        s.setStroke(couleur);
        pane.getChildren().add(s);
        s.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(selectShape(s)) {
                    s.setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            move(e.getSceneX() - 153., e.getSceneY());
                        }
                    });
                }
            }
        });
        resetSelect();
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
            if(x < 0){
                x = -x;
            }
            if(y < 0){
                y = -y;
            }
           e.setRadiusX(x);
           e.setRadiusY(y);
        }else if(cl.equals("javafx.scene.shape.Rectangle")){
            Rectangle r = (Rectangle) figure;
            r.setHeight(y);
            r.setWidth(x);
        }else if(cl.equals("javafx.scene.shape.Line")){
            Line l = (Line) figure;
            l.setEndX(l.getStartX()+x);
            l.setEndY(l.getStartY()+y);
        }
    }

    /**
     * change la figure courante avec la figure selectionner
     *
     * @param s figure a selectionner
     */
    @Override
    public boolean selectShape(Shape s) {
        if (mode.equals("Select/Move")){
            resetSelect();
            currentShape(s);
            s.setStroke(Color.RED);
            return true;
        }else{
            resetSelect();
            return false;
        }
    }

    /**
     * Déplace une figure
     *
     * @param x déplacement en x
     * @param y déplacement en y
     */
    @Override
    public void move(double x, double y) {
        String cl = figure.getClass().getName();
        if (cl.equals("javafx.scene.shape.Ellipse")){
            Ellipse e = (Ellipse) figure;
            e.setCenterX(x);
            e.setCenterY(y);
        }else if(cl.equals("javafx.scene.shape.Rectangle")){
            Rectangle r = (Rectangle) figure;
            r.setX(x);
            r.setY(y);
        }else if(cl.equals("javafx.scene.shape.Line")){
            Line l = (Line) figure;
            l.setTranslateX(x);
            l.setTranslateY(y);
        }
    }

    /**
     * modifie la couleur d'un objet ou de dessein de base
     *
     * @param c couleur
     */
    @Override
    public void couleur(Color c) {
        couleur = c;
        if(mode.equals("Select/Move")){
            if (figure != null){
                figure.setFill(couleur);
            }
        }
    }

    /**
     * deselectionne la forme surligner précédament
     */
    @Override
    public void resetSelect() {
        if (figure != null) {
            figure.setStroke(figure.getFill());
            figure = null;
        }
    }

    @Override
    public void remove() {
        if (figure != null || !mode.equals("Select/Move")) {
            pane.getChildren().remove(figure);
        }
    }

    /**
     * clone la figure selectionné
     */
    @Override
    public void cloneFigure() {
        if(figure == null || !mode.equals("Select/Move")){
            return;
        }
        String cl = figure.getClass().getName();
        if (cl.equals("javafx.scene.shape.Ellipse")){
            Ellipse e = (Ellipse) figure;
            drawShape(new Ellipse(e.getCenterX()+10,e.getCenterY()+10, e.getRadiusX(),e.getRadiusY()));
        }else if(cl.equals("javafx.scene.shape.Rectangle")){
            Rectangle r = (Rectangle) figure;
            drawShape(new Rectangle(r.getX()+10,r.getY()+10,r.getWidth(),r.getHeight()));
        }else if(cl.equals("javafx.scene.shape.Line")){
            Line l = (Line) figure;
            drawShape(new Line(l.getStartX()+10,l.getStartY()+10,l.getEndX()+10,l.getEndY()+10));
        }
        figure.setStroke(Color.RED);
    }
}
