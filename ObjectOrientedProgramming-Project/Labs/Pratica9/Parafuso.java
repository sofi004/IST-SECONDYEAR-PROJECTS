package Pratica9;

public class Parafuso extends Product{
    private double _diametro;
    public Parafuso(int custo, String descricao, double diametro){
        super(custo, descricao);
        _diametro = diametro;
    }
    public Product copy(){
        return new Parafuso(getCusto(), getDescricao(), _diametro);
    }
}
