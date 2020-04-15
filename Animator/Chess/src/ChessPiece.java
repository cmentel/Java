/**
 * Creates the interface for the ches pieces. Methods are getRow, getColumn, getColor, getPosition,
 * canMove, and CanKill.
 */
public interface ChessPiece {

  /**
   * Method that allows for the pieces's row to be found.
   *
   * @return the int value of the row on which the piece sits.
   */
  int getRow();


  /**
   * Method that allows for the piece's column to be found.
   *
   * @return the int value of the column on which the piece sits.
   */
  int getColumn();

  /**
   * Method that allows for the pieces's color to be retrieved.
   *
   * @return the Color value of the color to which the piece was created.
   */
  Color getColor();

  /**
   * Method that allows for the pieces's position to be retrieved.
   *
   * @return the position as a string to which the piece was created.
   */
  String getPosition();

  /**
   * Method to check if piece can be moved to input spot based on the rules of chess.
   *
   * @param row    the int row value to which the piece is trying to move.
   * @param column the int column value to which the piece is trying to move.
   * @return true if can move, false if not.
   */
  boolean canMove(int row, int column);

  /**
   * Method to check if piece can kill another piece based on the rules of chess.
   *
   * @param piece the other piece that user is trying to kill
   * @return true if piece can be killed, false if not.
   */
  boolean canKill(ChessPiece piece);
}