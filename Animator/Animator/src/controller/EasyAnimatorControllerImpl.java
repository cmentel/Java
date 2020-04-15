package controller;

import java.io.FileNotFoundException;

import model.EasyAnimatorModel;
import view.EasyAnimatorView;


/**
 * Class that represents the controller for the model implementation.
 */
public class EasyAnimatorControllerImpl implements EasyAnimatorController {

  private EasyAnimatorView view;

  /**
   * Constructor for the EasyAnimatorController that takes the model and view as parameters.
   *
   * @param model The model to be passed in and implemented in the animation.
   * @param view  The view to be passed in and implemented to animate the model.
   */
  public EasyAnimatorControllerImpl(EasyAnimatorModel model, EasyAnimatorView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Neither model nor View can be null.");
    }
    this.view = view;
  }

  @Override
  public void start(int speed) throws FileNotFoundException {
    this.view.animate(speed);
  }
}
