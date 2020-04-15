/**
 * This class is the Main class allowing the program to be run.
 */

public class Main {


  /**
   * This method is the Main method allowing the program to be run.
   */

  public static void main(String [] args) {

    // create a new testVect instance.
    Vector3D testVect1 = new Vector3D(9,8,7);

    // create a new testVect2 instance.
    Vector3D testVect2 = new Vector3D(1,2,3);

    // calls normalize
    testVect1.normalize();

    // calls magnitude
    testVect1.getMagnitude();

    // calls multiply usint testVect1 values
    testVect1.multiply(4);

    // calls dotProduct with testVect2 input
    testVect1.dotProduct(testVect2);

    // calls add with testVect2 input
    testVect1.add(testVect2);

    // calls angleBetween with testVect2 input
    testVect1.angleBetween(testVect2);
  }
}
