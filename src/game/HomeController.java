package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;

public class HomeController {

    @FXML private ChoiceBox playerChoice;

    @FXML protected void playSD(ActionEvent event) throws Exception {
        int op = playerChoice.getSelectionModel().getSelectedIndex();
        GameState.numPlayers = op + 2;
        GameState.saveGame = 0;
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/game1.fxml")), 600, 900);
        GameState.mainStage.setScene(s);
    }

    @FXML protected void playHD(ActionEvent event) throws Exception {
        int op = playerChoice.getSelectionModel().getSelectedIndex();
        GameState.numPlayers = op + 2;
        GameState.saveGame = 0;
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/game2.fxml")), 600, 900);
        GameState.mainStage.setScene(s);
    }

    @FXML protected void openSettings(ActionEvent event) throws Exception {
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/settings.fxml")), 600, 900);
        GameState.mainStage.setScene(s);
    }

    @FXML protected void resume(ActionEvent event) throws Exception {
        SaveGame x = Main.load();
        GameState.colorCodes = x.colorCodes;
        GameState.numPlayers = x.numPlayers;
        GameState.saveGame = 1;
        if(x.grid[0][0].rad == 20) {
            Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/game1.fxml")), 600, 900);
            GameState.mainStage.setScene(s);
        }
        else {
            Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/game2.fxml")), 600, 900);
            GameState.mainStage.setScene(s);
        }
    }
}
