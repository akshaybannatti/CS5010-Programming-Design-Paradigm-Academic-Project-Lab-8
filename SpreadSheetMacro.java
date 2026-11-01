package spreadsheet;

/**
 * Interface for SpreadSheetMacro.
 */
public interface SpreadSheetMacro {
  /**
   * Execute the macro.
   *
   * @param sheet here spreadsheet will run the macro.
   * @throws IllegalArgumentException if macro cannot work due to wrong parameter.
   */
  void execute(SpreadSheet sheet) throws IllegalArgumentException;

}
