package creep;

import level.Level;
import model.MyGame;

@SuppressWarnings("serial")
public class Creep3 extends Creep{
    static {
        defaultSpeed = 0.2; 
        defaultHP = 20; 
        defaultEXP = 1.5;
        imageDirectory = "resources/creep3.png";
    }

    public Creep3(MyGame game, Level level, double HP) {
        super(game, level, HP);
    }

}
