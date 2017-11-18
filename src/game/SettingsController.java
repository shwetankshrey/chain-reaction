package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SettingsController {

    private Stage stagex = new Stage();

    @FXML
    protected void goBack(ActionEvent event) throws Exception {
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/home.fxml")), 600, 900);
        GameState.mainStage.setScene(s);
    }

    @FXML protected void changePS1(ActionEvent event) throws Exception {
        GameState.alterPlayer = 1;
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/preferences.fxml")), 550, 250);
        stagex.setScene(s);
        stagex.setTitle("Customisation for Player 1");
        stagex.show();
    }

    @FXML protected void changePS2(ActionEvent event) throws Exception {
        GameState.alterPlayer = 2;
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/preferences.fxml")), 550, 250);
        stagex.setScene(s);
        stagex.setTitle("Customisation for Player 2");
        stagex.show();
    }

    @FXML protected void changePS3(ActionEvent event) throws Exception {
        GameState.alterPlayer = 3;
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/preferences.fxml")), 550, 250);
        stagex.setScene(s);
        stagex.setTitle("Customisation for Player 3");
        stagex.show();
    }

    @FXML protected void changePS4(ActionEvent event) throws Exception {
        GameState.alterPlayer = 4;
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/preferences.fxml")), 550, 250);
        stagex.setScene(s);
        stagex.setTitle("Customisation for Player 4");
        stagex.show();
    }

    @FXML protected void changePS5(ActionEvent event) throws Exception {
        GameState.alterPlayer = 5;
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/preferences.fxml")), 550, 250);
        stagex.setScene(s);
        stagex.setTitle("Customisation for Player 5");
        stagex.show();
    }

    @FXML protected void changePS6(ActionEvent event) throws Exception {
        GameState.alterPlayer = 6;
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/preferences.fxml")), 550, 250);
        stagex.setScene(s);
        stagex.setTitle("Customisation for Player 6");
        stagex.show();
    }

    @FXML protected void changePS7(ActionEvent event) throws Exception {
        GameState.alterPlayer = 7;
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/preferences.fxml")), 550, 250);
        stagex.setScene(s);
        stagex.setTitle("Customisation for Player 7");
        stagex.show();
    }

    @FXML protected void changePS8(ActionEvent event) throws Exception {
        GameState.alterPlayer = 8;
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/preferences.fxml")), 550, 250);
        stagex.setScene(s);
        stagex.setTitle("Customisation for Player 8");
        stagex.show();
    }


}
