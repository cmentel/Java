package cs5004.questionnaire;

/**
 * Class representing the ShortAnswer type question.
 */

public class ShortAnswer implements Question {
  private String prompt;
  private boolean required;
  private String answer;


  /**
   * Constructor for ShortAnswer question.
   *
   * @param shortAnswerPrompt The question prompt.
   * @param required          The boolean value representing the mandate of the question.
   * @throws IllegalArgumentException if prompt is blank or null.
   */
  public ShortAnswer(String shortAnswerPrompt, boolean required) throws IllegalArgumentException {
    if (shortAnswerPrompt == null || shortAnswerPrompt.equals("")) {
      throw new IllegalArgumentException("Prompt cannot be empty");
    }
    this.prompt = shortAnswerPrompt;
    this.required = required;
  }

  @Override
  public String getPrompt() {
    return this.prompt;
  }

  @Override
  public boolean isRequired() {
    return this.required;
  }

  @Override
  public String getType() {
    return "ShortAnswer";
  }

  @Override
  public String answer(String shortAnswerAnswer) throws IllegalArgumentException {

    if (shortAnswerAnswer == null || shortAnswerAnswer.equals("")) {
      throw new IllegalArgumentException("Answer should not be empty / null.");
    }

    if (QuestionPredicate.predType(this).equals("ShortAnswer")
            && shortAnswerAnswer.length() < 281
            && shortAnswerAnswer.length() > 0) {
      this.answer = shortAnswerAnswer;
      return this.answer;
    }

    throw new IllegalArgumentException("Question is type ShortAnswer");

  }

  @Override
  public String getAnswer() {
    if (this.answer == null) {
      return "";
    }
    return this.answer;
  }

  @Override
  public Question copy() {
    if (QuestionPredicate.predType(this).equals("ShortAnswer")) {
      ShortAnswer toBeReturned = new ShortAnswer(this.prompt, this.required);
      toBeReturned.answer = this.answer;
      return toBeReturned;
    }
    throw new IllegalArgumentException("Question is not of type ShortAnswer.");

  }
}
