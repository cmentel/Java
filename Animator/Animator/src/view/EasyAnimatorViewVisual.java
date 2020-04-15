package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.EasyAnimatorModelImpl;


/**
 * EasyAnimatorViewVisual class that extends JFrame and implements EasyAnimatorView to view the
 * animation from the passed in model. Each instance of this class represents a visualization of the
 * model.
 */
public class EasyAnimatorViewVisual extends JFrame implements EasyAnimatorView {

  private EasyAnimatorPanel easyAnimatorPanel;

  /**
   * Constructor for EasyAnimatorViewVisual that takes one parameter and throws an exception. Each
   * instance represents the visualization of the model that is passed in.
   *
   * @param model the model that will be animated.
   * @throws IllegalArgumentException if the model is null.
   */
  public EasyAnimatorViewVisual(EasyAnimatorModelImpl model) throws IllegalArgumentException {
    super("Easy Animation");
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    setBounds(10, 10, 600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.easyAnimatorPanel = new EasyAnimatorPanel(model);
    add(easyAnimatorPanel);
    pack();
  }


  @Override
  public void animate(int tempo) throws IllegalArgumentException {
    if (tempo == 0) {
      throw new IllegalArgumentException("Tempo cannot be zero.");
    }
    setVisible(true);
    int delay = 1000 / tempo;
    ActionListener listener = new ActionListener() {
      private int tick = 0;

      @Override
      public void actionPerformed(ActionEvent e) {
        onTick(tick);
        tick++;
      }
    };
    new Timer(delay, listener).start();
    JOptionPane.showMessageDialog(null, "Animation Running - Click OK to end.");
  }

  /**
   * Draw the state of the animation at the given tick.
   *
   * @param tick the current tick
   * @throws IllegalArgumentException thrown when negative value is passed in.
   */
  private void onTick(int tick) throws IllegalArgumentException {
    if (tick < 0) {
      throw new IllegalArgumentException("Tick cannot be negative.");
    }
    easyAnimatorPanel.removeAll();
    easyAnimatorPanel.setCurrentTick(tick);
    easyAnimatorPanel.revalidate();
    easyAnimatorPanel.repaint();
    //System.out.println(tick);
  }
}
