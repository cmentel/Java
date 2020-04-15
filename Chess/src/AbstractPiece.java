/**
 * Creates the AbstractPiece class with variable row, column, and enum Color.
 */

public abstract class AbstractPiece implements ChessPiece {
  private final int row;
  private final int column;
  private final Color color;

  /**
   * Creates the AbstractPiece object.
   *
   * @param row    the row on which the AbstractPiece will be created.
   * @param column the column on which the AbstractPiece will be created.
   * @param color  the colon of which the AbstractPiece will have.
   * @throws IllegalArgumentException thrown if rows are out of bounds or color not BlACK or WHITE
   */

  public AbstractPiece(int row, int column, Color color) throws IllegalArgumentException {
    if (row < 0 || row > 7) {
      throw new IllegalArgumentException("Row must be between 0 - 7, inclusive.");
    } else {
      this.row = row;
    }
    if (column < 0 || column > 7) {
      throw new IllegalArgumentException("Row must be between 0 - 7, inclusive.");
    } else {
      this.column = column;
    }
    switch (color) {
      case BLACK:
        this.color = Color.BLACK;
        break;
      case WHITE:
        this.color = Color.WHITE;
        break;
      default:
        throw new IllegalArgumentException("Color must be either BLACK or WHITE");
    }
  }

  /**
   * Method that allows for the AbstractPiece's row to be found.
   *
   * @return the int value of the row on which the AbstractPiece sits.
   */
  @Override
  public int getRow() {
    return this.row;
  }

  /**
   * Method that allows for the AbstractPiece's column to be found.
   *
   * @return the int value of the column on which the AbstractPiece sits.
   */
  @Override
  public int getColumn() {
    return this.column;
  }

  /**
   * Method that allows for the AbstractPiece's color to be found.
   *
   * @return the string value of the color on which the AbstractPiece sits.
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * Method that provides the position of the AbstractPiece.
   *
   * @return a String that shows the row and column of the AbstractPiece.
   */
  @Override
  public String getPosition() {
    return ("Row: " + this.row + " Column: " + this.column);
  }

  /**
   * Helper method to check if the input is within bounds of the board and the move isn't to the
   * position piece is already in.
   *
   * @param row    the int row value to which the AbstractPiece would like to move.
   * @param column the int column value to which the AbstractPiece would like to move.
   * @return true will be returned if the AbstractPiece CANNOT move to input spot.
   */
  protected boolean checkBoard(int row, int column) {
    return ((row > 7 || row < 0 || column > 7 || column < 0)
            || (this.column == column && this.row == row));
  }
}
