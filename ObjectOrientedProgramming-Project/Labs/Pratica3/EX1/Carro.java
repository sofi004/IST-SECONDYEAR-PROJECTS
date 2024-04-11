package Pratica3.EX1;

/*
-----------------------------------------------       +-----------+
Carro                                         |       | Pneu      |
----------------------------------------------------> +-----------+
- _marca:String                               |       |           |
- _quilo:int                                  |       |           |
- _vMax:int                                   |       |           |
-----------------------------------------------       +-----------+
+ Carro(marca:String, vMax:int, pneus:Pneu[]) |       |           |
+ obtemMarca():String                         |       |           |
+ obtemQuilo():int                            |       |           |
+ pneuVazio():boolean                         |       |           |
+ alterQuilo(nova_quilo:int):void             |       |           |
+ montaPneus(novos:Pneu[]):boolean            |       |           |
-----------------------------------------------       +-----------+
 */

public class Carro{
    private String _marca;
    private int _quilo;
    private int _vMax;
    private Pneu[] _pneus;

    public Carro(String marca, int vMax, Pneu[] pneus){
        _marca = marca;
        _vMax = vMax;
        _pneus = pneus;
        _quilo = 0;
    }

    public String obtemMarca(){
        return _marca;
    }

    public int obtemQuilo(){
        return _quilo;
    }

    public boolean pneuVazio(){
        int i;

        for(i=0; i<_pneus.length; i++){
            if(_pneus[i].pneuVazio()){
                return true;
            }
        }
        return false;
    }

    public void alteraQuilo(int novaQuilo){
        _quilo = novaQuilo;
    }

    public boolean montaPneus(Pneu[] novosPneus){
        int i;

        for(i=0;i < novosPneus.length - 1;i++){
            if(novosPneus[i].obtemPressaoArRecomendada() != novosPneus[i+1].obtemPressaoArRecomendada()){
                return false;
            }
        }

        _pneus = novosPneus;
        return true;
    }
}
