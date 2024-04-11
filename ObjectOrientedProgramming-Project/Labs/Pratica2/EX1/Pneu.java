package Pratica2.EX1;
/*
+-------------------------------------------+
|Pneu                                       |
+-------------------------------------------+
| - _pressaoAr:int                          |
| - _pressaoArRecomendada:int               |
| - _furado:boolean                         |
+-------------------------------------------+
| + Pneu(pressao:int, recomendada:int)      |
| + alterarPressao(novaPressao:int):void    |
| + obtemPressaoAr(): int                   |
| + obtemPressaoArRecomendada(): int        |
| + pneuVazio(): boolean                    |
| + estaFurado(): boolean                   |
+-------------------------------------------+
*/

public class Pneu {
  private int _pressaoAr;
  private int _pressaoArRecomendada;
  private boolean _furado;

  public Pneu(int pressaoAr, int recomendada) {
    _pressaoAr = pressaoAr;
    _pressaoArRecomendada = recomendada;
    //_furado = false;
  }

  public int obtemPressaoAr() {
    return _pressaoAr;
  }

  public int obtemPressaoArRecomendada() {
    return _pressaoArRecomendada;
  }

  public boolean estaFurado() {
    return _furado;
  }

  public boolean pneuVazio() {
    return _pressaoAr < 0.8 * _pressaoArRecomendada;
  }

  public void alterarPressao(int novaPressao) {
    if (!_furado) {
      if (novaPressao > 1.5 * _pressaoArRecomendada) {
        _furado = true;
        _pressaoAr = 0;
      } else
        _pressaoAr = novaPressao;
    }
  }
}
