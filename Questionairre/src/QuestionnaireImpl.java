package cs5004.questionnaire;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * Questionnaire implementation class.
 */
public class QuestionnaireImpl implements Questionnaire {

  private List<QuestionAndID> questionList;

  public QuestionnaireImpl() {
    this.questionList = new ArrayList<>();
  }

  private static class QuestionAndID {
    String id;
    Question question;

    private QuestionAndID(String id, Question question) throws IllegalArgumentException {
      if (id == null || id.equals("") || question == null) {
        throw new IllegalArgumentException("ID cannot be blank / null");
      }
      this.id = id;
      this.question = question;

    }
  }


  @Override
  public String getIdentifier(Question q) throws NoSuchElementException {
    if (q == null) {
      throw new IllegalArgumentException("question is null.");
    }
    for (int i = 1; i <= questionList.size(); i++) {
      if (questionList.get(i - 1).question.equals(q)) {
        return questionList.get(i - 1).id;
      }
    }
    throw new NoSuchElementException("No identifier for given question");
  }

  @Override
  public void addQuestion(String identifier, Question q) throws IllegalArgumentException {
    if (identifier == null || q == null) {
      throw new IllegalArgumentException("Question cannot be null.");
    }
    if (identifier.equals("")) {
      throw new IllegalArgumentException("Question cannot be null.");
    }
    this.questionList.add(new QuestionAndID(identifier, q));
  }

  @Override
  public void removeQuestion(String identifier) {
    if (identifier == null || identifier.equals("")) {
      throw new IllegalArgumentException("Identifier is null");
    }
    for (int i = 1; i <= questionList.size(); i++) {
      if (getIdentifier(getQuestion(i)).equals(identifier)) {
        questionList.remove(i - 1);
      }
    }
  }

  @Override
  public Question getQuestion(int num) throws IndexOutOfBoundsException {
    if (num <= 0) {
      throw new IllegalArgumentException("Number is too low.");
    }
    return (questionList.get(num - 1).question);
  }

  @Override
  public Question getQuestion(String identifier) throws NoSuchElementException {
    if (identifier == null || identifier.equals("")) {
      throw new IllegalArgumentException("Identifier is null / empty.");
    }
    for (int i = 1; i <= questionList.size(); i++) {
      if (getIdentifier(getQuestion(i)).equals(identifier)) {
        return getQuestion(i);
      }
    }
    throw new NoSuchElementException("No question with that identifier");
  }

  @Override
  public List<Question> getRequiredQuestions() {
    List<Question> requiredQuestions = new ArrayList<>();
    for (int i = 1; i <= questionList.size(); i++) {
      if (QuestionPredicate.predRequired(getQuestion(i))) {
        requiredQuestions.add(getQuestion(i));
      }
    }
    return requiredQuestions;
  }

  @Override
  public List<Question> getOptionalQuestions() {
    List<Question> optionalQuestions = new ArrayList<>();
    for (int i = 1; i <= questionList.size(); i++) {
      if (!QuestionPredicate.predRequired(getQuestion(i))) {
        optionalQuestions.add(getQuestion(i));
      }
    }
    return optionalQuestions;
  }

  @Override
  public boolean isComplete() {
    for (int i = 1; i <= questionList.size(); i++) {
      if (!QuestionPredicate.predAnswered(getQuestion(i))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public List<String> getResponses() {
    List<String> responses = new ArrayList<>();
    for (int i = 1; i <= questionList.size(); i++) {
      if (QuestionPredicate.predAnswered(getQuestion(i))
              && getQuestion(i).getAnswer() != null) {
        responses.add(getQuestion(i).getAnswer());
      } else {
        responses.add("");
      }
    }
    return responses;
  }

  @Override
  public Questionnaire filter(Predicate<Question> pq) throws IllegalArgumentException {
    if (pq == null) {
      throw new IllegalArgumentException("pq is null");
    }
    Questionnaire toBeReturnedQuestionairre = new QuestionnaireImpl();
    for (int i = 1; i <= questionList.size(); i++) {
      if (pq.test(getQuestion(i))) {
        toBeReturnedQuestionairre.addQuestion(getIdentifier(getQuestion(i)), getQuestion(i).copy());
      }
    }
    return toBeReturnedQuestionairre;

  }

  @Override
  public void sort(Comparator<Question> comp) throws IllegalArgumentException {
    if (comp == null) {
      throw new IllegalArgumentException("Comparator is null");
    }


    questionList.sort((o1, o2) -> comp.compare(o1.question, o2.question));

  }

  @Override
  public <R> R fold(BiFunction<Question, R, R> bf, R seed) throws IllegalArgumentException{

    if (bf == null || seed == null) {
      throw new IllegalArgumentException("bf / seed are null");
    }

    for (int i = 1; i <= questionList.size(); i++) {
      seed = bf.apply(getQuestion(i), seed);
    }
    return seed;

  }


  @Override
  public String toString() {
    if (this.questionList == null || this.questionList.isEmpty()) {
      return "";
    }
    StringBuilder printable = new StringBuilder();
    int questionSpot = 1;
    for (int i = 1; i <= questionList.size(); i++) {
      if (questionSpot < questionList.size()) {
        printable.append("Question: ").append(getQuestion(i).getPrompt());
        printable.append("\n\n");
        printable.append("Answer: ").append(getQuestion(i).getAnswer());
        printable.append("\n\n");
        questionSpot++;
      } else {
        printable.append("Question: ").append(getQuestion(i).getPrompt());
        printable.append("\n\n");
        printable.append("Answer: ").append(getQuestion(i).getAnswer());
      }
    }
    return printable.toString();
  }


}