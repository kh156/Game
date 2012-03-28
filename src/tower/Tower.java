package tower;


import java.util.List;

import shoot.Shoot;
import level.Level;
import model.MyGame;
import model.UpgradePanel;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

import creep.Creep;

@SuppressWarnings("serial")
public abstract class Tower extends Sprite{

    protected static double rangeOfAttack[], PeriodOfFire[], damagePerShoot[], price[], laserMovingSpeed[];  
    protected static int maximumLevel;
    protected static String imageDirectory[], imageDirectoryForLaser[];

    protected int level;
    protected double totalPrice;
    protected MyGame myGame;
    protected Level myLevel;

    private Timer timer;
    private boolean triggered;

    public Tower(MyGame game, Level level) {
        super(game.getImage(imageDirectory[0]));
        myGame = game;
        myLevel = level;
        timer = new Timer((int) (PeriodOfFire[this.level] * 1000));
        triggered = true;
        this.level = 0;
        totalPrice = price[0];
    }

    public void update(long elapsedTime) {
        super.update(elapsedTime);
        if (timer.action(elapsedTime)) {
            triggered = true; 
        }
        if (triggered) {
            if (fire()) {
                triggered = false;
                timer.refresh();
            }
        }
        if (myGame.click()) {
            UpgradePanel upgradePanel = myLevel.getUpgradePanel();
            if (myGame.getDistance(getX()+10.0, getY()+10.0, myGame.getMouseX(), myGame.getMouseY()) < 12.0) {
                upgradePanel.setHidden(false);
                upgradePanel.setParametes(this);
            }
            else {
            }
        }
    }

    public void upgrade() {
        level++;
        totalPrice += price[level];
        triggered = true;
    }

    public void sell() {
        setActive(false);
    }

    protected boolean fire() {
        List<Creep> creeps = myLevel.getCreeps();
        Creep target = findTarget(creeps);
        if (target == null) {
            return false;
        }
        else {
            Shoot shoot = generateShoot(target);
            myLevel.addShoot(shoot);
            return true;
        }
    }

    protected abstract Shoot generateShoot(Creep creep);


    protected Creep findTarget(List<Creep> creeps){
        for (Creep c:creeps) {
            double dist = myGame.getDistance(getX(), getY(), c.getX(), c.getY());
            if (dist <= rangeOfAttack[level]){
                return c;
            }
        }
        return null;
    }

    public String getImageDirectoryForShoot(){
        return imageDirectoryForLaser[level];
    }

    public double getMovingSpeedForShoot() {
        return laserMovingSpeed[level];
    }

    public double getDamagePerShoot() {
        return damagePerShoot[level];
    }

    public int getLevel() {
        return level;
    }
    
    public double getRangeOfAttack() {
        return rangeOfAttack[level];
    }

    public double getUpgradePrice() {
        if (level+1 == maximumLevel)
            return 9999;
        else
            return price[level];
    }

    public double getSalePrice() {
        return totalPrice / 2.0;
    }
    
    public abstract String toString();
    

}

