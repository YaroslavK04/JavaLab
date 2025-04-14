
package tanki;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Buff {
    public int id;
    private int x,y,width,height;
    private int TypeBaff;
    private boolean active = true;
    private Color color;
    private BufferedImage BuffImage;
    
    
    
    public Buff(int id,int x, int y, int width, int height){
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = Color.ORANGE;
    }
    public void draw(Graphics2D g2d) {
        if (active) {
            try {
                switch(TypeBaff){
                    case 0: // Щит
                        BuffImage = ImageIO.read(getClass().getResource("/pictures/ShieldBuff.png"));

                        color = Color.BLUE;
                    break;
                    case 1: // уменьшенная перезарядка
                        BuffImage = ImageIO.read(getClass().getResource("/pictures/RateFireBuff.png"));

                        color = Color.ORANGE;
                    break;
                    case 2:// мины
                        BuffImage = ImageIO.read(getClass().getResource("/pictures/MineBuff.png"));

                        color = Color.GREEN;
                    break;

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (BuffImage != null){
                 g2d.drawImage(BuffImage,(int)x,(int)y,width, height, null);
            }else{
                g2d.setColor(color);
                g2d.fillRect(x, y, width, height);
            }
            
        }
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    public void setTypeBaff(int TypeBaff){
        this.TypeBaff = TypeBaff;
    }
    public int  getTypeBaff(){
        return TypeBaff;
    }
}
