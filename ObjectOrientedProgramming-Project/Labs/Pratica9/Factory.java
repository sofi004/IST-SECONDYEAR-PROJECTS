package Pratica9;

import java.util.*;

public class Factory {
    private List<Product> _prod;
    public Factory(){
        _prod = new ArrayList<Product>();
    }
    public boolean addProduct(Product p){
        return _prod.add(p);
    }
    public List<Product> produzir(){
        List<Product> listCopy = new ArrayList<Product>();
        for(Product p: _prod){
            listCopy.add(p.copy());
        }
        return listCopy;
    }
}
