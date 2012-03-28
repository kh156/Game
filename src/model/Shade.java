package model;

import java.awt.event.KeyEvent;
import java.util.List;

import level.Level;

import tower.BlasterTower;
import tower.LaserTower;
import tower.MissileTower;
import tower.ShockTower;
import tower.Tower;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class Shade extends Sprite{

    private MyGame myGame;
    private Level  myLevel;
    boolean collided, tempCollided;
    Circle myCircle;
    int myTowerCode;

    static String imageDirectory = "resources/shade.png";
    public static String collisionImageDirectory = "resources/shade_collided.png"; 

    public Shade(MyGame game, Level level) {
        super(game.getImage(imageDirectory));
        myGame = game;
        myLevel = level;
        collided = false;
        myCircle = level.getCircle();
        this.setImmutable(true);
        setActive(false);
    }

    @Override
    public void update(long elapsedTime) {
        super.update(elapsedTime);
            setLocation(myGame.getMouseX()-10, myGame.getMouseY()-10); 
            Tower tower = generateTower();
            tower.setLocation(-999, -999);
            myCircle.setHidden(false);
            myCircle.setAndDraw(myGame.getMouseX(), myGame.getMouseY(), tower.getRangeOfAttack());
            //checkCollision();
            if (myGame.click() && !collided) {
                tower.setLocation(getX(), getY());
                myLevel.buyTower(tower);
                setActive(false);
            }
    }

/*    
    private void checkCollision() {
        collided = false;
        List<Tower> list = myLevel.getTowers();
        for (Tower t:list) {
            if (myGame.getDistance(getX(), getY(), t.getX(), t.getY()) < 20.0) {
                setCollided(true);
            }
        }
        setCollided(false);
    }
    */
    
    public void setCollided() {
        collided = tempCollided;
        if (collided) {
            setImage(myGame.getImage(collisionImageDirectory));
        }
        else {
            setImage(myGame.getImage(imageDirectory));
        }

    }

    public void setTempCollided(boolean flag) {
        tempCollided = flag;
    }
    
    public void setTowerSelection(int t) {
        myTowerCode = t;
    }
    
    public Tower generateTower() {
        switch (myTowerCode) {
        case KeyEvent.VK_1:
            return new BlasterTower(myGame, myLevel);
        case KeyEvent.VK_2:
            return new LaserTower(myGame, myLevel);
        case KeyEvent.VK_3:
            return new MissileTower(myGame, myLevel);
        case KeyEvent.VK_4:
            return new ShockTower(myGame, myLevel);
        default: return null;
        }
    }

}
