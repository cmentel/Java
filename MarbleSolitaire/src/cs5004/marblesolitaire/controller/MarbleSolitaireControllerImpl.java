package cs5004.marblesolitaire.controller;

import java.io.IOException;
import java.util.Scanner;

import cs5004.marblesolitaire.model.MarbleSolitaireModel;

/**
 * This class implements the MarbleSolitaireController and represents a
 * MarbleSolitaireControllerImpl. One instance represents one MarbleSolitaireControllerImpl. This
 * class represents the interaction the user will have with the model. It has one constructor that
 * takes two parameters; a Readable and an Appendable.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private Readable rd;
  private Appendable ap;

  /**
   * Constructor for MarbleSolitaireControllerImpl that creates a controller by taking in two
   * parameters.
   *
   * @param rd The Readable as the first parameter.
   * @param ap The Appendable as the second parameter.
   * @throws IllegalArgumentException if either is null.
   */
  public MarbleSolitaireControllerImpl(Readable rd, Appendable ap)
          throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Readable or Appendable is null.");
    }
    this.rd = rd;
    this.ap = ap;
  }


  @Override
  public void playGame(MarbleSolitaireModel model)
          throws IllegalArgumentException, IllegalStateException {

    Scanner scan = new Scanner(this.rd);
    try {
      while (!model.isGameOver()) {
        this.appendBoard(model);
        this.appendScore(model);
        if (!this.execute(model, scan)) {
          String toBeAppended = "Score: " + model.getScore();
          this.appendCatch("Game quit!\nCurrent GameState:\n", "End of game.");
          this.appendBoard(model);
          this.appendCatch(toBeAppended, "Unable to append game information.");
          return;
        }
      }

    } catch (NullPointerException e) {
      throw new IllegalArgumentException("NullPointerException Catch.");
    }
    this.appendCatch("Game over!\n", "Unable to append game information.");
    this.appendBoard(model);
    this.appendScore(model);
  }

  private void appendCatch(String string, String error) {
    try {
      this.ap.append(string);
    } catch (IOException e) {
      throw new IllegalStateException(error);
    }
  }

  private void appendBoard(MarbleSolitaireModel board) {
    this.appendCatch(board.getGameState() + "\n", "Board unable to be appended.");
  }

  private void appendScore(MarbleSolitaireModel board) {
    String combinedScore = "Score: " + board.getScore() + "\n";
    this.appendCatch(combinedScore, "Score unable to be appended.");
  }

  private boolean parseString(String string) {
    if (string.equalsIgnoreCase("q")) {
      return false;
    }
    this.appendCatch("Please try again.\n", "User string move invalid.");
    return true;
  }

  private boolean parseInt(int integer) {
    if (integer < 0) {
      this.appendCatch("Please try again\n", "User integer move invalid.");
      return false;
    }
    return true;
  }

  private boolean execute(MarbleSolitaireModel board, Scanner scan) {
    int inputs = 0;
    int[] moves = new int[]{0, 0, 0, 0};
    while (inputs < 4) {
      String next = scan.next();
      int nextInteger;
      try {
        nextInteger = Integer.parseInt(next);
      } catch (NumberFormatException e) {
        return this.parseString(next);
      }

      if (this.parseInt(nextInteger)) {
        moves[inputs] = nextInteger - 1;
        inputs++;
        if (inputs == 4) {
          try {
            board.move(moves[0], moves[1], moves[2], moves[3]);
          } catch (IllegalArgumentException e) {
            this.appendCatch(e.getMessage()
                    + "\n", "User attempted move invalid.");
            inputs = 0;
          }
        }
      }
    }
    return true;
  }


}
