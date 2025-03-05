
public class MyThread extends Thread  {
    private double DownLimit,UpLimit, Step, Result;

    public MyThread(double Down,double Up,double Step ) {
        this.DownLimit = Down;
        this.UpLimit = Up;
        this.Step = Step;
        this.Result = 0;
    }
    
    public void run(){
        for (double i = DownLimit; i < UpLimit; i += Step ){
            Result += ((i + Step) > UpLimit) ? ((Math.tan(i) + Math.tan(UpLimit)) * (UpLimit - i) / 2) : ((Math.tan(i) + Math.tan(i+ Step)) * (Step ) / 2);
        }
        
    }
    
    public double getResult(){
        return Result;
    }
}
