package shoot;

import tower.Tower;
import level.Level;
import model.MyGame;

import com.golden.gamedev.object.Sprite;

import creep.Creep;

@SuppressWarnings("serial")
public abstract class Shoot extends Sprite{

    protected MyGame myGame;
    protected Level myLevel;
    protected Creep myTarget;
    protected Tower myTower;
    protected double movingSpeed;
    protected double damage;
    
    public Shoot(MyGame game, Level level, Creep target, Tower tower) {
        super(game.getImage(tower.getImageDirectoryForShoot()));
        myGame = game;
        myLevel = level;
        myTarget = target;
        myTower = tower;
        movingSpeed = tower.getMovingSpeedForShoot();
        damage = tower.getDamagePerShoot();
        initialize();
    }
    
    public abstract void initialize(); 
    
    public void update(long elapsedTime) {
        super.update(elapsedTime);
        updateDirection(elapsedTime);
    }
    
    public double getDamage() {
        return damage;
    }
    
    protected abstract void updateDirection(long elapsedTime);
    
    public abstract void hit(Creep creep);
    
}
