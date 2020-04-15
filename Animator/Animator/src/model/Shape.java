package model;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 * This interface represents the operations offered by a shape in the animation.
 */
public interface Shape {

  /**
   * Get name of shape.
   *
   * @return name of shape
   */
  String getName();

  /**
   * Gets shape reference point.
   *
   * @return shape reference point
   */
  Point2D getReference();

  /**
   * Method to set the reference point of a shape.
   *
   * @param reference reference point of a shape
   */
  void setReference(Point2D reference);

  /**
   * Get type of shape.
   *
   * @return type of shape
   */
  String getType();

  /**
   * Get dimensions of shape.
   *
   * @return type of shape
   */
  String getDimensions();

  /**
   * Get color of shape.
   *
   * @param time current tick
   * @return color of shape
   */
  Color getColor(double time);

  /**
   * Gets area of shape at specified time.
   *
   * @param time the time at which area should be retrieved.
   * @return the area of the shape.
   */
  double getArea(double time);

  /**
   * Get list of shape moves.
   *
   * @return list of shape moves
   */
  List<PositionChange> getPositionChanges();

  /**
   * Get list of shape resizes.
   *
   * @return list of shape resizes
   */
  List<SizeChange> getSizeChanges();

  /**
   * Get list of shape color changes.
   *
   * @return list of color changes
   */
  List<ColorChange> getColorChanges();

  /**
   * Get time when shape appears.
   *
   * @return time shape appears
   */
  double getAppears();

  /**
   * Get time when shape disappears.
   *
   * @return time shape disappears
   */
  double getDisappears();

  /**
   * Set shape current position.
   */
  public void setPosition(Point2D newReference);

  /**
   * Set size of shape relative to current size.
   *
   * @param factor factor to scale by
   */
  public void setSize(double factor);

  /**
   * Move this shape to position (x1,y1) beginning at time t0 and ending at time t1.
   *
   * @param positionChange move to add to shape
   */
  void addPositionChange(PositionChange positionChange);

  /**
   * Change the color of this shape beginning at time t0 and ending at time t1.
   *
   * @param colorChange color change to add to shape
   */
  void addColorChange(ColorChange colorChange);

  /**
   * Resize this shape.
   *
   * @param sizeChange resize to add to shape
   */
  void addSizeChange(SizeChange sizeChange);

  /**
   * Get description of shape.
   *
   * @return description of shape
   */
  @Override
  String toString();

  /**
   * Method to draw the shape using the graphics.
   *
   * @param g the graphics used to draw the shape.
   */
  void draw(Graphics g);

  /**
   * Get specs of shape.
   *
   * @return specs of shape
   */
  String getSpecsString();

  /**
   * Getter for the Specs in the format of a List.
   *
   * @return the List of the specs.
   */
  List<Double> getSpecs();

  /**
   * Method to set the color of a shape.
   *
   * @param color color of a shape
   */
  void setColor(Color color);

  /**
   * Remove move of a shape.
   *
   * @param name         name of shape
   * @param positionInit initial position of shape
   * @param x2           final x position of shape
   * @param y2           final y position of shape
   * @param t1           start time of move
   * @param t2           end time of move
   */
  void removeMove(String name, Point2D positionInit, double x2, double y2, int t1, int t2);

  /**
   * Returns copy of a shape.
   *
   * @return copy of shape
   */
  Shape copy();

  /**
   * Accept method that accepts a ShapeVisitor.
   *
   * @param visitor a ShapeVisitor
   */
  void accepts(ShapeVisitor visitor);

}
