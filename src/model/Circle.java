package model;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import level.Level;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class Circle extends SpriteGroup{
    private MyGame myGame;
    private Level  myLevel;
    boolean hidden;
    
    public Circle(MyGame game, Level level) {
        super("Circle");
        myGame = game;
        myLevel = level;
        hidden = true;
    }
    
    public void setAndDraw(double x, double y, double r) {
        this.clear();
        double diff = 0.5;
        for (double d=0; d<=360; d+=diff) {
            Sprite one = new Sprite(myGame.getImage("resources/bullet.png"));
            double corX = x + r * Math.cos(Math.toRadians(d));
            double corY = y + r * Math.sin(Math.toRadians(d));
            one.setLocation(corX - 4.0, corY - 4.0);
            this.add(one);
        }
    }
    
    public void render(Graphics2D g) {
        if (!hidden) {
            super.render(g);
        }
    }
    
    public void setHidden(boolean flag) {
        hidden = flag;
    }
}
