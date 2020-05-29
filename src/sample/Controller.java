package sample;

import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Button;
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

    @FXML
    Canvas canvas;
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
                myModel.addFigure(event.getSceneX()-153.0, event.getSceneY());
                pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        double x = e.getSceneX() - event.getSceneX()-153.0;
                        double y = e.getSceneY() - event.getSceneY();
                        myModel.resize(x,y);
                    };
                });
            }
        });

        myModel.addCanvas(canvas);
    }
}
