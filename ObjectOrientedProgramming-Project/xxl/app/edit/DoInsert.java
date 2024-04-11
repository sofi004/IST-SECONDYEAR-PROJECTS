package xxl.app.edit;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.app.exception.UnknownFunctionException;
import xxl.core.Cell;
import xxl.core.Range;
import xxl.core.Spreadsheet;
import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.Parser;

import java.io.IOException;
import java.util.List;

/**
 * Class for inserting data.
 */
class DoInsert extends Command<Spreadsheet> {
  DoInsert(Spreadsheet receiver) {
    super(Label.INSERT, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
    Parser parse = new Parser(_receiver);
    String rangeDescription = Form.requestString(Message.address());
    String contentDescription = Form.requestString(Message.contents()); 
    try{
      Range range = _receiver.buildRange(rangeDescription);
      List<Cell> listCells = range.getListCells();
      _receiver.setSaved(false);
      for(Cell c: listCells){
          c.insertContent(parse.parseContent(contentDescription));
      }
    } catch (UnrecognizedEntryException e){
      _display.addLine(e.getMessage());
      _display.display();
    }
  }
}
