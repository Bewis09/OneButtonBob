package src.levels;

import gamelibx.Rectangle;
import gamelibx.Text;
import src.Level;
import src.OneButtonBobGame;

import java.awt.geom.Rectangle2D;

public class L1Instructions extends Level {
    public static Text instructionText1 = new Text(384, 150, "In every level there is", 40, true, false);
    public static Text instructionText2 = new Text(384, 200, "a specific action you can perform!", 40, true, false);
    public static Rectangle ground = new Rectangle(0, 448, 768, 64);

    public L1Instructions() {
        super(instructionText1, instructionText2, ground);

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
