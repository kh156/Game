package level;

import com.golden.gamedev.object.Timer;

import creep.Creep;
import creep.Creep1;
import creep.Creep2;
import creep.Creep3;

import model.MyGame;


public class Level2 extends Level{

    static {
        waveNum = 3;
        initialGold = 20;
        initialLife = 5;
        bufferTime = new int[]{3, 10, 10, 10, 10, 10};

        creepSpeedScale = 1.0;
        creepHPScale = 3.0;
        creepEXPScale = 3.0;
        corX = new double[]{40, 41, 577, 579, 172, 174, 487, 487, 240, 239, 420, 347, 291};
        corY = new double[]{432, 106, 55, 424, 427, 159, 127, 350, 351, 222, 214, 320, 272};
        pathDirectory = "resources/path2.png";
    }

    public Level2(MyGame game) {
        super(game);
    }

    @Override
    public void nextWave() {
        switch (waveIndex) {
        case 1: {
            creepTimer = new Timer(300);
            creepCounter = 20;
            break;
        }
        case 2: {
            creepTimer = new Timer(300);
            creepCounter = 20;
            break;
        }
        case 3: {
            creepTimer = new Timer(300);
            creepCounter = 20;
            break;
        }}
        creepsRemained += creepCounter;
    }

    @Override
    protected void releaseCreep() {
        Creep creep = null;
        switch (waveIndex) {
        case 1: {
            creep = new Creep1(myGame, this, 300);
            break;
        }
        case 2: {
            creep = new Creep2(myGame, this, 2000);
            break;
        }
        case 3: {
            creep = new Creep3(myGame, this, 200);
            break;
        }}
        this.addCreep(creep);
    }
}
