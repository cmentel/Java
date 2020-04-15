import java.io.FileNotFoundException;

import cs5004.easyanimator.controller.EasyAnimatorControllerImpl;
import cs5004.easyanimator.model.EasyAnimatorModelImpl;
import cs5004.easyanimator.view.EasyAnimatorViewSVG;
import cs5004.easyanimator.view.EasyAnimatorViewVisual;
import cs5004.easyanimator.view.EasyAnimatorViewVisualPlayback;

/**
 * Class that represents the EasyAnimator holding the main method to utilize command line arguments
 * for our program.
 */
public final class EasyAnimator {

  /**
   * Main method to run the animator utilizing command line arguments throwing two exceptions.
   *
   * @param args String of characters that is the command line arguments passed into method.
   * @throws IllegalArgumentException Exception thrown if no valid commands found.
   * @throws FileNotFoundException    Exception thrown if unable to locate the file.
   */
  public static void main(String[] args) throws IllegalArgumentException, FileNotFoundException {

    System.out.println(System.getProperty("user.dir"));

    String infile = null;
    String outfile = null;
    String speed = null;
    String viewtype = null;

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-in")) {
        infile = System.getProperty("user.dir") + "/" + args[i + 1];
      }
      if (args[i].equals("-out")) {
        outfile = args[i + 1];
      }
      if (args[i].equals("-speed")) {
        speed = args[i + 1];
      }
      if (args[i].equals("-view")) {
        viewtype = args[i + 1];
      }
    }
    if (speed == null) {
      speed = "1";
    }
    if (outfile == null) {
      outfile = "System.out";
    }
    if (viewtype == null) {
      throw new IllegalArgumentException("Must use -view in command line arguments.");
    }
    if (infile == null) {
      throw new IllegalArgumentException("Must use -in in command line arguments.");
    }

    EasyAnimatorModelImpl model = new EasyAnimatorModelImpl(infile);

    switch (viewtype) {
      case "visual": {
        EasyAnimatorViewVisual view = new EasyAnimatorViewVisual(model);
        EasyAnimatorControllerImpl controller = new EasyAnimatorControllerImpl(model, view);
        controller.start(1);
        break;
      }
      case "svg":
      case "text": {
        EasyAnimatorViewSVG view = new EasyAnimatorViewSVG(model, outfile);
        EasyAnimatorControllerImpl controller = new EasyAnimatorControllerImpl(model, view);
        controller.start(1);
        break;
      }
      case "playback": {
        EasyAnimatorViewVisualPlayback view = new EasyAnimatorViewVisualPlayback(model);
        EasyAnimatorControllerImpl controller = new EasyAnimatorControllerImpl(model, view);
        controller.start(Integer.parseInt(speed));
        break;
      }

      default: {
        System.out.println("Please enter valid command.");
      }


    }

  }

}

