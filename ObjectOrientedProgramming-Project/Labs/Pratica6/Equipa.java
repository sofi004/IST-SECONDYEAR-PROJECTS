package Pratica6;
import java.util.*;

public class Equipa {
    private ArrayList<Jogador> _jogs;
    public Equipa(){
        _jogs = new ArrayList<Jogador>();
    }

    public boolean adicionaJogador (Jogador j){
        if(j.getAno() > 2004) return false;
        _jogs.add(j);
        return true;
    }

    public ArrayList<Jogador> equipaSub23(){
        ArrayList<Jogador> sub = new ArrayList<Jogador>();
        for(Jogador j : sub){
            if(j.getAno() >= 2001)
            sub.add(j);
        } 
        return sub;
    }

    public removeJogadores(String p){
        Iterator<Jogador> itr = _jogs iterator();
        while(itr.hasNext()){
            Jogador j = itr.next();
            if(j.getFoot().equals(p)) itr.remove();
        }
    }
}
