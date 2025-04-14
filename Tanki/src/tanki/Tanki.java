package tanki;

import Message.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Tanki extends javax.swing.JFrame  {
    private SquarePanel squarePanel; // Панель для отрисовки квадрата

    private boolean wPressed = false;
    private boolean aPressed = false;
    private boolean sPressed = false;
    private boolean dPressed = false;
    private boolean zPressed = false;
    private boolean cPressed = false;
    
    private boolean TakeShot = true;

    private boolean spacePressed = false;

    private Map data;
    private Socket socket ;
    private List<Bullet> bullets = new CopyOnWriteArrayList<>();
    private List<Box> Boxs = new CopyOnWriteArrayList<>();
    private List<Wall> walls = new ArrayList<>();
    private List<Buff> Buffs = new ArrayList<>();
    private List<Mine> Mines = new CopyOnWriteArrayList<>();
    private List <Tank> Tanks = new CopyOnWriteArrayList<>();
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    private Timer timerBuff;
    private Timer timerShot;
    private Timer ShieldBuffTimer;
    private Timer RechargeBuffTimer; 

    private BufferedImage backgroundImage;
    
    private boolean IsRunningServer = true;
    
    private Clip fonSound;
    
    private int WhoWon = 0;



      
    
    public class ConnectServerThread extends Thread {
        

        public ConnectServerThread( ) {

        }

        public void run()
        {
          System.out.println("Server connect");
          
          while (IsRunningServer) {
           try {
               if (socket == null || socket.isClosed() ){
                   in.close();
                   out.close();
                   break;
               }
                Message data = (Message) in.readObject();
                //System.out.println( data.nameMassage);
                switch(data.nameMassage){
                    case 0: // движение танка и башни
                        Tanks.getLast().x = data.x;
                        Tanks.getLast().y = data.y;
                        Tanks.getLast().rotateTank = data.rotateTank;
                        Tanks.getLast().rotateTower = data.rotateTower;
                        break;
                    case 1: // выстрел
                        Tanks.getLast().Shot = true;
                        Bullet bullet = new Bullet(data.rotateTank, data.rotateTower, data.angle,true);
                        bullets.add(bullet);
                        break;
                    case 2:
                        for (var buffs: Buffs){
                            //System.out.println(data.nameButton);
                            if(buffs.id == data.idBuff){
                                buffs.setActive(data.pressButoon);
                                buffs.setTypeBaff(data.nameButton);
                            }
                            //System.out.println(buffs.isActive());
                        }
                        break;
                    case 3:
                         System.out.println( data.nameMassage + data.nameButton);
                        switch(data.nameButton){
                                case 0: // Щит
                                    Tanks.getLast().setBuff(data.nameButton, data.pressButoon,0);
                                break;
                                case 1: // уменьшенная перезарядка
                                    
                                break;
                                case 2:// мины
                                   Mine mine = new Mine(data.rotateTank, data.rotateTower, data.angle,true);
                                   Mines.add(mine);
                                break;

                            }
                        break;
                    default:
                        
                        
                        break;
                }
            }   catch(SocketException  e){
                e.printStackTrace();
                
                IsRunningServer = false;
                 break;
                
            } catch(EOFException e){
                e.printStackTrace();
                IsRunningServer = false;
                
                
            } catch (IOException e) {
                //System.err.println("Ошибка ввода-вывода: " + e.getMessage());
                e.printStackTrace();
                IsRunningServer = false;
            } catch (ClassNotFoundException e) {
               // System.err.println("Класс не найден: " + e.getMessage());
                e.printStackTrace();
                IsRunningServer = false;

            }
           }

        }
    }
    
    
    public Tanki() throws InterruptedException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException {
        
        String serverAddress = "localhost"; 
        int port = 12345; 
        
        
        try {
            socket = new Socket(serverAddress, port);
            socket.setSoTimeout(5000);
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            data = (Map) in.readObject();
            ConnectServerThread ServerConnect = new ConnectServerThread();
            ServerConnect.start();   
        } catch (IOException e) {
            System.err.println("Failed to connect to server: " + e.getMessage());
            e.printStackTrace();
        }
        
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/pictures/fon.png"));
        } catch (IOException ex) {
            Logger.getLogger(Tanki.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            URL soundUrl = getClass().getResource("/sounds/fonmusic.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundUrl);
            fonSound = AudioSystem.getClip();
            fonSound.open(audioIn);
            FloatControl gainControl = (FloatControl) fonSound.getControl(FloatControl.Type.MASTER_GAIN);
    
            float volume = 1f; // от 0.0 до 1.0 (50% громкости)
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
            fonSound.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Ошибка загрузки звука выстрела");
            e.printStackTrace();
        }
        
        fonSound.start();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() throws UnsupportedAudioFileException, LineUnavailableException {
        // Настройка основного окна
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Танки");
        Tanks.add(new Tank(data.numberTank,data.x[data.numberTank],data.y[data.numberTank],data.widthTank,data.heightTank,data.widthTower, data.heightTower,data.colorTank[data.numberTank],data.Rechargetime,data.StartXPositionHeart[data.numberTank],data.StartYPositionHeart[data.numberTank]));
        int AnotherNumberTank = (data.numberTank == 1)? 0: 1;
        Tanks.add(new Tank(AnotherNumberTank,data.x[AnotherNumberTank],data.y[AnotherNumberTank],data.widthTank,data.heightTank,data.widthTower, data.heightTower,data.colorTank[AnotherNumberTank],data.Rechargetime,data.StartXPositionHeart[AnotherNumberTank],data.StartYPositionHeart[AnotherNumberTank]));
        
        if (!data.RetWall().isEmpty()){
            
            for(var Walls: data.RetWall()){
                walls.add(new Wall(Walls.x,Walls.y,Walls.height,Walls.width,Walls.color));
            }
        }
        if (!data.RetBuff().isEmpty()){
            int idCounter = 0;
            for(var buff: data.RetBuff()){
                Buffs.add(new Buff(idCounter,buff.x,buff.y,buff.height,buff.width));
                idCounter++;
            }
        }
        if (!data.RetBox().isEmpty()){
            for(var box: data.RetBox()){
                Boxs.add(new Box(box.x,box.y,box.height,box.width,box.color));
            }
        }
       


        
        squarePanel = new SquarePanel();
         
        // Устанавливаем GroupLayout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(squarePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1600, Short.MAX_VALUE)
            
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(squarePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Не используется в данном примере
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Обработка нажатия клавиш
                handleKeyPress(e, true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyPress(e, false);
            }
        });


        Timer timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (var tank: Tanks){
                    tank.update();
                    tank.AnimationBoom();
                    squarePanel.repaint();
                }
                
                Tanks.getFirst().update();
                for (Bullet bullet : bullets) {
                    checkBulletCollisions(bullet);
                    bullet.update();
                   
                }
                if (!Mines.isEmpty()){
                    for (Mine mines : Mines) {
                        checkMineCollisions(mines);
                        mines.update();
                        
                    }
                }

                updateSquarePosition();
                squarePanel.repaint(); // Перерисовываем панель
            }
        });
        

        timerShot = new Timer(Tanks.getFirst().Rechargetime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Обновляем позиции пуль
               TakeShot = true;
            }
        });
        timerBuff = new Timer(6000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               for (var Buff: Buffs){
                   if (!Buff.isActive()){
                        Buff.setActive(true);
                        Random rand = new Random();
                        Buff.setTypeBaff(rand.nextInt(3));

                        Message message = new Message(2); 
                        message.pressButoon = true;
                        message.idBuff = Buff.id;
                        message.nameButton = Buff.getTypeBaff();

                        try { 
                            out.writeObject(message);
                            out.flush();
                        } catch (IOException ex) {
                            Logger.getLogger(Tanki.class.getName()).log(Level.SEVERE, null, ex);
                        }
                   }
               }
            }
        });
        ShieldBuffTimer = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Обновляем позиции пуль
               Tanks.getFirst().setBuff(0, false,0);
               Tanks.getFirst().HaveBuff = false;
                Message message = new Message(3); 
                message.pressButoon = false;
                message.nameButton = 0;

                try { 
                    out.writeObject(message);
                    out.flush();
                } catch (IOException ex) {
                    Logger.getLogger(Tanki.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        RechargeBuffTimer = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
               Tanks.getFirst().setBuff(0, false,data.Rechargetime);
               timerShot.setInitialDelay(data.Rechargetime);
               Tanks.getFirst().HaveBuff = false;
                
            }
        });
        
        //timerAnimationTank.start();
        timer.start();

        pack();
    }
     private void handleKeyPress(KeyEvent e, boolean isPressed) {
        int keyCode = e.getKeyCode(); // Получаем код нажатой клавиши

        // Перемещаем квадрат в зависимости от нажатой клавиши
        switch (keyCode) {
            case KeyEvent.VK_A:           
                aPressed = isPressed;
                break;
            case KeyEvent.VK_S:
                sPressed = isPressed;
                break;
            case KeyEvent.VK_D:
                dPressed = isPressed;
                break;
            case KeyEvent.VK_Z:
                zPressed = isPressed;
                break;
            case KeyEvent.VK_C:
                cPressed = isPressed;
                break;
            case KeyEvent.VK_W:
               wPressed = isPressed;
                break;
            case KeyEvent.VK_SPACE: // Выстрел
                if (isPressed){
                    if (TakeShot){
                        TakeShot = false;
                        double endOfTowerX = Tanks.getFirst().x +  Tanks.getFirst().widthTank  / 2  + Math.sin(Tanks.getFirst().rotateTank + Tanks.getFirst().rotateTower) * Tanks.getFirst().heightTower/2;
                        double endOfTowerY = Tanks.getFirst().y +  Tanks.getFirst().heightTank / 2  - Math.cos(Tanks.getFirst().rotateTank + Tanks.getFirst().rotateTower) * Tanks.getFirst().heightTower/2 ;
                        Bullet bullet = new Bullet(endOfTowerX, endOfTowerY, Tanks.getFirst().rotateTank + Tanks.getFirst().rotateTower,false);
                        Message message = new Message(1); 
                        message.rotateTank = endOfTowerX;
                        message.rotateTower = endOfTowerY;
                        message.angle = Tanks.getFirst().rotateTank + Tanks.getFirst().rotateTower;
                        Tanks.getFirst().fire();
                        Tanks.getFirst().Shot = true;
                        try { 
                            out.writeObject(message);
                            out.flush();
                        } catch (IOException ex) {
                            Logger.getLogger(Tanki.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        bullets.add(bullet);
                        timerShot.setRepeats(false);
                        timerShot.start();
                    }
                }
                break;
                case KeyEvent.VK_Q: // Выстрел миной
                if (isPressed){
                    if (Tanks.getFirst().NumberMines > 0){
                        if (TakeShot){
                            Tanks.getFirst().NumberMines--;
                            if(Tanks.getFirst().NumberMines <= 0)Tanks.getFirst().HaveBuff = false;
                            TakeShot = false;
                            double endOfTowerX = Tanks.getFirst().x +  Tanks.getFirst().widthTank  / 2  + Math.sin(Tanks.getFirst().rotateTank + Tanks.getFirst().rotateTower) * Tanks.getFirst().heightTower;
                            double endOfTowerY = Tanks.getFirst().y +  Tanks.getFirst().heightTank / 2  - Math.cos(Tanks.getFirst().rotateTank + Tanks.getFirst().rotateTower) * Tanks.getFirst().heightTower ;
                            Mine mine = new Mine(endOfTowerX, endOfTowerY, Tanks.getFirst().rotateTank + Tanks.getFirst().rotateTower,false);
                            Message message = new Message(3); 
                            message.nameButton = 2;
                            message.rotateTank = endOfTowerX;
                            message.rotateTower = endOfTowerY;
                            message.angle = Tanks.getFirst().rotateTank + Tanks.getFirst().rotateTower;

                            try { 
                                out.writeObject(message);
                                out.flush();
                            } catch (IOException ex) {
                                Logger.getLogger(Tanki.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Mines.add(mine);
                            timerShot.setRepeats(false);
                            timerShot.start();
                        }
                    }
                }
                break;
        }


    }
      private void updateSquarePosition(  ) {
            int newX = Tanks.getFirst().x;
            int newY = Tanks.getFirst().y;

            if (aPressed) Tanks.getFirst().rotateTank -= 0.05;
            if (dPressed) Tanks.getFirst().rotateTank += 0.05;
            if (wPressed) {
                newY -= Math.cos(-Tanks.getFirst().rotateTank) * 5;
                newX -= Math.sin(-Tanks.getFirst().rotateTank) * 5;
            }
            if (sPressed) {
                newY += Math.cos(-Tanks.getFirst().rotateTank) * 5;
                newX += Math.sin(-Tanks.getFirst().rotateTank) * 5;
            }
            if (zPressed) Tanks.getFirst().rotateTower -= 0.05;
            if (cPressed) Tanks.getFirst().rotateTower += 0.05;
            if (!checkCollisionTank(newX,newY,Tanks.getFirst().widthTank,Tanks.getFirst().heightTank)) {
                Tanks.getFirst().x = newX;
                Tanks.getFirst().y = newY;

            }
            Message message = new Message(0); 
            message.x = Tanks.getFirst().x;
            message.y = Tanks.getFirst().y;
            message.rotateTank = Tanks.getFirst().rotateTank;
            message.rotateTower = Tanks.getFirst().rotateTower;
            try { 
                out.writeObject(message);
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(Tanki.class.getName()).log(Level.SEVERE, null, ex);
            }
        // Перерисовываем панель
        squarePanel.repaint();
    }




    public boolean checkCollisionTank(int newX, int newY,int widthTank,int heightTank) {
        Rectangle tankBounds = new Rectangle(newX, newY, widthTank, heightTank);
       boolean collision = false;
        for (Wall wall : walls) {
            if (tankBounds.intersects(wall.getBounds())) {
                collision = true; 
            }
        }
        for (var box: Boxs){
            if (box != null && box.isActive()) {
                if ( tankBounds.intersects(box.getBounds())) {
                    collision = true;
                }
            }
        }
        if (!Tanks.getFirst().HaveBuff){
            for (Buff buffs : Buffs) {
                if (tankBounds.intersects(buffs.getBounds())) {

                    try { 
                        Message message = new Message(2); 
                        message.pressButoon = false;
                        out.writeObject(message);
                        out.flush();
                        Message message1 = new Message(3);
                        message1.idBuff = buffs.id;
                        message1.nameButton = buffs.getTypeBaff();
                        message1.pressButoon = true;
                        out.writeObject(message1);
                        out.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Tanki.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    buffs.setActive(false);
                    timerBuff.setRepeats(false); 
                    timerBuff.start();
                    
                    Tanks.getFirst().setBuff(buffs.getTypeBaff(), true,20);
                    switch(buffs.getTypeBaff()){
                        case 0:
                            Tanks.getFirst().HaveBuff = true;
                            ShieldBuffTimer.setRepeats(false);
                            ShieldBuffTimer.start();
                            break;
                        case 1:
                            Tanks.getFirst().HaveBuff = true;
                            timerShot.setInitialDelay(Tanks.getFirst().Rechargetime);
                            RechargeBuffTimer.setRepeats(false);
                            RechargeBuffTimer.start();
                            break;
                        case 2:
                            Tanks.getFirst().HaveBuff = true;
                            Tanks.getFirst().setBuff(2, true, 2000);
                            
                            break;
                    }
                    
                }
            }
        }
        if (tankBounds.intersects(Tanks.getLast().getBounds())){
            collision = true; 
        }
        return collision;
    }


    private void checkBulletCollisions(Bullet bullet) {

            if (bullet.isActive()) {
                Rectangle bulletBounds = bullet.getBounds();
                if (bulletBounds.intersects(Tanks.getFirst().getBounds()) && bullet.getWhoseBullet() ){
                    if (!Tanks.getFirst().HaveShield){
                        Tanks.getFirst().Boom = true;
                        Tanks.getFirst().HP --;
                        if ( Tanks.getFirst().HP == 0){
                            WhoWon = 1;

                        }
                        Tanks.getLast().ReturnToSpawn();
                        Mines.clear();
                    }
                    bullets.remove(bullet);
                }else if(bulletBounds.intersects(Tanks.getLast().getBounds()) && !bullet.getWhoseBullet()){
                     if (!Tanks.getLast().HaveShield){
                        Tanks.getLast().Boom = true;
                        Tanks.getLast().HP --;
                        if ( Tanks.getLast().HP == 0){
                            WhoWon = 2;

                        }
                        Tanks.getFirst().ReturnToSpawn();
                        Mines.clear();
                     }
                     bullets.remove(bullet);
                }
                for (Wall wall : walls) {
                    if (bulletBounds.intersects(wall.getBounds())) {
                        boolean horizontalCollision = isHorizontalCollision(bulletBounds, wall.getBounds());
                        
                        bullet.reflect(horizontalCollision);
                    }
                }
                for (var box: Boxs){
                    if (box != null && box.isActive()) {
                        if ( bulletBounds.intersects(box.getBounds())) {
                            if (!box.notifyHitListeners(bullet)){
                                Boxs.remove(box);
                            }
                            bullets.remove(bullet);
                        }
                    }
                }
                
            }
        
    }
        private void checkMineCollisions(Mine mine) {


                Rectangle mineBounds = mine.getBounds();
                if (mineBounds.intersects(Tanks.getFirst().getBounds()) && mine.getWhoseMine() ){
                    if (!Tanks.getFirst().HaveShield){
                        Tanks.getFirst().Boom = true;
                        Tanks.getFirst().HP --;
                        Tanks.getLast().ReturnToSpawn();
                    }
                    
                    Mines.remove(mine);
                }else if(mineBounds.intersects(Tanks.getLast().getBounds()) && !mine.getWhoseMine()){
                    if (!Tanks.getLast().HaveShield){
                        Tanks.getLast().Boom = true;
                        Tanks.getLast().HP --;
                        Tanks.getFirst().ReturnToSpawn();
                     }
                    
                    Mines.remove(mine);
                }
                for (Wall wall : walls) {
                    if (mineBounds.intersects(wall.getBounds())) {
                        boolean horizontalCollision = isHorizontalCollision(mineBounds, wall.getBounds());  
                        mine.reflect(horizontalCollision);
                    }
                }
                for (var box: Boxs){
                    if (mineBounds.intersects(box.getBounds())) {
                        boolean horizontalCollision = isHorizontalCollision(mineBounds, box.getBounds());  
                        mine.reflect(horizontalCollision);
                    }
                }
                
            
        
    }
        
        private boolean isHorizontalCollision(Rectangle bulletBounds, Rectangle wallBounds) {
            boolean horizontalCollision = true;

            if (bulletBounds.getCenterX() < wallBounds.getMinX() || bulletBounds.getCenterX() > wallBounds.getMaxX()) {
                horizontalCollision = true; // Вертикальная стенка
            } else if (bulletBounds.getCenterY() < wallBounds.getMinY() || bulletBounds.getCenterY() > wallBounds.getMaxY()) {
                horizontalCollision = false; // Горизонтальная стенка
            }

            return horizontalCollision;
        }
    

    class SquarePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics tank) {
            super.paintComponent(tank);
            Graphics2D g2d = (Graphics2D) tank;
            if (WhoWon == 0){
                int x = (getWidth() - backgroundImage.getWidth()) / 2;
                int y = (getHeight() - backgroundImage.getHeight()) / 2;
                g2d.drawImage(backgroundImage, x, y, null);
            
                
                
                for (Mine mine : Mines) {
                    mine.draw(g2d);
                }
                var oldTransform = g2d.getTransform();
                for (var Tankss: Tanks){
                    try {
                        Tankss.draw(g2d);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Tanki.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                g2d.setTransform(oldTransform);
                for (Box box : Boxs) {
                    box.draw(g2d);
                }

                for (Wall wall : walls) {
                    wall.draw(g2d);
                }

                for (Bullet bullet : bullets) {
                    bullet.draw(g2d);
                }
                for (Buff buff : Buffs) {
                    buff.draw(g2d);
                }
            }else{
                fonSound.stop();
                Mines.clear();
                Tanks.clear();
                Boxs.clear();
                walls.clear();
                bullets.clear();
                Buffs.clear();
                int rectWidth = 1600 ;
                int rectHeight = 900 ;

                // Координаты для центрирования
                int rectX = (getWidth() - rectWidth) / 2;
                int rectY = (getHeight() - rectHeight) / 2;
                g2d.setFont(new Font("Arial", Font.BOLD, 56));
                if (WhoWon == 2){


                    String TextWhoWon = "ПОБЕДА";

                    try {
                        // Загружаем изображение (лучше делать это один раз при инициализации)
                        Image BGImage = ImageIO.read(getClass().getResource("/pictures/Win.png"));

                        // Рисуем изображение вместо прямоугольника
                        g2d.drawImage(BGImage, rectX, rectY, rectWidth, rectHeight, null);

                    } catch (IOException ex) {
                        // Если изображение не загрузилось, рисуем запасной вариант
                        g2d.setColor(new Color(0, 0, 0, 200)); // Полупрозрачный черный
                        g2d.fillRect(rectX, rectY, rectWidth, rectHeight);
                    }

                    // Настройка текста
                    
                    g2d.setColor(Color.GREEN);

                    FontMetrics fm = g2d.getFontMetrics();
                    int textWidth = fm.stringWidth(TextWhoWon);
                    int textHeight = fm.getHeight();

                    int textX = rectX + (rectWidth - textWidth) / 2;
                    int textY = rectY + (rectHeight - textHeight) / 2 + fm.getAscent();

                    g2d.drawString(TextWhoWon, textX, textY);
                }else{
                    String TextWhoWon = "ПОPИГРЫШ";

                    try {
                        // Загружаем изображение (лучше делать это один раз при инициализации)
                        Image BGImage = ImageIO.read(getClass().getResource("/pictures/lost.png"));

                        // Рисуем изображение вместо прямоугольника
                        g2d.drawImage(BGImage, rectX, rectY, rectWidth, rectHeight, null);

                    } catch (IOException ex) {
                        // Если изображение не загрузилось, рисуем запасной вариант
                        g2d.setColor(new Color(0, 0, 0, 200)); // Полупрозрачный черный
                        g2d.fillRect(rectX, rectY, rectWidth, rectHeight);
                    }

                    // Настройка текста
                    
                    g2d.setColor(Color.RED);

                    FontMetrics fm = g2d.getFontMetrics();
                    int textWidth = fm.stringWidth(TextWhoWon);
                    int textHeight = fm.getHeight();

                    int textX = rectX + (rectWidth - textWidth) / 2;
                    int textY = rectY + (rectHeight - textHeight) / 2 + fm.getAscent();

                    g2d.drawString(TextWhoWon, textX, textY);
                }
            }

        }
        
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try { 
                    new Tanki().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Tanki.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Tanki.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(Tanki.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(Tanki.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}

