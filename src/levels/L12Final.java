package src.levels;

import gamelibx.Rectangle;
import gamelibx.Text;
import src.FireworkDrawStyle;
import src.Level;
import src.OneButtonBobGame;

import java.awt.geom.Rectangle2D;

public class L12Final extends Level {
    public static Rectangle ground = new Rectangle(0, 448, 512, 64);
    public static Rectangle lava = new Rectangle(512, 480, 256, 32);
    public static Text movingText = new Text(384, 150, "You won!", 40, true, false);
    public static Text diedText = new Text(384, 200, "You died 0 times and", 30, true, false);
    public static Text buttonText = new Text(384, 230, "pressed a button 0 times!", 30, true, false);
    public static Rectangle fireworkLeft = new Rectangle(32, 64, 192, 192, new FireworkDrawStyle("resources/firework_1.gif", 0));
    public static Rectangle fireworkRight = new Rectangle(544, 64, 192, 192, new FireworkDrawStyle("resources/firework_3.gif", 50));
    public static Rectangle hat = new Rectangle(0, 0, 44, 56);
    public boolean ducking = false;

    public L12Final() {
        super(ground, movingText, lava, hat, diedText, buttonText, fireworkLeft, fireworkRight);

        ground.makePassive();
        ground.setRepeatingImage("resources/grass.png", new Rectangle2D.Float(0, 32, 64, 64));
        lava.setRepeatingImage("resources/lava.png", new Rectangle2D.Float(0, 32, 64, 32));
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

        if (game.player.getCenterY() > 448) {
            game.resetPostion(true);
        }

        diedText.setText("You died " + game.deaths + " times and");
        buttonText.setText("pressed a button " + game.buttonPresses + " times!");
    }

    @Override
    public void button(OneButtonBobGame game) {
        ducking = !ducking;
    }

    @Override
    public void afterDead() {
        ducking = false;
    }
}
