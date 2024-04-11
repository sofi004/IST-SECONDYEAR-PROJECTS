package xxl.core;

import java.util.List;

public class Concat extends IntervalFunction {

    public Concat(Range range, String name){
        super(range, name);
        update();
    }

    @Override
    protected Literal compute(){
        List<Cell> listCells = _range.getListCells();
        String n = "'";
        for(Cell c: listCells){
            try{
            String[] content = c.getContent().asString().split("'", 2);
            n += content[content.length - 1];
            }catch(ArithmeticException e){
                continue;
            }
        }
        return new Character(n);
    }

    @Override
    public boolean accept(Visitor visitor){
        return visitor.visit(this);
    }
}
