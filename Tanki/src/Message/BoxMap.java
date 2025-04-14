
package Message;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;


public class BoxMap implements Serializable {
    private static final long serialVersionUID = 1L;
    public int x, y, width, height;
    public Color color;
    public boolean active = true; // Активна ли коробка

    

    public BoxMap(int x, int y, int width, int height, Color color) {

    } 
}
