package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PreferencesController {

    @FXML private ColorPicker colorPicker;

    @FXML protected void okayCol(ActionEvent event) throws Exception {
        Color c = colorPicker.getValue();
        GameState.colorCodes[GameState.alterPlayer-1] = c;
        Stage stage = (Stage) colorPicker.getScene().getWindow();
        stage.close();
    }

    @FXML protected void cancelCol(ActionEvent event) throws Exception {
        Stage stage = (Stage) colorPicker.getScene().getWindow();
        stage.close();
    }
}
