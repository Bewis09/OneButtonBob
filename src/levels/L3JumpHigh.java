package src.levels;

import gamelibx.Rectangle;
import gamelibx.Text;
import src.Level;
import src.OneButtonBobGame;

import java.awt.geom.Rectangle2D;

public class L3JumpHigh extends Level {
    public static Rectangle ground = new Rectangle(0, 448, 256, 64);
    public static Rectangle platform1 = new Rectangle(384, 384, 128, 16);
    public static Rectangle platform2 = new Rectangle(640, 320, 128, 16);
    public static Text jumpText = new Text(384, 200, "...again!", 40, true, false);
    public static Rectangle lava = new Rectangle(0, 480, 768, 32);

    public L3JumpHigh() {
        super(ground, platform1, platform2, jumpText, lava);

        ground.makePassive();
        platform1.makePassive();
        platform2.makePassive();

        ground.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(32, 32, 64, 64));
        platform1.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        platform2.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        lava.setRepeatingImage("resources/lava.png", new Rectangle2D.Float(0, 32, 64, 32));

        lava.toBackground();
    }

    @Override
    public void tick(OneButtonBobGame game) {
        if(game.player.getCenterY() > 448) {
            game.resetPostion(true);
        }
    }

    @Override
    public void button(OneButtonBobGame game) {
        if(game.player.isOnGround())
            game.player.setVelocityY(-7);
    }
}
