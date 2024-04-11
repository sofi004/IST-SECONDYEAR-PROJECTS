package xxl.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cell implements Serializable{
    private int _column;
    private int _row;
    private Content _content;
    private List<Observer> _observers;

    public Cell(int row, int column){
        _column = column;
        _row = row;
        _content = new Null();
        _observers = new ArrayList<Observer>();
    }

    public int getRow(){
        return _row;
    }

    public int getCollumn(){
        return _column;
    }

    public List<Observer> getObservers(){
        return _observers;
    }

    public Content getContent(){
        return _content;
    }

    public String toString(){
        return  _row + ";" + _column + "|" + _content.toString(); 
    }

    public Literal value(){
        return _content.value();
    }

    public void insertContent(Content content){
        Visitor visitor = new VisitFunctionsReferecences();
        if(_content.accept(visitor)){
            _content.stopObserving();
        }
        _content = content;
        recalcula();
    }

    public void addObserver(Observer o){
        _observers.add(o);
    }

    public void recalcula(){
        for(Observer o: _observers){
            o.update();
        }
    }
}
