package Pratica2.Teste;

/*
------------------------------------
 Caderno                           |
 -----------------------------------
 - _nLinhas :int                   |
 - _nLinhasEscritas :int           |
 -----------------------------------
 + Caderno(nLinhas :int)           |
 + estaCheio() :boolean            |
 + getNLinhasEscritas() :int       |
 + escrever(texto :String):boolean |
 -----------------------------------
 */
 public class Caderno{
    private int _nLinhas;
    private int _nLinhasEscritas;

    public Caderno(int nLinhas){
        _nLinhas = nLinhas;
        _nLinhasEscritas = 0;
    }

    public boolean estaCheio(){
        return _nLinhas == _nLinhasEscritas;
    }

    public int getNLinhasEscritas(){
        return _nLinhasEscritas;
    }

    public boolean escrever(String texto){
        if(!estaCheio()){
            if(texto.length() > 0){
                _nLinhasEscritas++;
                return true;
            }
        }
        return false;
    }
 }