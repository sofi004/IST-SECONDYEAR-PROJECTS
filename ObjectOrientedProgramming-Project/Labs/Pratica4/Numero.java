package Pratica4;

/*
-----------------------------------
Numero                            |
-----------------------------------
- _valor:int                      |
- _nome:String                    |
-----------------------------------
+ Numero(valor:int, nome:String)  |
+ Numero()                        |
+ obterValor():int                |
+ set(valor:int)                  |
+ obterNumero():String            |
+ equals(x:Numero):boolean        |
+ maior(x:Numero):Numero          |
-----------------------------------
 */

public class Numero{
    private int _valor;
    private String _nome;

    public Numero(int valor, String nome){
        _valor = valor;
        _nome = nome;
    }

    public Numero(){
        _valor = 0;
        _nome = "zero";
    }

    public int obterValor(){
        return _valor;
    }

    public void set(int valor){
        _valor = valor;
    }

    public String obterNumero(){
        return _nome;
    }

    public boolean equals(Numero x){
        return _valor == x._valor;
    }

    public Numero maior(Numero x){
        if (_valor > x._valor){
            return this;
        } else{
            return x;
        }
    }

}
