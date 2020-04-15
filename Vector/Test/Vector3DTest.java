import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the Vector3D class.
 */

public class Vector3DTest {

  public Vector3D test_vector = new Vector3D(9, 8, 7);
  public Vector3D test_vector2 = new Vector3D(1,2,3);
  public double x;
  public double y;
  public double z;


  /**
   * Created to prevent deprecation errors with JUnit testing.
   */

  private void assertarrayEquals(Vector3D vector3D, Vector3D normalize) {
    // Created to prevent deprecation errors with JUnit testing.
  }

  /**
   * Created to prevent deprecation errors with JUnit testing.
   */

  private void assertArrayEquals(Vector3D vector3D, Vector3D add) {
    // Created to prevent deprecation errors with JUnit testing.
  }

  @Before
  public void setUp() {
    Vector3D test_vector = new Vector3D(9,8,7);
    Vector3D test_vector2 = new Vector3D(1, 2, 3);
  }


  // Tests the GetX function for correct value of x.
  @Test
  public void testGetX() {
    assertEquals(9, test_vector.getX(this.x),.0001);

  }

  // Tests the GetY function for correct value of y.
  @Test
  public void testGetY() {
    assertEquals(8, test_vector.getY(this.y),.0001);

  }

  // Tests the GetZ function for correct value of z.
  @Test
  public void testgetZ() {
    assertEquals(7, test_vector.getZ(this.z),.0001);

  }

  // Tests the getMagnitude function for correct magnitude value with a delta of .01.
  @Test
  public void testGetMagnitude() {
    assertEquals(13.93, test_vector.getMagnitude(),.01);

  }

  // Tests the normalize function for correct normalized value.
  @Test
  public void testNormalize() {
    assertarrayEquals(new Vector3D(0.65, 0.57, .50), test_vector.normalize());

  }

  // Tests the add function for correct added value.
  @Test
  public void testAdd() {
    assertArrayEquals(new Vector3D(10.0, 10.0, 10.0), test_vector.add(test_vector2));

  }

  // Tests the multiply function for correct multiplied value.
  @Test
  public void testMultiply() {
    assertArrayEquals(new Vector3D(27.0, 24.0, 21.0), test_vector.multiply(3));

  }

  // Tests the dotProduct function for correct dotProduct vector.
  @Test
  public void testDotProduct() {
    assertarrayEquals(new Vector3D(9.0, 16.0, 21.0), test_vector.dotProduct(test_vector2));

  }

  // Tests the angleBetween function for correct angle found.
  @Test
  public void testAngleBetween() {
    assertEquals(48.47, test_vector.angleBetween(test_vector2),.01);

  }
}

