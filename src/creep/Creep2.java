package creep;

import level.Level;
import model.MyGame;

@SuppressWarnings("serial")
public class Creep2 extends Creep{
    static {
        defaultSpeed = 0.05; 
        defaultHP = 150; 
        defaultEXP = 1.5;
        imageDirectory = "resources/creep2.png";
    }

    public Creep2(MyGame game, Level level, double HP) {
        super(game, level, HP);
    }

}
