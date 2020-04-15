package model;

import java.awt.Color;
import java.util.List;

/**
 * This class represents the Abstract shape that will be extended by the respective shape classes.
 * Each instance of the shape represents a shape that will be used in the greater animation.
 */
public abstract class AbstractShape implements Shape {

  protected String name;
  protected Point2D reference;
  protected Color color;
  protected List<PositionChange> positionChanges;
  protected List<SizeChange> sizeChanges;
  protected List<ColorChange> colorChanges;
  protected double appears;
  protected double disappears;
  protected boolean visible;

  /**
   * AbstractShape constructor that creates the AbstractShape object. Each object can be
   * instantiated in the form of a Rectangle or a Circle.
   *
   * @param name            The string name of the shape to be created.
   * @param color           the Java.Color to be assigned to the shape.
   * @param reference       the reference point that will be created for each shape dependent on
   *                        type.
   * @param positionChanges the List of position changes the shape will make throughout the
   *                        animation.
   * @param sizeChanges     the list of size changes the shape will make throughout the animation.
   * @param colorChanges    the list of color changes the shape will make throughout the animation.
   * @param appears         the time at which the shape will appear in the animation.
   * @param disappears      the time at which the shape will disappear in the animation.
   * @throws IllegalArgumentException if the time to appear or disappear is invalid.
   */
  public AbstractShape(String name, Color color, Point2D reference,
                       List<PositionChange> positionChanges,
                       List<SizeChange> sizeChanges, List<ColorChange> colorChanges,
                       double appears, double disappears) throws IllegalArgumentException {
    this.name = name;
    this.reference = reference;
    this.color = color;
    this.positionChanges = positionChanges;
    this.sizeChanges = sizeChanges;
    this.colorChanges = colorChanges;
    if (appears < 0 || disappears < 0 || disappears < appears) {
      throw new IllegalArgumentException("Time cannot be negative.");
    }
    this.appears = appears;
    this.disappears = disappears;

  }

  /**
   * Secondary constructor.
   *
   * @param name String name of shape
   */
  public AbstractShape(String name) {
    this.name = name;
  }

  @Override
  public void addPositionChange(PositionChange positionChange) throws IllegalArgumentException {
    if (positionChange == null) {
      throw new IllegalArgumentException("Position Change cannot be null.");
    }
    this.positionChanges.add(positionChange);
  }

  @Override
  public void addSizeChange(SizeChange sizeChange) throws IllegalArgumentException {
    if (sizeChange == null) {
      throw new IllegalArgumentException("Size Change cannot be null.");
    }
    this.sizeChanges.add(sizeChange);
  }

  @Override
  public void addColorChange(ColorChange colorChange) throws IllegalArgumentException {
    if (colorChange == null) {
      throw new IllegalArgumentException("Color Change cannot be null.");
    }
    this.colorChanges.add(colorChange);
  }

  @Override
  public Point2D getReference() {
    return this.reference;
  }

  @Override
  public List<PositionChange> getPositionChanges() {
    return this.positionChanges;
  }

  @Override
  public Color getColor(double time) {
    if (time < 0) {
      throw new IllegalArgumentException("Time cannot be negative.");
    }
    Color temp = this.color;
    for (ColorChange colorChange : this.colorChanges) {
      if (colorChange.getTime() < time) {
        temp = this.color;
      }
      temp = colorChange.getColor();
    }
    return temp;
  }

  @Override
  public List<SizeChange> getSizeChanges() {
    return this.sizeChanges;
  }

  @Override
  public List<ColorChange> getColorChanges() {
    return this.colorChanges;
  }

  @Override
  public double getAppears() {
    return this.appears;
  }

  @Override
  public double getDisappears() {
    return this.disappears;
  }


  @Override
  public void setPosition(Point2D newReference) throws IllegalArgumentException {
    if (newReference == null) {
      throw new IllegalArgumentException("New Reference cannot be null.");
    }
    this.reference = newReference;
  }

  @Override
  public void setReference(Point2D reference) {
    this.reference = reference;
  }

  @Override
  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public void removeMove(String name, Point2D positionInit, double x2, double y2, int t1, int t2) {
    for (int i = 0; i < this.positionChanges.size(); i++) {
      PositionChange positionChange = this.positionChanges.get(i);
      if ((positionChange.getStartTime() == t1)
              && (positionChange.getEndTime() == t2)
              && (positionChange.getFrom().getX() == positionInit.getX())
              && (positionChange.getFrom().getY() == positionInit.getY())
              && (positionChange.getTo().getX() == x2)
              && (positionChange.getTo().getY() == y2)) {
        this.positionChanges.remove(i);
      }
    }
  }
}
