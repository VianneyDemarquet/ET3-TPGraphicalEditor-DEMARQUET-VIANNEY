package sample;

import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Controller {

    private Model myModel;

    @FXML
    ToggleGroup button;

    @FXML
    ColorPicker color;

    @FXML
    Button delete;

    @FXML
    Button clone;

    @FXML
    Pane pane;

    public Controller(){
        myModel = new Model();
    }

    public void initialize(){

        button.selectedToggleProperty().addListener(new javafx.beans.value.ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
               myModel.changeMode(((RadioButton) newValue).getText());
            }
        });

        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                myModel.addFigure(event.getSceneX()-153., event.getSceneY());
                pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        double x = e.getSceneX() - event.getSceneX();
                        double y = e.getSceneY() - event.getSceneY();
                        myModel.resize(x,y);
                    };
                });
            }
        });

        color.setOnAction(new EventHandler() {
            public void handle(Event t) {
                myModel.couleur(color.getValue());
            }
        });

        myModel.addPane(pane);
    }
}
