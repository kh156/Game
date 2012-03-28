package level;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import shoot.Shoot;
import tower.LaserTower;
import tower.Tower;

import collision.CreepShootCollision;
import model.Circle;
import model.MyGame;
import model.Path;
import model.Shade;
import collision.ShadeCollision;
import model.UpgradePanel;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ImageBackground;

import creep.Creep;

public abstract class Level {

    MyGame           myGame;
    public PlayField        playfield;         
    Background       background;
    Sprite             path;

    SpriteGroup      myCreeps;
    SpriteGroup      myTowers;
    SpriteGroup      myShoots;
    SpriteGroup      laserGroup;

    Circle           circle; 
    Shade            shade;
    UpgradePanel     panel;
    Timer            secTimer, creepTimer;
    int waveIndex, secTillNextWave, gold, life, creepCounter, creepsRemained;
    boolean hasFinished, hasWon;

    GameFont           font, smallFont;

    protected static int waveNum, initialGold, initialLife, bufferTime[];
    protected static double creepSpeedScale, creepHPScale, creepEXPScale, corX[], corY[];
    protected static String pathDirectory;

    public Level(MyGame game) {
        myGame = game;
        initResources();
    }

    public double getCreepSpeedScale() {
        return creepSpeedScale;
    }

    public double getCreepHPScale() {
        return creepHPScale;
    }

    public double getCreepEXPScale() {
        return creepEXPScale;
    }

    public double getCorXForCreep(int index) {
        return corX[index];
    }

    public double getCorYForCreep(int index) {
        return corY[index];
    }

    public boolean checkFinishedForCreep(int index) {
        return index+1 >= corX.length;
    }

    public void initResources() {
        playfield = new PlayField();

        background = new ImageBackground(myGame.getImage("resources/background.png"), 640, 480);
        playfield.setBackground(background);

        myCreeps = new SpriteGroup("Creep");
        myTowers = new SpriteGroup("Tower");
        myShoots = new SpriteGroup("Shoot");
        
        path = new Sprite(myGame.getImage(pathDirectory), 0, 0);
        SpriteGroup pathGroup = new SpriteGroup("Path");
        pathGroup.add(path);
        playfield.addGroup(pathGroup);

        playfield.addGroup(myCreeps);
        playfield.addGroup(myTowers);
        playfield.addGroup(myShoots);

        circle = new Circle(myGame, this);
        playfield.addGroup(circle);

        shade = new Shade(myGame, this);
        SpriteGroup shadeGroup = new SpriteGroup("Shade");
        shadeGroup.add(shade);
        playfield.addGroup(shadeGroup);
        
        
        waveIndex = 0;
        gold = initialGold;
        life = initialLife;
        secTillNextWave = bufferTime[0];
        secTimer = new Timer(1000);
        creepsRemained = 0;

        hasFinished = false;
        
        playfield.add(shade);
        
        playfield.addCollisionGroup(myCreeps, myShoots, new CreepShootCollision(myGame, this));
        playfield.addCollisionGroup(shadeGroup, pathGroup, new ShadeCollision(myGame, this));
        playfield.addCollisionGroup(shadeGroup, myTowers, new ShadeCollision(myGame, this));
        
        font = myGame.fontManager.getFont(myGame.getImages("resources/font.png", 20, 3),
                " !            .,0123" +
                        "456789:   -? ABCDEFG" +
                "HIJKLMNOPQRSTUVWXYZ ");
        smallFont = myGame.fontManager.getFont(myGame.getImages("resources/smallfont.png", 8, 12),
                " ! #$%&'" +
                        "()*+,-./" +
                        "01234567" +
                        "89:;<=>?" +
                        "@ABCDEFG" +
                        "HIJKLMNO" +
                        "PQRSTUVW" +
                        "XYZ[ ]^_" +
                        "`abcdefg" +
                        "hijklmno" +
                        "pqrstuvw" +
                        "xyz{|}~-");

        panel = new UpgradePanel(myGame, this, font);
        playfield.add(panel);

    }

    public void update(long elapsedTime) {
        if (myGame.click())
            panel.setHidden(true);
        shade.setCollided();
        shade.setTempCollided(false);
        circle.setHidden(true);

        playfield.update(elapsedTime);

        if (secTimer.action(elapsedTime)) {
            secTillNextWave--;
            secTimer.refresh();
        }

        if ((secTillNextWave==0) && (waveIndex<waveNum)) {
            waveIndex ++;
            if (waveIndex < waveNum) {
                secTillNextWave = bufferTime[waveIndex];
            }
            else {
                secTimer.setActive(false);
            }
            nextWave();
        }

        if (life == 0) {
            lose();
        }

        if ((waveIndex == waveNum) && (creepsRemained == 0)) {
            win();
        }

        if (creepTimer != null && creepTimer.action(elapsedTime)) {
            creepCounter--;
            if (creepCounter == 0) {
                creepTimer.setActive(false);
            }
            releaseCreep();
        }
        
        if (myGame.keyPressed(KeyEvent.VK_1)) {
            shade.setActive(!shade.isActive());
            shade.setTowerSelection(KeyEvent.VK_1);
        }
        else if (myGame.keyPressed(KeyEvent.VK_2)) {
            shade.setActive(!shade.isActive());
            shade.setTowerSelection(KeyEvent.VK_2);
        }
        else if (myGame.keyPressed(KeyEvent.VK_3)) {
            shade.setActive(!shade.isActive());
            shade.setTowerSelection(KeyEvent.VK_3);
        }
        else if (myGame.keyPressed(KeyEvent.VK_4)) {
            shade.setActive(!shade.isActive());
            shade.setTowerSelection(KeyEvent.VK_4);
        }
    }

    public void render(Graphics2D g) {        
        playfield.render(g);

        if (hasFinished) {
            if (hasWon) {
                font.drawString(g, String.format("YOU WIN!"), 250, 230);
            }
            else {
                font.drawString(g, String.format("YOU LOSE!"), 250, 230);
            }
        }
        font.drawString(g, String.format("TIME: %2d         WAVE: %2d %2d", secTillNextWave, waveIndex, waveNum), 10, 10);
        font.drawString(g, String.format("LIFE: %2d         GOLD: %3d", life, gold), 10, 30);
    }

    protected abstract void nextWave(); 

    protected abstract void releaseCreep();


    private void lose() {
        hasFinished = true;
        hasWon = false;
    }

    private void win() {
        hasFinished = true;
        hasWon = true;
    }

    public void oneCreepMissed(Creep creep) {
        life --;
        creepsRemained --;
        removeCreep(creep);
    }

    public void oneCreepKilled(Creep creep) {
        gold += creep.getEXP();
        creepsRemained --;
        removeCreep(creep);
    }

    public List<Creep> getCreeps() { 
        Sprite[] s = myCreeps.getSprites();
        List<Creep> toReturn = new ArrayList<Creep>();
        for (Sprite c: s) {
            if (c != null)
                toReturn.add((Creep) c);
        }
        return toReturn;
    }

    public List<Tower> getTowers() {
        Sprite[] s = myTowers.getSprites();
        List<Tower> toReturn = new ArrayList<Tower>();
        for (Sprite c: s) {
            if (c != null)
                toReturn.add((Tower) c);
        }
        return toReturn;    
    }

    public Shoot[] getShoots() {
        return (Shoot[]) myShoots.getSprites();
    }

    public void addShoot(Shoot shoot) {
        myShoots.add(shoot);
    }

    public void removeShoot(Shoot shoot) {
        myShoots.remove(shoot);
    }

    public void buyTower(Tower tower) {
        if (gold >= tower.getUpgradePrice()) {
            gold -= tower.getUpgradePrice();
            myTowers.add(tower);
            System.out.println("Built a " + tower.toString());
        }
    }

    public void removeTower(Tower tower) {
        myTowers.remove(tower);
    }

    public void addCreep(Creep creep) {
        myCreeps.add(creep);
    }

    public void removeCreep(Creep creep) {
        myCreeps.remove(creep);
    }

    public UpgradePanel getUpgradePanel() {
        return panel;
    }
    
    public Shade getShade() {
        return shade;
    }
    
    public Circle getCircle() {
        return circle;
    }
    
    public void upgradeTower(Tower t) {
        if (gold >= t.getUpgradePrice()) {
            gold -= t.getUpgradePrice();
            t.upgrade();
        }
    }
    
    public void sellTower(Tower t) {
        t.setActive(false);
        gold += t.getSalePrice();
        removeTower(t);
    }

}
