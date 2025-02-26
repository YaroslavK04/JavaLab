

import java.io.Serializable;
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    public double DownL,UpL,Step;
    public boolean StopServer;
    
    public Message(double DownL, double UpL, double Step, boolean StopServer){
        this.DownL = DownL;
        this.UpL = UpL;
        this.Step = Step;
        this.StopServer = StopServer;
    }
}
