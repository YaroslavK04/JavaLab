
package tanki;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Mine {
        private double x,y;
        private double angle; 
        private double speed = 5; 
        private boolean WhoseMine;
        private boolean ReducingSpeed = false;
        private BufferedImage MineImage;
        public Mine(double x, double y,double angle,boolean WhoseBullet ){
            this.x = x;
            this.y = y;
            this.angle = angle;
            this.WhoseMine = WhoseBullet;
             try {
                MineImage = ImageIO.read(getClass().getResource("/pictures/bomb.png"));
           } catch (IOException e) {
                e.printStackTrace();
           }
        }
        public void draw(Graphics2D g2d) {
            if (MineImage != null){
                 g2d.drawImage(MineImage,(int)x,(int)y,22, 30, null);
            }else{
                g2d.setColor(Color.PINK);
                g2d.fillOval((int) x, (int) y, 30, 30);
            }

        }
        public void update() {
            
            if (speed > 0){
                x += Math.sin(angle) * speed;
                y -= Math.cos(angle) * speed;
                if(ReducingSpeed) speed = speed-0.1;
            }
            
        }
        public Rectangle getBounds() {
            return new Rectangle((int) x, (int) y, 22, 30);
        }
        public void reflect(boolean horizontal) {
            ReducingSpeed = true;
            if (horizontal) {
                // Отражение по горизонтали (вертикальная стенка)
                angle = -angle;
            } else {
                // Отражение по вертикали (горизонтальная стенка)
                angle = Math.PI - angle;
            }
            
        }
        public boolean getWhoseMine(){return WhoseMine;}
}
