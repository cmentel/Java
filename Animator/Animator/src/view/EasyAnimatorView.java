package view;

import java.io.FileNotFoundException;

/**
 * This interface represents the operations offered by the easy animator view.
 */
public interface EasyAnimatorView {

  /**
   * Begin the animation.
   *
   * @param tempo tempo of animation (in seconds)
   */
  void animate(int tempo) throws FileNotFoundException;

}
