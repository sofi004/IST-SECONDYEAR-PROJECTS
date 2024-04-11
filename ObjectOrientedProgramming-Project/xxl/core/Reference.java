package xxl.core;

import java.io.Serializable;

public class Reference extends Content implements Serializable{
    private int _row;
    private int _column;
    private Spreadsheet _spreadsheet;
    private Cell _cell;
    private Literal _value;

    public Reference(int row, int column, Spreadsheet spreadsheet){
        _row = row;
        _column = column;
        _spreadsheet = spreadsheet;
        _cell = _spreadsheet.getCells().searchCell(row, column);
        _cell.addObserver(this);
        update();
    }

    public Cell getCell(){
        return _cell;
    }

    @Override
    public String toString(){
           return  _cell.value() + "=" + String.valueOf(_row)+";"+String.valueOf(_column);
    }

    @Override
    Literal value(){
        return _value;   
    }

    @Override
    public void update(){
        _value = _cell.value();
    }

    @Override
    public void stopObserving(){
        _cell.getObservers().remove(this);
    }

    @Override
    public boolean accept(Visitor visitor){
        return visitor.visit(this);
    }

}
