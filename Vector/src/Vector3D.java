/**
 * This class represents a Vector. A Vector has points x,y,z.
 */

public class Vector3D {
  public double x;
  public double y;
  public double z;

  /**
   * Constructing a Vector object that has the components x, y, z.
   *
   */

  public Vector3D(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }


  /**
   * Return the x component of the vector.
   *
   * @return the x component of the vector.
   */

  public double getX(double x) {
    return this.x;
  }


  /**
   * Return the y component of the vector.
   *
   * @return the y component of the vector.
   */

  public double getY(double y) {
    return this.y;
  }

  /**
   * Return the z component of the vector.
   *
   * @return the z component of the vector.
   */

  public double getZ(double z) {
    return this.z;
  }

  /**
   * toString(): Return the string that describes the vector.
   *
   * @return The string that describes the vector rounded to two decimal places.
   */

  public String toString() {
    String xrounded = String.format("%.2f",this.x);
    String yrounded = String.format("%.2f",this.y);
    String zrounded = String.format("%.2f",this.z);
    return ("(" + xrounded + "," + yrounded + "," + zrounded + ")" + "\n");
  }

  /**
   * getMagnitude(): Return The magnitude of the vector.
   *
   * @return The magnitude value of the vector.
   */

  public double getMagnitude() {
    double mag = ((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
    return Math.pow(mag, .5);
  }

  /**
   * normalize (): Return The normalization of the vector.
   *
   * @return A new vector with components equal to the normalized of the vector input.
   */

  public Vector3D normalize() {
    try {
      double mag = ((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
      double finalmag = Math.pow(mag, .5);
      double xnorm = (this.x / finalmag);
      double ynorm = (this.y / finalmag);
      double znorm = (this.z / finalmag);
      return new Vector3D(xnorm,ynorm,znorm);

    }

    catch (Exception e) {
      throw new IllegalStateException();
    }
  }



  /**
   * add (): Return the addition of one vector with another.
   *
   * @return A new vector with components equal to the addition
   *     of the two vectors respective components.
   */

  public Vector3D add(Vector3D other_Vector) {
    double addX = (this.x + other_Vector.x);
    double addY = (this.y + other_Vector.y);
    double addZ = (this.z + other_Vector.z);
    return new Vector3D(addX,addY,addZ);
  }


  /**
   * multiply(): Return the multiplication of one vector with a constant.
   *
   * @return A new vector with component values equal to input multiplied by a constant.
   */

  public Vector3D multiply(double constant) {
    double prodX = (this.x * constant);
    double prodY = (this.y * constant);
    double prodZ = (this.z * constant);
    return new Vector3D(prodX,prodY,prodZ);



  }

  /**
   * dotProduct(): Return the dot product of one vector and another.
   *
   * @return A new vector with component values equal to
   *     the dot product value of one vector with another.
   */

  public Vector3D dotProduct(Vector3D other_Vector) {
    double dotProd1 = (this.x * other_Vector.x);
    double dotProd2 = (this.y * other_Vector.y);
    double dotProd3 = (this.z * other_Vector.z);
    return new Vector3D(dotProd1,dotProd2,dotProd3);
  }

  /**
   * angleBetween(): Return the angle between two vectors in degrees.
   *
   * @return The angle between two vectors in degrees.
   */

  public double angleBetween(Vector3D other_Vector) {
    try {
      double numerator = (this.x * other_Vector.x);
      double denominator = ((Math.abs(this.y)) * (Math.abs(other_Vector.y)));
      return Math.toDegrees(Math.cos(numerator / denominator));
    }

    catch (Exception e) {
      throw new IllegalStateException();

    }


  }


}