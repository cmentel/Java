/**
 * NodeADT interface to represent a term in the polynomial.
 */

public interface NodeADT {

  /**
   * Takes a coefficient and a power and adds the resulting term to the polynomial. Throws an
   * IllegalArgumentException if a negative power is passed to it.
   *
   * @param coefficient the coefficient being used in the polynomial.
   * @param power       the power to which the spot in the polynomial will be used.
   * @return a NodeADT with the coefficient and power in proper place.
   * @throws IllegalArgumentException when a negative power is passed to it.
   */
  NodeADT addTerm(int coefficient, int power) throws IllegalArgumentException;


  /**
   * Takes a power and removes any and all terms in the polynomial with that power.
   *
   * @param power the power of the term to be removed.
   * @return the polynomial with the term removed.
   */
  NodeADT removeTerm(int power);


  /**
   * Returns the degree of polynomial.
   *
   * @return an int representing the degree of the polynomial.
   */
  int getDegree();


  /**
   * Takes a power and returns the coefficient for the term with that power.
   *
   * @param power the power for which the coefficient will be returned.
   * @return an integer representing the coefficient.
   */
  int getCoefficient(int power);


  /**
   * Takes a double-precision decimal number and returns a double-precision result.
   *
   * @param decNum the number to be used for 'x' so the polynomial can be evaluated.
   * @return the result of the polynomial where the variable is replaced by the decNum.
   */
  double evaluate(double decNum);


  /**
   * Takes another Polynomial object and returns the polynomial obtained by adding the two.
   *
   * @param polynomial the polynomial that will be added to the polynomial on which it will be
   *                   called.
   * @return The new polynomial obtained by adding with the polynomial being passed in.
   * @throws IllegalArgumentException if the polynomial is a different class being passed in.
   */
  Polynomial add(Polynomial polynomial) throws IllegalArgumentException;

}