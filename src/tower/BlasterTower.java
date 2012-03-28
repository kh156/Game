package tower;

import creep.*;
import shoot.*;
import level.Level;
import model.*;

@SuppressWarnings("serial")
public class BlasterTower extends Tower{
    
    static {
        rangeOfAttack = new double[]{60, 90, 140, 200, 300};
        PeriodOfFire = new double[]{0.4, 0.4, 0.4, 0.4, 1.2};  
        damagePerShoot = new double[]{10, 30, 60, 100, 400}; 
        price = new double[]{10, 10, 15, 25, 40};
        maximumLevel = 5;
        imageDirectory = new String[]{"resources/blastertower.png", "resources/blastertower.png", "resources/blastertower.png", "resources/blastertower.png", "resources/blastertower.png"};
        
        laserMovingSpeed = new double[]{0.4, 0.4, 0.4, 0.4, 0.6};
        imageDirectoryForLaser = new String[]{"resources/bullet.png", "resources/bullet.png", "resources/bullet.png", "resources/bullet.png", "resources/bullet.png"};
    }

    public BlasterTower(MyGame game, Level level) {
        super(game, level);
    }

    @Override
    protected Shoot generateShoot(Creep creep) {
        Shoot one = new Blaster(myGame, myLevel, creep, this);
        return one;
    }

    @Override
    public String toString() {
        return "Blaster Tower";
    }
    
    

}
