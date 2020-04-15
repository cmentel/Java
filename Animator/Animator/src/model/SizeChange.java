package model;
/**
 * This class represents the resize of a shape.
 */
public class SizeChange implements Change {

  private double factor;
  private double sizeStart;
  private double sizeStop;

  /**
   * Constructor for the SizeChange class that takes three parameters.
   *
   * @param factor    The factor that changes the shape of the shape.
   * @param sizeStart The time at which the size change will begin.
   * @param sizeStop  The time at which the size change will conclude.
   * @throws IllegalArgumentException if construction is invalid.
   */
  public SizeChange(double factor, double sizeStart, double sizeStop)
          throws IllegalArgumentException {
    if (factor == 0 || sizeStop < sizeStart) {
      throw new IllegalArgumentException("Invalid construction.");
    }
    this.factor = factor;
    this.sizeStart = sizeStart;
    this.sizeStop = sizeStop;
  }

  /**
   * Getter for the factor of the size change.
   *
   * @return the factor of the size change.
   */
  public double getFactor() {
    return this.factor;
  }

  /**
   * Getter for the time at which the size change will occur.
   *
   * @return the time at which the chance commences.
   */
  public double getStartTime() {
    return this.sizeStart;
  }

  /**
   * Getter for the time at which the size change will end.
   *
   * @return the time at which the size change concludes.
   */
  public double getEndTime() {
    return this.sizeStop;
  }

  @Override
  public String getType() {
    return "Size change";
  }
}
