package src;

import gamelibx.Game;
import gamelibx.Rectangle;
import gamelibx.Sound;
import src.levels.*;

import java.awt.*;
import java.util.ArrayList;

public class OneButtonBobGame extends Game {
    public Rectangle player;
    public final ArrayList<Level> levels = new ArrayList<>();
    public int level = 0;
    public int death_overlay = 0;

    public int deaths = 0;
    public int buttonPresses = 0;

    Sound death;

    public OneButtonBobGame() {
        super("One Button Bob", 768, 512);

        death = new Sound("resources/death.wav");

        setBackgroundImage("resources/background.png");

        player = new Rectangle(64, 256, 44, 56) {
            @Override
            public void draw(Graphics2D graphics2D) {
                if (this.initialized) {
                    graphics2D.translate(this.getCenterX(), this.getCenterY());
                    graphics2D.scale(getCurrentLevel().playerReversedHorizontally() ? -1 : 1, getCurrentLevel().playerReversedVertically() ? -1 : 1);
                    this.getDrawStyle().draw(graphics2D, this.relativeShape);
                    graphics2D.scale(getCurrentLevel().playerReversedHorizontally() ? -1 : 1, getCurrentLevel().playerReversedVertically() ? -1 : 1);
                    graphics2D.translate(-this.getCenterX(), -this.getCenterY());
                }
            }
        };
        player.makeActive();

        levels.add(new L0Greeting());
        levels.add(new L1Instructions());
        levels.add(new L2Jump());
        levels.add(new L3JumpHigh());
        levels.add(new L4Ladder());
        levels.add(new L5FlappyBird());
        levels.add(new L6Reverse());
        levels.add(new L7Dash());
        levels.add(new L8StopAndRun());
        levels.add(new L9Direction());
        levels.add(new L10Duck());
        levels.add(new L11TopDown());
        levels.add(new L12Final());

        levels.forEach(Level::hide);
        getCurrentLevel().show();

        player.toForeground();
        player.setImage("resources/bob.gif");

        resetPostion(false);

        startTicker();
    }

    @Override
    public void tick() {
        player.setVelocityX(3);
        player.setGravity(0.3f);
        player.setVisible(true);
        getCurrentLevel().tick(this);

        if (player.getCenterX() > 790) {
            getCurrentLevel().hide();
            level++;
            if (level >= levels.size()) {
                level = 0;
            }
            resetPostion(false);
            getCurrentLevel().show();
        }

        if (player.getCenterY() > 544 || player.getCenterY() < -28) {
            resetPostion(true);
        }

        death_overlay = Math.max(0, death_overlay - 1);
    }

    @Override
    public void beforePaint(Graphics2D g) {
        super.beforePaint(g);
        getCurrentLevel().beforePaint(g);
    }

    @Override
    public void afterPaint(Graphics2D g) {
        g.setColor(new Color(255, 0, 0, (int) (Math.pow(death_overlay / 2f, 2))));
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public Level getCurrentLevel() {
        return levels.get(level);
    }

    @Override
    public void mousePressed(int x, int y, int button) {
        buttonPresses++;
        getCurrentLevel().button(this);
    }

    @Override
    public void keyPressed(char c, int code) {
        buttonPresses++;
        getCurrentLevel().button(this);
    }

    public static void main(String[] args) {
        new OneButtonBobGame();
    }

    public void resetPostion(boolean playSound) {
        this.player.setCenterX(-27);
        this.player.setCenterY(getCurrentLevel().startY());
        this.player.setVelocity(0,0);

        if (playSound) {
            death.play();
            death_overlay = 32;
            getCurrentLevel().afterDead();
            deaths++;
        }
    }
}
