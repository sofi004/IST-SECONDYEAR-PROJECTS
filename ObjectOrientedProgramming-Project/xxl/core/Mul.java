package xxl.core;

public class Mul extends BinaryFunction {
    public Mul(Content arg0, Content arg1, String name){
        super(arg0, arg1, name);
        update();
    }
    
    @Override
    protected Literal compute(){
        try{
            return new Number(_arg0.value().asInt() * _arg1.value().asInt());

        }catch(ArithmeticException e){
            return new Null().value();
        }
    }
    
    @Override
    public boolean accept(Visitor visitor){
        return visitor.visit(this);
    }
}
