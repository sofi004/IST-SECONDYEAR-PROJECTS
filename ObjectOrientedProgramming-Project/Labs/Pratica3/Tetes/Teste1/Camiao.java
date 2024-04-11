package Pratica3.Tetes.Teste1;
/*
+-------------------------------------+                +----------------+
Camiao                                |                | Pacote         |
+-------------------------------------+--------------> +----------------+
- _capacidade :int                    |              * |                |
- _numPacotes :int                    |                |                |
+-------------------------------------+                +----------------+
+ Camiao(capacidade :int)             |                |                |
+ carregar(pack :Pacote) :boolean     |                |                |
+ descarregar() :Pacote               |                |                |
+ compara(camiaoComp :Camiao) :Camiao |                |                |
+-------------------------------------+                +----------------+
 */

public class Camiao {
    private int _capacidade;
    private int _numPacotes;
    private Pacote[] _pacotes;

    public Camiao(int capacidade){
        _capacidade = capacidade;
        _numPacotes = 0;
        _pacotes = new Pacote[capacidade];
    }

    public boolean carregar(Pacote pack){
        if(_capacidade > _numPacotes){
            _pacotes[_numPacotes] = pack;
            _numPacotes ++;
            return true;
        }
        return false;
    }

    public Pacote descarrega(){
        Pacote pacoteDev;
        if(_numPacotes != 0){
            _numPacotes --;
            pacoteDev = _pacotes[_numPacotes];
            _pacotes[_numPacotes] = null;
            return pacoteDev;
        }
        return null;
    }

    public Camiao compara(Camiao camiaoComp){
        if(_capacidade > camiaoComp._capacidade){
            return this;
        }
        return camiaoComp;
    }
}
