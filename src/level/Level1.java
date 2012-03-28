package level;

import com.golden.gamedev.object.Timer;

import creep.Creep;
import creep.Creep1;
import creep.Creep2;
import creep.Creep3;

import model.MyGame;


public class Level1 extends Level{

    static {
        waveNum = 3;
        initialGold = 20;
        initialLife = 5;
        bufferTime = new int[]{3, 20, 20, 20};

        creepSpeedScale = 1.0;
        creepHPScale = 1.0;
        creepEXPScale = 1.0;
        //corX = new double[]{-40, 160, 160, 360, 360, 520, 520, 640}; 
        //corY = new double[]{320, 320, 120, 120, 360, 360, 160, 160};
        corX = new double[]{22, 245, 136, 450, 528, 610};
        corY = new double[]{407, 410, 189, 29, 401, 408};
        pathDirectory = "resources/path1.png";
    }

    public Level1(MyGame game) {
        super(game);
    }

    @Override
    public void nextWave() {
        switch (waveIndex) {
        case 1: {
            creepTimer = new Timer(800);
            creepCounter = 10;
            break;
        }
        case 2: {
            creepTimer = new Timer(1000);
            creepCounter = 10;
            break;
        }
        case 3: {
            creepTimer = new Timer(1000);
            creepCounter = 10;
            break;
        }}
        creepsRemained += creepCounter;
    }

    @Override
    protected void releaseCreep() {
        Creep creep = null;
        switch (waveIndex) {
        case 1: {
            creep = new Creep1(myGame, this, 30);
            break;
        }
        case 2: {
            creep = new Creep2(myGame, this, 200);
            break;
        }
        case 3: {
            creep = new Creep3(myGame, this, 20);
            break;
        }}
        this.addCreep(creep);
    }
}
