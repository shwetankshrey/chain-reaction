package game;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.audio.AudioPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Game1Controller {

    private int count, numP;
    private Color cs[];
    private Cell grid[][];
    private Cell undogrid[][];
    private ArrayList<Button> lst = new ArrayList<Button>();
    @FXML private Pane root;
    @FXML private Button undoB;

    @FXML public void initialize() {
        undoB.setVisible(false);
        numP = GameState.numPlayers;
        cs = new Color[numP];
        grid = new Cell[6][8];
        undogrid = new Cell[6][8];
        for(int i = 0 ; i < 6 ; i++) {
            for(int j = 0 ; j < 8 ; j++) {
                if(GameState.saveGame == 1) {
                    grid[i][j] = SaveGame.grid[i][j];
                    undogrid[i][j] = SaveGame.grid[i][j];
                }
                else {
                    grid[i][j] = new Cell(i, j, 100, 219, 80, 20);
                    undogrid[i][j] = new Cell(i, j, 100, 219, 80, 20);
                }
                root.getChildren().add(grid[i][j].pn);
            }
        }
        if(GameState.saveGame == 1) {
            count = SaveGame.count;
        }
        else {
            count = 0;
        }
        GameState.saveGame = 0;
        for(int i = 0 ; i < numP ; i++) {
            cs[i] = GameState.colorCodes[i];
        }
        List<Node> l = root.getChildren();
        for (Node i : l) {
            if(i instanceof Rectangle) {
                ((Rectangle) i).setStroke(cs[count%numP]);
            }
            if(i instanceof Line) {
                ((Line) i).setStroke(cs[count%numP]);
            }
            if(i instanceof Button)
                lst.add((Button) i);
        }
    }

    @FXML protected void restart(ActionEvent event) throws Exception {
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/game1.fxml")), 600, 900);
        GameState.mainStage.setScene(s);
    }

    @FXML protected void save(ActionEvent event) throws Exception {
        SaveGame s1 = new SaveGame();
        s1.numPlayers = GameState.numPlayers;
        s1.colorCodes = GameState.colorCodes;
        s1.grid = grid;
        s1.count = count;
        Main.save(s1);
    }

    @FXML protected void goHome(ActionEvent event) throws Exception {
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/home.fxml")), 600, 900);
        GameState.mainStage.setScene(s);
    }

    @FXML protected void clk(ActionEvent event) throws Exception {
        Button rx = (Button) event.getSource();
        int xx = ((int) rx.getLayoutX()-60)/80;
        int yy = ((int) rx.getLayoutY()-179)/80;
        int i = count%numP;
        if(grid[xx][yy].noColor()) {
            for(int ix = 0 ; ix < 6 ; ix++) {
                for(int j = 0 ; j < 8 ; j++) {
                    undogrid[ix][j].copy(grid[ix][j]);
                }
            }
            grid[xx][yy].numb++;
            grid[xx][yy].setColor(cs[i]);
            grid[xx][yy].clk();
            checkExplosion(xx, yy);
        }
        else {
            if(grid[xx][yy].getColor() != cs[i]) {
                return;
            }
            for(int ix = 0 ; ix < 6 ; ix++) {
                for(int j = 0 ; j < 8 ; j++) {
                    undogrid[ix][j].copy(grid[ix][j]);
                }
            }
            grid[xx][yy].numb++;
            grid[xx][yy].clk();
            checkExplosion(xx, yy);
        }
        undoB.setVisible(true);
        checkGame();
        List<Node> l = root.getChildren();
        for (Button ix : lst) {
            ix.toFront();
        }
        /*
        System.out.println(count);
        if(count >= numP) {
            boolean playerCheck = true;
            while (playerCheck) {
                count++;
                Color cx = cs[count % numP];
                for (int ix = 0; ix < 6; ix++) {
                    for (int j = 0; j < 8; j++) {
                        if (cx == grid[i][j].getColor()) {
                            playerCheck = false;
                            break;
                        }
                    }
                }
            }
        }
        else {
            count++;
        }
        */
        count++;
        for (Node ix : l) {
            if (ix instanceof Rectangle)
                ((Rectangle) ix).setStroke(cs[count%numP]);
            if (ix instanceof Line)
                ((Line) ix).setStroke(cs[count%numP]);
        }
    }

    private void checkExplosion(int x, int y) throws Exception {
        boolean toExp = false;
        Color col = grid[x][y].getColor();
        if((x==0 && y ==0) || (x==5 && y==0) || (x==5 && y==7) || (x==0 & y==7)) {
            if (grid[x][y].numb > 1) {
                toExp = true;
            }
        }
        else if(x==0 || x==5 || y==0 || y==7) {
            if(grid[x][y].numb > 2) {
                toExp = true;
            }
        }
        else {
            if (grid[x][y].numb > 3) {
                toExp = true;
            }
        }
        if(toExp) {
            grid[x][y].numb = 0;
            grid[x][y].setColor(null);
            grid[x][y].clk();
            //GameState.sfx.play();
            if(x!=0) {
                grid[x][y].expLeft();
            }
            if(x!=5) {
                grid[x][y].expRight();
            }
            if(y!=0) {
                grid[x][y].expUp();
            }
            if(y!=7) {
                grid[x][y].expDown();
            }
            if(x!=0) {
                grid[x-1][y].numb++;
                grid[x-1][y].setColor(col);
                grid[x-1][y].clk();
                checkExplosion(x-1,y);
            }
            if(x!=5) {
                grid[x+1][y].numb++;
                grid[x+1][y].setColor(col);
                grid[x+1][y].clk();
                checkExplosion(x+1,y);
            }
            if(y!=0) {
                grid[x][y-1].numb++;
                grid[x][y-1].setColor(col);
                grid[x][y-1].clk();
                checkExplosion(x,y-1);
            }
            if(y!=7) {
                grid[x][y+1].numb++;
                grid[x][y+1].setColor(col);
                grid[x][y+1].clk();
                checkExplosion(x,y+1);
            }
        }
        return;
    }

    private void checkGame() throws IOException {
        if(count < 2) {
            return;
        }
        Color cx = null;
        for(int i = 0 ; i < 6 ; i++) {
            for(int j = 0 ; j < 8 ; j++) {
                if(cx != null && grid[i][j].getColor() != null && cx != grid[i][j].getColor()) {
                    return;
                }
                if(cx == null && grid[i][j].getColor() != null) {
                    cx = grid[i][j].getColor();
                }
            }
        }
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/winner.fxml")), 550, 250);
        Stage stagex = new Stage();
        stagex.setScene(s);
        stagex.setTitle("Player " + (count%numP + 1) + " WINS!!");
        stagex.show();
    }
    @FXML protected void undo(ActionEvent event) throws Exception {
        List<Node> l = root.getChildren();
        grid = new Cell[6][8];
        for(int i = 0 ; i < 6 ; i++) {
            for(int j = 0 ; j < 8 ; j++) {
                grid[i][j] = undogrid[i][j];
                grid[i][j].copy(undogrid[i][j]);
                grid[i][j].clk();
            }
        }
        count--;
        undoB.setVisible(false);
        for (Node i : l) {
            if (i instanceof Rectangle) {
                ((Rectangle) i).setStroke(cs[count % numP]);
            }
            if (i instanceof Line) {
                ((Line) i).setStroke(cs[count % numP]);
            }
        }
    }
}
