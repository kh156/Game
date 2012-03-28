package tower;

import java.util.List;

import creep.*;
import shoot.*;
import level.Level;
import model.*;

@SuppressWarnings("serial")
public class LaserTower extends Tower{
    
    static {
        rangeOfAttack = new double[]{80, 110, 140, 180, 300};
        PeriodOfFire = new double[]{4, 4, 4, 4, 4};  
        damagePerShoot = new double[]{10, 30, 60, 100, 400}; 
        price = new double[]{10, 10, 15, 25, 40};
        maximumLevel = 5;
        imageDirectory = new String[]{"resources/lasertower.png", "resources/lasertower.png", "resources/lasertower.png", "resources/lasertower.png", "resources/lasertower.png"};
        
        laserMovingSpeed = new double[]{0.3, 0.3, 0.3, 0.3, 0.6};
        imageDirectoryForLaser = new String[]{"resources/bullet.png", "resources/bullet.png", "resources/bullet.png", "resources/bullet.png", "resources/bullet.png"};
    }

    public LaserTower(MyGame game, Level level) {
        super(game, level);
    }

    @Override
    protected Shoot generateShoot(Creep creep) {
        return null;
    }
    
    @Override
    protected boolean fire() {
        List<Creep> creeps = myLevel.getCreeps();
        Creep target = findTarget(creeps);
        if (target == null) {
            return false;
        }
        else {
            LaserGenerator lg = new LaserGenerator(myGame, myLevel, target, this);
            List<Laser> list = lg.getLaserList();
            for (Laser l:list) {
                myLevel.addShoot(l);
            }
            return true;
        }
    }

    @Override
    public String toString() {
        return "Laser Tower";
    }


}
