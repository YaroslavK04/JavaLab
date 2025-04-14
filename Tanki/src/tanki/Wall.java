
package tanki;
import java.awt.*;

public class Wall {
    private int x,y;
    private int height, width;
    private Color color;
    
    public Wall(int x, int y, int height, int width, Color color){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
    }
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillRect(x, y, width, height);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, width, height);
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, width+2, height);
    }

}
