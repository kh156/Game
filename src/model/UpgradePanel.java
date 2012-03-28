package model;

import java.awt.Graphics2D;

import tower.Tower;

import level.Level;

import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class UpgradePanel extends Sprite{

    GameFont font;
    Level myLevel;
    MyGame myGame;
    Tower myTower;
    Circle myCircle;
    boolean hidden;
    int corX, corY, salePrice, upgradePrice;

    public UpgradePanel(MyGame game, Level level, GameFont font) {
        //super(game.getImage("resources/updatepanel.png"));
        super();
        this.font = font;
        myGame = game;
        myLevel = level;
        myTower = null;
        myCircle = myLevel.getCircle();
        this.setImmutable(true);
        hidden = true;
    }

    public void setParametes(Tower t) {
        corX = (int)t.getX() - 80;
        corY = (int)t.getY() - 60;
        salePrice = (int) t.getSalePrice();
        upgradePrice = (int) t.getUpgradePrice();
        myTower = t;
    }

    public void setHidden(boolean flag) {
        hidden = flag;
    }

    public void update(long elapsedTime) {
        super.update(elapsedTime);
        if (myLevel.getShade().isActive()) {
            hidden = true;
        }
        if (myTower != null) {
            myCircle.setHidden(false);
            myCircle.setAndDraw(myTower.getX()+10.0, myTower.getY()+10.0, myTower.getRangeOfAttack());
        }
        if (myGame.click()) {
            if (myGame.getMouseX() > corX && myGame.getMouseX() < corX + 160 &&
                    myGame.getMouseY() > corY && myGame.getMouseY() < corY + 20) {
                System.out.println("upgrade");
                myLevel.upgradeTower(myTower);
                hidden = true;
            }
            if (myGame.getMouseX() > corX && myGame.getMouseX() < corX + 160 &&
                    myGame.getMouseY() > corY + 30 && myGame.getMouseY() < corY + 50) {
                System.out.println("sell");
                myLevel.sellTower(myTower);
                hidden = true;
            }
        }
    }

    public void render(Graphics2D g) { 
        if (!hidden) {
            font.drawString(g, String.format("UPGRADE:%3d", upgradePrice), (int)corX, (int)corY);
            font.drawString(g, String.format("SELL:   %3d", salePrice), (int)corX, (int)corY+30);
        }
    }
}
