package spreadsheet;

/**
 * Macro computes the average of a range of cells it goes too.
 */
public class AverageMacro implements SpreadSheetMacro {
  private final int fromRow;
  private final int fromCol;
  private final int toRow;
  private final int toCol;
  private final int destRow;
  private final int destCol;

  /**
   * Create an average macro.
   *
   * @param fromRow start row.
   * @param fromCol starting col.
   * @param toRow end row.
   * @param toCol end column range
   * @param destRow row for the result.
   * @param destCol the destination column.
   * @throws IllegalArgumentException if row and col negative.
   */
  public AverageMacro(int fromRow, int fromCol, int toRow, int toCol, int destRow, int destCol)
      throws IllegalArgumentException {
    if (fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0 || destRow < 0 || destCol < 0) {
      throw new IllegalArgumentException("Row column indices cannot be in negative");
    }
    if (fromRow > toRow || fromCol > toCol) {
      throw new IllegalArgumentException("indices must be <= to indices");
    }
    this.fromRow = fromRow;
    this.fromCol = fromCol;
    this.toRow = toRow;
    this.toCol = toCol;
    this.destRow = destRow;
    this.destCol = destCol;
  }

  @Override
  public void execute(SpreadSheet sheet) throws IllegalArgumentException {
    if (sheet == null) {
      throw new IllegalArgumentException("Spreadsheet does not have a null");
    }

    double sum = 0.0;
    int count = 0;

    for (int row = fromRow; row <= toRow; row++) {
      for (int col = fromCol; col <= toCol; col++) {
        sum += sheet.get(row, col);
        count++;
      }
    }

    double average = (count > 0) ? (sum / count) : 0.0;
    sheet.set(destRow, destCol, average);
  }
}
