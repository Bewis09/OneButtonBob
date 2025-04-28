package src.levels;

import gamelibx.Rectangle;
import gamelibx.Text;
import src.Level;
import src.OneButtonBobGame;

import java.awt.geom.Rectangle2D;

public class L5FlappyBird extends Level {
    public static Rectangle fly = new Rectangle(0, 0, 52, 48);
    public static Rectangle pipe_top = new Rectangle(320, 0, 64, 192);
    public static Rectangle pipe_bottom_top = new Rectangle(320, 320, 64, 64);
    public static Rectangle pipe_bottom = new Rectangle(320, 384, 64, 128);
    public static Rectangle pipe_top2 = new Rectangle(576, 0, 64, 256);
    public static Rectangle pipe_bottom_top2 = new Rectangle(576, 384, 64, 64);
    public static Rectangle pipe_bottom2 = new Rectangle(576, 444, 64, 78);

    public static Text flyText = new Text(160, 100, "You can fly!", 40, true, false);

    public L5FlappyBird() {
        super(fly, pipe_bottom, pipe_bottom_top, pipe_top, pipe_top2, pipe_bottom2, pipe_bottom_top2, flyText);

        fly.setImage("resources/flying_bob.png");
        pipe_top.setRepeatingImage("resources/dirt.png", new Rectangle2D.Float(0, 32, 64, 64));
        pipe_bottom.setRepeatingImage("resources/dirt.png", new Rectangle2D.Float(0, 32, 64, 64));
        pipe_bottom_top.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        pipe_top2.setRepeatingImage("resources/dirt.png", new Rectangle2D.Float(0, 32, 64, 64));
        pipe_bottom_top2.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        pipe_bottom2.setRepeatingImage("resources/dirt.png", new Rectangle2D.Float(0, 32, 64, 64));

        flyText.toBackground();
    }

    @Override
    public void tick(OneButtonBobGame game) {
        fly.setCenterX(game.player.getCenterX());
        fly.setCenterY(game.player.getCenterY());

        game.player.setVisible(false);
        game.player.setGravity(0.15f);

        if (fly.containsRect(pipe_top.getBounds()) || fly.containsRect(pipe_bottom.getBounds()) ||
                fly.containsRect(pipe_top2.getBounds()) || fly.containsRect(pipe_bottom2.getBounds()) ||
                fly.containsRect(pipe_bottom_top.getBounds()) || fly.containsRect(pipe_bottom_top2.getBounds())) {
            game.resetPostion(true);
        }
    }

    @Override
    public void button(OneButtonBobGame game) {
        game.player.setVelocityY(-3f);
    }

    @Override
    public int startY() {
        return 256;
    }
}
