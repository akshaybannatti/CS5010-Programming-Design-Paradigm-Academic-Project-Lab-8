package spreadsheet;

import java.util.Scanner;

/**
 * This controller extends SpreadSheetController to support macro operations.
 * It adds support for bulk-assign-value, av
 */
public class MacroSpreadSheetController extends SpreadSheetController {

  /**
   * Macro working controller.
   *
   * @param sheet sheet for model.
   * @param readable object for inputs
   * @param appendable object to transmit any output
   */
  public MacroSpreadSheetController(SpreadSheet sheet, Readable readable, Appendable appendable) {
    super(sheet, readable, appendable);
  }

  @Override
  protected void processCommand(String userInstruction, Scanner sc, SpreadSheet sheet) {
    int fromRow;
    int fromCol;
    int toRow;
    int toCol;
    double value;
    int destRow;
    int destCol;
    double startValue;
    double increment;

    switch (userInstruction) {
      case "bulk-assign-value":
        try {
          fromRow = getRowNum(sc.next());
          fromCol = sc.nextInt() - 1;
          toRow = getRowNum(sc.next());
          toCol = sc.nextInt() - 1;
          value = sc.nextDouble();
          SpreadSheetMacro bulkMacro = new BulkAssignMacro(fromRow, fromCol, toRow, toCol, value);
          if (sheet instanceof SpreadSheetWithMacro) {
            ((SpreadSheetWithMacro) sheet).executeMacro(bulkMacro);
          } else {
            bulkMacro.execute(sheet);
          }
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;

      case "average":
        try {
          fromRow = getRowNum(sc.next());
          fromCol = sc.nextInt() - 1;
          toRow = getRowNum(sc.next());
          toCol = sc.nextInt() - 1;
          destRow = getRowNum(sc.next());
          destCol = sc.nextInt() - 1;
          SpreadSheetMacro avgMacro = new AverageMacro(fromRow, fromCol, toRow, toCol,
              destRow, destCol);
          if (sheet instanceof SpreadSheetWithMacro) {
            ((SpreadSheetWithMacro) sheet).executeMacro(avgMacro);
          } else {
            avgMacro.execute(sheet);
          }
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;

      case "range-assign":
        try {
          fromRow = getRowNum(sc.next());
          fromCol = sc.nextInt() - 1;
          toRow = getRowNum(sc.next());
          toCol = sc.nextInt() - 1;
          startValue = sc.nextDouble();
          increment = sc.nextDouble();
          SpreadSheetMacro rangeMacro = new RangeAssignMacro(fromRow, fromCol, toRow, toCol,
              startValue, increment);
          if (sheet instanceof SpreadSheetWithMacro) {
            ((SpreadSheetWithMacro) sheet).executeMacro(rangeMacro);
          } else {
            rangeMacro.execute(sheet);
          }
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;

      default:
        super.processCommand(userInstruction, sc, sheet);
        break;
    }
  }

  @Override
  protected void printMenu() throws IllegalStateException {
    writeMessage("Supported user instructions are: " + System.lineSeparator());
    writeMessage("assign-value row-num col-num value (set a cell to a value)"
        + System.lineSeparator());
    writeMessage("print-value row-num col-num (print the value at a given cell)"
        + System.lineSeparator());
    writeMessage("bulk-assign-value from-row-num from-col-num to-row-num to-col-num value "
        + "(set a range of cells to a value)" + System.lineSeparator());
    writeMessage("range-assign from-row-num from-col-num to-row-num to-col-num start-value "
        + "increment (set a row or column of cells to a range of values starting at the "
        + "given value and advancing by the increment)" + System.lineSeparator());
    writeMessage("average from-row-num from-col-num to-row-num to-col-num dest-row-num "
        + "dest-col-num (compute the average of a range of cells and put it at the given "
        + "location)" + System.lineSeparator());
    writeMessage("menu (Print supported instruction list)" + System.lineSeparator());
    writeMessage("q or quit (quit the program) " + System.lineSeparator());
  }
}