package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import model.EasyAnimatorModelImpl;


/**
 * EasyAnimatorViewVisual class that extends JFrame and implements EasyAnimatorView to view the
 * animation from the passed in model. Each instance of this class represents a visualization of the
 * model.
 */
public class EasyAnimatorViewVisualPlayback extends JFrame
        implements EasyAnimatorView, ActionListener {

  private final EasyAnimatorModelImpl orig_model;
  int tick;
  int tempo;
  int changing_speed;
  int looping;
  int window_x;
  int window_y;
  int window_width;
  int window_height;
  private EasyAnimatorPanel easyAnimatorPanel;
  private Timer timer;

  /**
   * Constructor for EasyAnimatorViewVisual that takes one parameter and throws an exception. Each
   * instance represents the visualization of the model that is passed in.
   *
   * @param model the model that will be animated.
   * @throws IllegalArgumentException if the model is null.
   */
  public EasyAnimatorViewVisualPlayback(EasyAnimatorModelImpl model)
          throws IllegalArgumentException {
    super("Easy Animation");
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }

    this.orig_model = new EasyAnimatorModelImpl(model.getShapes());
    this.orig_model.view_window_x = model.view_window_x;
    this.orig_model.view_window_y = model.view_window_y;
    this.orig_model.view_window_width = model.view_window_width;
    this.orig_model.view_window_height = model.view_window_height;
    // this.window_x = model.view_window_x;
    // this.window_y = model.view_window_y;
    // this.window_width = model.view_window_width;
    // this.window_height = model.view_window_height;

    // setBounds(this.window_x, this.window_y, this.window_width, this.window_height);


    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.easyAnimatorPanel = new EasyAnimatorPanel(model);
    add(this.easyAnimatorPanel);
    pack();
    this.tempo = 0;
    this.changing_speed = 0;
    this.looping = 0;
  }


  @Override
  public void animate(int tempo) throws IllegalArgumentException {
    if (tempo == 0) {
      throw new IllegalArgumentException("Tempo cannot be zero.");
    }

    this.tempo = tempo;
    //if ((this.changing_speed == 1)) {
    // this.tempo = 1;
    // System.out.println("Tempo must be greater than zero.");
    //}

    setVisible(true);
    int delay = 1000 / tempo;
    ActionListener listener = this;


    this.timer = new Timer(delay, listener);

    if (this.changing_speed == 1) {
      this.timer.start();
      this.changing_speed = 0;
    }

    activatePlayback();

  }

  private void stopLoop() {
    this.looping = 0;
  }

  private void loop() {

    restart();
    this.looping = 1;
    while (true) {
      if (this.looping == 1) {
        if (this.tick == this.orig_model.getDurationTicks()) {
          restart();
        }
      }
    }

  }

  /**
   * Method to increase the speed of the animation.
   */
  private void increaseSpeed() {
    this.timer.stop();
    this.changing_speed = 1;
    this.tempo = this.tempo + 1;
    animate(this.tempo + 1);
  }

  /**
   * Method to decrease the speed of the animation.
   */
  private void decreaseSpeed() {
    this.timer.stop();
    this.changing_speed = 1;
    if (this.tempo == 0) {
      System.out.println("Tempo must be greater than 0.");
    } else {
      this.tempo = this.tempo - 1;
      animate(this.tempo - 1);
    }
  }

  /**
   * Method for starting the animation.
   */
  private void start() {
    this.timer.start();
  }

  /**
   * Method for pausing the animation.
   */
  private void pause() {
    this.timer.stop();
  }

  /**
   * Method for resuming the animation after pausing.
   */
  private void resume() {
    this.timer.start();
  }

  /**
   * Method for restarting the animation (from the beginning).
   */
  private void restart() {
    this.tick = 0;
    this.easyAnimatorPanel = new EasyAnimatorPanel(this.orig_model);
    add(this.easyAnimatorPanel);
    repaint();
    pack();
    this.timer.start();
    activatePlayback();
  }


  /**
   * Draw the state of the animation at the given tick.
   *
   * @param tick the current tick
   */
  private void onTick(int tick) {

    easyAnimatorPanel.removeAll();
    easyAnimatorPanel.setCurrentTick(tick);
    easyAnimatorPanel.repaint();
    easyAnimatorPanel.validate();
    System.out.println(tick);
    //System.out.println(this.orig_model.describe());
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e == null) {
      throw new IllegalArgumentException("Action event is null");
    }
    onTick(this.tick);
    this.tick++;
  }

  private void activatePlayback() {
    while (true) {
      String[] controls = {"Stop Loop", "Loop", "Decrease Speed", "Increase Speed", "Restart",
          "Resume", "Pause", "Start"};
      int response = JOptionPane.showOptionDialog(null, "Controls",
              "Controls", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
              controls, "NA");

      switch (response) {
        case 0:
          stopLoop();
          break;
        case 1:
          loop();
          break;
        case 2:
          decreaseSpeed();
          break;
        case 3:
          increaseSpeed();
          break;
        case 4:
          restart();
          break;
        case 5:
          resume();
          break;
        case 6:
          pause();
          break;
        case 7:
          start();
          break;
        case -1:
          System.exit(0);
          break;
        default:
          System.out.println("Default reached.");
      }
    }
  }

}
