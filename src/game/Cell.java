package game;

import javafx.animation.*;
import javafx.geometry.Point3D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

/**
 * <h1>CELL CLASS</h1>
 * This class represents the individual element
 * in a grid. Every cell has a pane of its own
 * where the orbs are added. Every cell also
 * has a unique color.
 *
 * @author Shwetank Shrey and Kanav Bhagat
 * @version 0.2
 * @since November 2017
 */
class Cell {
    private Color color;
    private int x;
    private int y;
    public int numb;
    private Pane pn;
    private int rad;

    /**
     * Constructor for Cell class.
     * @param xd X-Value
     * @param yd Y-Value
     * @param xs X-Centre1
     * @param ys Y-Centre1
     * @param wd Width
     * @param bs Ball Radius
     */
    Cell(int xd, int yd, int xs, int ys, int wd, int bs) {
        numb = 0;
        color = null;
        pn= new Pane();
        x = xs + xd*wd;
        y = ys + yd*wd;
        rad = bs;
    }

    /**
     * Method for copy.
     * @param c Cell to be copied from
     */
    public void copy(Cell c) {
        this.color = c.color;
        this.x = c.x;
        this.y = c.y;
        this.numb = c.numb;
        this.pn = c.pn;
        this.rad = c.rad;
    }

    /**
     * Getter for Cell Pane.
     * @return Pane
     */
    public Pane getPn() {return pn;}

    /**
     * Getter for Cell Color.
     * @return Color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Getter for Radius.
     * @return Radius
     */
    public int getRad() { return rad; }

    /**
     * Setter for Cell Color.
     * @param color Cell Color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Checks if Cell has any Color or not.
     * @return Boolean value accordingly
     */
    public boolean noColor() {
        return color==null;
    }

    /**
     * Adds orbs to cell according to the cell state.
     */
    public void clk() {
        pn.getChildren().clear();
        switch (numb) {
            case 0: return;
            case 1: oneSphere(); return;
            case 2: twoSphere(); return;
            case 3: threeSphere(); return;
        }
    }

    /**
     * Creates a sphere, adds a rotation transition
     * and adds it to the cell pane.
     */
    private void oneSphere() {
        Sphere sp = new Sphere(rad);
        sp.setMaterial(new PhongMaterial(color));
        sp.setLayoutX(x);
        sp.setLayoutY(y);
        RotateTransition rt = new RotateTransition(Duration.millis(1000), pn);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setCycleCount(Timeline.INDEFINITE);
        rt.setAxis(new Point3D(x,y,5));
        rt.setByAngle(360);
        rt.setAutoReverse(false);
        rt.play();
        pn.getChildren().add(sp);
    }

    /**
     * Creates two spheres, adds a rotation transition
     * and adds it to the cell pane.
     */
    private void twoSphere() {
        Sphere s1 = new Sphere(rad);
        Sphere s2 = new Sphere(rad);
        s1.setMaterial(new PhongMaterial(color));
        s1.setLayoutX(x-10);
        s1.setLayoutY(y-10);
        s2.setMaterial(new PhongMaterial(color));
        s2.setLayoutX(x+10);
        s2.setLayoutY(y+10);
        RotateTransition rt = new RotateTransition(Duration.millis(1000), pn);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setCycleCount(Timeline.INDEFINITE);
        rt.setAxis(new Point3D(x,y,5));
        rt.setByAngle(360);
        rt.setAutoReverse(false);
        rt.play();
        pn.getChildren().add(s1);
        pn.getChildren().add(s2);
    }

    /**
     * Creates three spheres, adds a rotation transition
     * and adds it to the cell pane.
     */
    private void threeSphere() {
        Sphere s1 = new Sphere(rad);
        Sphere s2 = new Sphere(rad);
        Sphere s3 = new Sphere(rad);
        s1.setMaterial(new PhongMaterial(color));
        s1.setLayoutX(x-10);
        s1.setLayoutY(y-10);
        s2.setMaterial(new PhongMaterial(color));
        s2.setLayoutX(x+10);
        s2.setLayoutY(y-10);
        s3.setMaterial(new PhongMaterial(color));
        s3.setLayoutX(x);
        s3.setLayoutY(y+10);
        RotateTransition rt = new RotateTransition(Duration.millis(1000), pn);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setCycleCount(Timeline.INDEFINITE);
        rt.setAxis(new Point3D(x,y,5));
        rt.setByAngle(360);
        rt.setAutoReverse(false);
        rt.play();
        pn.getChildren().add(s1);
        pn.getChildren().add(s2);
        pn.getChildren().add(s3);
    }
}