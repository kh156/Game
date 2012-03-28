package shoot;

import tower.Tower;
import creep.Creep;
import level.Level;
import model.MyGame;

@SuppressWarnings("serial")
public class Blaster extends Shoot{
    

    public Blaster(MyGame game, Level level, Creep target, Tower tower) {
        super(game, level, target, tower);
    }

    @Override
    public void initialize() {
        setLocation(myTower.getX(), myTower.getY());
        updateDirection(0);
    }

    @Override
    protected void updateDirection(long elapsedTime) {
        if (!myTarget.isActive()) {
            hit(myTarget);
        }
        double dirX = myTarget.getX() - getX();
        double dirY = myTarget.getY() - getY();
        double offset = Math.sqrt(dirX*dirX + dirY*dirY);
        setHorizontalSpeed(dirX * movingSpeed / offset);
        setVerticalSpeed(dirY * movingSpeed / offset);
    }

    @Override
    public void hit(Creep creep) {
        this.setActive(false);
        myLevel.removeShoot(this);
    }
    
    
}
