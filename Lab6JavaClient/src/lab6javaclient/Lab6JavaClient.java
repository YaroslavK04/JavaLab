
package lab6javaclient;
import java.io.*;
import java.net.*;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;




public class Lab6JavaClient {

    public static void main(String[] args) {
          
        String serverAddress = "localhost"; 
        int port = 12345; 
        
        try (Socket socket = new Socket(serverAddress, port);
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            //while(true){
                Message data = (Message) in.readObject();
                System.out.printf("DownL: %.5f, UpL: %.5f, Step: %.5f%n", data.DownL, data.UpL, data.Step);
                if (data.StopServer){
                    socket.close();
                    //break;
                }
                double Square = 0;
                List<MyThread> ListThread = new ArrayList<>();
                int CountThread = 4;
                double range = (data.UpL - data.DownL)/CountThread;
                double down, up;
                for (int i = 0; i < CountThread; i++)
                {
                    down = data.DownL + i * range;
                    up = (i == CountThread - 1) ? data.UpL : down + range;
                    ListThread.add(new MyThread(down,up,data.Step));
                    ListThread.getLast().start();
                }

                for(MyThread thread: ListThread){
                    try{
                        thread.join();
                        Square += thread.getResult();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                
                out.writeObject(Square);
                out.flush();
            //}

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        
        }
    }
    
}
