
import java.io.*;
import java.net.*;
public class SaveSocket {
    public Socket socket;
    ObjectOutputStream out;
    DataInputStream in;
    public SaveSocket(Socket sock)
    {
        try{
        socket = sock;
        in = new DataInputStream(sock.getInputStream());
        out = new ObjectOutputStream(sock.getOutputStream());
        }catch(IOException e){
            e.getMessage();        
        }
                               
    }  
}
