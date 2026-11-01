import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import org.junit.Test;
import spreadsheet.MacroSpreadSheetController;
import spreadsheet.SparseSpreadSheet;
import spreadsheet.SpreadSheet;

/**
 * Test class for MacroSpreadSheetController.
 */
public class MacroSpreadSheetControllerTest {

  @Test
  public void testBulkAssignValueCommand() {
    StringBuilder input = new StringBuilder();
    input.append("bulk-assign-value A 1 B 3 10.5").append(System.lineSeparator());
    input.append("print-value A 1").append(System.lineSeparator());
    input.append("print-value A 2").append(System.lineSeparator());
    input.append("print-value B 3").append(System.lineSeparator());
    input.append("print-value C 1").append(System.lineSeparator());
    input.append("q").append(System.lineSeparator());

    Readable readable = new StringReader(input.toString());
    Appendable appendable = new StringBuilder();
    SpreadSheet sheet = new SparseSpreadSheet();
    MacroSpreadSheetController controller = new MacroSpreadSheetController(sheet, readable,
        appendable);
    controller.control();
    String output = appendable.toString();
    assert (output.contains("Welcome to the spreadsheet program!"));
    assert (output.contains("Value: 10.5"));
    assert (output.contains("Value: 0.0"));
  }

  @Test
  public void testAverageCommand() {
    StringBuilder input = new StringBuilder();
    input.append("assign-value A 1 10").append(System.lineSeparator());
    input.append("assign-value A 2 20").append(System.lineSeparator());
    input.append("assign-value A 3 30").append(System.lineSeparator());
    input.append("average A 1 A 3 B 1").append(System.lineSeparator());
    input.append("print-value B 1").append(System.lineSeparator());
    input.append("q").append(System.lineSeparator());
    Readable readable = new StringReader(input.toString());
    Appendable appendable = new StringBuilder();
    SpreadSheet sheet = new SparseSpreadSheet();
    MacroSpreadSheetController controller = new MacroSpreadSheetController(sheet, readable,
        appendable);
    controller.control();
    String output = appendable.toString();
    assert (output.contains("Value: 20.0"));
  }

  @Test
  public void testRangeAssignCommand() {
    StringBuilder input = new StringBuilder();
    input.append("range-assign A 1 A 5 1 1").append(System.lineSeparator());
    input.append("print-value A 1").append(System.lineSeparator());
    input.append("print-value A 3").append(System.lineSeparator());
    input.append("print-value A 5").append(System.lineSeparator());
    input.append("q").append(System.lineSeparator());
    Readable readable = new StringReader(input.toString());
    Appendable appendable = new StringBuilder();
    SpreadSheet sheet = new SparseSpreadSheet();
    MacroSpreadSheetController controller = new MacroSpreadSheetController(sheet, readable,
        appendable);
    controller.control();
    String output = appendable.toString();
    assert (output.contains("Value: 1.0"));
    assert (output.contains("Value: 3.0"));
    assert (output.contains("Value: 5.0"));
  }

  @Test
  public void testMenuIncludesMacroCommands() {
    StringBuilder input = new StringBuilder();
    input.append("menu").append(System.lineSeparator());
    input.append("q").append(System.lineSeparator());
    Readable readable = new StringReader(input.toString());
    Appendable appendable = new StringBuilder();
    SpreadSheet sheet = new SparseSpreadSheet();
    MacroSpreadSheetController controller = new MacroSpreadSheetController(sheet, readable,
        appendable);
    controller.control();
    String output = appendable.toString();
    assert (output.contains("bulk-assign-value"));
    assert (output.contains("range-assign"));
    assert (output.contains("average"));
  }

  @Test
  public void testInvalidBulkAssignRange() {
    StringBuilder input = new StringBuilder();
    input.append("bulk-assign-value B 1 A 3 10.5").append(System.lineSeparator());
    input.append("q").append(System.lineSeparator());
    Readable readable = new StringReader(input.toString());
    Appendable appendable = new StringBuilder();
    SpreadSheet sheet = new SparseSpreadSheet();
    MacroSpreadSheetController controller = new MacroSpreadSheetController(sheet, readable,
        appendable);
    controller.control();
    String output = appendable.toString();
    assert (output.contains("Error:"));
  }
}