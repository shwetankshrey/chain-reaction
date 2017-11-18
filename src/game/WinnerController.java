package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WinnerController {

    @FXML Button okayButton;

    @FXML protected void okayCol(ActionEvent event) throws Exception {
        Stage stage = (Stage) okayButton.getScene().getWindow();
        stage.close();
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/home.fxml")), 600, 900);
        GameState.mainStage.setScene(s);
    }
}
