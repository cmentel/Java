package cs5004.questionnaire;

/**
 * Class representing a YesNo question.
 */
public class YesNo implements Question {
  private String prompt;
  private boolean required;
  private String answer;


  /**
   * Constructor for YesNo question.
   *
   * @param yesNoPrompt the yesNoPrompt of the question passed as a string.
   * @param required    the boolean value to represent if the question is required to be answered.
   * @throws IllegalArgumentException if prompt is blank or null.
   */
  public YesNo(String yesNoPrompt, boolean required) throws IllegalArgumentException {
    if (yesNoPrompt == null || yesNoPrompt.equals("")) {
      throw new IllegalArgumentException("Prompt cannot be empty");
    }
    this.prompt = yesNoPrompt;
    this.required = required;
    this.answer = "";
  }


  @Override
  public String getType() {
    return "YesNo";
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
  public String answer(String yesNoAnswer) throws IllegalArgumentException {
    if (yesNoAnswer == null || yesNoAnswer.equals("")) {
      throw new IllegalArgumentException("Answer cannot be null or empty");
    }

    if (yesNoAnswer.equalsIgnoreCase("yes")
            || yesNoAnswer.equalsIgnoreCase("no")) {
      this.answer = yesNoAnswer;
      return this.answer;
    }

    throw new IllegalArgumentException("Answer must be Yes or No");
  }


  @Override
  public String getAnswer() {
    if (this.answer == null) {
      return "";
    }
    return this.answer;
  }

  @Override
  public Question copy() throws IllegalArgumentException {
    if (QuestionPredicate.predType(this).equals("YesNo")) {
      YesNo toBeReturned = new YesNo(this.prompt, this.required);
      toBeReturned.answer = this.answer;
      return toBeReturned;
    }
    throw new IllegalArgumentException("Question is not of type YesNo");

  }

}
