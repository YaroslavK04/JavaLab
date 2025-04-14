
package tankiserver;

import Message.*;
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyThread extends Thread  {
    private Socket player, opponent;
    private ObjectOutputStream out;
    private ObjectInputStream in; 
    private int numberTank;

    public MyThread(Socket player,Socket opponent, int numberTank ) throws IOException {
        this.numberTank = numberTank;
        this.player = player;
        this.opponent = opponent;
        out = new ObjectOutputStream( this.opponent.getOutputStream());
        out.flush();
        in = new ObjectInputStream( this.player.getInputStream());
    }

    public void run()
    {
        Map sendMap = new Map(numberTank); 
        try { 
            out.writeObject(sendMap);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        }
                
       while(true){
            try {
                
                Message message = (Message) in.readObject();
                out.writeObject(message);
                out.flush();
               // System.out.println("Данные получены: " + message.nameButton + " " + message.pressButoon);
            } catch(SocketException  e){
                e.printStackTrace();
                try {
                    in.close();
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                break;
            } catch (IOException e) {
                System.err.println("Ошибка ввода-вывода: " + e.getMessage());
                e.printStackTrace();
                
            } catch (ClassNotFoundException e) {
                System.err.println("Класс не найден: " + e.getMessage());
                e.printStackTrace();
            }finally{
                //break;
            }
       }

    }

    public double getResult(){
        return 0.0;
    }
}

