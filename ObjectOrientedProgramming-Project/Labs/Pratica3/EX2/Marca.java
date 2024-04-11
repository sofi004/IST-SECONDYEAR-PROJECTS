package Pratica3.EX2;
/*
----------------------------------------------             ----------------
Marca                                        |             |    Caneta    |
---------------------------------------------------------> ----------------
- _nome:String                               |           * |              |
- _ano:int                                   |             |              |
- _nCanetas:int                              |             |              |
----------------------------------------------             ----------------
+ Marca(ano:int, nome:String)                |             |              |
+ criaCaneta(capacidade:int, cor:String):void|             |              |
----------------------------------------------             ----------------
 */

public class Marca{
    private String _nome;
    private int _ano;
    private Caneta[] _canetas;
    private int _nCanetas;

    public Marca(int ano, String nome){
        _ano = ano;
        _nome = nome;
        _canetas = new Caneta[1000];
        _nCanetas = 0;
    }

    public void criaCaneta(int capacidade, String cor){
        Caneta novaCaneta = new Caneta(capacidade, cor);
        _canetas[_nCanetas] = novaCaneta;
        _nCanetas += 1;
    }
}
