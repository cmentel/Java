import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * Class to test polynomial objects.
 */
public class PolynomialTest {

  Polynomial test1 = new PolynomialImpl();
  Polynomial test2 = new PolynomialImpl();
  Polynomial test3 = new PolynomialImpl();
  Polynomial test4 = new PolynomialImpl();
  Polynomial test5 = new PolynomialImpl();
  Polynomial test6 = new PolynomialImpl("3x^4 +3x^2 +4x^1 +7");
  Polynomial test7 = new PolynomialImpl("0");
  Polynomial test8 = new PolynomialImpl("-29x^9 -45x^8 -50");
  Polynomial test9 = new PolynomialImpl("6x^1");
  Polynomial test10 = new PolynomialImpl("-100x^100 +99x^99");
  Polynomial test11 = new PolynomialImpl("-100");
  Polynomial test12 = new PolynomialImpl("1");
  Polynomial test13 = new PolynomialImpl("1x^1");
  Polynomial test14 = new PolynomialImpl("-10x^1");
  Polynomial test15 = new PolynomialImpl(" ");
  Polynomial test16 = new PolynomialImpl("");
  Polynomial test20 = new PolynomialImpl("0x^6");


  @Before
  public void setUp() {

    // 4x^3 +3x^2 +2x^1 +1
    test1.addTerm(4, 3);
    test1.addTerm(3, 2);
    test1.addTerm(2, 1);
    test1.addTerm(1, 0);

    // 4x^1
    test2.addTerm(1, 1);
    test2.addTerm(1, 1);
    test2.addTerm(1, 1);
    test2.addTerm(1, 1);

    // 9x^1 +8x^2 +7x^3 +6x^4
    test3.addTerm(9, 1);
    test3.addTerm(8, 2);
    test3.addTerm(7, 3);
    test3.addTerm(6, 4);

    // -9x^8
    test4.addTerm(-9, 8);

    // 63x^41 +3x^8 +30x^4 +13x^3 +8x^2 +9x^1 +34
    test5.addTerm(9, 1);
    test5.addTerm(8, 2);
    test5.addTerm(7, 3);
    test5.addTerm(6, 4);
    test5.addTerm(6, 4);
    test5.addTerm(6, 4);
    test5.addTerm(6, 4);
    test5.addTerm(6, 4);
    test5.addTerm(3, 8);
    test5.addTerm(6, 3);
    test5.addTerm(34, 0);
    test5.addTerm(63, 41);

  }


  @Test
  public void testPolynomialCreation() {
    Assert.assertEquals("4x^3 +3x^2 +2x^1 +1", test1.toString());
    Assert.assertEquals("4x^1", test2.toString());
    Assert.assertEquals("6x^4 +7x^3 +8x^2 +9x^1", test3.toString());
    Assert.assertEquals("-9x^8", test4.toString());
    Assert.assertEquals("63x^41 +3x^8 +30x^4 +13x^3 +8x^2 +9x^1 +34", test5.toString());
    Assert.assertEquals("3x^4 +3x^2 +4x^1 +7", test6.toString());
    Assert.assertEquals("0", test7.toString());
    Assert.assertEquals("-29x^9 -45x^8 -50", test8.toString());
    Assert.assertEquals("6x^1", test9.toString());
    Assert.assertEquals("-100x^100 +99x^99", test10.toString());
    Assert.assertEquals("-100", test11.toString());
    Assert.assertEquals("1", test12.toString());
    Assert.assertEquals("1x^1", test13.toString());
    Assert.assertEquals("-10x^1", test14.toString());
    Assert.assertEquals("0", test15.toString());
    Assert.assertEquals("0", test16.toString());
    Assert.assertEquals("0", test20.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPolynomialIllegalArgument1() throws IllegalArgumentException {
    test5.addTerm(2, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPolynomialIllegalArgument3() throws IllegalArgumentException {
    test5.addTerm(0, -3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPolynomialIllegalArgument4() throws IllegalArgumentException {
    test5.addTerm(-2, -3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPolynomialIllegalArgument5() throws IllegalArgumentException {
    Polynomial test12 = new PolynomialImpl("-100x^-10");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPolynomialIllegalArgument6() throws IllegalArgumentException {
    Polynomial test12 = new PolynomialImpl("0x^-1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPolynomialIllegalArgument7() throws IllegalArgumentException {
    Polynomial test17 = new PolynomialImpl(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPolynomialIllegalArgument8() throws IllegalArgumentException {
    Polynomial test18 = new PolynomialImpl("4x^3++3x^2");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPolynomialIllegalArgument9() throws IllegalArgumentException {
    Polynomial test19 = new PolynomialImpl("+4x^3+3x^2");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPolynomialIllegalArgument10() throws IllegalArgumentException {
    Polynomial test22 = new PolynomialImpl("4x^3--3x^2+2x^8+1x^1");
  }


  @Test
  public void testRemoveTerm() {
    test1.removeTerm(1);
    Assert.assertEquals("4x^3 +3x^2 +1", test1.toString());

    test2.removeTerm(1);
    Assert.assertEquals("0", test2.toString());

    test3.removeTerm(2);
    Assert.assertEquals("6x^4 +7x^3 +9x^1", test3.toString());

    test4.removeTerm(8);
    Assert.assertEquals("0", test4.toString());

    test5.removeTerm(41);
    Assert.assertEquals("3x^8 +30x^4 +13x^3 +8x^2 +9x^1 +34", test5.toString());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testRemoveTermIllegal() throws IllegalArgumentException {
    test5.removeTerm(-2);
  }


  @Test
  public void testGetDegree() {
    Assert.assertEquals(3, test1.getDegree());
    Assert.assertEquals(1, test2.getDegree());
    Assert.assertEquals(4, test3.getDegree());
    Assert.assertEquals(8, test4.getDegree());
    Assert.assertEquals(41, test5.getDegree());
  }


  @Test
  public void testGetCoefficient() {
    Assert.assertEquals(3, test1.getCoefficient(2));
    Assert.assertEquals(4, test2.getCoefficient(1));
    Assert.assertEquals(7, test3.getCoefficient(3));
    Assert.assertEquals(-9, test4.getCoefficient(8));
    Assert.assertEquals(63, test5.getCoefficient(41));
    Assert.assertEquals(0, test5.getCoefficient(42));
  }


  @Test
  public void testEvaluate() {
    Assert.assertEquals(49, test1.evaluate(2), .01);
    Assert.assertEquals(8, test2.evaluate(2), .01);
    Assert.assertEquals(202, test3.evaluate(2), .01);
    Assert.assertEquals(-2304, test4.evaluate(2), .01);
    Assert.assertEquals(1.38538465101212E14, test5.evaluate(2), .01);
    Assert.assertEquals(34, test5.evaluate(0), .01);
    Assert.assertEquals(-23, test1.evaluate(-2), .01);
    Assert.assertEquals(-488, test2.evaluate(-122), .01);
    Assert.assertEquals(1.582116E7, test3.evaluate(40), .01);
    Assert.assertEquals(-59049, test4.evaluate(-3), .01);
    Assert.assertEquals(160, test5.evaluate(1), .01);
    Assert.assertEquals(0, test4.evaluate(0), .01);


  }

  @Test
  public void testAdd() {
    Assert.assertEquals("4x^3 +3x^2 +6x^1 +1",
            test1.add(test2).toString());
  }

  @Test
  public void testAdd2() {
    Assert.assertEquals("63x^41 +3x^8 +30x^4 +17x^3 +11x^2 +11x^1 +35",
            test1.add(test5).toString());
  }

  @Test
  public void testAdd3() {
    Assert.assertEquals("6x^4 +11x^3 +11x^2 +11x^1 +1",
            test1.add(test3).toString());
  }

  @Test
  public void testAdd4() {
    Assert.assertEquals("-100x^100 +99x^99 +3x^4 +3x^2 +4x^1 +7",
            test6.add(test10).toString());
  }

  @Test
  public void testAdd5() {
    Assert.assertEquals("63x^41 -29x^9 -42x^8 +30x^4 +13x^3 +8x^2 +9x^1 -16",
            test5.add(test8).toString());
  }

  @Test
  public void testAdd6() {
    Assert.assertEquals("-9x^8 +4x^1",
            test4.add(test2).toString());
  }

  @Test
  public void testAdd7() {
    Assert.assertEquals("-9x^8 +4x^1",
            test2.add(test4).toString());
  }


}
