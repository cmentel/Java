package model;
import java.awt.Color;

/**
 * This class represents the color change of a shape.
 */
public class ColorChange implements Change {

  private Color color;
  private double time;

  /**
   * Constructor for Color Change that takes a color and the time at which the color should be
   * changed.
   *
   * @param color The color to be assigned to the object.
   * @param time  The time at which the color will be assigned to the object.
   */
  public ColorChange(Color color, double time) {
    this.color = color;
    this.time = time;
  }

  /**
   * Getter for color of the object.
   *
   * @return the Color of the object.
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Getter for the time of the color change.
   *
   * @return the time at which the object's color will change.
   */
  public double getTime() {
    return this.time;
  }

  /**
   * Getter to retrieve the change type.
   *
   * @return The string representing the change type.
   */
  @Override
  public String getType() {
    return "Color change";
  }
}
