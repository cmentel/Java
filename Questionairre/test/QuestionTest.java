import org.junit.Test;
import org.junit.Assert;

import cs5004.questionnaire.Likert;
import cs5004.questionnaire.ShortAnswer;
import cs5004.questionnaire.YesNo;

/**
 * Test for Questions.
 */

public class QuestionTest {

  YesNo test1 = new YesNo("Are you happy?", true);
  YesNo test2 = new YesNo("Do you want to be friends?", false);
  YesNo test3 = new YesNo("Do you like pasta?", false);
  YesNo test4 = new YesNo("Are you ok?", false);
  Likert test5 = new Likert("OOD has been fairly taught and graded.", true);
  Likert test6 = new Likert("Do you agree?", false);
  Likert test7 = new Likert("I am angry.", true);
  Likert test8 = new Likert("OOD makes me feel like an idiot.", true);
  ShortAnswer test9 = new ShortAnswer("Explain your feelings.", true);
  ShortAnswer test10 = new ShortAnswer("Explain your feelings.", true);
  ShortAnswer test11 = new ShortAnswer("Explain your feelings.", true);
  ShortAnswer test12 = new ShortAnswer("Explain your feelings.", true);


  Likert test13 = new Likert("Explain your feelings.", false);


  /**
   * Test to get correct type of question.
   */
  @Test
  public void testGetType() {
    Assert.assertEquals("YesNo", test1.getType());
    Assert.assertEquals("YesNo", test2.getType());
    Assert.assertEquals("YesNo", test3.getType());
    Assert.assertEquals("YesNo", test4.getType());

    Assert.assertEquals("Likert", test5.getType());
    Assert.assertEquals("Likert", test6.getType());
    Assert.assertEquals("Likert", test7.getType());
    Assert.assertEquals("Likert", test8.getType());

    Assert.assertEquals("ShortAnswer", test9.getType());
    Assert.assertEquals("ShortAnswer", test10.getType());
    Assert.assertEquals("ShortAnswer", test11.getType());
    Assert.assertEquals("ShortAnswer", test12.getType());


  }


  /**
   * Test to get prompt of question.
   */
  @Test
  public void testGetPrompt() {
    Assert.assertEquals("Are you happy?", test1.getPrompt());
    Assert.assertEquals("Do you want to be friends?", test2.getPrompt());
    Assert.assertEquals("Do you like pasta?", test3.getPrompt());
    Assert.assertEquals("Are you ok?", test4.getPrompt());
    Assert.assertEquals("OOD has been fairly taught and graded.", test5.getPrompt());
    Assert.assertEquals("Do you agree?", test6.getPrompt());
    Assert.assertEquals("I am angry.", test7.getPrompt());
    Assert.assertEquals("OOD makes me feel like an idiot.", test8.getPrompt());
    Assert.assertEquals("Explain your feelings.", test9.getPrompt());
    Assert.assertEquals("Explain your feelings.", test10.getPrompt());
    Assert.assertEquals("Explain your feelings.", test11.getPrompt());
    Assert.assertEquals("Explain your feelings.", test12.getPrompt());


  }


  /**
   * Test to get requirement status of question.
   */
  @Test
  public void testRequired() {
    Assert.assertTrue(test1.isRequired());
    Assert.assertTrue(test5.isRequired());
    Assert.assertTrue(test7.isRequired());
    Assert.assertTrue(test8.isRequired());
    Assert.assertTrue(test9.isRequired());
    Assert.assertTrue(test10.isRequired());
    Assert.assertTrue(test11.isRequired());
    Assert.assertTrue(test12.isRequired());

    Assert.assertFalse(test2.isRequired());
    Assert.assertFalse(test3.isRequired());
    Assert.assertFalse(test4.isRequired());
    Assert.assertFalse(test6.isRequired());

  }


  /**
   * Test to answer the question.
   */
  @Test
  public void testAnswer() {
    Assert.assertEquals("no", test1.answer("no"));
    Assert.assertEquals("no", test2.answer("no"));
    Assert.assertEquals("yes", test3.answer("yes"));
    Assert.assertEquals("no", test4.answer("no"));
    Assert.assertEquals("Agree", test5.answer("Agree"));
    Assert.assertEquals("Disagree", test6.answer("Disagree"));
    Assert.assertEquals("Neither Agree Nor Disagree",
            test7.answer("Neither Agree Nor Disagree"));
    Assert.assertEquals("Disagree", test8.answer("Disagree"));
    Assert.assertEquals("yes", test9.answer("yes"));
    Assert.assertEquals("no", test10.answer("no"));
    Assert.assertEquals("no", test11.answer("no"));
    Assert.assertEquals("yes", test12.answer("yes"));

  }

  /**
   * Test to get answer of the question.
   */
  @Test
  public void testGetAnswer() {
    Assert.assertEquals("no", test1.answer("no"));
    Assert.assertEquals("no", test1.getAnswer());

    Assert.assertEquals("no", test2.answer("no"));
    Assert.assertEquals("no", test2.getAnswer());

    Assert.assertEquals("yes", test3.answer("yes"));
    Assert.assertEquals("yes", test3.getAnswer());

    Assert.assertEquals("no", test4.answer("no"));
    Assert.assertEquals("no", test4.getAnswer());

    Assert.assertEquals("Agree", test5.answer("Agree"));
    Assert.assertEquals("Agree", test5.getAnswer());

    Assert.assertEquals("Disagree", test6.answer("Disagree"));
    Assert.assertEquals("Disagree", test6.getAnswer());

    Assert.assertEquals("Neither Agree Nor Disagree",
            test7.answer("Neither Agree Nor Disagree"));
    Assert.assertEquals("Neither Agree Nor Disagree", test7.getAnswer());

    Assert.assertEquals("Disagree", test8.answer("Disagree"));
    Assert.assertEquals("Disagree", test8.getAnswer());

    Assert.assertEquals("yes", test9.answer("yes"));
    Assert.assertEquals("yes", test9.getAnswer());

    Assert.assertEquals("no", test10.answer("no"));
    Assert.assertEquals("no", test10.getAnswer());

    Assert.assertEquals("no", test11.answer("no"));
    Assert.assertEquals("no", test11.getAnswer());

    Assert.assertEquals("yes", test12.answer("yes"));
    Assert.assertEquals("yes", test12.getAnswer());


  }


  /**
   * Test to throw argument that the answer input is invalid.
   *
   * @throws IllegalArgumentException if answer input is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnswerFailure1() throws IllegalArgumentException {
    test4.answer("maybe");
  }


  /**
   * Test to throw argument that the answer input is invalid.
   *
   * @throws IllegalArgumentException if answer input is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnswerFailure2() throws IllegalArgumentException {
    test1.answer("");
    test5.answer("");
    test9.answer("");
  }


  /**
   * Test to throw argument that the answer input is invalid.
   *
   * @throws IllegalArgumentException if answer input is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnswerFailure5() throws IllegalArgumentException {
    test1.answer(null);
    test5.answer(null);
    test9.answer(null);
  }


  /**
   * Test to throw argument that the answer input is invalid.
   *
   * @throws IllegalArgumentException if answer input is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnswerFailure7() throws IllegalArgumentException {
    YesNo test13 = new YesNo("", false);
    Likert test14 = new Likert("", false);
    ShortAnswer test15 = new ShortAnswer("", false);

    Assert.assertEquals("", test13.getPrompt());
    Assert.assertEquals("", test14.getPrompt());
    Assert.assertEquals("", test15.getPrompt());
  }

  /**
   * Test to copy the question.
   */
  @Test
  public void copyTest() {
    YesNo copyTest1 = new YesNo("Are you happy?", true);
    Assert.assertEquals(copyTest1.getType(), test1.copy().getType());
    Assert.assertEquals(copyTest1.getPrompt(), test1.copy().getPrompt());
    Assert.assertEquals(copyTest1.isRequired(), test1.isRequired());
    Assert.assertEquals("no", test1.answer("no"));
    Assert.assertEquals("no",test1.getAnswer());

    Likert copyTest5 = new Likert("OOD has been fairly taught and graded.", true);
    Assert.assertEquals(copyTest5.getType(), test5.copy().getType());
    Assert.assertEquals(copyTest5.getPrompt(), test5.copy().getPrompt());
    Assert.assertEquals(copyTest5.isRequired(), test5.isRequired());
    Assert.assertEquals("Strongly Disagree", test5.answer("Strongly Disagree"));
    Assert.assertEquals("Strongly Disagree",test5.getAnswer());

    ShortAnswer copyTest12 = new ShortAnswer("Explain your feelings.", true);
    Assert.assertEquals(copyTest12.getType(), test12.copy().getType());
    Assert.assertEquals(copyTest12.getPrompt(), test12.copy().getPrompt());
    Assert.assertEquals(copyTest12.isRequired(), test12.isRequired());
    Assert.assertEquals("I am sad.", test12.answer("I am sad."));
    Assert.assertEquals("I am sad.",test12.getAnswer());


  }



}

