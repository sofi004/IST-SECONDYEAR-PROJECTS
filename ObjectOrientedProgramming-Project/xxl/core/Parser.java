package xxl.core;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import xxl.app.exception.InvalidCellRangeException;
import xxl.app.exception.UnknownFunctionException;
import xxl.core.exception.UnrecognizedEntryException;

public class Parser {
  private Spreadsheet _spreadsheet;
  
  public Parser() {
  }

  public Parser(Spreadsheet spreadsheet) {
    _spreadsheet = spreadsheet;
  }

  Spreadsheet parseFile(String filename) throws IOException, UnrecognizedEntryException, UnknownFunctionException, InvalidCellRangeException /* More Exceptions? */ {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      parseDimensions(reader);
      String line;
      while ((line = reader.readLine()) != null)
      parseLine(line);
    }
    return _spreadsheet;
  }

  private void parseDimensions(BufferedReader reader) throws IOException, UnrecognizedEntryException {
    int rows = -1;
    int columns = -1;

    for ( int i = 0; i < 2; i++) {
      String[] dimension = reader.readLine().split("=");
      if (dimension[0].equals("linhas"))
        rows = Integer.parseInt(dimension[1]);
      else if (dimension[0].equals("colunas"))
        columns = Integer.parseInt(dimension[1]);
      else
        throw new UnrecognizedEntryException(dimension[0]);
    }
    if (rows <= 0 || columns <= 0)
      throw new UnrecognizedEntryException("Dimensões inválidas para a folha");
    _spreadsheet = new Spreadsheet(rows, columns);
  }

  private void parseLine(String line) throws UnrecognizedEntryException, UnknownFunctionException, InvalidCellRangeException {
    String[] components = line.split("\\|");

    if (components.length == 1) // do nothing
      return;
    if (components.length == 2) {
      String[] address = components[0].split(";");
      Content content = parseContent(components[1]);
      _spreadsheet.getCells().insertContent(Integer.parseInt(address[0]), Integer.parseInt(address[1]), content);
    } else
      throw new UnrecognizedEntryException("Wrong format in line: " + line);
  }

  // parse the begining of an expression
  public Content parseContent(String contentSpecification) throws UnrecognizedEntryException, UnknownFunctionException, InvalidCellRangeException {
    char c = contentSpecification.charAt(0);

    if (c == '=')
      return parseContentExpression(contentSpecification.substring(1));
    else
      return parseLiteral(contentSpecification);
  }

  private Literal parseLiteral(String literalExpression) throws UnrecognizedEntryException {
    if (literalExpression.charAt(0) == '\'')
      return new Character(literalExpression);
    else {
      try {
        int val = Integer.parseInt(literalExpression);
        return new Number(val);
      } catch (NumberFormatException nfe) {
        throw new UnrecognizedEntryException("Número inválido: " + literalExpression);
      }
    }
  }

  // contentSpecification is what comes after '='
  private Content parseContentExpression(String contentSpecification) throws UnrecognizedEntryException, UnknownFunctionException, InvalidCellRangeException {
    if (contentSpecification.contains("("))
      return parseFunction(contentSpecification);
    // It is a reference
    String[] address = contentSpecification.split(";");
    return new Reference(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1]), _spreadsheet);
  }

  private Content parseFunction(String functionSpecification) throws UnrecognizedEntryException, 
    UnknownFunctionException, InvalidCellRangeException{
    String[] components = functionSpecification.split("[()]");

    if (components[1].contains(","))
      return parseBinaryFunction(components[0], components[1]);
        
    return parseIntervalFunction(components[0], components[1]);
  }

  private Content parseBinaryFunction(String functionName, String args) throws UnrecognizedEntryException,
    UnknownFunctionException{
    String[] arguments = args.split(",");
    Content arg0 = parseArgumentExpression(arguments[0]);
    Content arg1 = parseArgumentExpression(arguments[1]);
    
    return switch (functionName) {
      case "ADD" -> new Add(arg0, arg1, functionName);
      case "SUB" -> new Sub(arg0, arg1, functionName);
      case "MUL" -> new Mul(arg0, arg1, functionName);
      case "DIV" -> new Div(arg0, arg1, functionName);
      default -> throw new UnknownFunctionException(functionName);
    };
  }

  private Content parseArgumentExpression(String argExpression) throws UnrecognizedEntryException {
    if (argExpression.contains(";")  && argExpression.charAt(0) != '\'') {
      String[] address = argExpression.split(";");
      return new Reference(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1]), _spreadsheet);
    } else
      return parseLiteral(argExpression);
  }

  private Content parseIntervalFunction(String functionName, String rangeDescription)
    throws UnrecognizedEntryException, UnknownFunctionException, InvalidCellRangeException{
    Range range = _spreadsheet.buildRange(rangeDescription);
    return switch (functionName) {
      case "CONCAT" -> new Concat(range, functionName);
      case "COALESCE" -> new Coalesce(range, functionName);
      case "PRODUCT" -> new Product(range, functionName);
      case "AVERAGE" -> new Average(range, functionName);
      default -> throw new UnknownFunctionException(functionName);
    };
  }
}
