package model;
import java.awt.Color;
import java.util.List;

/**
 * This interface represents the operations offered by the easy animator model. One object of the
 * model represents one animation.
 */
public interface EasyAnimatorModel {

  /**
   * Get list of shapes in the animation.
   *
   * @return list of shapes in the animation
   */
  List<Shape> getShapes();

  List<Shape> getExactShapes();

  /**
   * Change a shape in the animation to a different shape.
   *
   * @param shapeInit  name of initial shape.
   * @param shapeFinal new shape object.
   */
  void changeShape(String shapeInit, Shape shapeFinal);

  /**
   * Add a shape move.
   *
   * @param name           name of shape to move.
   * @param positionChange move for shape.
   */
  void addMove(String name, PositionChange positionChange);

  /**
   * Add a shape resize.
   *
   * @param name       name of shape to resize
   * @param sizeChange resize for shape
   */
  void addResize(String name, SizeChange sizeChange);

  /**
   * Add a shape color change.
   *
   * @param name        name of shape to change color
   * @param colorChange color change for shape
   */
  void addColorChange(String name, ColorChange colorChange);

  /**
   * Add a shape to the animation.
   *
   * @param shape shape to be added
   */
  void addShape(Shape shape);


  /**
   * Get a description of the animation.
   *
   * @return a description of the animation
   */
  String describe();

  /**
   * Get a copy of the model.
   *
   * @return copy of the model
   */
  EasyAnimatorModelImpl copy();

  /**
   * Method to set reference point of a shape.
   *
   * @param name      name of shape
   * @param reference reference point of shape
   */
  void setReference(String name, Point2D reference);

  /**
   * Method to change the color of a shape.
   *
   * @param name  name of shape
   * @param color color of shape
   */
  void setColor(String name, Color color);

  /**
   * Get duration of animation in ticks.
   *
   * @return duration of animation in ticks
   */
  double getDurationTicks();

  /**
   * Set view window x position.
   *
   * @param x view window x position
   */
  void setViewWindowX(int x);

  /**
   * Set view window y position.
   *
   * @param y view window y position
   */
  void setViewWindowY(int y);

  /**
   * Set view window width.
   *
   * @param width view window width
   */
  void setViewWindowWidth(int width);

  /**
   * Set view window height.
   *
   * @param height view window height
   */
  void setViewWindowHeight(int height);

  /**
   * Method to set shapes for model.
   */
  void setShapes(List<Shape> shapes);
}
