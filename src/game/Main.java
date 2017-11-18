package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene home = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/home.fxml")), 600, 900);
        primaryStage.setTitle("Chain Reaction");
        primaryStage.setScene(home);
        GameState.mainStage = primaryStage;
        GameState.mainStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    public static void save(SaveGame s1) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/resources/save/savegame"));
        out.writeObject(s1);
        out.close();
    }
    public static SaveGame load() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/resources/save/savegame"));
        SaveGame s1 = (SaveGame) in.readObject();
        in.close();
        return s1;
    }
}
