package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sun.audio.AudioPlayer;

public class PreferencesController {

    @FXML private ColorPicker colorPicker;
    @FXML private ChoiceBox soundchoice;

    @FXML protected void okayCol(ActionEvent event) throws Exception {
        Color c = colorPicker.getValue();
        GameState.colorCodes[GameState.alterPlayer-1] = c;
        int op = soundchoice.getSelectionModel().getSelectedIndex();
        if(op==0)
            GameState.sfx = new AudioClip(AudioPlayer.class.getResource("../resources/sound/beep.wav").toString());
        if(op==1)
            GameState.sfx = new AudioClip(AudioPlayer.class.getResource("../resources/sound/click.wav").toString());
        if(op==2)
            GameState.sfx = new AudioClip(AudioPlayer.class.getResource("../resources/sound/fastpop.wav").toString());
        if(op==3)
            GameState.sfx = new AudioClip(AudioPlayer.class.getResource("../resources/sound/pop.wav").toString());
        if(op==4)
            GameState.sfx = new AudioClip(AudioPlayer.class.getResource("../resources/sound/laser.wav").toString());
        if(op==5)
            GameState.sfx = new AudioClip(AudioPlayer.class.getResource("../resources/sound/laser2.wav").toString());
        Stage stage = (Stage) colorPicker.getScene().getWindow();
        stage.close();
    }

    @FXML protected void cancelCol(ActionEvent event) throws Exception {
        Stage stage = (Stage) colorPicker.getScene().getWindow();
        stage.close();
    }
}
