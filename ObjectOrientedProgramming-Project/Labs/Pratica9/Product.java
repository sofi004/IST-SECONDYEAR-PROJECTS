package Pratica9;

public abstract class Product {
    private int _custo;
    private String _descricao;

    public Product(int custo, String descricao){
        _custo = custo;
        _descricao = descricao;
    }

    public abstract Product copy();

    public int getCusto(){
        return _custo;
    }
    
    public String getDescricao(){
        return _descricao;
    }
}
