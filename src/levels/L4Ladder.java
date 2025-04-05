package src.levels;

import gamelibx.Rectangle;
import gamelibx.Text;
import src.Level;
import src.OneButtonBobGame;

import java.awt.geom.Rectangle2D;

public class L4Ladder extends Level {
    public static Rectangle ground = new Rectangle(0, 448, 512, 64);
    public static Rectangle ladder = new Rectangle(444, 256, 48, 192);
    public static Rectangle platform = new Rectangle(496, 256, 370, 64);
    public static Rectangle lava = new Rectangle(0, 480, 768, 32);
    public static Text ladderText = new Text(384, 80, "You can climb ladders!", 40, true, false);

    public boolean climbing = false;

    public L4Ladder() {
        super(ground, ladder, platform, lava, ladderText);

        ground.makePassive();
        platform.makePassive();
        ground.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        ladder.setImage("resources/ladder.png");
        platform.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        lava.setRepeatingImage("resources/lava.png", new Rectangle2D.Float(0, 32, 64, 32));

        lava.toBackground();
    }

    @Override
    public void tick(OneButtonBobGame game) {
        if(game.player.getCenterY() > 448) {
            game.resetPostion(true);
        }

        if (climbing && game.player.getCenterX() >= ladder.getCenterX()) {
            if (game.player.getCenterY() + game.player.getHeight() / 2 > ladder.getCenterY() - ladder.getHeight() / 2) {
                game.player.setVelocityY(-2);
                game.player.setVelocityX(0);
                game.player.setCenterX(ladder.getCenterX());
            } else {
                game.player.setVelocityY(0);
                game.player.setGravity(0f);
            }
        }
    }

    @Override
    public void button(OneButtonBobGame game) {
        if (game.player.getCenterX() > ladder.getCenterX() - ladder.getWidth() / 2 && game.player.getCenterX() < ladder.getCenterX()) {
            climbing = true;
        }
    }
}
