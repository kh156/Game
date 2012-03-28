package shoot;

import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

import tower.Tower;
import creep.Creep;
import level.Level;
import model.MyGame;

@SuppressWarnings("serial")
public class Laser extends Shoot{

    Timer timer;
    boolean powerOff;

    public Laser(MyGame game, Level level, Creep target, Tower tower) {
        super(game, level, target, tower);
        timer = new Timer(1000);
        timer.setActive(true);
        powerOff = false;
    }

    @Override
    public void initialize() {
    }

    @Override
    protected void updateDirection(long elapsedTime) {
    }

    @Override
    public void hit(Creep creep) {
        powerOff = true;
    }

    @Override
    public void update(long elapsedTime) {
        super.update(elapsedTime);
        if (timer.action(elapsedTime)) {
            setActive(false);
            myLevel.removeShoot(this);
        }
    }
    
}
