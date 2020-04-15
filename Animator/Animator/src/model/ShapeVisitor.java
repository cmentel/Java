package model;
/**
 * Visitor class to set shape sizes.
 */
public class ShapeVisitor {

  private int width;
  private int height;
  private int radius;

  /**
   * Constructor for ShapeVisitor.
   * @param width rectangle width
   * @param height rectangle height
   * @param radius circle radius
   */
  ShapeVisitor(int width, int height, int radius) {
    this.width = width;
    this.height = height;
    this.radius = radius;
  }

  /**
   * Visit method to set size of rectangle.
   * @param rectangle rectangle
   */
  void visit(Rectangle rectangle) {
    rectangle.setWidth(this.width);
    rectangle.setHeight(this.height);
  }

  /**
   * Visit method to set size of circle.
   * @param circle circle
   */
  void visit(Circle circle) {
    circle.setRadius(this.radius);
  }

}
