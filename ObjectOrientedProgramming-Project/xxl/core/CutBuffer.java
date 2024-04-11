package xxl.core;

import java.io.Serializable;
import java.util.List;

public class CutBuffer implements Serializable {
    private List<Cell> _listCells;

    public CutBuffer(List<Cell> listcell){
        _listCells = listcell;
    }

    public List<Cell> getListCells(){
        return _listCells;
    }
}
