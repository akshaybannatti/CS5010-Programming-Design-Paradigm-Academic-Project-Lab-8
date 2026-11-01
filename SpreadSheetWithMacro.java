package spreadsheet;

/**
 * This interface extends the SpreadSheet interface.
 */
public interface SpreadSheetWithMacro extends SpreadSheet {
  /**
   * Executed the macro interface here.
   *
   * @param macro the macro.
   * @throws IllegalArgumentException It will not run if the macro does not work.
   */
  void executeMacro(SpreadSheetMacro macro) throws IllegalArgumentException;
}