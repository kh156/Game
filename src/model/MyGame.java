package model;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.*;

import tower.BlasterTower;
import tower.Tower;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.sprite.*;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.object.collision.*;

import creep.Creep;
import creep.Creep1;

import shoot.Shoot;
import level.*;


public class MyGame extends Game {

    LevelSelector mySelector;
    Level myLevel;

    public void initResources() {
        mySelector = new LevelSelector();

        System.out.println("Choose level 1 or 2:");
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        if (i == 1) {
            myLevel = new Level1(this);
        }
        else {
            myLevel = new Level2(this);
        }
        System.out.println("Try using key 1, 2, 3 ");
    }

    @Override
    public void update(long elapsedTime) {
        myLevel.update(elapsedTime);
    }

    @Override
    public void render(Graphics2D g) {
        myLevel.render(g);
    }


    public double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }


    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new MyGame(), new Dimension(640,480), false);
        game.start();
    }


}


/*
class ShadeTowerCollision extends BasicCollisionGroup {
    MyGame owner;

    public ShadeTowerCollision(MyGame owner) {
        this.owner = owner;
    }

    @Override
    public void collided(Sprite shade, Sprite tower) {
        ((Shade)shade).collided();
    }
}
*/