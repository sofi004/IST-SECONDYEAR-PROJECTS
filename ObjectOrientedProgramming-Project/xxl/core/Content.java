package xxl.core;

public abstract class Content implements Observer{
    public abstract String toString();
    abstract Literal value();

    public int asInt(){
        return this.value().asInt();    
    }

    public String asString(){
        return this.value().asString();
    }

    public boolean accept(Visitor v){
        return v.visit(this);
    }

    public String getName(){
        return null;
    }

    public void update(){
     
    }

    public void stopObserving(){

    }

    public Cell getCell(){
        return null;
    }
}