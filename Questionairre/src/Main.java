package cs5004.questionnaire;


/**
 * Main class to run and test Sentence / Word Interface and respective methods.
 */

public class Main {

  /**
   * Main method to run the program.
   *
   * @param args contains the supplied command-line arguments as an array of String objects.
   */
  public static void main(String[] args) {

    YesNo q1 = new YesNo("Are you tired of OOD?", true);
    YesNo q2 = new YesNo("Are you 21?", true);
    Likert q3 = new Likert("OOD is treating me right.", false);
    ShortAnswer q4 = new ShortAnswer("What're your fav thing about yourself?", false);
    ShortAnswer q5 = new ShortAnswer("test", false);


    q1.answer("Yes");
    q2.answer("No");
    q3.answer("Neither Agree Nor Disagree");
    q4.answer(" ");
    q5.answer("");
    System.out.println(q4.getAnswer());
    System.out.println(q5.getAnswer());


    Questionnaire questionnaire1 = new QuestionnaireImpl();
    questionnaire1.addQuestion("Question1", q1);
    questionnaire1.addQuestion("Question2", q2);
    questionnaire1.addQuestion("Question3", q3);
    questionnaire1.addQuestion("Question4", q4);


  }
}