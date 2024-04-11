package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import xxl.app.exception.InvalidCellRangeException;

/*
* Class representing a spreadsheet.
*/
public class Spreadsheet implements Serializable {
  @Serial
  private static final long serialVersionUID = 202308312359L;

  private boolean _saved;
  private CutBuffer _cutBuffer;
  private CellsRepresentation _CellsList;
  private String _name;
  private boolean _named;
  private List<User> _users = new ArrayList<User>();;

  public Spreadsheet(int row, int column){
    _saved = false;
    _named = false;
    _CellsList = new MatrixCellRepresentation(row, column);
    List<Cell> bufferList = new ArrayList<Cell>();
    CutBuffer cutBuffer = new CutBuffer(bufferList);
    _cutBuffer = cutBuffer;
    
  }

  public String getFileName(){
    return _name;
  }

  public boolean isNamed(){
    return _named;
  }

  public void setName(String name){
    _name = name;
    _named = true;
  }

  public boolean isSaved(){
    return _saved;
  }

  public void setSaved(boolean val){
    _saved = val;
  }

  public void setCutBuffer(CutBuffer cutBuffer){
    _cutBuffer = cutBuffer;
  }

  public CutBuffer getCutBuffer(){
    return _cutBuffer;
  }

  public CellsRepresentation getCells(){
    return _CellsList;
  }

  public Range buildRange(String rangeDescription) throws InvalidCellRangeException{
    String[] rangeCoordinates;
    int firstRow, firstColumn, lastRow, lastColumn;
    if (rangeDescription.indexOf(':') != -1) {
      rangeCoordinates = rangeDescription.split("[:;]");
      firstRow = Integer.parseInt(rangeCoordinates[0]);
      firstColumn = Integer.parseInt(rangeCoordinates[1]);
      lastRow = Integer.parseInt(rangeCoordinates[2]);
      lastColumn = Integer.parseInt(rangeCoordinates[3]);
    } else {
      rangeCoordinates = rangeDescription.split(";");
      firstRow = lastRow = Integer.parseInt(rangeCoordinates[0]);
      firstColumn = lastColumn = Integer.parseInt(rangeCoordinates[1]);
    }
    if(firstRow > lastRow || firstColumn > lastColumn ||
    lastRow > _CellsList.getRowsnum() || lastColumn > _CellsList.getColumnsnum()
    || ((lastRow != firstRow) && (lastColumn != firstColumn))){

      throw new InvalidCellRangeException(rangeDescription);
    }
    return new Range(firstRow, firstColumn, lastRow, lastColumn, this);
  }

  public void paste(Range selectedCells){
    List<Cell> targetCells = selectedCells.getListCells();
    List<Cell> cutBufferCells = _cutBuffer.getListCells();
    int r = targetCells.get(0).getRow();
    int c = targetCells.get(0).getCollumn();
    int r_iterator = 0;
    int c_iterator = 0;
    if(cutBufferCells.size() > 1){
      r_iterator = cutBufferCells.get(1).getRow() - cutBufferCells.get(0).getRow();
      c_iterator = cutBufferCells.get(1).getCollumn() - cutBufferCells.get(0).getCollumn();
    }
    if (targetCells.size() == 1){
      for(Cell e: cutBufferCells){
        if(r <= _CellsList.getRowsnum() && c <= _CellsList.getColumnsnum()){
          _CellsList.searchCell(r, c).insertContent(e.getContent());
          r += r_iterator;
          c += c_iterator;
        }
      }  
    }
    else if(targetCells.size() > 1){
      for(int i = 0; i < targetCells.size(); i++){
        if(r <= _CellsList.getRowsnum() && c <= _CellsList.getColumnsnum()){
          targetCells.get(i).insertContent(cutBufferCells.get(i).getContent());
          r += r_iterator;
          c += c_iterator;
        }
      }
    }
  }

  public void copy(List<Cell> copiedCells) throws InvalidCellRangeException{
    List<Cell> listCells = new ArrayList<Cell>();
    int r = 1;
    int c = 1;
    int r_iterator = 0;
    int c_iterator = 0;
    if(copiedCells.size() > 1){
      r_iterator = copiedCells.get(1).getRow() - copiedCells.get(0).getRow();
      c_iterator = copiedCells.get(1).getCollumn() - copiedCells.get(0).getCollumn();
    }
    for(int k = 0; k < copiedCells.size(); k++){
      Cell cell = new Cell(r,c);
      cell.insertContent(copiedCells.get(k).getContent());
      listCells.add(cell);
      r += r_iterator;
      c += c_iterator;
    }
    CutBuffer cutBuffer = new CutBuffer(listCells);
    _cutBuffer = cutBuffer;
  }

  public void delete(List<Cell> listCells){
    for(Cell c: listCells){
        Null n = new Null();
        c.insertContent(n);
    }
  }
}
