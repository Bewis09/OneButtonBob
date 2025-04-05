package src.levels;

import gamelibx.Rectangle;
import gamelibx.Text;
import src.Level;
import src.OneButtonBobGame;

import java.awt.geom.Rectangle2D;

public class L9Direction extends Level {
    public static Rectangle ground = new Rectangle(0, 448, 768, 64);
    public static Rectangle wallT = new Rectangle(384, 128, 192, 64);
    public static Rectangle wallB = new Rectangle(512, 192, 64, 320);
    public static Rectangle wallM = new Rectangle(320, 320, 128, 64);
    public static Rectangle airstream1 = new Rectangle(448, 320, 64, 128);
    public static Rectangle airstream2 = new Rectangle(320, 128, 64, 192);
    public static Text directionText = new Text(384, 50, "Wow, this is high!", 40, true, false);

    public int airstreamPos = 0;
    public boolean direction = false;

    public L9Direction() {
        super(ground, wallT, wallB, wallM, airstream1, airstream2, directionText);

        ground.makePassive();
        wallT.makePassive();
        wallB.makePassive();
        wallM.makePassive();
        ground.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        wallT.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(32, 32, 64, 64));
        wallB.setRepeatingImage("resources/dirt.png", new Rectangle2D.Float(32, 32, 64, 64));
        wallM.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        airstream1.setRepeatingImage("resources/airstream.png", new Rectangle2D.Float(0, 64 - airstreamPos, 64, 64));
        airstream2.setRepeatingImage("resources/airstream.png", new Rectangle2D.Float(0, 64 - airstreamPos, 64, 64));
    }

    @Override
    public void tick(OneButtonBobGame game) {
        airstream1.setRepeatingImage("resources/airstream.png", new Rectangle2D.Float(0, 64 - airstreamPos, 64, 64));
        airstream2.setRepeatingImage("resources/airstream.png", new Rectangle2D.Float(0, 64 - airstreamPos, 64, 64));
        airstreamPos = (airstreamPos + 1) % 64;

        if (game.player.containsRect(airstream1.getBounds()) || game.player.containsRect(airstream2.getBounds())) {
            game.player.setGravity(-0.05f);
        }

        game.player.setVelocityX(direction ? -2f : 2f);
    }

    @Override
    public void button(OneButtonBobGame game) {
        direction = !direction;
    }

    @Override
    public void afterDead() {
        direction = false;
    }

    @Override
    public boolean playerReversedHorizontally() {
        return direction;
    }
}
