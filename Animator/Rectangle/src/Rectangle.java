import java.util.NoSuchElementException;

/**
 * Creates the Rectangle class with variable x, y, height, and width.
 */

public class Rectangle {
  public int x;
  public int y;
  public int width;
  public int height;


  /**
   * Constructor of Rectangle.
   *
   * @param x      the x-coordinate of the bottom left point of the rectangle.
   * @param y      the y-coordinate of the bottom left point of the rectangle.
   * @param height the height up from the bottom left coordinate.
   * @param width  the width extending right from the bottom left coordinate.
   */

  public Rectangle(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    if (height <= 0) {
      throw new IllegalArgumentException("\nPlease enter a positive value for height.");
    } else {
      this.height = height;
    }
    if (width <= 0) {
      throw new IllegalArgumentException("\nPlease enter a positive value for height.");
    } else {
      this.width = width;
    }
  }


  /**
   * Returns true if this rectangle overlaps with the other, false if otherwise.
   *
   * @param other the rectangle being passed into the function to be tested.
   * @return Rectangles that touch each other are not considered to be overlapping.
   */

  public boolean overlap(Rectangle other) {
    int x1 = this.x;
    int y1 = this.y;
    int y2 = this.y + this.height;
    int x2 = this.x + this.width;
    int x3 = other.x;
    int y3 = other.y;
    int x4 = other.x + other.width;
    int y4 = other.y + other.height;

    int leftX = Math.max(x1, x3);
    // Right x
    int rightX = Math.min(x2, x4);
    // Bottom y
    int botY = Math.max(y1, y3);
    // TopY
    int topY = Math.min(y2, y4);

    return rightX > leftX && topY > botY;
  }

  /**
   * Returns a rectangle object that represents the overlap of the two rectangles. If no
   * intersection exists, it will throw a NoSuchElementException and a message.
   *
   * @param other the rectangle being passed into the function to be tested.
   * @return rectangle object that represents the overlap of the two rectangles.
   * @throws NoSuchElementException Exception thrown when rectangles don't intersect.
   */

  public Rectangle intersect(Rectangle other) throws NoSuchElementException {
    if (overlap(other)) {
      // Bottom left of intersection.
      int x1 = java.lang.Math.max(this.x, other.x);
      int y1 = java.lang.Math.max(this.y, other.y);

      // Top right of intersection.
      int x2 = java.lang.Math.min((this.x + this.width), (other.x + other.width));
      int y2 = java.lang.Math.min((this.y + this.height), (other.y + other.height));

      // New height and width values.
      int heightNew = y2 - y1;
      int widthNew = x2 - x1;
      return new Rectangle(x1, y1, widthNew, heightNew);

    } else {
      throw new NoSuchElementException("\nPlease try again with rectangles that intersect.");
    }
  }


  /**
   * Returns a rectangle object that represents the union of the two rectangles.
   *
   * @param other the rectangle being passed into the function to be tested.
   * @return rectangle object that represents the union of the two rectangles.
   */

  public Rectangle union(Rectangle other) {
    // Bottom left of intersection.
    int x1 = java.lang.Math.min(this.x, other.x);
    int y1 = java.lang.Math.min(this.y, other.y);

    // Top right of intersection.
    int y2 = java.lang.Math.max((this.y + this.height), (other.y + other.height));
    int x2 = java.lang.Math.max((this.x + this.width), (other.x + other.width));

    // New height and width values.
    int widthNew = java.lang.Math.abs(x2 - x1);
    int heightNew = java.lang.Math.abs(y2 - y1);

    return new Rectangle(x1, y1, widthNew, heightNew);
  }

  /**
   * Returns a string showing the x and y coordinates of bottom left corner. As well as the width
   * and height of the rectangles.
   *
   * @return string showing x, y, width, and height.
   */

  public String toString() {
    return "x:" + String.valueOf(this.x) +
            ", y:" + String.valueOf(this.y) +
            ", w:" + String.valueOf(this.width) +
            ", h:" + String.valueOf(this.height);
  }
}
