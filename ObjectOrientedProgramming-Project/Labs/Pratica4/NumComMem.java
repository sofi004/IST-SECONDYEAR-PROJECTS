package Pratica4;

/*
-----------------------------------                 ----------------------------
Numero                            |                 | NumComMem                |
-----------------------------------                 ----------------------------
- _valor:int                      |                 | - _memoria:int           |
- _nome:String                    |                 ----------------------------
--------------------------------------------------> | + NumComMem()            |
+ Numero(valor:int, nome:String)  |                 | + NumComMem(int)         |
+ Numero()                        |                 | + set(int)               |
+ obterValor():int                |                 | + undo(): void           |
+ obterNumero():String            |                 | + anterior():void        |
+ equals(x:Numero):boolean        |                 ----------------------------
+ maior(x:Numero):Numero          |
-----------------------------------
 */

public class NumComMem extends Numero{
    private int _memoria;

    public NumComMem(){
        super ();
        _memoria = 0;
    }

    public NumComMem(int val){
        _memoria = obterValor();
        super.set(val);
    }

    public void undo(){
        set(_memoria);
    }
}
