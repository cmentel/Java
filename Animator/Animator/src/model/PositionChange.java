package model;
/**
 * This class represents a position change of a shape.
 */
public class PositionChange implements Change {

  private double referenceXCoordinate;
  private double referenceYCoordinate;
  private double toXCoordinate;
  private double toYCoordinate;
  private double moveStart;
  private double moveStop;

  /**
   * Constructor for Position Change that takes a reference point, x to position change to , y to
   * position change to the time at which the position change should start and the time at which the
   * position change should stop.
   *
   * @param reference     The reference point of the shape to position change.
   * @param toXCoordinate The x coordinate to which the shape will position change.
   * @param toYCoordinate The y coordinate to which the shape will position change.
   * @param moveStart     The time at which the position change will begin to move.
   * @param moveStop      The time at which the position change will conclude.
   * @throws IllegalArgumentException for many invalid inputs see respective exception comments.
   */
  public PositionChange(Point2D reference, double toXCoordinate, double toYCoordinate,
                        double moveStart, double moveStop) throws IllegalArgumentException {

    if (reference == null) {
      throw new IllegalArgumentException("Reference is null.");
    }


    this.referenceXCoordinate = reference.getX();
    this.referenceYCoordinate = reference.getY();
    this.toXCoordinate = toXCoordinate;
    this.toYCoordinate = toYCoordinate;


    if (moveStart < 0 || moveStop < 0) {
      throw new IllegalArgumentException("Move time cannot be negative.");
    }

    if (moveStop < moveStart) {
      throw new IllegalArgumentException("Move stop time cannnot occur before start time.");
    }
    if (moveStart == moveStop) {
      throw new IllegalArgumentException("Move start and stop time are the same.");
    }

    this.moveStart = moveStart;
    this.moveStop = moveStop;
  }

  /**
   * Getter for the point of departure.
   *
   * @return the point from which the shape will be moving.
   */
  public Point2D getFrom() {
    return new Point2D(this.referenceXCoordinate, this.referenceYCoordinate);
  }

  /**
   * Getter for the point of arrival.
   *
   * @return the point to which the shape will be arriving.
   */
  public Point2D getTo() {
    return new Point2D(this.toXCoordinate, this.toYCoordinate);
  }

  /**
   * Getter for the time at which the shape will move.
   *
   * @return the time the shape begins to move.
   */
  public double getStartTime() {
    return this.moveStart;
  }

  /**
   * Getter for the time at which the shape will conclude changing position.
   *
   * @return the time at which the shape stops changing.
   */
  public double getEndTime() {
    return this.moveStop;
  }


  @Override
  public String getType() {
    return "Position change";
  }
}
