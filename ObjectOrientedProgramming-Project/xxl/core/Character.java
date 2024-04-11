package xxl.core;
import java.io.Serializable;

public class Character extends Literal implements Serializable{
    private String _value;

    public Character(String val){
        _value = val;
    }

    @Override
    public String toString(){
        return _value;  
    }
    
    @Override
    public String asString(){
        return _value;
    }

    @Override
    public boolean accept(Visitor visitor){
        return visitor.visit(this);
    }
}
