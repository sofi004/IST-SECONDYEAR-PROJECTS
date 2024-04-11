package xxl.core;

public abstract class IntervalFunction extends Function{
    protected Range _range;

    public IntervalFunction(Range range, String name){
        super(name);
        _range = range;
        addRecalculate();
    }

    @Override
    public String toString(){
        String func = value().toString() + "=" + super.getName() + "(" + _range.getBeginRow() + ";" + 
        _range.getBeginColumn() + ":" + _range.getEndRow() + ";" + _range.getEndColumn() + ")"; 
        return func;
    }

    @Override
    public boolean accept(Visitor visitor){
        return visitor.visit(this);
    }

    public void addRecalculate(){
        for(Cell c: _range.getListCells()){
            c.addObserver(this);
        }
    }

    @Override
    public void stopObserving(){
        for(Cell c: _range.getListCells()){
            c.getObservers().remove(this);
        }
    }

}
