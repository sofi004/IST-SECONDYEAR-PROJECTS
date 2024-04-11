package xxl.app.edit;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Cell;
import xxl.core.Spreadsheet;
import java.util.List;

/**
 * Copy command.
 */
class DoCopy extends Command<Spreadsheet> {
  DoCopy(Spreadsheet receiver) {
    super(Label.COPY, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
    String rangeDescription = Form.requestString(Message.address());
    try{
      List<Cell> copiedCells = _receiver.buildRange(rangeDescription).getListCells();
      _receiver.copy(copiedCells);
    }catch(InvalidCellRangeException e){
      _display.addLine(e.getMessage());
      throw new InvalidCellRangeException(rangeDescription);
    }
    _display.display();
  }
}
