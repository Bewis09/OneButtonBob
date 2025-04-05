package src.levels;

import gamelibx.Rectangle;
import gamelibx.Text;
import src.Level;
import src.OneButtonBobGame;

import java.awt.geom.Rectangle2D;

public class L7Dash extends Level {
    public static Text jumpText = new Text(384, 200, "It looks the same, but it isn't!", 40, true, false);
    public static Rectangle ground1 = new Rectangle(0, 448, 192, 64);
    public static Rectangle ground2 = new Rectangle(320, 448, 128, 64);
    public static Rectangle ground3 = new Rectangle(576, 448, 192, 64);
    public static Rectangle lava = new Rectangle(0, 480, 768, 64);
    public static Rectangle fly = new Rectangle(0, 0, 52, 48);

    public int dashTime = 0;

    public L7Dash() {
        super(jumpText, ground1, ground2, ground3, lava, fly);

        fly.setImage("resources/flying_bob.png");
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
        fly.setCenterX(game.player.getCenterX());
        fly.setCenterY(game.player.getCenterY());

        if(game.player.getCenterY() > 448) {
            game.resetPostion(true);
        }

        if (dashTime > 0)
            dashTime--;

        if(dashTime > 10) {
            game.player.setVisible(false);
            game.player.setVelocityX(13);
            game.player.setVelocityY(0);
        }

        fly.setVisible(dashTime > 10);
    }

    @Override
    public void button(OneButtonBobGame game) {
        if(dashTime == 0 && game.player.isOnGround())
            dashTime = 20;
    }
}
