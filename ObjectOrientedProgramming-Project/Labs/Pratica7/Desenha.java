package Pratica7;
import java.util.*;
import pt.tecnico.uilib.menus.Command;

public class Desenha extends Command<Editor>{
    public Desenha(Editor ent){
        super("Titulo", ent);
    }

    protected void execute(){
        List<Form> list = new ArrayList<Form>(_reciever.getForms());
        Collections.sort(list, new AreaComparator());
        for(Form f : list){
            if(f.getArea() > (f.getOrigin.getX * f.getOrigin.getY)){
                _display.addLine(f.toString())
            }
        }
        _display.display();
    }
}

class AreaComparator implements Comparator<Form>{
    public int compare(Form a1, Form a2){
        return (a1.getArea() - a2.getArea()); //Se positivo troca a posição das formas
    }
}
