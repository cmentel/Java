package cs5004.questionnaire;

/**
 * Class representing the Likert type question.
 */
public class Likert implements Question {

  private String prompt;
  private boolean required;
  private String answer;


  /**
   * Likert constructor that creates a likert scale question.
   *
   * @param likertPrompt the likertPrompt of the question to be created.
   * @param required     the status of the requirement of the question.
   * @throws IllegalArgumentException if prompt is blank or null.
   */
  public Likert(String likertPrompt, boolean required) throws IllegalArgumentException {
    if (likertPrompt == null || likertPrompt.equals("")) {
      throw new IllegalArgumentException("Prompt cannot be empty");
    }

    this.prompt = likertPrompt;
    this.required = required;


  }

  @Override
  public String getType() {
    return "Likert";
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
  public String answer(String likertAnswer) throws IllegalArgumentException {
    if (likertAnswer == null || likertAnswer.equals("")) {
      throw new IllegalArgumentException("Answer should not be empty / null.");
    }

    for (LikertResponseOption res : LikertResponseOption.values()) {
      String res_Text = res.getText();
      if (likertAnswer.equalsIgnoreCase(res_Text)) {
        this.answer = likertAnswer;
        return this.answer;
      }
    }
    throw new IllegalArgumentException("Question is not of type Likert");
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
    Likert toBeReturned = new Likert(this.prompt, this.required);
    toBeReturned.answer = this.answer;
    return toBeReturned;
  }
}