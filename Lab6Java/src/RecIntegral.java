
import java.io.Serializable;


public class RecIntegral implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String DowbLimit, UpLimit,Step,Result;
    public RecIntegral(String DowbLimit,String UpLimit,String Step,String Result) {
        this.DowbLimit = DowbLimit;
        this.UpLimit = UpLimit;
        this.Step = Step;
        this.Result = Result;
    }

    public String[] Ret(){
        return new String[]{DowbLimit, UpLimit,Step,Result};
    }

}