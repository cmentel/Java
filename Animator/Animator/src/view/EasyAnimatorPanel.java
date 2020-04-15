package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JPanel;

import model.EasyAnimatorModel;
import model.EasyAnimatorModelImpl;
import model.Point2D;
import model.PositionChange;
import model.Shape;
import model.SizeChange;


/**
 * Public class that extends JPanel to allow us to animate the animation with actual moving shapes.
 * Each instance of this class represents an animation panel that is actually visualized.
 */
public class EasyAnimatorPanel extends JPanel {

  private final EasyAnimatorModel model;
  private int currentTick;
  private HashMap<String, Boolean> visibleShapes;

  /**
   * Constructor for the panel that takes one parameter which is the Model.
   *
   * @param model the model that is being passed into the animation and will be animated.
   * @throws IllegalArgumentException if the model is null.
   */
  EasyAnimatorPanel(EasyAnimatorModelImpl model) throws IllegalArgumentException {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    System.out.println("Setting height of panel:" + model.view_window_height);
    this.setPreferredSize(new Dimension(model.view_window_width, model.view_window_height));
    this.model = model;
    this.currentTick = 0;
    this.setBackground(Color.WHITE);


    //JButton pause_button = new JButton("PAUSE");
    // pause_button.setVerticalTextPosition(AbstractButton.CENTER);
    // pause_button.setHorizontalTextPosition(AbstractButton.LEADING);
    // this.add(pause_button);
    // pause_button.addActionListener(new ActionListener() {


    // @Override
    // public void actionPerformed(ActionEvent e) {
    // pause_button.setBackground(Color.BLUE);
    // }
    // });

    HashMap<String, Boolean> visibles = new HashMap<String, Boolean>();
    for (Shape shape : model.getShapes()) {
      visibles.put(shape.getName(), true);
    }

    this.visibleShapes = visibles;
  }

  /**
   * Get the model.
   *
   * @return the model
   */
  public EasyAnimatorModel getModel() {
    return model;
  }

  /**
   * Return the current tick.
   */
  public int getCurrentTick() {
    return this.currentTick;
  }

  /**
   * Set the current tick of the panel.
   *
   * @param tick the current tick
   * @throws IllegalArgumentException if a tick less than 0 is given.
   */
  public void setCurrentTick(int tick) throws IllegalArgumentException {
    if (tick < 0) {
      throw new IllegalArgumentException("Tick can not be negative.");
    }
    this.currentTick = tick;
  }

  /**
   * Gets the visible shapes hashmap from the AnimatorPanel.
   *
   * @return the visibleShapes HashMap
   */
  public HashMap<String, Boolean> getVisibleShapes() {
    return this.visibleShapes;
  }

  /**
   * Sets the visibility of a specific shape based on name.
   *
   * @param shapeName the name of the shape as a String
   * @param visible   the visibility of the shape
   */
  public void setShapeVisiblility(String shapeName, boolean visible)
          throws IllegalArgumentException {
    if (shapeName == null || shapeName.equals("")) {
      throw new IllegalArgumentException("Shape Name cannot be null.");
    }
    this.visibleShapes.replace(shapeName, visible);
  }

  @Override
  protected void paintComponent(Graphics g) throws IllegalArgumentException {
    super.paintComponent(g);
    if (g == null) {
      throw new IllegalArgumentException("Graphics cannot be null");
    }

    for (Shape shape : model.getExactShapes()) {
      for (PositionChange move : shape.getPositionChanges()) {

        double dt = move.getEndTime() - move.getStartTime();
        double x = move.getTo().getX() - move.getFrom().getX();
        double y = move.getTo().getY() - move.getFrom().getY();
        double dx = x / dt;
        double dy = y / dt;

        for (int i = 0; i < dt; i++) {
          if (currentTick == move.getStartTime() + i) {
            shape.setPosition(new Point2D(shape.getReference().getX()
                    + dx, shape.getReference().getY() + dy));
          }
        }
      }
    }

    for (Shape shape : model.getExactShapes()) {
      for (SizeChange resize : shape.getSizeChanges()) {
        if (currentTick == resize.getStartTime()) {
          shape.setSize(resize.getFactor());
        }
      }
    }

    for (Shape shape : model.getExactShapes()) {
      if (visibleShapes.get(shape.getName())) {
        g.setColor(shape.getColor(currentTick));
        shape.draw(g);
      }
    }
  }


}
