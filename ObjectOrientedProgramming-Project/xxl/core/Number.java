package xxl.core;  

import java.io.Serializable;

public class Number extends Literal implements Serializable{
    private int _value;
    
    public Number(int val){
        _value = val;
    }

    @Override
    public int asInt(){
        return _value;
    }

    @Override
    public String toString(){
        return String.valueOf(_value);
    }

    @Override
    public boolean accept(Visitor visitor){
        return visitor.visit(this);
    }

}
