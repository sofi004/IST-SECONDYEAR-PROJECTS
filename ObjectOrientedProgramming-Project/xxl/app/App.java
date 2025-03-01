/*
 * Group 73 PO2L010
 * João Tomás de Almeida Santos Antunes Gomes Ist1106204
 * Sofia Dinis Pinto Piteira Ist1106194
 */

package xxl.app;

import pt.tecnico.uilib.Dialog;
import xxl.app.exception.InvalidCellRangeException;
import xxl.app.exception.UnknownFunctionException;
import xxl.core.exception.ImportFileException;

/**
 * Class that represents the spreadsheet's textual interface.
 */
public class App {
  public static void main(String[] args) throws UnknownFunctionException, InvalidCellRangeException{
    try (var ui = Dialog.UI) {
      var receiver = new xxl.core.Calculator();
      String datafile = System.getProperty("import");
      if (datafile != null) {
        try {
          receiver.importFile(datafile);
        } catch (ImportFileException e) {
          // no behavior described: just present the problem
          e.printStackTrace();
        }
      }
      (new xxl.app.main.Menu(receiver)).open();
    }
  }
}
