package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.FileOpenFailedException;
import xxl.core.Calculator;
import xxl.core.exception.UnrecognizedEntryException;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {
  DoNew(Calculator receiver) {
    super(Label.NEW, receiver); 
  }
  
  @Override
  protected final void execute() throws FileOpenFailedException, CommandException{
    if(_receiver.getSpreadsheet() != null &&  !_receiver.getSpreadsheet().isSaved()){
      boolean booleanAnswer = Form.confirm(Message.saveBeforeExit());
      if(booleanAnswer){
        DoSave cmd = new DoSave(_receiver);
        try{
          cmd.performCommand();
        }catch(CommandException e){
          throw new FileOpenFailedException(e);
        }
      }
    }
    Integer numberl = Form.requestInteger(Message.lines());
    Integer numberc = Form.requestInteger(Message.columns());
    try{
      _receiver.addSpreadsheet(numberl, numberc); 
    } catch(UnrecognizedEntryException e){
      e.printStackTrace();
    }
  }
}
