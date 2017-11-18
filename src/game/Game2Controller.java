package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class Game2Controller {
    @FXML
    protected void restart(ActionEvent event) throws Exception {

    }

    @FXML protected void save(ActionEvent event) throws Exception {

    }

    @FXML
    protected void goHome(ActionEvent event) throws Exception {
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/home.fxml")), 600, 900);
        GameState.mainStage.setScene(s);
    }
}
