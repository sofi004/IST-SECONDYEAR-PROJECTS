package xxl.app.main;

import xxl.core.Calculator;

/**
 * Menu builder for managers.
 */
public class Menu extends pt.tecnico.uilib.menus.Menu {

  public Menu(Calculator receiver) {
    super(Label.TITLE, //
          new DoNew(receiver), //
          new DoOpen(receiver), // loads the state of the app saves in a file
          new DoSave(receiver), // saves the current state of the app in a file
          new DoOpenEditMenu(receiver), // opens the edit menu of a spreadsheet
          new DoOpenSearchMenu(receiver) // opens the search menu on a spreadsheet
          );
  }
}
