/**
 * Creates the PolynomialImpl class to create polynomial terms.
 */

public class PolynomialImpl implements Polynomial {
  private NodeADT head;

  public PolynomialImpl() {
    this.head = new Empty();
  }

  /**
   * Parsing of a polynomial from string to the terms.
   *
   * @param polynomialToBeParsed the string polynomial to be constructed.
   */

  public PolynomialImpl(String polynomialToBeParsed) throws IllegalArgumentException {
    if (polynomialToBeParsed == null
            || polynomialToBeParsed.contains("++")
            || polynomialToBeParsed.contains("--")
            || polynomialToBeParsed.startsWith("+")) {
      throw new IllegalArgumentException("Not a valid polynomial");
    }

    if (polynomialToBeParsed.equals("")) {
      this.head = (new Empty());

    } else {
      int powwow = 0;
      int coeff = 0;
      this.head = new Empty();
      String[] terms = polynomialToBeParsed.split(" ");
      for (String term : terms) {
        String scoeff = term.split("x")[0];
        coeff = Integer.parseInt(scoeff);
        try {
          String spower = term.split("\\^")[1];
          powwow = Integer.parseInt(spower);
        } catch (Exception e) {
          powwow = 0;
        }
        addTerm(coeff, powwow);

      }
    }

  }


  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Exponent must not be negative");
    }
    head = head.addTerm(coefficient, power);
  }

  @Override
  public void removeTerm(int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Exponent must not be negative");
    }
    head = head.removeTerm(power);
  }

  @Override
  public int getDegree() {
    return head.getDegree();
  }

  @Override
  public int getCoefficient(int power) {
    if (power < 0) {
      throw new IllegalArgumentException("Exponent must not be negative");
    }
    return head.getCoefficient(power);
  }

  @Override
  public double evaluate(double decNum) {
    return head.evaluate(decNum);
  }


  @Override
  public Polynomial add(Polynomial polynomial) throws IllegalArgumentException {

    if (!(polynomial instanceof Polynomial)) {
      throw new IllegalArgumentException("Please try with type Polynomial");
    }

    PolynomialImpl newPoly = new PolynomialImpl();

    int maxPower = Math.max(this.getDegree(), polynomial.getDegree());
    for (int i = maxPower; i >= 0; i--) {
      if (this.getCoefficient(i) + polynomial.getCoefficient(i) != 0) {
        int newCoef = this.getCoefficient(i) + polynomial.getCoefficient(i);
        newPoly.addTerm(newCoef, i);
      }
    }
    return newPoly;
  }

  @Override
  public String toString() {
    return head.toString();
  }
}