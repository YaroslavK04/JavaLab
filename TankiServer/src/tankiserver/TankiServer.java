
package tankiserver;
import Message.Map;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;
import java.net.*;


public class TankiServer {
    
    public static void main(String[] args) throws InterruptedException {
       
        int port = 12345; 
        int COUNT_CLIENT =2 ;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server start...");
            while (true){
                 List<Socket> ClientSocket = new ArrayList<>();
                for (int i = 0; i<COUNT_CLIENT; i++){
                    System.out.println("Wait " + (i+1) +" client...");
                    Socket newClient = serverSocket.accept();
                    ClientSocket.add(newClient);
                    System.out.println("Accept " + (i+1) +" client!");
                }

                MyThread player1 = new MyThread(ClientSocket.get(0),ClientSocket.get(1),0);
                MyThread player2 = new MyThread(ClientSocket.get(1),ClientSocket.get(0),1);
                player1.start();
                player2.start();

            }
         } catch (IOException e) {
            System.err.println("Ошибка сервера: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}

