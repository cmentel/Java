package controller;

import java.io.FileNotFoundException;

/**
 * This interface represents the operations offered by the easy animator controller.
 */
public interface EasyAnimatorController {

  /**
   * Start the program.
   */
  void start(int speed) throws FileNotFoundException;

}
