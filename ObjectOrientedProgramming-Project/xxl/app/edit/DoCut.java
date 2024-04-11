package xxl.app.edit;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Cell;
import xxl.core.Spreadsheet;
import java.util.List;

/**
 * Cut command.
 */
class DoCut extends Command<Spreadsheet> {
  DoCut(Spreadsheet receiver) {
    super(Label.CUT, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
    String rangeDescription = Form.requestString(Message.address());
    try{
      List<Cell> copiedCells = _receiver.buildRange(rangeDescription).getListCells();
      _receiver.copy(copiedCells);
      _receiver.delete(copiedCells);
      _receiver.setSaved(false);
    }catch(CommandException e){
      _display.addLine(e.getMessage());
      throw new InvalidCellRangeException(rangeDescription);
    }
    _display.display();
  }
}
