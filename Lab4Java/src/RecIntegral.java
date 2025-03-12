
import java.io.Externalizable ;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;


public class RecIntegral implements Externalizable 
{
    private static final long serialVersionUID = 1L;
    private String DowbLimit, UpLimit,Step,Result;
    public RecIntegral() {}
    public RecIntegral(String DowbLimit,String UpLimit,String Step,String Result) {
        this.DowbLimit = DowbLimit;
        this.UpLimit = UpLimit;
        this.Step = Step;
        this.Result = Result;
    }

    public String[] Ret(){
        return new String[]{DowbLimit, UpLimit,Step,Result};
    }
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(DowbLimit);
        out.writeObject(UpLimit);
        out.writeObject(Step);
        out.writeObject(Result);
    }
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        DowbLimit = (String) in.readObject();
        UpLimit = (String) in.readObject();
        Step = (String) in.readObject();
        Result = (String) in.readObject();
    }
}