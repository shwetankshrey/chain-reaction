package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        Pane root = new Pane();
        Scene home = new Scene(root, 600, 800);
        Sphere s = new Sphere(50);
        Sphere s2 = new Sphere(50);
        Rectangle r = new Rectangle(100, 100, 100, 100 );
        s.setTranslateX(300);
        s.setTranslateY(400);
        s2.setTranslateX(330);
        s2.setTranslateY(400);
        RotateTransition rx = new RotateTransition(Duration.seconds(2), r);
        rx.setToAngle(360);
        rx.play();
        root.getChildren().add(s);
        root.getChildren().add(r);
        root.getChildren().add(s2);
        */
        Scene home = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/home.fxml")), 600, 900);
        primaryStage.setTitle("Chain Reaction");
        primaryStage.setScene(home);
        GameState.mainStage = primaryStage;
        GameState.mainStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
