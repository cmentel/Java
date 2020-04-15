/**
 * Main class to run and test Rectangle Class and respective methods.
 */

public class Main {

  /**
   * Main method to run the program.
   *
   * @param args contains the supplied command-line arguments as an array of String objects.
   */
  public static void main(String[] args) {

    Rectangle test1 = new Rectangle(1, 1, 6, 2);
    Rectangle test2 = new Rectangle(2, 3, 2, 2);
    Rectangle test3 = new Rectangle(-2, 3, 1, 4);
    Rectangle test4 = new Rectangle(3, 7, 2, 5);
    Rectangle test5 = new Rectangle(-3, -5, 3, 8);
    Rectangle test6 = new Rectangle(1, 2, 1, 1);
    Rectangle test7 = new Rectangle(4, -5, 13, 1);


    Rectangle test8 = new Rectangle(0, 0, 20, 10);
    Rectangle test9 = new Rectangle(-22, -20, 23, 21);

    System.out.println(new Rectangle(-1193959466, -1139614796, 7875, 25989));
    Rectangle test11 = new Rectangle(-50, -48, 5, 5);

    System.out.println(test8.overlap(test9));
    System.out.println(test8.intersect(test9));


  }
}
