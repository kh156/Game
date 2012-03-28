package creep;

import shoot.Shoot;
import level.*;
import model.MyGame;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public abstract class Creep extends Sprite{
    final private static double DIFF_OFFSET = 1.5;
    
    protected static double defaultSpeed, defaultHP, defaultEXP;
    protected static String imageDirectory;
    
    private double speed, HP, EXP;
    private Level myLevel;
    private MyGame myGame;

    private int indexInPath;
    private double dirX, dirY;
        
    public Creep(MyGame game, Level level, double HP) {
        super(game.getImage(imageDirectory));
        myLevel = level;
        myGame = game;
        indexInPath = 0;
        speed = defaultSpeed * myLevel.getCreepSpeedScale();
//        HP = defaultHP * myLevel.getCreepHPScale();
        this.HP =  HP;
        EXP = defaultEXP * myLevel.getCreepEXPScale();
        updateAtTurns();
    }
    
    public double getEXP() {
        return EXP;
    }
    
    public void setDirX(double dir) {
        dirX = dir;
    }

    public void setDirY(double dir) {
        dirY = dir;
    }
    
    public int getIndexInPath() {
        return indexInPath;
    }
    
    @Override
    public void update(long elapsedTime) {
        super.update(elapsedTime);
        checkAtTurns(elapsedTime);
    }
    
    private void checkAtTurns(long elapsedTime) {
        double targetX = myLevel.getCorXForCreep(indexInPath);
        double targetY = myLevel.getCorYForCreep(indexInPath);
        double dist = myGame.getDistance(getX(), getY(), targetX, targetY);
        if (dist < DIFF_OFFSET) {
            if (checkFinished())
                return;
            updateAtTurns();
        }
//        System.out.println(String.format("X : %.0f      Y : %.0f", getX(), getY()));
    }
    
    private boolean checkFinished() {
        if (myLevel.checkFinishedForCreep(indexInPath)) {
            setActive(false);
            myLevel.oneCreepMissed(this);
            return true;
        }
        return false;
    }
    
    private void updateAtTurns() {
        setLocation(myLevel.getCorXForCreep(indexInPath), myLevel.getCorYForCreep(indexInPath));
        
        indexInPath ++;
        dirX = myLevel.getCorXForCreep(indexInPath) - getX();
        dirY = myLevel.getCorYForCreep(indexInPath) - getY();
        double offset = Math.sqrt(dirX*dirX + dirY*dirY);
        setHorizontalSpeed(dirX * speed / offset);
        setVerticalSpeed(dirY * speed / offset);
    }

    public void gotShot(Shoot shoot) {
        HP -= shoot.getDamage();
        checkDead();
    }
    
    private void checkDead() {
        if (HP <= 0) {
            setActive(false);
            myLevel.oneCreepKilled(this);
        }
    }
}
