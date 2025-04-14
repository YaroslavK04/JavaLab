
package Message;
import tanki.*;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Map implements Serializable {
    private List<BoxMap> Box = new ArrayList<>();
    private List<WallMap> Wall = new ArrayList<>();
    private List<BuffMap> Buff = new ArrayList<>();
    public int[] x;
    public int[] y;
    public int widthTank, heightTank,widthTower,heightTower;
    public Color colorTank[];
    public int numberTank;
    public int Rechargetime;
    public int[] StartXPositionHeart;
    public int[] StartYPositionHeart;

    public Map(int numberTank)
    {
       
    }
    public List<BoxMap> RetBox(){
        return Box;
    }
    public List<WallMap> RetWall(){
        return Wall;
    }
    public List<BuffMap> RetBuff(){
        return Buff;
    }
}
