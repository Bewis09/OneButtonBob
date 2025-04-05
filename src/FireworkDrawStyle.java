package src;

import gamelibx.drawing.DrawStyle;
import gamelibx.resource_loader.ResourceLoader;

import javax.imageio.*;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class FireworkDrawStyle implements DrawStyle {

    private final int frameCount = 74;
    private final BufferedImage[] canvas;
    private int currentFrame;

    public FireworkDrawStyle(String string, int startFrame) {
        canvas = new BufferedImage[frameCount];
        currentFrame = startFrame;

        new Thread(() -> {
            try {
                Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("gif");
                if (!readers.hasNext()) {
                    throw new IllegalStateException("No GIF readers found");
                }

                ImageReader reader = readers.next();
                ImageInputStream stream = ImageIO.createImageInputStream(ResourceLoader.getResourceAsStream(string));
                reader.setInput(stream);

                BufferedImage firstFrame = reader.read(0);
                int width = firstFrame.getWidth();
                int height = firstFrame.getHeight();

                for (int i = 0; i < frameCount; i++) {
                    canvas[i] = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                    BufferedImage frame = reader.read(i);
                    Graphics2D canvasGraphics = canvas[i].createGraphics();
                    canvasGraphics.setComposite(AlphaComposite.Clear);
                    canvasGraphics.fillRect(0, 0, width, height);
                    canvasGraphics.setComposite(AlphaComposite.SrcOver);

                    canvasGraphics.drawImage(frame, 0, 0, null);
                    canvasGraphics.dispose();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void draw(Graphics2D g, Shape bounds) {
        try {
            currentFrame = (currentFrame + 1) % (frameCount * 2);

            Rectangle rect = bounds.getBounds();

            g.drawImage(canvas[currentFrame / 2], rect.x, rect.y, rect.width, rect.height, null);
        } catch (Exception ignored) {

        }
    }
}
