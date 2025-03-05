
import java.io.*;
import java.net.*;

public class Lab6JavaClient {

    public static void main(String[] args) {
          
        String serverAddress = "localhost"; 
        int port = 12345; 
        try (Socket socket = new Socket(serverAddress, port);
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            while (true) {
                try {

                    Message data = (Message) in.readObject();
                    System.out.printf("DownL: %.5f, UpL: %.5f, Step: %.5f%n", data.DownL, data.UpL, data.Step);

                    if (data.StopServer) {
                        System.out.println("Server requested to stop.");
                        break;
                    }

               
                    double square = 0;
                    for (double i = data.DownL; i < data.UpL; i += data.Step) {
                        if (i + data.Step > data.UpL) {
                            square += (data.UpL - i) * (Math.tan(i) + Math.tan(data.UpL)) / 2;
                        } else {
                            square += (data.Step / 2) * (Math.tan(i) + Math.tan(i + data.Step));
                        }
                    }

                
                    out.writeDouble(square);
                    out.flush();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    break; // Выход из цикла в случае ошибки десериализации
                } catch (IOException e) {
                    e.printStackTrace();
                    break; // Выход из цикла в случае ошибки ввода-вывода
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
