package spreadsheet;

/**
 * This macro assigns a specific value to a range of cells.
 */
public class BulkAssignMacro implements SpreadSheetMacro {
  private final int fromRow;
  private final int fromCol;
  private final int toRow;
  private final int toCol;
  private final double value;

  /**
   * Created a bulk assign for macro.
   *
   * @param fromRow the row
   * @param fromCol the column
   * @param toRow the row
   * @param toCol the end column
   * @param value value to assign to all cells in range.
   * @throws IllegalArgumentException if row or column is negative,
   */
  public BulkAssignMacro(int fromRow, int fromCol, int toRow, int toCol, double value)
      throws IllegalArgumentException {
    if (fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0) {
      throw new IllegalArgumentException("Row & column can not have a negative value");
    }
    if (fromRow > toRow || fromCol > toCol) {
      throw new IllegalArgumentException("indices must be <= to indices");
    }
    this.fromRow = fromRow;
    this.fromCol = fromCol;
    this.toRow = toRow;
    this.toCol = toCol;
    this.value = value;
  }


  @Override
  public void execute(SpreadSheet sheet) throws IllegalArgumentException {
    if (sheet == null) {
      throw new IllegalArgumentException("Spreadsheet cannot have a null");
    }
    for (int row = fromRow; row <= toRow; row++) {
      for (int col = fromCol; col <= toCol; col++) {
        sheet.set(row, col, value);
      }
    }
  }

}
