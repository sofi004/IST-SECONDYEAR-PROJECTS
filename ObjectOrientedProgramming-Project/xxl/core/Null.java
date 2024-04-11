package xxl.core;
import java.io.Serializable;

public class Null extends Literal implements Serializable{
    public Null(){
    }

    @Override
    public String toString(){
        return "";
    }

    @Override
    public Literal value(){
        return new Character("#VALUE");
    }

    @Override
    public boolean accept(Visitor visitor){
        return visitor.visit(this);
    }

}
