package src.levels;

import gamelibx.Game;
import gamelibx.Rectangle;
import gamelibx.Text;
import src.Level;
import src.OneButtonBobGame;

import java.awt.geom.Rectangle2D;

public class L2Jump extends Level {
    public static Text jumpText = new Text(384, 200, "You can jump!", 40, true, false);
    public static Rectangle ground1 = new Rectangle(0, 448, 192, 64);
    public static Rectangle ground2 = new Rectangle(320, 448, 128, 64);
    public static Rectangle ground3 = new Rectangle(576, 448, 192, 64);
    public static Rectangle lava = new Rectangle(0, 480, 768, 64);

    public L2Jump() {
        super(jumpText, ground1, ground2, ground3, lava);

        ground1.makePassive();
        ground2.makePassive();
        ground3.makePassive();
        ground1.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        ground2.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        ground3.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
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
