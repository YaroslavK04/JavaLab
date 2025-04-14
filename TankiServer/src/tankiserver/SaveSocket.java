package tankiserver;

import java.io.*;
import java.net.*;

public class SaveSocket {
    
    public Socket socket;
    public ObjectOutputStream out;
    public ObjectInputStream in;
    public SaveSocket(Socket sock,ObjectInputStream in,ObjectOutputStream out)
    {
        socket = sock;
        this.in = in;
        this.out = out;
                               
    }  
}
