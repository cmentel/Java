package cs5004.marblesolitaire.model;

/**
 * Enum class to represent the options available at each spot on the board.
 */
public enum Spot {
  EMPTY("_"),
  MARBLE("O"),
  NA(" ");

  private final String txt;


  /**
   * Spot enum retrieving data method.
   *
   * @param txt The text of the spot.
   */
  Spot(String txt) {
    if (txt == null) {
      throw new IllegalArgumentException("txt is null");
    }

    this.txt = txt;
  }


  String getText() {
    return txt;
  }

}
