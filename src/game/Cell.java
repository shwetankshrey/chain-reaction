package game;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

class Cell {
    Color color;
    int x;
    int y;
    int numb;
    Pane pn;

    Cell(int xd, int yd) {
        numb = 0;
        color = null;
        pn= new Pane();
        x = 100 + xd*80;
        y = 219 + yd*80;
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
            case 1: oneSphere(); return;
            case 2: twoSphere(); return;
            case 3: threeSphere(); return;
        }
    }

    private void oneSphere() {
        Sphere sp = new Sphere(20);
        sp.setMaterial(new PhongMaterial(color)); //cs[count%numP]
        sp.setLayoutX(x);
        sp.setLayoutY(y);
        pn.getChildren().add(sp);
    }

    private void twoSphere() {
        Sphere s1 = new Sphere(20);
        Sphere s2 = new Sphere(20);
        s1.setMaterial(new PhongMaterial(color)); //cs[count%numP]
        s1.setLayoutX(x-10);
        s1.setLayoutY(y-10);
        s2.setMaterial(new PhongMaterial(color)); //cs[count%numP]
        s2.setLayoutX(x+10);
        s2.setLayoutY(y+10);
        pn.getChildren().add(s1);
        pn.getChildren().add(s2);
    }

    private void threeSphere() {
        Sphere s1 = new Sphere(20);
        Sphere s2 = new Sphere(20);
        Sphere s3 = new Sphere(20);
        s1.setMaterial(new PhongMaterial(color)); //cs[count%numP]
        s1.setLayoutX(x-10);
        s1.setLayoutY(y-10);
        s2.setMaterial(new PhongMaterial(color)); //cs[count%numP]
        s2.setLayoutX(x+10);
        s2.setLayoutY(y-10);
        s3.setMaterial(new PhongMaterial(color)); //cs[count%numP]
        s3.setLayoutX(x);
        s3.setLayoutY(y+10);
        pn.getChildren().add(s1);
        pn.getChildren().add(s2);
        pn.getChildren().add(s3);
    }
}