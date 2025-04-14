
package Message;
import tankiserver.*;
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
    public int[] StartXPositionHeart;
    public int[] StartYPositionHeart;

    public int widthTank, heightTank,widthTower,heightTower;
    public Color[] colorTank;
    public int numberTank;
     public int Rechargetime = 2000;

    public Map(int numberTank)
    {
        this.x = new int[2]; // Массив для двух танков
        this.y = new int[2]; // Массив для двух танков
        this.StartXPositionHeart = new int [2];
        this.StartYPositionHeart = new int [2];

        this.colorTank = new Color[2]; // Массив для двух цветов
        this.numberTank = numberTank;
        
        x[0] = 70;
        x[1] = 1460;
        y[0] = 770;
        y[1] = 40;
        StartXPositionHeart[0] = 20;
        StartXPositionHeart[1] = 1550;
        StartYPositionHeart[0] = 750;
        StartYPositionHeart[1] = 20;
        colorTank[0] = Color.BLUE;
        colorTank[1] = Color.GREEN;
        widthTank = 62;
        heightTank = 70;
        widthTower = 40;
        heightTower = 90;
        
        Buff.add(new BuffMap(170,85,50,50));
        Buff.add(new BuffMap(1350,800,50,50));
        
        Box.add(new BoxMap(350, 170, 40, 40, Color.MAGENTA));
        Box.add(new BoxMap(400, 170, 40, 40, Color.MAGENTA));
        
        Box.add(new BoxMap(350, 170, 40, 40, Color.MAGENTA));
        
        Box.add(new BoxMap(1200, 700, 40, 40, Color.MAGENTA));
        Box.add(new BoxMap(1240, 700, 40, 40, Color.MAGENTA));
        Box.add(new BoxMap(1280, 700, 40, 40, Color.MAGENTA));
        Box.add(new BoxMap(1320, 700, 40, 40, Color.MAGENTA));
        Box.add(new BoxMap(1360, 700, 40, 40, Color.MAGENTA));

        
        // рамки
        Wall.add( new WallMap(0,-30, 30, 1600, Color.GRAY));
        Wall.add( new WallMap(-30, 0, 900, 30, Color.GRAY));
        Wall.add( new WallMap(1600,0, 900, 30, Color.GRAY));
        Wall.add( new WallMap(0, 900, 30, 1600, Color.GRAY));
       // Wall.add( new WallMap(100, 400, 30, 300, Color.GRAY));
       
        //вертикальные стены 
        Wall.add( new WallMap(200, 700, 200, 40, Color.GRAY));
        Wall.add( new WallMap(400, 540, 170, 40, Color.GRAY));
        Wall.add( new WallMap(580, 0, 170, 40, Color.GRAY));
        Wall.add( new WallMap(780, 515, 575, 40, Color.GRAY));
        Wall.add( new WallMap(910, 0, 350, 40, Color.GRAY));
        Wall.add( new WallMap(1200, 80, 320, 40, Color.GRAY));
        Wall.add( new WallMap(1160, 700, 350, 40, Color.GRAY));
        Wall.add( new WallMap(1400, 0, 170, 40, Color.GRAY));
        Wall.add( new WallMap(1400, 330, 210, 40, Color.GRAY));
        
        // горизонтальные стены
        Wall.add( new WallMap(0, 170, 40, 340, Color.GRAY));
        Wall.add( new WallMap(450, 170, 40, 340, Color.GRAY));
        
        Wall.add( new WallMap(100, 340, 40, 500, Color.GRAY));
        Wall.add( new WallMap(750, 340, 40, 345, Color.GRAY));
         
        Wall.add( new WallMap(0, 500, 40, 500, Color.GRAY));
        Wall.add( new WallMap(600, 500, 40, 450, Color.GRAY));
        Wall.add( new WallMap(1200, 500, 40, 200, Color.GRAY));
        
        Wall.add( new WallMap(1000, 700, 40, 200, Color.GRAY));
        Wall.add( new WallMap(1400, 700, 40, 200, Color.GRAY));
        
       
        
       
        
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


