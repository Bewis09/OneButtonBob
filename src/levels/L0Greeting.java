package src.levels;

import gamelibx.Rectangle;
import gamelibx.Text;
import src.Level;
import src.OneButtonBobGame;

import java.awt.geom.Rectangle2D;

public class L0Greeting extends Level {
    public static Text greetingText = new Text(384, 200, "Hello, Bob!", 40, true, false);
    public static Rectangle ground = new Rectangle(0, 448, 768, 64);

    public L0Greeting() {
        super(greetingText, ground);

        ground.makePassive();
        ground.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
    }

    @Override
    public void tick(OneButtonBobGame game) {

    }

    @Override
    public void button(OneButtonBobGame game) {

    }
}
