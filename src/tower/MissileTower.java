package tower;

import creep.*;
import shoot.*;
import level.Level;
import model.*;

@SuppressWarnings("serial")
public class MissileTower extends Tower{
    
    static {
        rangeOfAttack = new double[]{100, 120, 140, 180, 300};
        PeriodOfFire = new double[]{3, 2.4, 1.8, 1.4, 3};  
        damagePerShoot = new double[]{40, 80, 160, 320, 700}; 
        price = new double[]{10, 10, 15, 25, 40};
        maximumLevel = 5;
        imageDirectory = new String[]{"resources/missiletower.png", "resources/missiletower.png", "resources/missiletower.png", "resources/missiletower.png", "resources/missiletower.png"};
        
        laserMovingSpeed = new double[]{0.18, 0.18, 0.18, 0.18, 0.18};
        imageDirectoryForLaser = new String[]{"resources/bullet.png", "resources/bullet.png", "resources/bullet.png", "resources/bullet.png", "resources/bullet.png"};
    }

    public MissileTower(MyGame game, Level level) {
        super(game, level);
    }

    @Override
    protected Shoot generateShoot(Creep creep) {
        Shoot one = new Missile(myGame, myLevel, creep, this);
        return one;
    }

    @Override
    public String toString() {
        return "Laser Tower";
    }

}
