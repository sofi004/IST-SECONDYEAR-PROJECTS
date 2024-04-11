package xxl.core;

public abstract class BinaryFunction extends Function{
    protected Content _arg0;
    protected Content _arg1;

    public BinaryFunction(Content arg0, Content arg1, String name){
        super(name);
        _arg0 = arg0;
        _arg1 = arg1;
        addRecalculate();
    }

    @Override
    public String toString(){
        String[] arg0 = _arg0.toString().split("=");
        String[] arg1 = _arg1.toString().split("=");
        String func = value().toString() + "=" + super.getName() + "(" + arg0[arg0.length - 1] + "," + arg1[arg1.length-1] + ")"; 
        return func;
    }

    public Content getFirstArg(){
        return _arg0;
    }

    public Content getSecondArg(){
        return _arg1;
    }

    public void addRecalculate(){
        Visitor visitor = new VisitFunctionsReferecences();
        if(_arg0.accept(visitor)){
        _arg0.getCell().addObserver(this);
        }
        if(_arg1.accept(visitor)){
        _arg1.getCell().addObserver(this);
        }
    }

    @Override
    public boolean accept(Visitor visitor){
        return visitor.visit(this);
    }

    @Override
    public void stopObserving(){
        Visitor visitor = new VisitFunctionsReferecences();
        if(_arg0.accept(visitor)){
            _arg0.getCell().getObservers().remove(this);
        }
        if(_arg1.accept(visitor)){
            _arg1.getCell().getObservers().remove(this);
        }
    }
}