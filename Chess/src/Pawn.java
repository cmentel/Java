/**
 * Creates the Pawn class with variable row, column, and enum Color extending from AbstractPiece.
 */

public class Pawn extends AbstractPiece {

  public Pawn(int row, int column, Color color) {
    super(row, column, color);
  }

  @Override
  public boolean canMove(int row, int column) {
    if (checkBoard(row, column)) {
      return false;
    }
    if (this.getColor() == Color.WHITE) {
      return (row == this.getRow() + 1 && (column == this.getColumn()));
    }
    // If color of Pawn is BLACK
    else {
      return (row == this.getRow() - 1 && (column == this.getColumn()));
    }
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    if (this.getColor() == Color.WHITE) {
      return ((piece.getColumn() == this.getColumn() - 1
              || piece.getColumn() == this.getColumn() + 1)
              && piece.getRow() == this.getRow() + 1
              && !getColor().equals(piece.getColor()));
    }

    // If color of Pawn is BLACK
    else {
      return (piece.getColumn() == this.getColumn() - 1
              || piece.getColumn() == this.getColumn() + 1)
              && piece.getRow() == this.getRow() - 1
              && !getColor().equals(piece.getColor());
    }
  }
}