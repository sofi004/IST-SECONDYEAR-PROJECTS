package Pratica3.Tetes.Teste2;

/*
 ------------------------------------------                 ------------
 Grade                                    |                 |Garrafa   |
 ------------------------------------------ --------------> ------------
 - _numGarrafas :int                      |               * |          |
 - _capacidade  :int                      |                 |          |
 ------------------------------------------                 ------------
+ Grade(capacidade :int)                  |                 |          |
+ adicionar(garrafaAdd :Garrafa) :boolean |                 |          |
+ compara(gradeComp :Grade) :Grade        |                 |          |
-------------------------------------------                 ------------
 */

public class Grade{
    private int _capacidade;
    private int _numGarrafas;
    private Garrafa[] _garrafas;

    public Grade(int capacidade){
        _capacidade = capacidade;
        _numGarrafas = 0;
        _garrafas = new Garrafa[capacidade];
    }

    public boolean adicionar(Garrafa garrafaAdd){
        if(_capacidade > _numGarrafas){
            _garrafas[_numGarrafas] = garrafaAdd;
            _numGarrafas ++;
            return true;
        }
        return false;
    }

    public Grade compara(Grade gradeComp){
        if(_numGarrafas > gradeComp._numGarrafas)return this;
        return gradeComp;
    }
}
