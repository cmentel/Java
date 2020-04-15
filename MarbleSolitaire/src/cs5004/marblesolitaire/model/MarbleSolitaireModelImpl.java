package cs5004.marblesolitaire.model;


import java.util.ArrayList;


/**
 * This class implements the MarbleSolitaireModel and  represents a MarbleSolitaireImpl. One
 * instance represents one MarbleSolitaireModelImpl. This class represents the data with four
 * constructors. Each has 3 parameters / characteristics: armThickness, startRow, startColumn.
 */

public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {
  protected ArrayList<ArrayList<Spot>> board = new ArrayList<>();

  /**
   * First constructor that takes no parameters. Creates a MarbleSolitaireModelImpl with
   * armThickness 3, startRow 3, and startColumn 3.
   */
  public MarbleSolitaireModelImpl() {
    this(3, 3, 3);
  }

  /**
   * Second constructor that takes two parameters creating a MarbleSolitaireImpl with armThickness
   * 3, startRow passed in, and startColumn passed in.
   *
   * @param startRow    The row coordinate that represents the starting position.
   * @param startColumn The column coordinate that represents the starting position.
   * @throws IllegalArgumentException if the construction is invalid.
   */
  public MarbleSolitaireModelImpl(int startRow, int startColumn) throws IllegalArgumentException {
    this(3, startRow, startColumn);
  }

  /**
   * Third constructor that takes one parameter creating a MarbleSolitaireImpl with armThickness
   * passed in, starRow in the center, startColumn in the center, dependent on armThickness.
   *
   * @param armThickness The thickness value on which the board will be created.
   * @throws IllegalArgumentException if the construction is invalid.
   */
  public MarbleSolitaireModelImpl(int armThickness) throws IllegalArgumentException {
    this(armThickness, ((3 * armThickness) / 2) - 1, ((3 * armThickness) / 2) - 1);
    if (armThickness < 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Invalid arm thickness.");
    }
  }

  /**
   * Fourth constructor that takes three parameters creating a MarbleSolitaireImpl with
   * armThickness, startRow, and startColumn all passed in.
   *
   * @param armThickness The thickness value on which the board will be created.
   * @param startRow     The row coordinate that represents the starting position.
   * @param startColumn  The column coordinate that represents the starting position.
   * @throws IllegalArgumentException if construction is invalid.
   */
  public MarbleSolitaireModelImpl(int armThickness, int startRow, int startColumn)
          throws IllegalArgumentException {


    // If the armThickness is not positive OR if the arm thickness is EVEN, throw exception.
    if (armThickness < 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Please adjust thickness.");
    }

    // Filling out row by row.

    // For each spot less than 3 times the thickness -2 for middle portion.
    for (int holderForRow = 0; holderForRow < (3 * armThickness) - 2; holderForRow++) {
      ArrayList<Spot> rowArrayHolder = new ArrayList<>();

      // Composing for each row that holds some Empty and some Marble.

      for (int holderForColumn = 0; holderForColumn < (3 * armThickness) - 2; holderForColumn++) {

        // Places marble in spot.
        if ((armThickness - 2 < holderForRow && holderForRow < 2 * armThickness - 1)
                || (armThickness - 2 < holderForColumn && holderForColumn < 2 * armThickness - 1)) {
          rowArrayHolder.add(Spot.MARBLE);

          // Places NA in spot.
        } else {
          rowArrayHolder.add(Spot.NA);
        }
      }

      // Adds row to board.
      board.add(rowArrayHolder);

    }

    // Try to sets the start as an empty spot.
    try {

      // If start is a marble.
      if (board.get(startRow).get(startColumn).equals(Spot.MARBLE)) {

        // Set as Empty
        board.get(startRow).set(startColumn, Spot.EMPTY);

      } else {
        throw new IndexOutOfBoundsException("Invalid start.");
      }
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Invalid start position.");
    }
  }


  @Override
  public void move(int fromRow, int fromColumn, int toRow, int toColumn)
          throws IllegalArgumentException {
    // If move can't be made from (fromRow, fromColumn) -> to (toRow, toColumn).
    if (!this.canMoveTo(fromRow, fromColumn, toRow, toColumn)) {
      throw new IllegalArgumentException("Invalid move. Please try again.");
    }

    // Variables representing the marble that will be hopped / removed.
    int rowHopped = (fromRow + toRow) / 2;
    int colHopped = (fromColumn + toColumn) / 2;

    board.get(fromRow).set(fromColumn, Spot.EMPTY);
    board.get(rowHopped).set(colHopped, Spot.EMPTY);
    this.addMarble(toRow, toColumn);
  }

  /**
   * Helper function that takes four parameters to determine if a move can be made from a spot to
   * another spot. The parameters are the row of departure, column of departure, row of arrival, and
   * column of arrival.
   *
   * @param rowDep Represents the departing row.
   * @param colDep Represents the departing column.
   * @param rowArr Represents the arriving row.
   * @param colArr Represents the arriving column.
   * @return True is the move is executable, False if it is not.
   */
  protected boolean canMoveTo(int rowDep, int colDep, int rowArr, int colArr) {
    int rowHopped = (rowDep + rowArr) / 2;
    int colHopped = (colDep + colArr) / 2;
    int boardMax = this.board.size();

    return ((rowDep >= 0 && colDep >= 0) && (rowArr >= 0 && colArr >= 0))
            && ((rowDep < boardMax && colDep < boardMax)
            && (rowArr < boardMax && colArr < boardMax))
            && (board.get(rowDep).get(colDep) == Spot.MARBLE)
            && ((Math.abs(rowDep - rowArr) == 2 && colDep - colArr == 0)
            || (Math.abs(colDep - colArr) == 2 && rowDep - rowArr == 0))
            && (board.get(rowHopped).get(colHopped) == Spot.MARBLE)
            && (board.get(rowArr).get(colArr) == Spot.EMPTY);
  }

  /**
   * Helper function that takes two parameters and adds Marble in corresponding spot. Parameters are
   * the row and column where the Marble will be added.
   *
   * @param row the row where the marble will be added.
   * @param col the column where the marble will be added.
   * @throws IllegalArgumentException if trying to add to a spot that is not empty.
   */
  protected void addMarble(int row, int col) throws IllegalArgumentException {
    if (board.get(row).get(col) != Spot.EMPTY) {
      throw new IllegalArgumentException("Please add a marble at an empty spot");
    }
    board.get(row).set(col, Spot.MARBLE);
  }

  @Override
  // Game is over when no more valid moves can be made.
  public boolean isGameOver() {
    if (this.getScore() == 1) {
      return true;
    }

    int counter = board.size();
    for (int i = 0; i < counter; i++) {
      for (int j = 0; j < counter; j++) {

        // Tries to change gamePlayStatus depending on:
        // if there is a spot that has a marble
        // AND
        // that marble on that spot can move.
        if (board.get(i).get(j).equals(Spot.MARBLE) && this.canMarbleMove(i, j)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Helper function that takes two parameters and determines if a marble can move from spot. The
   * parameters are the row and column location of the marble.
   *
   * @param row the row at which the marble exists.
   * @param col the column at which the marble exists.
   * @return the boolean of validity.
   */
  protected boolean canMarbleMove(int row, int col) {
    int size = board.size();

    // If row or col are negative OR if spot does not hold a marble.
    if (row < 0 || col < 0 || board.get(row).get(col) != Spot.MARBLE) {
      return false;
    }

    // If move can be made using helper.
    // canMoveTo(rowDep, colDep, rowArr, colArr).
    return row < (size - 1) && this.canMoveTo(row, col, row + 2, col)
            || row > 1 && this.canMoveTo(row, col, row - 2, col)
            || col < (size - 1) && this.canMoveTo(row, col, row, col + 2)
            || col > 1 && this.canMoveTo(row, col, row, col - 2);
  }

  @Override
  public String getGameState() {
    // Board to be returned.
    StringBuilder gameState = new StringBuilder();

    // Size of board.
    int maxSize = board.size();

    // Loops through each spot.
    for (int row = 0; row < maxSize; row++) {
      for (int col = 0; col < maxSize; col++) {

        // Temporary Spot for loop.
        Spot tempSpot = board.get(row).get(col);

        // For top and bottom parts of board, not appending spaces after.
        if (col > 0 && !(tempSpot == Spot.NA && col > maxSize / 2)) {
          gameState.append(" ");
        }

        // For arms of board appending the remaining spots.
        if (!(tempSpot == Spot.NA && col > ((2 * maxSize / 3) - 1))) {
          gameState.append(tempSpot.getText());

        }
      }
      // Appends new line for the board.
      if (row < maxSize - 1) {
        gameState.append("\n");
      }
    }
    return gameState.toString();
  }

  @Override
  public int getScore() {
    int score = 0;
    int counter = board.size();
    for (ArrayList<Spot> spots : board) {
      for (int col = 0; col < counter; col++) {
        Spot pos = spots.get(col);
        if (pos == Spot.MARBLE) {
          score++;
        }
      }
    }
    return score;
  }


}