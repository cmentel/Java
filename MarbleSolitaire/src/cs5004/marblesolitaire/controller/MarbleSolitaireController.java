package cs5004.marblesolitaire.controller;

import cs5004.marblesolitaire.model.MarbleSolitaireModel;

public interface MarbleSolitaireController {
  /**
   * This method should play a new game of Marble Solitaire using the provided model. It will parse
   * input from the user, display the game state and the current score. In the case of unacceptable
   * inputs from the user, it will prompt for re-entry of values before completing a move. This
   * method will then check the users inputs against the model to determine the validity of a move.
   * In the case of an invalid move, this method will again prompt the user to correct their input.
   *
   * @param model to represent the model used for marble solitaire.
   * @throws IllegalArgumentException If the provided model is null.
   * @throws IllegalStateException    If the Appendable object is unable to transmit output or the
   *                                  Readable object is unable to provide inputs
   */
  void playGame(MarbleSolitaireModel model) throws IllegalArgumentException, IllegalStateException;
}


