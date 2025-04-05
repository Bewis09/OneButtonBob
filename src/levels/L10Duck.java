package src.levels;

import gamelibx.Rectangle;
import gamelibx.Text;
import src.Level;
import src.OneButtonBobGame;

import java.awt.geom.Rectangle2D;

public class L10Duck extends Level {
    public static Rectangle ground = new Rectangle(512, 448, 256, 64);
    public static Rectangle step = new Rectangle(0, 384, 512, 64);
    public static Rectangle stepB = new Rectangle(0, 448, 512, 64);
    public static Rectangle hat = new Rectangle(0, 0, 44, 56);
    public static Rectangle projectile = new Rectangle(-25, 336, 8, 4);
    public static Text watchOutText = new Text(384, 200, "Watch out!", 40, true, false);
    public boolean ducking = false;

    public L10Duck() {
        super(ground, step, stepB, hat, projectile, watchOutText);

        ground.makePassive();
        step.makePassive();

        ground.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        step.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        stepB.setRepeatingImage("resources/dirt.png", new Rectangle2D.Float(0, 32, 64, 64));
        projectile.setImage("resources/bullet.png");
        hat.setImage("resources/hat.png");
    }

    @Override
    public void tick(OneButtonBobGame game) {
        game.player.setVisible(!ducking);

        if (ducking) {
            game.player.setVelocityX(0);
            hat.setCenterX(game.player.getCenterX());
            hat.setCenterY(game.player.getCenterY());
        }

        hat.setVisible(ducking);

        if (projectile.getCenterX() < -12) {
            projectile.setCenterX(780);
        }

        projectile.setCenterX(projectile.getCenterX() - 10);

        if (game.player.containsRect(projectile.getBounds()) && !ducking) {
            game.resetPostion(true);
            projectile.setCenterX(780);
        }
    }

    @Override
    public void button(OneButtonBobGame game) {
        ducking = !ducking;
    }

    @Override
    public int startY() {
        return 340;
    }

    @Override
    public void afterDead() {
        ducking = false;
    }
}
