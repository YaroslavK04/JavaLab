
package tanki;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
;

public class Bullet {
        private double x; // Позиция пули по X
        private double y; // Позиция пули по Y
        private double angle; // Угол направления пули
        private double speed = 8; // Скорость пули
        private boolean active = true;
        private int hp = 3;
        private boolean WhoseBullet;
        private BufferedImage BulletImage;

        public Bullet(double startX, double startY, double angle,boolean WhoseBullet) {
            this.x = startX;
            this.y = startY;
            this.angle = angle;
            this.WhoseBullet = WhoseBullet;
            try {
                BulletImage = ImageIO.read(getClass().getResource("/pictures/Bullet.png"));
           } catch (IOException e) {
                e.printStackTrace();
           }
        }

        // Обновление позиции пули
        public void update() {
            if (active) {
                x += Math.sin(angle) * speed;
                y -= Math.cos(angle) * speed;
            }
        }

        // Отрисовка пули
        public void draw(Graphics2D g2d) {
            if (active) {
                if (BulletImage != null){
                     g2d.drawImage(BulletImage,(int)x,(int)y,10, 10, null);
                }
                else {
                    g2d.setColor(Color.RED);
                    g2d.fillOval((int) x, (int) y, 10, 10); 
                }
            }
        }
        

        public Rectangle getBounds() {
            return new Rectangle((int) x, (int) y, 10, 10);
        }


        public void reflect(boolean horizontal) {
            if (hp == 0) active=false;
            if (horizontal) {
                // Отражение по горизонтали (вертикальная стенка)
                angle = -angle;
            } else {
                // Отражение по вертикали (горизонтальная стенка)
                angle = Math.PI - angle;
            }
            hp--;
            
        }
        public boolean isActive() {
            return active;
        }
        public void setActive(boolean active) {
            this.active = active;
        }
        public boolean getWhoseBullet(){return WhoseBullet;}


       
}
