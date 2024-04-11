package Pratica2.EX3;
/*
+---------------------------------------------------+
|Caneta                                             |
+---------------------------------------------------+
| - _capacidade: int                                |
| - _cor: string                                    |
| - _marca: string                                  |
| - _quantidade: int                                |
+---------------------------------------------------+
| + Caneta(capacidade:int, cor:string, marca:string)|
| + escrever(texto:string)                          |
| + recarregar(quantidade:int):int                  |
| + getQuantidade():int                             |
| + getCor():string                                 |
+---------------------------------------------------+
*/

public class Caneta {
    private int _capacidade;
    private String _cor;
    private String _marca;
    private int _quantidade;

    public Caneta(int capacidade, String cor, String marca){
        _capacidade = capacidade;
        _cor = cor;
        _marca = marca;
        _quantidade = capacidade;
    }

    public String getCor(){
        return _cor;
    }

    public int getQuantidade(){
        return _quantidade;
    }

    public void escrever(String texto){
        if(_quantidade == 0)return;
        _quantidade -= texto.length();
        System.out.println(texto);
        if(_quantidade < 0) _quantidade = 0;
    }

    public int recarregar(int quantidade){
        _quantidade += quantidade;
        int diferenca = _quantidade - _capacidade;
        if(diferenca > 0){
            _quantidade = _capacidade;
            return diferenca;
        }
        return 0;
    }

    public int recarega(int quantidadeTinta, String cor){
        if(cor == _cor)return recarregar(quantidadeTinta);
        return -1;
    }
}
