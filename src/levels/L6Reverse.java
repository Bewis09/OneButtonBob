package src.levels;

import gamelibx.Text;
import src.Level;
import src.OneButtonBobGame;
import gamelibx.Rectangle;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class L6Reverse extends Level {
    public static Rectangle ground1 = new Rectangle(0, 448, 384, 64);
    public static Rectangle ground2 = new Rectangle(384, 320, 384, 64);
    public static Rectangle ground3 = new Rectangle(384, 448, 64, 64);
    public static Rectangle ground4 = new Rectangle(448, 384, 320, 128);
    public static Text text = new Text(384, 250, "What did just happen?", 40, true, false){
        @Override
        public void draw(Graphics2D graphics2D) {
            graphics2D.scale(1, -1);
            graphics2D.translate(0, -512);
            super.draw(graphics2D);
            graphics2D.scale(1, -1);
            graphics2D.translate(0, -512);
        }
    };

    public L6Reverse() {
        super(ground1, ground2, ground3, ground4, text);

        ground1.makePassive();
        ground2.makePassive();
        ground3.makePassive();
        ground4.makePassive();
        ground1.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        ground2.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        ground3.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        ground4.setRepeatingImage("resources/dirt.png", new Rectangle2D.Float(32, 32, 64, 64));
    }

    @Override
    public void beforePaint(Graphics2D g) {
        g.scale(1, -1);
        g.translate(0, -512);
    }

    @Override
    public void tick(OneButtonBobGame game) {
        if (game.player.getCenterY() > 320 && game.player.getCenterX() > 424) {
            game.resetPostion(true);
        }
    }

    @Override
    public void button(OneButtonBobGame game) {
        if (game.player.isOnGround())
            game.player.setVelocityY(-9);
    }
}
