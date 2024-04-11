package xxl.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Range implements Serializable{

    private int _beginRow;
    private int _beginColumn;
    private int _endRow;
    private int _endColumn;
    private Spreadsheet _spreadsheet;
    private List<Cell> _listCells;

    public Range(int beginRow, int beginColumn, int endRow, int endColumn, Spreadsheet spreadsheet){
        _beginRow = beginRow;
        _beginColumn = beginColumn;
        _endRow = endRow;
        _endColumn = endColumn;
        _spreadsheet = spreadsheet;
        _listCells = new ArrayList<Cell>();
        getCells();
    }

    List<Cell> getCells(){
        for(int i = _beginRow; i <= _endRow; i++){
            for(int k = _beginColumn; k <= _endColumn; k++){
                _listCells.add(_spreadsheet.getCells().searchCell(i, k));
                }
            }
        return _listCells;
    }

    public List<Cell> getListCells(){
        return _listCells;
    }

    public int getBeginRow(){
        return _beginRow;
    }

    public int getBeginColumn(){
        return _beginColumn;
    }

    public int getEndRow(){
        return _endRow;
    }
    
    public int getEndColumn(){
        return _endColumn;
    }
}


    
