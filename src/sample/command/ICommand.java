package sample.command;

import javafx.scene.shape.Shape;

public interface ICommand {

    /**
     * Sauvegarde la figure
     * @param s figure a sauvegarder
     */
    ICommand save(Shape s);

    /**
     * rend l'action effetuer avant le undo
     * @return action
     */
    ICommand GetRedo();

    /**
     * rend l'action précédament effectuer
     * @return action
     */
    ICommand GetUndo();

    /**
     * restore les valeur ) leur position avant modification
     */
    void restor();
}
