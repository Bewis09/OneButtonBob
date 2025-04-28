package src.levels;

import gamelibx.Rectangle;
import gamelibx.Text;
import src.Level;
import src.OneButtonBobGame;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class L11TopDown extends Level {
    public static Rectangle groundB1 = new Rectangle(0, 448, 128, 64);
    public static Rectangle groundB2 = new Rectangle(256, 448, 128, 64);
    public static Rectangle groundB3 = new Rectangle(512, 448, 128, 64);
    public static Rectangle groundT1 = new ReveredRectangle(128, 0, 128, 64);
    public static Rectangle groundT2 = new ReveredRectangle(384, 0, 128, 64);
    public static Rectangle groundT3 = new ReveredRectangle(640, 0, 128, 64);
    public static Text reversedText = new Text(384, 200, "Gravity Reversed!", 40, true, false);
    public boolean reversed = false;

    public L11TopDown() {
        super(groundB1, groundB2, groundB3, groundT1, groundT2, groundT3, reversedText);

        groundB1.makePassive();
        groundB2.makePassive();
        groundB3.makePassive();
        groundT1.makePassive();
        groundT2.makePassive();
        groundT3.makePassive();

        groundB1.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        groundB2.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        groundB3.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        groundT1.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        groundT2.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        groundT3.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
    }

    @Override
    public void tick(OneButtonBobGame game) {
        game.player.setGravity(reversed ? -0.3f : 0.3f);
    }

    @Override
    public void button(OneButtonBobGame game) {
        if ((Math.abs(game.player.getCenterY() - 420) < 2 && !reversed) || (Math.abs(game.player.getCenterY() - 92) < 2 && reversed))
            reversed = !reversed;
    }

    @Override
    public boolean playerReversedVertically() {
        return reversed;
    }

    @Override
    public void afterDead() {
        reversed = false;
    }

    public static class ReveredRectangle extends Rectangle {
        public ReveredRectangle(float x, float y, float width, float height) {
            super(x, y, width, height);
        }

        @Override
        public void draw(Graphics2D graphics2D) {
            if (this.initialized) {
                graphics2D.translate(this.getCenterX(), this.getCenterY());
                graphics2D.scale(1, -1);
                this.getDrawStyle().draw(graphics2D, this.relativeShape);
                graphics2D.scale(1, -1);
                graphics2D.translate(-this.getCenterX(), -this.getCenterY());
            }
        }
    }
}
