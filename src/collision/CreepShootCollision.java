package collision;

import java.awt.image.BufferedImage;

import level.Level;
import model.MyGame;

import shoot.Shoot;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.sprite.VolatileSprite;

import creep.Creep;

public class CreepShootCollision extends BasicCollisionGroup {
    Level    owner;
    MyGame   myGame;
    
    public CreepShootCollision(MyGame game, Level owner) {
        this.pixelPerfectCollision = true;
        myGame = game;
        this.owner = owner;
    }

    public void collided(Sprite creep, Sprite shoot) {
        ((Creep)creep).gotShot((Shoot)shoot);
        ((Shoot)shoot).hit((Creep)creep); 

        BufferedImage[] images = myGame.getImages("resources/explosion.png", 7, 1);
        VolatileSprite explosion = new VolatileSprite(images, creep.getX(), creep.getY());
        owner.playfield.add(explosion);
    }
}
