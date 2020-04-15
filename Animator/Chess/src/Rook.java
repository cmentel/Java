/**
 * Creates the Rook class with variable row, column, and enum Color extending from AbstractPiece.
 */

public class Rook extends AbstractPiece {

  public Rook(int row, int column, Color color) {
    super(row, column, color);
  }

  @Override
public boolean canMove(int row, int column) {
  if (checkBoard(row, column)) {
    return false;
  } else {
    return (row == this.getRow() || column == this.getColumn());
  }
}

  @Override
  public boolean canKill(ChessPiece piece) {
    return canMove(piece.getRow(), piece.getColumn())
            && !getColor().equals(piece.getColor());
  }
}