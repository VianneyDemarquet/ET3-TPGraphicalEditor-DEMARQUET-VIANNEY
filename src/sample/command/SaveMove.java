package sample.command;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SaveMove implements ICommand{
    private double x;
    private double y;
    private double height;
    private double width;
    private Shape shape;
    private ICommand undo;
    private ICommand redo;

    public SaveMove(){
        x=0;
        y=0;
        height=0;
        width=0;

    }

    public SaveMove(Shape s, ICommand command){
        shape = s;
        undo = command;
        if (s == null){
            x=0;
            y=0;
            height=0;
            width=0;
        }else{
            String cl = shape.getClass().getName();
            if(cl.equals("javafx.scene.shape.Ellipse")){
                Ellipse e = (Ellipse) shape;
                x = e.getCenterX();
                y = e.getCenterY();
                height = e.getRadiusX();
                width = e.getRadiusY();
            }else if(cl.equals("javafx.scene.shape.Rectangle")){
                Rectangle r = (Rectangle) shape;
                x = r.getX();
                y = r.getY();
                height = r.getHeight();
                width = r.getWidth();
            }else if(cl.equals("javafx.scene.shape.Line")){
                Line l = (Line) shape;
                x = l.getStartX();
                y = l.getStartY();
                height = l.getEndX();
                width = l.getEndY();
            }
        }
    }
    /**
     * Sauvegarde la figure
     *
     * @param s figure a sauvegarder
     */
    @Override
    public ICommand save(Shape s) {
        redo = new SaveMove(s, this);
        return redo;
    }

    /**
     * rend l'action effetuer avant le undo
     *
     * @return action
     */
    @Override
    public ICommand GetRedo() {
        if (redo == null){
            return this;
        }
        redo.restor();
        return redo;
    }

    /**
     * rend l'action précédament effectuer
     *
     * @return action
     */
    @Override
    public ICommand GetUndo() {
        if (undo == null){
            return this;
        }
        undo.restor();
        return undo;
    }

    /**
     * restore les valeur ) leur position avant modification
     */
    @Override
    public void restor() {
        if (shape == null){
            return;
        }
        String cl = shape.getClass().getName();
        if(cl.equals("javafx.scene.shape.Ellipse")){
            Ellipse e = (Ellipse) shape;
            e.setCenterX(x);
            e.setCenterY(y);
            e.setRadiusX(height);
            e.setRadiusY(width);
        }else if(cl.equals("javafx.scene.shape.Rectangle")){
            Rectangle r = (Rectangle) shape;
            r.setX(x);
            r.setY(y);
            r.setHeight(height);
            r.setWidth(width);
        }else if(cl.equals("javafx.scene.shape.Line")){
            Line l = (Line) shape;
            l.setStartX(x);
            l.setStartY(y);
            l.setEndX(height);
            l.setEndY(width);
        }
    }
}
