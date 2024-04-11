package xxl.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User implements Serializable{
    private final String _name;
    private List<Spreadsheet> _spreadsheets = new ArrayList<>();
    
    public User(String name){
        _name = name;
    }

    void add(Spreadsheet s) {
        _spreadsheets.add(s);
    }

    public List<Spreadsheet> getSpreadSheets() {
        return Collections.unmodifiableList(_spreadsheets);
    }

    public final String getName() {
        return _name;
    }
}
