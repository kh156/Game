package model;

import java.util.ArrayList;
import java.util.List;

import level.Level;
import shoot.Laser;
import tower.Tower;
import creep.Creep;

public class LaserGenerator {

    private MyGame myGame;
    private Level myLevel;
    private Creep myTarget;
    private Tower myTower;
    private List<Laser> laserList;

    
    public LaserGenerator(MyGame game, Level level, Creep target, Tower tower) {
        myGame = game;
        myLevel = level;
        myTarget = target;
        myTower = tower;
        laserList = new ArrayList<Laser>();
        initialize();
    }
    
    private void initialize() {
        double dirX = myTarget.getX() - myTower.getX();
        double dirY = myTarget.getY() - myTower.getY();
        double dist = Math.sqrt(dirX*dirX + dirY*dirY);
        //double dist = myGame.getDistance(myTarget.getX(), myTarget.getY(), myTower.getX(), myTower.getY());
        
        double currX = myTower.getX() + 5, currY = myTower.getY() + 5;
        while (currX > 0 && currX < 640 && currY > 0 && currY < 480) {
            currX += dirX / dist * 2.0;
            currY += dirY / dist * 2.0;
            Laser bullet = new Laser(myGame, myLevel, myTarget, myTower);
            bullet.setLocation(currX, currY);
            laserList.add(bullet);
        }
    }
    
    public List<Laser> getLaserList() {
        return laserList;
    }

    

}
