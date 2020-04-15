package model;
import java.awt.Color;
import java.lang.reflect.Field;
import java.util.Optional;

/**
 * Class to retrieve the name of the color because Java provided Color class has few non-static
 * fields and none hold the String name of the color.
 */
public class ColorName {

  /**
   * Method to return the name of the color as compared to the object ID / Address.
   *
   * @param colorToRetrieveName the color object being passed in.
   * @return the string value name of the color.
   */
  public static Object colorName(Color colorToRetrieveName) {
    for (Field f : Color.class.getDeclaredFields()) {
      // Only want fields of type Color
      if (f.getType().equals(Color.class)) {
        try {
          if (f.get(null).equals(colorToRetrieveName)) {
            String str = Optional.of(f.getName().toLowerCase()).get();
            return capitalize(str);
          }
        } catch (IllegalArgumentException | IllegalAccessException e) {
          // Shouldn't not be thrown, but just in case print its stacktrace.
          e.printStackTrace();
        }
      }
    }
    return Optional.empty();
  }


  /**
   * Helper method to capitalize the name of the color.
   *
   * @param stringToBeProper the color string to be capitalized.
   * @return the proper form of the color name with first letter capitalized.
   */
  public static String capitalize(String stringToBeProper) {
    if (stringToBeProper == null || stringToBeProper.isEmpty()) {
      return stringToBeProper;
    }

    return stringToBeProper.substring(0, 1).toUpperCase() + stringToBeProper.substring(1);
  }

}