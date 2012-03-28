package shoot;

import com.golden.gamedev.object.Timer;
import com.golden.gamedev.util.Utility;

import tower.Tower;
import creep.Creep;
import level.Level;
import model.MyGame;

@SuppressWarnings("serial")
public class Missile extends Shoot{

    Timer secTimer;
    int counter;
    double dirX, dirY;

    public Missile(MyGame game, Level level, Creep target, Tower tower) {
        super(game, level, target, tower);
        counter = 4; 
        secTimer = new Timer(500);
        dirX = dirY = 0;
    }

    @Override
    public void initialize() {
        setLocation(myTower.getX(), myTower.getY());
        double t = Utility.getRandom(0, 360);
        double r = Math.toRadians(t);
        dirX = Math.cos(r);
        dirY = Math.sin(r);
        System.out.println(dirX + "    " + dirY);
        setHorizontalSpeed(dirX * movingSpeed);
        setVerticalSpeed(dirY * movingSpeed);        
    }

    @Override
    protected void updateDirection(long elapsedTime) {
        counter = 0;
        if (counter > 0) {
            if (secTimer.action(elapsedTime)) {
                counter --;
                double t = Utility.getRandom(0, 360);
                double r = Math.toRadians(t);
                dirX = Math.cos(r);
                dirY = Math.sin(r);
            }
        }
        else {
            System.out.println("YES");
            if (!myTarget.isActive()) {
                hit(myTarget);
            }
            dirX = myTarget.getX() - getX();
            dirY = myTarget.getY() - getY();
        }
        double offset = Math.sqrt(dirX*dirX + dirY*dirY);
        System.out.println(offset);
        setHorizontalSpeed(dirX * movingSpeed / offset);
        setVerticalSpeed(dirY * movingSpeed / offset);
    }

    @Override
    public void hit(Creep creep) {
        this.setActive(false);
        myLevel.removeShoot(this);
    }


}
