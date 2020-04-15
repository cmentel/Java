import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import cs5004.questionnaire.Likert;
import cs5004.questionnaire.Question;
import cs5004.questionnaire.QuestionPredicate;
import cs5004.questionnaire.Questionnaire;
import cs5004.questionnaire.QuestionnaireImpl;
import cs5004.questionnaire.ShortAnswer;
import cs5004.questionnaire.YesNo;

/**
 * Class to test the Questionnaire.
 */

public class QuestionnaireTest {

  Questionnaire questionSet1 = new QuestionnaireImpl();
  Questionnaire questionSet2 = new QuestionnaireImpl();
  Questionnaire questionSet3 = new QuestionnaireImpl();
  Questionnaire questionSet4 = new QuestionnaireImpl();
  Questionnaire questionSet5 = new QuestionnaireImpl();
  Questionnaire questionSet6 = new QuestionnaireImpl();

  // incomplete answered questionnaire.
  Questionnaire questionSet8 = new QuestionnaireImpl();
  Questionnaire questionSet9 = new QuestionnaireImpl();

  // empty questionnaire.
  Questionnaire questionSet7 = new QuestionnaireImpl();


  Question questionA = new YesNo("Are you happy with your job?", true);
  Question questionB = new YesNo("Do you think you're smart?", false);
  Question questionC = new YesNo("Are you older than 21?", true);
  Question questionD = new Likert("I am proud of who I am.", false);
  Question questionE = new Likert("Time is of the essence.", true);
  Question questionF = new Likert("Tis the season.", false);
  Question questionG = new Likert("Roses are red.", true);
  Question questionH = new ShortAnswer("What are you most proud of?", false);
  Question questionI = new ShortAnswer("Who is your role model and why?", true);
  Question questionJ = new ShortAnswer("What was your childhood like?", false);
  Question questionL = new ShortAnswer("Why are you using this survey?", true);
  Question questionM = new ShortAnswer("Describe your favorite memory.", false);

  // unanswered questions
  Question questionN = new YesNo("Are you getting enough sleep?", false);
  Question questionO = new Likert("I am getting enough sleep.", true);
  Question questionP = new ShortAnswer("Why are you still here?", true);


  @Before
  public void answerQuestion() {
    questionA.answer("Yes");
    questionB.answer("No");
    questionC.answer("Yes");
    questionD.answer("Agree");
    questionE.answer("Strongly Disagree");
    questionF.answer("Neither Agree Nor Disagree");
    questionG.answer("Strongly Agree");
    questionH.answer("My education and sense of humour.");
    questionI.answer("My dad because he puts others before himself.");
    questionJ.answer("Normal and fulfilling.");
    questionL.answer("To complete my homework assignment.");
    questionM.answer("Being on my dad's shoulders watching fireworks on the 4th of July");
  }

  @Before
  public void addQuestionsTest() {
    questionSet1.addQuestion("QA", questionA);
    questionSet1.addQuestion("QB", questionB);
    questionSet1.addQuestion("QC", questionC);
    questionSet1.addQuestion("QD", questionD);


    questionSet2.addQuestion("QE", questionE);
    questionSet2.addQuestion("QF", questionF);
    questionSet2.addQuestion("QG", questionG);
    questionSet2.addQuestion("QH", questionH);


    questionSet3.addQuestion("QA", questionE);
    questionSet3.addQuestion("QE", questionF);
    questionSet3.addQuestion("QG", questionG);

    questionSet4.addQuestion("QH", questionH);
    questionSet4.addQuestion("QI", questionI);
    questionSet4.addQuestion("QJ", questionJ);
    questionSet4.addQuestion("QL", questionL);
    questionSet4.addQuestion("QM", questionM);
    questionSet4.addQuestion("QB", questionB);

    // one required question
    questionSet5.addQuestion("QM", questionM);

    // one optional question
    questionSet6.addQuestion("QL", questionL);

    // unanswered questionsets
    questionSet8.addQuestion("QN", questionN);

    questionSet9.addQuestion("QN", questionN);
    questionSet9.addQuestion("QO", questionO);
    questionSet9.addQuestion("QP", questionP);

  }


  @Test(expected = Test.None.class)
  public void removeQuestionTest() {

    questionSet1.removeQuestion("QA");

    questionSet2.removeQuestion("QE");

    questionSet3.removeQuestion("QI");

    questionSet4.removeQuestion("QL");

  }


  @Test
  public void getQuestionTest() {
    Assert.assertEquals("Are you happy with your job?",
            (questionSet1.getQuestion(1).getPrompt()));
    Assert.assertEquals("Do you think you're smart?",
            (questionSet1.getQuestion(2).getPrompt()));
    Assert.assertEquals("Are you older than 21?",
            (questionSet1.getQuestion(3).getPrompt()));
    Assert.assertEquals("I am proud of who I am.",
            (questionSet1.getQuestion(4).getPrompt()));

    Assert.assertEquals("Time is of the essence.",
            (questionSet2.getQuestion(1).getPrompt()));
    Assert.assertEquals("Tis the season.",
            (questionSet2.getQuestion(2).getPrompt()));
    Assert.assertEquals("Roses are red.",
            (questionSet2.getQuestion(3).getPrompt()));
    Assert.assertEquals("What are you most proud of?",
            (questionSet2.getQuestion(4).getPrompt()));


    Assert.assertEquals("Time is of the essence.",
            (questionSet3.getQuestion(1).getPrompt()));
    Assert.assertEquals("Tis the season.",
            (questionSet3.getQuestion(2).getPrompt()));
    Assert.assertEquals("Roses are red.",
            (questionSet3.getQuestion(3).getPrompt()));


    Assert.assertEquals("What are you most proud of?",
            (questionSet4.getQuestion(1).getPrompt()));
    Assert.assertEquals("Who is your role model and why?",
            (questionSet4.getQuestion(2).getPrompt()));
    Assert.assertEquals("What was your childhood like?",
            (questionSet4.getQuestion(3).getPrompt()));
    Assert.assertEquals("Why are you using this survey?",
            (questionSet4.getQuestion(4).getPrompt()));
    Assert.assertEquals("Describe your favorite memory.",
            (questionSet4.getQuestion(5).getPrompt()));
    Assert.assertEquals("Do you think you're smart?",
            (questionSet4.getQuestion(6).getPrompt()));

  }

  @Test
  public void getIdentifierTest() {
    Assert.assertEquals("QA",
            (questionSet1.getIdentifier(questionA)));
    Assert.assertEquals("QB",
            (questionSet1.getIdentifier(questionB)));
    Assert.assertEquals("QE",
            (questionSet2.getIdentifier(questionE)));
    Assert.assertEquals("QG",
            (questionSet3.getIdentifier(questionG)));
    Assert.assertEquals("QI",
            (questionSet4.getIdentifier(questionI)));

  }


  @Test
  public void getRequiredQuestionsTest() {
    List<Question> testReqQ1 = new ArrayList<>();
    testReqQ1.add(questionA);
    testReqQ1.add(questionC);
    Assert.assertEquals(testReqQ1, (questionSet1.getRequiredQuestions()));

    List<Question> testReqQ2 = new ArrayList<>();
    testReqQ2.add(questionE);
    testReqQ2.add(questionG);
    Assert.assertEquals(testReqQ2, (questionSet2.getRequiredQuestions()));

    // no required questions.
    List<Question> testReqQ3 = new ArrayList<>();
    Assert.assertEquals(testReqQ3, (questionSet5.getRequiredQuestions()));
  }

  @Test
  public void getOptionalQuestionsTest() {
    List<Question> testReqQ4 = new ArrayList<>();
    testReqQ4.add(questionB);
    testReqQ4.add(questionD);
    Assert.assertEquals(testReqQ4, (questionSet1.getOptionalQuestions()));

    List<Question> testReqQ2 = new ArrayList<>();
    testReqQ2.add(questionF);
    testReqQ2.add(questionH);
    Assert.assertEquals(testReqQ2, (questionSet2.getOptionalQuestions()));

    // no optional questions.
    List<Question> testReqQ3 = new ArrayList<>();
    Assert.assertEquals(testReqQ3, (questionSet6.getOptionalQuestions()));
  }

  @Test
  public void isCompleteTest() {

    // complete.
    Assert.assertTrue(questionSet1.isComplete());
    Assert.assertTrue(questionSet2.isComplete());


    // incomplete
    Assert.assertFalse(questionSet8.isComplete());
    Assert.assertFalse(questionSet9.isComplete());

  }


  @Test
  public void getResponsesTest() {
    List<String> testResp1 = new ArrayList<>();
    testResp1.add(questionA.getAnswer());
    testResp1.add(questionB.getAnswer());
    testResp1.add(questionC.getAnswer());
    testResp1.add(questionD.getAnswer());
    Assert.assertEquals(testResp1, questionSet1.getResponses());

    // empty questionnaire
    List<String> testResp2 = new ArrayList<>();
    Assert.assertEquals(testResp2, questionSet7.getResponses());


  }

  @Test
  public void filterTest() {


    Assert.assertEquals("Question: Are you happy with your job?\n"
            + "\n"
            + "Answer: Yes\n"
            + "\n"
            + "Question: Do you think you're smart?\n"
            + "\n"
            + "Answer: No\n"
            + "\n"
            + "Question: Are you older than 21?\n"
            + "\n"
            + "Answer: Yes\n"
            + "\n"
            + "Question: I am proud of who I am.\n"
            + "\n"
            + "Answer: Agree", questionSet1.filter(QuestionPredicate::predAnswered)
            .toString());

    Assert.assertEquals("Question: Are you happy with your job?\n"
            + "\n"
            + "Answer: Yes\n"
            + "\n"
            + "Question: Are you older than 21?\n"
            + "\n"
            + "Answer: Yes", questionSet1.filter(QuestionPredicate::predRequired)
            .toString());

    Assert.assertEquals("Question: Time is of the essence.\n"
                    + "\n"
                    + "Answer: Strongly Disagree\n"
                    + "\n"
                    + "Question: Tis the season.\n"
                    + "\n"
                    + "Answer: Neither Agree Nor Disagree\n"
                    + "\n"
                    + "Question: Roses are red.\n"
                    + "\n"
                    + "Answer: Strongly Agree\n"
                    + "\n"
                    + "Question: What are you most proud of?\n"
                    + "\n"
                    + "Answer: My education and sense of humour.",
            questionSet2.filter(QuestionPredicate::predAnswered)
                    .toString());

    Assert.assertEquals("Question: Time is of the essence.\n"
            + "\n"
            + "Answer: Strongly Disagree\n"
            + "\n"
            + "Question: Roses are red.\n"
            + "\n"
            + "Answer: Strongly Agree", questionSet2.filter(QuestionPredicate::predRequired)
            .toString());

    Assert.assertEquals("", questionSet2.filter(QuestionPredicate::predHasZ)
            .toString());

    Assert.assertEquals("Question: Time is of the essence.\n"
            + "\n"
            + "Answer: Strongly Disagree\n"
            + "\n"
            + "Question: Tis the season.\n"
            + "\n"
            + "Answer: Neither Agree Nor Disagree\n"
            + "\n"
            + "Question: Roses are red.\n"
            + "\n"
            + "Answer: Strongly Agree", questionSet3.filter(QuestionPredicate::predAnswered)
            .toString());

    Assert.assertEquals("Question: Time is of the essence.\n"
            + "\n"
            + "Answer: Strongly Disagree\n"
            + "\n"
            + "Question: Roses are red.\n"
            + "\n"
            + "Answer: Strongly Agree", questionSet3.filter(QuestionPredicate::predRequired)
            .toString());

    Assert.assertEquals("", questionSet3.filter(QuestionPredicate::predHasZ)
            .toString());


  }

  @Test
  public void sortTest() {

    questionSet1.sort(Comparator.comparing(Question::getPrompt));
    Assert.assertEquals("Question: Are you happy with your job?\n"
            + "\n"
            + "Answer: Yes\n"
            + "\n"
            + "Question: Are you older than 21?\n"
            + "\n"
            + "Answer: Yes\n"
            + "\n"
            + "Question: Do you think you're smart?\n"
            + "\n"
            + "Answer: No\n"
            + "\n"
            + "Question: I am proud of who I am.\n"
            + "\n"
            + "Answer: Agree", questionSet1.toString());

  }

  @Test
  public void foldTest() {

    questionSet1.fold((q, b) -> b + q.getPrompt(), "");
    Assert.assertEquals("Question: Are you happy with your job?\n"
            + "\n"
            + "Answer: Yes\n"
            + "\n"
            + "Question: Do you think you're smart?\n"
            + "\n"
            + "Answer: No\n"
            + "\n"
            + "Question: Are you older than 21?\n"
            + "\n"
            + "Answer: Yes\n"
            + "\n"
            + "Question: I am proud of who I am.\n"
            + "\n"
            + "Answer: Agree", questionSet1.toString());

  }

  @Test
  public void toStringTest() {
    // Empty questionnaire
    Assert.assertEquals("", questionSet7.toString());


    Assert.assertEquals("Question: Are you happy with your job?\n"
            + "\n"
            + "Answer: Yes\n"
            + "\n"
            + "Question: Do you think you're smart?\n"
            + "\n"
            + "Answer: No\n"
            + "\n"
            + "Question: Are you older than 21?\n"
            + "\n"
            + "Answer: Yes\n"
            + "\n"
            + "Question: I am proud of who I am.\n"
            + "\n"
            + "Answer: Agree", questionSet1.toString());
    Assert.assertEquals("Question: Time is of the essence.\n"
            + "\n"
            + "Answer: Strongly Disagree\n"
            + "\n"
            + "Question: Tis the season.\n"
            + "\n"
            + "Answer: Neither Agree Nor Disagree\n"
            + "\n"
            + "Question: Roses are red.\n"
            + "\n"
            + "Answer: Strongly Agree\n"
            + "\n"
            + "Question: What are you most proud of?\n"
            + "\n"
            + "Answer: My education and sense of humour.", questionSet2.toString());


  }


}
