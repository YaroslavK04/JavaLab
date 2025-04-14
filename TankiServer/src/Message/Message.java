
package Message;
import tankiserver.*;
import java.io.Serializable;


public class Message implements Serializable{
    private static final long serialVersionUID = 1L;
    public int nameMassage;
    public int nameButton;
    public boolean pressButoon;
    public int x,y;
    public double rotateTank = 0; 
    public double rotateTower = 0; 
    public double angle;
    public int idBuff;
    public Message(int nameMassage){
        
    }
}
