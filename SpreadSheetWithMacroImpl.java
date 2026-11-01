package spreadsheet;

/**
 * Implemented a spreadsheet here.
 */
public class SpreadSheetWithMacroImpl implements SpreadSheetWithMacro {
  private final SpreadSheet delegate;

  /**
   * Create a new spreadsheet with macro support using a default SparseSpreadSheet.
   */
  public SpreadSheetWithMacroImpl() {
    this.delegate = new SparseSpreadSheet();
  }

  /**
   * Created a new spreadsheet with macro.
   *
   * @param delegate spreadsheet implement.
   * @throws IllegalArgumentException if null.
   */
  public SpreadSheetWithMacroImpl(SpreadSheet delegate) throws IllegalArgumentException {
    if (delegate == null) {
      throw new IllegalArgumentException("Delegate spreadsheet cannot have a null value");
    }
    this.delegate = delegate;
  }

  @Override
  public void executeMacro(SpreadSheetMacro macro) throws IllegalArgumentException {
    if (macro == null) {
      throw new IllegalArgumentException("Macro cannot have a null value");
    }
    macro.execute(this);
  }

  @Override
  public double get(int row, int col) throws IllegalArgumentException {
    return delegate.get(row, col);
  }

  @Override
  public void set(int row, int col, double value) throws IllegalArgumentException {
    delegate.set(row, col, value);
  }

  @Override
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    return delegate.isEmpty(row, col);
  }

  @Override
  public int getWidth() {
    return delegate.getWidth();
  }

  @Override
  public int getHeight() {
    return delegate.getHeight();
  }
}