
import java.util.concurrent.Callable;


public class MyFuture implements Callable<Double> {
        private double DownLimit,UpLimit, Step, Result;

    public MyFuture(double Down,double Up,double Step ) {
        this.DownLimit = Down;
        this.UpLimit = Up;
        this.Step = Step;
        this.Result = 0;
    }
    
    @Override
    public Double call() throws Exception{
        for (double i = DownLimit; i < UpLimit; i += Step ){
            Result += ((i + Step) > UpLimit) ? ((Math.tan(i) + Math.tan(UpLimit)) * (UpLimit - i) / 2) : ((Math.tan(i) + Math.tan(i+ Step)) * (Step ) / 2);
        }
         return Result;
    }

}
