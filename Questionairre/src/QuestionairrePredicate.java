package cs5004.questionnaire;


/**
 * Predicate class that holds the predicate functions.
 */
public class QuestionairrePredicate {


  /**
   * Predicate to return if the question is required.
   *
   * @param questionnaire the questionnaire to be asked on the survey.
   */
  public void predRequired(Questionnaire questionnaire) {

    questionnaire.getRequiredQuestions();
  }


}
