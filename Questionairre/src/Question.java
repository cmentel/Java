package cs5004.questionnaire;

/**
 * Represents the questions and the methods included in the questions.
 */
public interface Question {


  /**
   * Method that gets the prompt of the question.
   *
   * @return the String that represents the question prompt.
   */
  String getPrompt();


  /**
   * Returns the status of whether the question is required or not.
   *
   * @return the boolean value of the mandate on the question.
   */
  boolean isRequired();


  /**
   * Returns a string that represents the type of question.
   *
   * @return the string that represents the type of question.
   */
  String getType();


  /**
   * Allows user to enter answer as a string.
   *
   * @param input the answer that will be entered as a string.
   */
  String answer(String input);


  /**
   * Returns the answer to the question, or empty string if no answer.
   *
   * @return the
   */
  String getAnswer();


  /**
   * Returns a copy of the question including all its data.
   *
   * @return
   */
  Question copy();


}
