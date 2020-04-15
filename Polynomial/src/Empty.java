/**
 * Class that represents the empty node indicating the end of list.
 */

public class Empty implements NodeADT {

  public Empty() {
    // intentionally left empty for empty node.
  }


  @Override
  public NodeADT addTerm(int coefficient, int power) {
    return new Term(coefficient, power, new Empty());
  }

  @Override
  public NodeADT removeTerm(int power) {
    return this;
  }

  @Override
  public int getDegree() {
    return 0;
  }

  @Override
  public int getCoefficient(int power) {
    return 0;
  }

  @Override
  public double evaluate(double decNum) {
    return 0;
  }

  @Override
  public Polynomial add(Polynomial polynomial) {
    return polynomial;
  }

  @Override
  public String toString() {
    return "0";
  }
}
