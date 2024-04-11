package Pratica3.EX2;

/*
-----------------------------------------------
Caneta                                        |
-----------------------------------------------
- _capacidade_tinta: int                      |
- _cor: String                                |
- _quantidade_tinta: int                      |
-----------------------------------------------
+ Caneta(capacidade_tinta:int, cor:String)    |
+ escrever(texto:String):void                 |
+ recarrega(recarga:int):int                  |
+ obtem_cor():String                          |
+ obtem_quantidade_tinta():int                |
-----------------------------------------------
 */

public class Caneta{

    private int _capacidade_tinta;
    private String _cor;
    private int _quantidade_tinta;

    public Caneta( int capacidade_tinta, String cor){
        _capacidade_tinta = capacidade_tinta;
        _cor = cor;
        _quantidade_tinta = capacidade_tinta;
    }

    public void escrever(String texto){
        if (texto.length() <= _quantidade_tinta){
            System.out.println(texto);
            _quantidade_tinta -= texto.length();
        }
    }

    public int recarrega(int recarga){
        int nova_tinta = _quantidade_tinta + recarga;

        if (nova_tinta>_capacidade_tinta){
            _quantidade_tinta = _capacidade_tinta;
            return nova_tinta - _capacidade_tinta;
        }
        _quantidade_tinta += recarga;
        return 0;
    }

    public String obtem_cor(){
        return _cor;
    }

    public int obtem_quantidade(){
        return _quantidade_tinta;
    }
}
