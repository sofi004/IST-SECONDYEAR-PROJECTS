package xxl.core;

import java.io.Serializable;

public class MatrixCellRepresentation implements CellsRepresentation, Serializable{
    private Cell[][] _listCells;
    private int _rowsnum;
    private int _columnsnum;

    public MatrixCellRepresentation(int rows, int columns){
        _rowsnum = rows;
        _columnsnum = columns;
        _listCells = new Cell[rows][columns];
        createListCell();
    }

    public void createListCell(){
        for(int i = 0; i < _rowsnum; i++){
          for(int j = 0; j < _columnsnum; j++){
            _listCells[i][j] = new Cell(i + 1, j + 1);
          }
        }
    }

    public Cell searchCell(int row, int column){
        return _listCells[row-1][column-1];
    }

    public void insertContent(int row, int column, Content content){
        searchCell(row, column).insertContent(content);
    }

    public int getRowsnum(){
        return _rowsnum;
    }
    
    public int getColumnsnum(){
        return _columnsnum;
    }
}
