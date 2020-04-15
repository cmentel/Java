package model;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a rectangle.  It defines all the operations mandated by the Shape
 * interface. Each instance of a rectangle represents a rectangle shape to be used in the
 * animation.
 */
public class Rectangle extends AbstractShape {

  private double width;
  private double height;

  /**
   * Rectangle constructor to create the Rectangle shape that will be implemented in animation. Each
   * Rectangle takes 11 Parameters.
   *
   * @param name            The String name of the Rectangle passed into the abstract class.
   * @param color           The java.color of the Rectangle passed into the abstract class.
   * @param x               The x coordinate of the reference point that will be passed into the
   *                        abstract class.
   * @param y               The y coordinate of the reference point that will be passed into the
   *                        abstract class.
   * @param width           The width that will be used for calculations within the animation
   *                        sizing.
   * @param height          The height that will be used for calculations within the animation
   *                        sizing.
   * @param positionChanges The list of position changes that will occur during the animation.
   * @param sizeChanges     The list of size changes that will occur during the animation.
   * @param colorChanges    The list of color changes that will occur during the animation.
   * @param appears         The time at which the Rectangle will appear.
   * @param disappears      The time at which the Rectangle will disappear.
   * @throws IllegalArgumentException if construction is invalid.
   */
  public Rectangle(String name, Color color, double x, double y, double width, double height,
                   List<PositionChange> positionChanges,
                   List<SizeChange> sizeChanges, List<ColorChange> colorChanges,
                   double appears, double disappears) throws IllegalArgumentException {

    super(name, color, new Point2D(x, y), positionChanges,
            sizeChanges, colorChanges, appears, disappears);
    if (name == null || name.equals("") || color == null
            || positionChanges == null || sizeChanges == null
            || colorChanges == null || disappears < appears) {
      throw new IllegalArgumentException("Invalid construction.");
    }

    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and Height must be greater than 0.");
    }
    this.width = width;
    this.height = height;
  }

  /**
   * Secondary constructor for Rectangle class.
   *
   * @param name String name of Rectangle
   */
  public Rectangle(String name) {
    super(name);
    this.width = 0;
    this.height = 0;
  }

  /**
   * Getter for the width of the Rectangle object.
   *
   * @return the width value of the rectangle.
   */
  public double getWidth() {
    return this.width;
  }

  /**
   * Getter for the height of the Rectangle object.
   *
   * @return the height value of the rectangle.
   */
  public double getHeight() {
    return this.height;
  }

  @Override
  public void addSizeChange(SizeChange sizeChange) {
    if (sizeChange == null || sizeChange.getFactor() == 0) {
      throw new IllegalArgumentException("SizeChange must be valid.");
    }
    this.width = width * sizeChange.getFactor();
    this.height = height * sizeChange.getFactor();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getType() {
    return "rectangle";
  }

  @Override
  public String getDimensions() {
    return String.format("Rectangle Width: %.2f, Height: %.2f\n", this.width, this.height);
  }


  @Override
  public String toString() {
    return String.format("Min corner: %s, Width: %.2f, Height: %.2f, Color: %s",
            this.reference.toString(),
            this.width, this.height, ColorName.colorName(this.color));
  }

  @Override
  public void draw(Graphics g) throws IllegalArgumentException {
    if (g == null) {
      throw new IllegalArgumentException("Graphics cannot be null.");
    }
    g.drawRect((int) this.reference.getX(), (int) this.reference.getY(),
            (int) this.width, (int) this.height);
    g.fillRect((int) this.reference.getX(), (int) this.reference.getY(),
            (int) this.width, (int) this.height);
  }

  @Override
  public void setSize(double factor) throws IllegalArgumentException {
    if (factor == 0) {
      throw new IllegalArgumentException("Factor cannot be zero.");
    }
    this.height = this.height * factor;
    this.width = this.width * factor;
  }

  @Override
  public double getArea(double time) {
    if (time < this.getAppears() || time > this.getDisappears()) {
      throw new IllegalArgumentException("Cannot get size for shape that doesn't exist yet.");
    }
    double tempWidth = this.width;
    double tempHeight = this.height;
    for (SizeChange sizeChange : this.sizeChanges) {
      if (sizeChange.getStartTime() < time) {
        tempWidth = this.width;
        tempHeight = this.height;
      }
      tempHeight = this.height * sizeChange.getFactor();
      tempWidth = this.width * sizeChange.getFactor();
    }
    return tempWidth * tempHeight;
  }

  @Override
  public List<Double> getSpecs() {
    List<Double> list = new ArrayList<Double>();
    list.add(this.width);
    list.add(this.height);
    return list;
  }

  @Override
  public Shape copy() {
    return new Rectangle(this.name, this.color,
            this.reference.getX(), this.reference.getY(), this.width,
            this.height, this.positionChanges,
            this.sizeChanges, this.colorChanges, this.appears, this.disappears);
  }

  @Override
  public String getSpecsString() {
    return " width= \"" + this.width + "cm\" height= \"" + this.height + "cm\"";
  }

  @Override
  public void accepts(ShapeVisitor visitor) {
    visitor.visit(this);
  }

  protected void setWidth(int width) {
    this.width = width;
  }

  protected void setHeight(int height) {
    this.height = height;
  }

}
