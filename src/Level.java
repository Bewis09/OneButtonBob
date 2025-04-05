package src;

import gamelibx.game.GameObject;

import java.awt.*;
import java.util.Arrays;

public abstract class Level {
    private final GameObject[] gameObjects;

    public Level(GameObject... gameObjects) {
        this.gameObjects = gameObjects;
    }

    public void hide() {
        Arrays.stream(gameObjects).forEach(o -> o.setVisible(false));
    }

    public void show() {
        Arrays.stream(gameObjects).forEach(o -> o.setVisible(true));
    }

    public int startY() {
        return 400;
    }

    public void beforePaint(Graphics2D g) {}

    public void afterDead() {}

    public abstract void tick(OneButtonBobGame game);

    public abstract void button(OneButtonBobGame game);

    public boolean playerReversedHorizontally() {
        return false;
    }

    public boolean playerReversedVertically() {
        return false;
    }
}
