package cs5004.questionnaire;


/**
 * Predicate class that holds the predicate functions.
 */
public class QuestionPredicate {


  /**
   * Predicate to return if the question has the letter z.
   *
   * @param question the question to be asked on the survey.
   * @return the boolean value of the question to be answered.
   */
  public static boolean predHasZ(Question question) {
    return (question.toString().contains("z")
            || question.toString().contains("Z"));
  }

  /**
   * Predicate to return if the question is required.
   *
   * @param question the question to be asked on the survey.
   * @return the boolean value of the question to be answered.
   */
  public static boolean predRequired(Question question) {
    return question.isRequired();
  }


  /**
   * Predicate to return if a question has been answered.
   *
   * @param question the question to be questioned.
   * @return the boolean value if the question has been answered.
   */
  public static boolean predAnswered(Question question) {
    return !question.getAnswer().equals("")
            && question.getAnswer() != null
            && !question.getAnswer().equals(" ");
  }


  /**
   * Predicate to return the type of question..
   *
   * @param question the question to be put assessed.
   * @return the string value representing the question type.
   */
  public static String predType(Question question) {
    if (question.getType().equals("YesNo")) {
      return "YesNo";
    }
    if (question.getType().equals("ShortAnswer")) {
      return "ShortAnswer";
    }
    if (question.getType().equals("Likert")) {
      return "Likert";
    }
    return "";
  }


}
