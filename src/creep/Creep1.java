package creep;

import level.*;
import model.MyGame;

@SuppressWarnings("serial")
public class Creep1 extends Creep{
    
    static {
        defaultSpeed = 0.1; 
        defaultHP = 30; 
        defaultEXP = 1;
        imageDirectory = "resources/creep1.png";
    }

    public Creep1(MyGame game, Level level, double HP) {
        super(game, level, HP);
    }

}
