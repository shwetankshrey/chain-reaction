package game;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>SD GAME CONTROLLER</h1>
 * Controller class for SD grid, including current state,
 * previous states, etc.
 * @author Shwetank Shrey and Kanav Bhagat
 * @version 0.2
 * @since November 2017
 */
public class Game1Controller {

    private int count, numP;
    private Color cs[];
    private Cell grid[][];
    private Cell undogrid[][];
    private ArrayList<Button> lst = new ArrayList<Button>();
    @FXML private Pane root;
    @FXML private Button undoB;

    /**
     * Initializes the FXML. Hides the UNDO button,
     * adds important parameters to the current gamestate,
     * creates and initialises new grids for current and
     * previous states, and adds pane for each cell to
     * the main root pane. Also starts the count and gives
     * initial colors to the grid.
     */
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
                root.getChildren().add(grid[i][j].getPn());
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

    /**
     * Event Handler for the button that restarts the game
     * by clearing the grid and creating a new instance of the FXML.
     * @param event Click Triggered Event
     * @throws Exception Any Exception
     */
    @FXML protected void restart(ActionEvent event) throws Exception {
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/game1.fxml")), 600, 900);
        GameState.mainStage.setScene(s);
    }

    /**
     * Event Handler for the Save button. Adds necessary
     * properties to the SaveGame static class properties
     * and uses the serialize in Main to save the state.
     * @param event Click Triggered Event
     * @throws Exception Any Exception
     */
    @FXML protected void save(ActionEvent event) throws Exception {
        SaveGame s1 = new SaveGame();
        s1.numPlayers = GameState.numPlayers;
        s1.colorCodes = GameState.colorCodes;
        s1.grid = grid;
        s1.count = count;
        Main.save(s1);
    }

    /**
     * Event Handler for the Exit Button to take the
     * user to the Home Screen.
     * @param event Click Triggered Event
     * @throws Exception Any Exception
     */
    @FXML protected void goHome(ActionEvent event) throws Exception {
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/home.fxml")), 600, 900);
        GameState.mainStage.setScene(s);
    }

    /**
     * Event Handler for the Game. The grid is comprised of
     * buttons arranged in a grid format. For every button
     * pressed the coordinated are checked and the
     * appropriate trigger cell is decided. While saving
     * the previous grid state, the grid is altered and
     * the number of orbs is increased. The appropriate
     * color is set for the cell and the grid is
     * checked for explosions. Then the grid color
     * is changed for the next player.
     * @param event Click Triggered Event
     * @throws Exception Any Exception
     */
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
        count++;
        for (Node ix : root.getChildren()) {
            if (ix instanceof Rectangle)
                ((Rectangle) ix).setStroke(cs[count%numP]);
            if (ix instanceof Line)
                ((Line) ix).setStroke(cs[count%numP]);
        }
        for (Button ix : lst) {
            ix.toFront();
        }
    }

    /**
     * Checks possibility of explosion for specified
     * cell, if exists, then explodes it by changing
     * numbers of the cells and using transitions to
     * show the animation and checks for explosions
     * in the affected cells.
     * @param x X Coordinate in Grid
     * @param y Y Coordinate in Grid
     */
    private void checkExplosion(int x, int y) {
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
            grid[x][y].clk();
            GameState.sfx.play();
            Group p = new Group();
            root.getChildren().add(p);
            TranslateTransition[] tt = new TranslateTransition[4];
            int i = 0;
            if(x!=0) {
                Sphere cr = new Sphere(20);
                cr.setLayoutX(100+x*80);
                cr.setLayoutY(219+y*80);
                cr.setMaterial(new PhongMaterial(grid[x][y].getColor()));
                p.getChildren().add(cr);
                tt[i] = new TranslateTransition(Duration.millis(500),cr);
                tt[i].setByX(-80f);
                tt[i].setCycleCount(1);
                i++;
            }
            if(x!=5) {
                Sphere cr = new Sphere(20);
                cr.setLayoutX(100+80*x);
                cr.setLayoutY(219+80*y);
                cr.setMaterial(new PhongMaterial(grid[x][y].getColor()));
                p.getChildren().add(cr);
                tt[i] = new TranslateTransition(Duration.millis(500),cr);
                tt[i].setByX(80f);
                tt[i].setCycleCount(1);
                i++;
            }
            if(y!=0) {
                Sphere cr = new Sphere(20);
                cr.setLayoutX(100+80*x);
                cr.setLayoutY(219+80*y);
                cr.setMaterial(new PhongMaterial(grid[x][y].getColor()));
                p.getChildren().add(cr);
                tt[i] = new TranslateTransition(Duration.millis(500),cr);
                tt[i].setByY(-80f);
                tt[i].setCycleCount(1);
                i++;
            }
            if(y!=7) {
                Sphere cr = new Sphere(20);
                cr.setLayoutX(100+80*x);
                cr.setLayoutY(219+80*y);
                cr.setMaterial(new PhongMaterial(grid[x][y].getColor()));
                p.getChildren().add(cr);
                tt[i] = new TranslateTransition(Duration.millis(500),cr);
                tt[i].setByY(80f);
                tt[i].setCycleCount(1);
                i++;
            }
            grid[x][y].setColor(null);
            ParallelTransition pt = new ParallelTransition();
            switch (i) {
                case 1: pt = new ParallelTransition(tt[0]); break;
                case 2: pt = new ParallelTransition(tt[0],tt[1]); break;
                case 3: pt = new ParallelTransition(tt[0],tt[1],tt[2]); break;
                case 4: pt = new ParallelTransition(tt[0],tt[1],tt[2],tt[3]); break;
            }
            pt.play();
            pt.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    p.getChildren().clear();
                    root.getChildren().remove(p);
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
            });
        }
        return;
    }

    /**
     * Checks if the current player has won the
     * game or not. Traverses through the grid
     * looking for a common color.
     * @throws IOException Input Output Exception
     */
    private void checkGame() throws IOException {
        if(count < numP) {
            return;
        }
        Color cx = null;
        for(int i = 0 ; i < 6 ; i++) {
            for(int j = 0 ; j < 8 ; j++) {
                if(cx == null && grid[i][j].getColor() != null) {
                    cx = grid[i][j].getColor();
                    break;
                }
            }
        }
        for(int i = 0 ; i < 6 ; i++) {
            for(int j = 0 ; j < 8 ; j++) {
                if(grid[i][j].getColor() != null && cx != grid[i][j].getColor()) {
                    return;
                }
            }
        }
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/winner.fxml")), 550, 250);
        Stage stagex = new Stage();
        stagex.setScene(s);
        stagex.setTitle("Player " + (count%numP + 1) + " WINS!!");
        stagex.show();
    }

    /**
     * Event handler for the UNDO button. Previous grid
     * is set as the current grid and the UNDO button
     * is hidden to prevent misuse.
     * @param event Click Triggered Event
     * @throws Exception Any Exception
     */
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
