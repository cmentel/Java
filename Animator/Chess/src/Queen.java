/**
 * Creates the Queen class with variable row, column, and enum Color extending from AbstractPiece.
 */

public class Queen extends AbstractPiece {

  public Queen(int row, int column, Color color) {
    super(row, column, color);
  }

  @Override
  public boolean canMove(int row, int column) {
    if (checkBoard(row, column)) {
      return false;
    } else {
      float absDiffX = Math.abs(column - this.getColumn());
      float absDiffY = Math.abs(row - this.getRow());
      return (row == this.getRow() || column == this.getColumn() || absDiffX == absDiffY);
    }
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    return canMove(piece.getRow(), piece.getColumn())
            && !getColor().equals(piece.getColor());
  }
}