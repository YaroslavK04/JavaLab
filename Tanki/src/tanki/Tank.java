package tanki;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Tank {
    public int x, y; // Позиция танка
    public int SpawnX, SpawnY; // Позиция танка
    public int widthTank, heightTank; // Размеры корпуса танка
    public int widthTower, heightTower; // Размеры башни танка
    public Color colorTank; // Цвет корпуса танка
    public double rotateTank = 0; // Угол поворота корпуса
    public double rotateTower = 0; // Угол поворота башни
    public int HP = 4;
    public boolean HaveShield = false;
    public boolean HaveBuff = false;
    public int Rechargetime;
    public int NumberMines = 0;
    private BufferedImage tankBodyImage;
    private BufferedImage tankTowerImage;
    private BufferedImage boomImage;
    private BufferedImage shieldImage;
    private BufferedImage[] heartImage;
    private int StartXPositionHeart,StartYPositionHeart ;
    public boolean Shot = false;
    public boolean Boom = false;
    
    
    private int currentAnimationFrame = 0;
    private int currentAnimationFrameBoom = 0;

    private long lastFrameTime;
    private long lastFrameTimeBoom;
    
    private Clip shootSound;
    private Clip boomSound;
    




    public Tank(int numbertank,int x, int y, int widthTank, int heightTank, int widthTower, int heightTower, Color colorTank,int Rechargetime, int StartXPositionHeart, int StartYPositionHeart) throws UnsupportedAudioFileException, LineUnavailableException {
        this.x = x;
        this.y = y;
        this.SpawnX = x;
        this.SpawnY = y;
        this.widthTank = widthTank;
        this.heightTank = heightTank;
        this.widthTower = widthTower;
        this.heightTower = heightTower;
        this.colorTank = colorTank;
        this.Rechargetime = Rechargetime;
        this.StartXPositionHeart = StartXPositionHeart;
        this.StartYPositionHeart = StartYPositionHeart;
        try {
            // Загрузка изображений (поместите файлы в папку resources)
            if (numbertank == 0){
                tankBodyImage = ImageIO.read(getClass().getResource("/pictures/body_tracks.png"));
                tankTowerImage = ImageIO.read(getClass().getResource("/pictures/tw1.png"));
            }else{
                tankBodyImage = ImageIO.read(getClass().getResource("/pictures/body_tracks2.png"));
                tankTowerImage = ImageIO.read(getClass().getResource("/pictures/tw3.png"));
            }
            boomImage = ImageIO.read(getClass().getResource("/pictures/boom.png"));
            shieldImage = ImageIO.read(getClass().getResource("/pictures/shield.png"));
            heartImage = new BufferedImage[HP];
            for (int i = 0; i <HP; i++){
                heartImage[i] = ImageIO.read(getClass().getResource("/pictures/Health.png"));
            }
            try {
                URL soundUrl = getClass().getResource("/sounds/shot.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundUrl);
                shootSound = AudioSystem.getClip();
                shootSound.open(audioIn);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.err.println("Ошибка загрузки звука выстрела");
                e.printStackTrace();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для поворота корпуса
    public void rotateTank(double angle) {
        this.rotateTank += angle;
    }

    // Метод для поворота башни
    public void rotateTower(double angle) {
        this.rotateTower += angle;
    }
    
    
    public void update() {
        // Обновление анимации выстрела
        if (Shot && System.currentTimeMillis() - lastFrameTime > 50) {
            currentAnimationFrame++;
            lastFrameTime = System.currentTimeMillis();
            
            if (currentAnimationFrame >= 8) {
                currentAnimationFrame = 0;
                Shot = false; // Анимация завершена
            }
        }
    }
    
    public void ReturnToSpawn(){
        x = SpawnX;
        y = SpawnY;
    }
    
    public void AnimationBoom() {
        // Обновление анимации выстрела
        if (Boom && System.currentTimeMillis() - lastFrameTimeBoom >1000) {
            currentAnimationFrameBoom++;
            lastFrameTimeBoom = System.currentTimeMillis();
            
            if (currentAnimationFrameBoom >= 4) {
                currentAnimationFrameBoom = 0;
                Boom = false; // Анимация завершена
                ReturnToSpawn();
                   
            }
        }
    }
    public void fire() {
        // Воспроизведение звука при выстреле
        if (shootSound != null) {
            shootSound.setFramePosition(0); // Перемотка в начало
            shootSound.start();
        }
        
    }

    // Метод для отрисовки танка
    public void draw(Graphics2D g2d) throws InterruptedException {
        AffineTransform oldTransform = g2d.getTransform();


        if (tankBodyImage != null  ) {
            g2d.rotate(rotateTank, x + widthTank / 2, y + heightTank / 2);
            g2d.drawImage(tankBodyImage, x, y, widthTank, heightTank, null);
        } else {
            // Фолбэк: рисуем прямоугольник, если изображение не загрузилось
            g2d.setColor(colorTank);
            g2d.rotate(rotateTank, x + widthTank / 2, y + heightTank / 2);
            g2d.fillRect(x, y, widthTank, heightTank);
            
        }
        if (this.HaveShield){
            if (shieldImage != null){
                     g2d.drawImage(shieldImage,x + widthTank / 2 - 50,y + heightTank / 2 - 50, 100, 100, null);
            }else{
                g2d.setColor(Color.RED);
                g2d.fillOval(x + widthTank / 2 - 50, y + heightTank / 2 - 50, 100, 100);
            }
         
        }
        g2d.setTransform(oldTransform);
        if (tankTowerImage != null ) {
            if (!Shot){
                g2d.rotate(rotateTank + rotateTower, x + widthTank / 2, y + heightTank / 2);
                g2d.drawImage(tankTowerImage.getSubimage(0, 0, 40, 90), 
                    x + widthTank / 2 - widthTower / 2, 
                    y + heightTank / 2 - heightTower /2 - 10, 
                    widthTower, heightTower, null);
            }else{
                g2d.rotate(rotateTank + rotateTower, x + widthTank / 2, y + heightTank / 2);
                    g2d.drawImage(tankTowerImage.getSubimage(
                        currentAnimationFrame * 40, 
                        0, 
                        40, 
                        90), 
                    x + widthTank / 2 - widthTower / 2, 
                    y + heightTank / 2 - heightTower / 2 - 10, 
                    widthTower, heightTower, null);
                
            }
            g2d.setTransform(oldTransform);
        } else {
            g2d.setColor(Color.YELLOW);
            g2d.rotate(rotateTank + rotateTower, x + widthTank / 2, y + heightTank / 2);
            g2d.fillRect(x + widthTank / 2 - widthTower / 2, 
                        y + heightTank / 2 - heightTower, 
                        widthTower, heightTower);
            g2d.setTransform(oldTransform);
        }
        if (Boom){
           
                    g2d.drawImage(boomImage.getSubimage(
                        currentAnimationFrameBoom * 45, 
                        0, 
                        45, 
                        47), 
                    x + widthTank / 2 - 50 , 
                    y + heightTank / 2 - 50, 
                    100, 100, null);
        }
        if (heartImage != null){
            for (int i =0; i < 4; i++){
                if ( i < HP){
                    g2d.drawImage(heartImage[i].getSubimage(
                        0, 
                        0, 
                        13, 
                        11), 
                    StartXPositionHeart , 
                    StartYPositionHeart + i*30, 
                    30, 30, null);
                } else{
                    g2d.drawImage(heartImage[i].getSubimage(
                        13, 
                        0, 
                        13, 
                        11), 
                    StartXPositionHeart , 
                    StartYPositionHeart + i*30, 
                    30, 30, null);
                }
            }
        }

  
        g2d.setTransform(oldTransform);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, widthTank, heightTank);
    }
    public void setBuff(int numberBuff, boolean statusBuff, int Rechargetime){
        //HaveBuff = true;
        switch(numberBuff){
                case 0: // Щит
                    HaveShield = statusBuff;
                break;
                case 1: // уменьшенная перезарядка
                    this.Rechargetime = Rechargetime;
                break;
                case 2:// мины
                    NumberMines = 3;
                break;

            }
    }


}
