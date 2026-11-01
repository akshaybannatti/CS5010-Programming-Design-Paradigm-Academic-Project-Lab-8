package spreadsheet;

/**
 * Macro for the values to a row or column of cells.
 */
public class RangeAssignMacro implements SpreadSheetMacro {
  private final int fromRow;
  private final int fromCol;
  private final int toRow;
  private final int toCol;
  private final double startValue;
  private final double increment;

  /**
   * Made a range assign macro.
   *
   * @param fromRow start row
   * @param fromCol start column (0-indexed)
   * @param toRow end row
   * @param toCol end col.
   * @param startValue starting sequence
   * @param increment increment value
   * @throws IllegalArgumentException if any row or column is negative.
   */
  public RangeAssignMacro(int fromRow, int fromCol, int toRow, int toCol,
                          double startValue, double increment)
      throws IllegalArgumentException {
    if (fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0) {
      throw new IllegalArgumentException("Row and column cannot be negative");
    }
    if (fromRow > toRow || fromCol > toCol) {
      throw new IllegalArgumentException("indices must be <= to indices");
    }
    if (fromRow != toRow && fromCol != toCol) {
      throw new IllegalArgumentException(
          "Range must be a single(not a rectangle)");
    }
    this.fromRow = fromRow;
    this.fromCol = fromCol;
    this.toRow = toRow;
    this.toCol = toCol;
    this.startValue = startValue;
    this.increment = increment;
  }

  @Override
  public void execute(SpreadSheet sheet) throws IllegalArgumentException {
    if (sheet == null) {
      throw new IllegalArgumentException("cannot be null");
    }

    double currentValue = startValue;

    if (fromRow == toRow) {
      for (int col = fromCol; col <= toCol; col++) {
        sheet.set(fromRow, col, currentValue);
        currentValue += increment;
      }
    } else {
      for (int row = fromRow; row <= toRow; row++) {
        sheet.set(row, fromCol, currentValue);
        currentValue += increment;
      }
    }
  }

}