
package Message;
import tanki.*;
import java.awt.*;
import java.io.Serializable;


public class WallMap implements Serializable {
    private static final long serialVersionUID = 1L;
    public int x,y;
    public int height, width;
    public Color color;
    
    
    public WallMap(int x, int y, int height, int width, Color color){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
    }
    
}
