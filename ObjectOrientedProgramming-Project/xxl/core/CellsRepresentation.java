package xxl.core;

public interface CellsRepresentation{
    public Cell searchCell(int row, int column);
    public void insertContent(int row, int column, Content content);
    public int getRowsnum();
    public int getColumnsnum();
}
