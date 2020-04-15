/**
 * Class that represents the term node in our polynomial.
 */

public class Term implements NodeADT {
  private int power;
  private int coefficient;
  private NodeADT rest;

  /**
   * Term constructor that takes a coefficient power and the rest of the list.
   *
   * @param coefficient the integer to represent the coefficient for the term.
   * @param power       the integer power to represent the power for the term.
   * @param rest        the next element in the list.
   */
  public Term(int coefficient, int power, NodeADT rest) {
    this.coefficient = coefficient;
    this.power = power;
    this.rest = rest;
  }

  @Override
  public NodeADT addTerm(int coefficient, int power) {

    if (power == this.power) {
      if (coefficient + this.coefficient == 0) {
        return this.rest;
      }
      this.coefficient = coefficient + this.coefficient;
      return this;
    } else if (power > this.power) {
      return new Term(coefficient, power, this);
    }


    // if added term power is less than what is existing
    else {
      this.rest = rest.addTerm(coefficient, power);
      return this;
    }
  }


  @Override
  public NodeADT removeTerm(int power) {
    if (this.power == power) {
      return this.rest;
    }
    this.rest = this.rest.removeTerm(power);
    return this;
  }


  @Override
  // because our addTerm adds a term in order we can just return the head.
  public int getDegree() {
    return this.power;
  }


  @Override
  public int getCoefficient(int power) {
    if (power > this.power) {
      return 0;
    }
    if (this.power == power) {
      return this.coefficient;
    }
    return this.rest.getCoefficient(power);
  }


  @Override
  public double evaluate(double decNum) {
    return ((this.coefficient) * (Math.pow(decNum, this.power))) + rest.evaluate(decNum);
  }


  @Override
  public Polynomial add(Polynomial polynomial) throws IllegalArgumentException {
    if (!(polynomial instanceof Polynomial)) {
      throw new IllegalArgumentException("Please try with type Polynomial");
    }
    return null;
  }


  @Override
  public String toString() {
    if (this.rest.equals(new Empty()) && this.coefficient != 0) {
      return "";
    }

    if (this.rest.equals(new Empty())) {
      return "0";
    }

    if (this.power == 0) {
      return "" + this.coefficient;
    }

    if (this.coefficient == 0
            && this.getCoefficient(rest.getDegree()) == 0
            && this.getCoefficient(this.rest.getDegree()) < 0
            && this.rest.getCoefficient(this.rest.getDegree() - 1) < 0) {
      return this.rest.toString();
    }

    if (this.coefficient == 0) {
      return this.rest.toString();
    }


    if (this.getCoefficient(this.rest.getDegree()) > 0) {
      return this.coefficient + "x^" + this.power + " +" + this.rest.toString();
    }

    if ((this.getCoefficient(this.rest.getDegree())) == 0 && !(this.rest.equals(new Empty()))) {
      return this.coefficient + "x^" + this.power;
    }

    return this.coefficient + "x^" + this.power + " " + this.rest.toString();


  }
}
