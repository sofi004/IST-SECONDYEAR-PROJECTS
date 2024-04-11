package xxl.core;

public abstract class Literal extends Content{
    @Override
    public int  asInt() throws ArithmeticException{
        throw new ArithmeticException();
    }

    @Override
    public String asString() throws ArithmeticException{
        throw new ArithmeticException();
    }
    
    @Override
    Literal value(){
        return this;
    }

    @Override
    public boolean accept(Visitor visitor){
        return visitor.visit(this);
    }
}
