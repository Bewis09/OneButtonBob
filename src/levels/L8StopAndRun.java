package src.levels;

import gamelibx.Rectangle;
import gamelibx.Text;
import src.Level;
import src.OneButtonBobGame;

import java.awt.geom.Rectangle2D;

public class L8StopAndRun extends Level {
    public static Rectangle ground1 = new Rectangle(0, 448, 192, 64);
    public static Rectangle ground2 = new Rectangle(576, 448, 192, 64);
    public static Rectangle platform = new Rectangle(512, 448, 64, 16);
    public static Rectangle lava = new Rectangle(192, 472, 384, 64);
    public static Text movingText = new Text(384, 200, "It is moving!", 40, true, false);

    public static boolean isPlayerMoving = true;
    public static boolean direction = true;

    public L8StopAndRun() {
        super(ground1, ground2, platform, lava, movingText);

        ground1.makePassive();
        ground2.makePassive();
        platform.makePassive();
        ground1.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        ground2.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        platform.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        lava.setRepeatingImage("resources/lava.png", new Rectangle2D.Float(0, 32, 64, 32));

        platform.toForeground();
    }

    @Override
    public void tick(OneButtonBobGame game) {
        if (!isPlayerMoving) {
            game.player.setVelocityX(0);
        }

        if (direction) {
            platform.setCenterX(platform.getCenterX() + 1f);
        } else {
            platform.setCenterX(platform.getCenterX() - 3f);
        }

        if (platform.getCenterX() > 544) {
            direction = false;
        } else if (platform.getCenterX() < 224) {
            direction = true;
        }

        if (game.player.getCenterY() > 436) {
            game.resetPostion(true);
        }
    }

    @Override
    public void button(OneButtonBobGame game) {
        isPlayerMoving = !isPlayerMoving;
    }

    @Override
    public void afterDead() {
        isPlayerMoving = true;
    }
}
