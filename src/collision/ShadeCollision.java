package collision;


import level.Level;
import model.MyGame;
import model.Shade;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class ShadeCollision extends BasicCollisionGroup {
    Level    owner;
    MyGame   myGame;
    
    public ShadeCollision(MyGame game, Level owner) {
        this.pixelPerfectCollision = true;
        myGame = game;
        this.owner = owner;
    }

    public void collided(Sprite shade, Sprite path) {
        ((Shade)shade).setTempCollided(true);
    }
}

