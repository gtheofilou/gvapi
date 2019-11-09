package gr.gt.gvapi.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public class ImageUtils {

    public static void createThumbnail(InputStream in, Path pathLocation, String format, int size) {

        BufferedImage originalBufferedImage = null;
        try {
            originalBufferedImage = ImageIO.read(in);

            int widthToScale, heightToScale;
            if (originalBufferedImage.getWidth() > originalBufferedImage.getHeight()) {
                heightToScale = (int) (1.1 * size);
                widthToScale = (int) ((heightToScale * 1.0) / originalBufferedImage.getHeight()
                        * originalBufferedImage.getWidth());
            } else {
                widthToScale = (int) (1.1 * size);
                heightToScale = (int) ((widthToScale * 1.0) / originalBufferedImage.getWidth()
                        * originalBufferedImage.getHeight());
            }

            BufferedImage resizedImage =
                    new BufferedImage(widthToScale, heightToScale, originalBufferedImage.getType());
            Graphics2D g = resizedImage.createGraphics();

            g.setComposite(AlphaComposite.Src);
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g.drawImage(originalBufferedImage, 0, 0, widthToScale, heightToScale, null);
            g.dispose();

            int x = (resizedImage.getWidth() - size) / 2;
            int y = (resizedImage.getHeight() - size) / 2;

            // Width of new thumbnail is bigger than original image
            // if (x < 0 || y < 0)
            // return null;

            BufferedImage thumbnailBufferedImage = resizedImage.getSubimage(x, y, size, size);
            ImageIO.write(thumbnailBufferedImage, format, pathLocation.toFile());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
