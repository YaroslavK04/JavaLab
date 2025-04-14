
package Message;

import java.awt.Color;
import java.io.Serializable;


public class BuffMap implements Serializable {
    private static final long serialVersionUID = 1L;
    public int x,y,width,height;
    public BuffMap(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
       
    }
}
