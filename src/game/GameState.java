package game;

import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sun.audio.AudioPlayer;

public class GameState {
    protected static Stage mainStage;
    protected static int numPlayers;
    protected static int alterPlayer;
    protected static int saveGame;
    protected static AudioClip sfx;// = new AudioClip(AudioPlayer.class.getResource("../resources/sound/beep.wav").toString());
    protected static Color[] colorCodes = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PURPLE, Color.CYAN, Color.ORANGE, Color.WHITE};
}
