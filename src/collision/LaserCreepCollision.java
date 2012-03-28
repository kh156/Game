package collision;

import java.awt.image.BufferedImage;

import level.Level;
import model.MyGame;

import shoot.Shoot;
import tower.LaserTower;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.sprite.VolatileSprite;

import creep.Creep;

public class LaserCreepCollision extends BasicCollisionGroup {
    Level    owner;
    MyGame   myGame;
    LaserTower myTower;
    
    public LaserCreepCollision(MyGame game, Level owner, LaserTower tower) {
        myGame = game;
        this.owner = owner;
        myTower = tower;
    }

    public void collided(Sprite laser, Sprite creep) {
        ((Creep)creep).gotShot((Shoot)laser);
        ((Shoot)laser).hit((Creep)creep); 

        BufferedImage[] images = myGame.getImages("resources/explosion.png", 7, 1);
        VolatileSprite explosion = new VolatileSprite(images, creep.getX(), creep.getY());
        owner.playfield.add(explosion);
    }
}
