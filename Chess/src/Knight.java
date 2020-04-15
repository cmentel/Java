/**
 * Creates the Knight class with variable row, column, and enum Color extending from AbstractPiece.
 */

public class Knight extends AbstractPiece {

  public Knight(int row, int column, Color color) {
    super(row, column, color);
  }

  @Override
  public boolean canMove(int row, int column) {
    int checker = Math.abs((row + column) - (this.getRow() + this.getColumn()));
    return row <= 7 && row >= 0 && column <= 7 && column >= 0
            && (checker == 1 || checker == 3)
            && (row < this.getRow() + 3)
            && (row > this.getRow() - 3)
            && (column < this.getColumn() + 3)
            && (column > this.getColumn() - 3)
            && (row != this.getRow())
            && (column != this.getColumn());
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    return canMove(piece.getRow(), piece.getColumn())
            && !getColor().equals(piece.getColor());
  }
}
