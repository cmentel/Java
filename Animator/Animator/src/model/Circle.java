package model;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Circle object that extends the AbstractShape class. Each instance of a
 * circle will be used in the animation.
 */
public class Circle extends AbstractShape {

  private double radius;

  /**
   * Constructor for the Circle Class that creates a circle taking 10 parameters allowing for
   * comprehensive animation.
   *
   * @param name            The String name of the Circle passed into the abstract class.
   * @param color           The java.color of the Circle passed into the abstract class.
   * @param x               The x coordinate of the reference point that will be passed into the
   *                        abstract class.
   * @param y               The y coordinate of the reference point that will be passed into the
   *                        abstract class.
   * @param radius          The radius size that will be used for calculations within the animation
   *                        sizing.
   * @param positionChanges The list of position changes that will occur during the animation.
   * @param sizeChanges     The list of size changes that will occur during the animation.
   * @param colorChanges    The list of color changes that will occur during the animation.
   * @param appears         The time at which the circle will appear.
   * @param disappears      The time at which the circle will disappear.
   * @throws IllegalArgumentException if construction is invalid.
   */
  public Circle(String name, Color color, double x, double y, double radius,
                List<PositionChange> positionChanges,
                List<SizeChange> sizeChanges, List<ColorChange> colorChanges,
                double appears, double disappears) {
    super(name, color, new Point2D(x, y), positionChanges, sizeChanges,
            colorChanges, appears, disappears);
    if (radius <= 0) {
      //throw new IllegalArgumentException("Circle radius must be greater than 0.");
      radius = 1;
    }
    this.radius = radius;
  }

  /**
   * Secondary constructor for Circle class.
   *
   * @param name String name of Circle
   */
  public Circle(String name) {
    super(name);
    this.radius = 0;
  }

  @Override
  public String getDimensions() {
    return String.format("Circle radius is %.2f\n", this.radius);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getType() {
    return "circle";
  }

  @Override
  public void addSizeChange(SizeChange sizeChange) throws IllegalArgumentException {
    if (sizeChange == null || sizeChange.getFactor() == 0) {
      throw new IllegalArgumentException("Value cannot be null.");
    }
    sizeChanges.add(sizeChange);
  }


  @Override
  public double getArea(double time) {
    if (time < this.getAppears() || time > this.getDisappears()) {
      throw new IllegalArgumentException("Cannot get size for shape that doesn't exist yet.");
    }
    double temp = this.radius;
    for (SizeChange sizeChange : this.sizeChanges) {
      if (sizeChange.getStartTime() < time) {
        temp = this.radius;
      }
      temp = this.radius * sizeChange.getFactor();
    }
    return Math.PI * temp * temp;
  }


  @Override
  public String toString() {
    return String.format("Center: %s, Radius: %.2f, Color: %s", this.reference.toString(),
            this.radius, ColorName.colorName(this.color));
  }

  @Override
  public void draw(Graphics g) throws IllegalArgumentException {
    if (g == null) {
      throw new IllegalArgumentException("Graphics cannot be null.");
    }
    g.drawOval((int) this.reference.getX(), (int) this.reference.getY(),
            (int) this.radius, (int) this.radius);
    g.fillOval((int) this.reference.getX(), (int) this.reference.getY(),
            (int) this.radius, (int) this.radius);
  }


  @Override
  public void setSize(double factor) {
    if (factor == 0) {
      throw new IllegalArgumentException("Factor cannot be zero.");
    }
    this.radius = this.radius * factor;
  }

  @Override
  public List<Double> getSpecs() {
    List<Double> list = new ArrayList<Double>();
    list.add(this.radius);
    return list;
  }

  @Override
  public Shape copy() {
    return new Circle(this.name, this.color, this.reference.getX(), this.reference.getY(),
            this.radius,
            this.positionChanges, this.sizeChanges, this.colorChanges, this.appears,
            this.disappears);
  }

  @Override
  public void accepts(ShapeVisitor visitor) {
    visitor.visit(this);
  }

  protected void setRadius(int radius) {
    this.radius = radius;
  }

  @Override
  public String getSpecsString() {
    return " radius= " + this.radius + "cm";
  }
}
