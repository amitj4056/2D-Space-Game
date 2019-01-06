
package space.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class BufferedImageLoader {
    private BufferedImage image;
    public BufferedImage loadImage(String path)
    {
        try {
            image  = ImageIO.read(new File(path));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return image;
    }
}
