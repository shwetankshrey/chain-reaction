package game;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

class Cell {
    Color color;
    int x;
    int y;
    int wd;
    int numb;
    Pane pn;
    int rad;

    Cell(int xd, int yd, int xs, int ys, int wd, int bs) {
        numb = 0;
        color = null;
        pn= new Pane();
        this.wd = wd;
        x = xs + xd*wd;
        y = ys + yd*wd;
        rad = bs;
    }

    public void copy(Cell c) {
        this.color = c.color;
        this.x = c.x;
        this.y = c.y;
        this.numb = c.numb;
        this.pn = c.pn;
        this.rad = c.rad;
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean noColor() {
        return color==null;
    }

    public void clk() {
        pn.getChildren().clear();
        switch (numb) {
            case 0: return;
            case 1: oneSphere(); return;
            case 2: twoSphere(); return;
            case 3: threeSphere(); return;
        }
    }

    private void oneSphere() {
        Sphere sp = new Sphere(rad);
        sp.setMaterial(new PhongMaterial(color));
        sp.setLayoutX(x);
        sp.setLayoutY(y);
        /*
        RotateTransition rt = new RotateTransition(Duration.millis(1000), pn);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setCycleCount(Timeline.INDEFINITE);
        rt.setAxis(Rotate.Z_AXIS);
        rt.setByAngle(360);
        rt.setAutoReverse(false);
        rt.play();
        */
        pn.getChildren().add(sp);
    }

    private void twoSphere() {
        Sphere s1 = new Sphere(rad);
        Sphere s2 = new Sphere(rad);
        s1.setMaterial(new PhongMaterial(color));
        s1.setLayoutX(x-10);
        s1.setLayoutY(y-10);
        s2.setMaterial(new PhongMaterial(color));
        s2.setLayoutX(x+10);
        s2.setLayoutY(y+10);
        /*
        RotateTransition rt = new RotateTransition(Duration.millis(1000), pn);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setCycleCount(Timeline.INDEFINITE);
        rt.setAxis(Rotate.Z_AXIS);
        rt.setByAngle(360);
        rt.setAutoReverse(false);
        rt.play();
        */
        pn.getChildren().add(s1);
        pn.getChildren().add(s2);
    }

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
        /*
        RotateTransition rt = new RotateTransition(Duration.millis(1000), pn);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setCycleCount(Timeline.INDEFINITE);
        rt.setAxis(Rotate.Z_AXIS);
        rt.setByAngle(360);
        rt.setAutoReverse(false);
        rt.play();
        */
        pn.getChildren().add(s1);
        pn.getChildren().add(s2);
        pn.getChildren().add(s3);
    }

    public void expRight() {
        Sphere cr = new Sphere(rad);
        cr.setLayoutX(x);
        cr.setLayoutY(y);
        //cr.setMaterial(new PhongMaterial(color));
        Timeline tml = new Timeline();
        tml.setCycleCount(1);
        KeyFrame movePlane = new KeyFrame(Duration.millis(500), new KeyValue(cr.translateXProperty(), wd));
        tml.getKeyFrames().add(movePlane);
        tml.play();
        pn.getChildren().add(cr);
        tml.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pn.getChildren().clear();
            }
        });
    }

    public void expLeft() {
        Sphere cr = new Sphere(rad);
        cr.setLayoutX(x);
        cr.setLayoutY(y);
        //cr.setMaterial(new PhongMaterial(color));
        Timeline tml = new Timeline();
        tml.setCycleCount(1);
        KeyFrame movePlane = new KeyFrame(Duration.millis(500), new KeyValue(cr.translateXProperty(), -wd));
        tml.getKeyFrames().add(movePlane);
        tml.play();
        pn.getChildren().add(cr);
        tml.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pn.getChildren().clear();
            }
        });
    }

    public void expUp() {
        Sphere cr = new Sphere(rad);
        cr.setLayoutX(x);
        cr.setLayoutY(y);
        //cr.setMaterial(new PhongMaterial(color));
        Timeline tml = new Timeline();
        tml.setCycleCount(1);
        KeyFrame movePlane = new KeyFrame(Duration.millis(500), new KeyValue(cr.translateYProperty(), -wd));
        tml.getKeyFrames().add(movePlane);
        tml.play();
        pn.getChildren().add(cr);
        tml.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pn.getChildren().clear();
            }
        });
    }

    public void expDown() {
        Sphere cr = new Sphere(rad);
        cr.setLayoutX(x);
        cr.setLayoutY(y);
        //cr.setMaterial(new PhongMaterial(color));
        Timeline tml = new Timeline();
        tml.setCycleCount(1);
        KeyFrame movePlane = new KeyFrame(Duration.millis(500), new KeyValue(cr.translateYProperty(), wd));
        tml.getKeyFrames().add(movePlane);
        tml.play();
        pn.getChildren().add(cr);
        tml.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pn.getChildren().clear();
            }
        });
    }
}