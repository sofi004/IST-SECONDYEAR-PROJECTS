package Pratica2.EX2;
import Pratica2.EX1.Pneu;

/*
+------------------------------------------------------------+            +-------+   
|Carro                                                       |            |Pneu   |
+------------------------------------------------------------+            +-------+
| - _marca : string                                          |            |       |
| - _quilometragem : int                                     |            |       |
| - _velocidadeMaxima : int                                  |            |       |
| - _velocidade : int                                        |            |       |
| - _travado : boolean                                       |            |       |
+------------------------------------------------------------+ ---------> +-------+
| + Carro(marca:String, velocidadeMaxima:int, pneus:pneu[4]) |          4 |       |
| + getMarca():String                                        |            |       |
| + getQuilometragem():int                                   |            |       |
| + getPneusVazios():boolean                                 |            |       |
| + setQuilometragem(novaKm:int)                             |            |       |
| + travar()                                                 |            |       |
| + destravar()                                              |            |       |
| + acelerar(velocidade:int)                                 |            |       |    
+------------------------------------------------------------+            +-------+
*/
public class Carro {
    private String _marca;
    private int _quilometragem;
    private int _velocidadeMaxima;
    private int _velocidade;
    private Pneu[] _pneus;
    private boolean _travado;

    public Carro(String marca, int velocidadeMaxima, Pneu[] pneus){
        _marca = marca;
        _velocidadeMaxima = velocidadeMaxima;
        _quilometragem = 0;
        _pneus = pneus;
        _velocidade = 0;
        _travado = true;
    }

    public String getMarca(){
        return _marca;
    }

    public int getQuilometragem(){
        return _quilometragem;
    }

    public boolean getPneusVazios(){
        for(Pneu p: _pneus){
            if(p.pneuVazio()) return true;
        }
        return false;
    }

    public void setQuilometragem(int novaQuilometragem){
        if(novaQuilometragem < _quilometragem) return;
        _quilometragem = novaQuilometragem;
    }

    public void trava(){
        _travado = true;
        _velocidade = 0;
    }

    public void destrava(){
        _travado = false;
    }

    public void acelera(int velocidade){
        if(_travado)return;
        if(velocidade > _velocidadeMaxima)return;
        _velocidade = velocidade;
    }

}
