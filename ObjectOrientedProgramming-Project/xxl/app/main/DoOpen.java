package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.FileOpenFailedException;
import xxl.core.Calculator;
import java.io.IOException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.UnavailableFileException;

/**
 * Open existing file.
 */
class DoOpen extends Command<Calculator> {
  DoOpen(Calculator receiver) {
    super(Label.OPEN, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException, FileOpenFailedException {
    if((_receiver.getSpreadsheet() != null) &&  (!_receiver.getSpreadsheet().isSaved())){
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
    try{
      String nameSaveAs = Form.requestString(Message.openFile());
      _receiver.load(nameSaveAs);
    }
    catch(IOException | MissingFileAssociationException | UnavailableFileException e){      
      throw new FileOpenFailedException(e);
    } 
  }
}
