/**
 * Creates the interface for the polynomials. Methods are __________________
 */

public interface Polynomial {


  /**
   * Takes a coefficient and a power, adds the resulting term to the polynomial. (This will enable
   * you to build a polynomial term-by-term.) It should throw an IllegalArgumentException if a
   * negative power is passed to it.
   *
   * @param coefficient the coefficient being used in the polynomial.
   * @param power       the power to which the spot in the polynomial will be used.
   * @throws IllegalArgumentException when a negative power is passed.
   */
  void addTerm(int coefficient, int power) throws IllegalArgumentException;


  /**
   * Takes a power and removes any and all terms in the polynomial with that power.
   *
   * @param power the power of the term to be removed.
   */
  void removeTerm(int power);


  /**
   * Returns the degree of polynomial.
   */
  int getDegree();


  /**
   * Takes a power and returns the coefficient for the term with that power.
   *
   * @param power the power of the term to be returned.
   */
  int getCoefficient(int power);


  /**
   * Takes a double-precision decimal number and returns a double-precision result.
   *
   * @param decNum the result of the polynomial where the variable is replaced by the decNum.
   */
  double evaluate(double decNum);


  /**
   * Takes another Polynomial object and returns the polynomial obtained by adding the two. If it is
   * a different class, the method may throw an IllegalArgumentException.
   *
   * @param polynomial the polynomial to be added to the current polynomial.
   */

  Polynomial add(Polynomial polynomial);


}