
package tanki;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Box {
    private int x, y, width, height;
    private Color color;
    private boolean active = true; // Активна ли коробка
    private int HP = 0;
    private BufferedImage BoxImage;

    public Box(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        
        try {
            BoxImage = ImageIO.read(getClass().getResource("/pictures/box.png"));
       } catch (IOException e) {
            e.printStackTrace();
       }
    }

    public void draw(Graphics2D g2d) {
        if (active) {
            if (BoxImage != null){
                 g2d.drawImage(BoxImage.getSubimage(370 * HP, 0, 370, 370), 
                    x, 
                    y, 
                    width, height, null);
                //g2d.drawImage(BoxImage,(int)x,(int)y,width, height, null);
            }
            else {
                g2d.setColor(color);
                g2d.fillRect(x, y, width, height);
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public boolean notifyHitListeners(Bullet bullet) {
        HP++;
        if (HP >= 3){
            active = false;
            return false;
        }
       return true;

    }
    
}
