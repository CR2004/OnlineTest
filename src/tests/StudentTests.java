package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import onlineTest.SystemManager;

/**
 * 
 * You need student tests if you are looking for help during office hours about
 * bugs in your code.
 * 
 * @author UMCP CS Department
 *
 */
public class StudentTests {

	@Test
	public void Test1() {
		StringBuffer result=new StringBuffer("Question Text: Abstract classes cannot have constructors.\n"
				+ "Points: 2.0\n"
				+ "Correct Answer: False\n"
				+ "Question Text: The equals method returns a boolean.\n"
				+ "Points: 4.0\n"
				+ "Correct Answer: True\n"
				+ "Question Text: Identifiers can start with numbers.\n"
				+ "Points: 3.0\n"
				+ "Correct Answer: False\n"
				+ "\n"
				+ "Exam score for Sanders,Linda 0.0");
		
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		manager.addExam(10, "Midterm");
		manager.addTrueFalseQuestion(10, 1, "Abstract classes cannot have constructors.", 2, false);
		manager.addTrueFalseQuestion(10, 2, "The equals method returns a boolean.", 4, true);
		manager.addTrueFalseQuestion(10, 3, "Identifiers can start with numbers.", 3, false);
		answer.append(manager.getKey(10));
		
		String studentName = "Sanders,Linda";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 10, 1, true);
		manager.answerTrueFalseQuestion(studentName, 10, 2, false);
		manager.answerTrueFalseQuestion(studentName, 10, 3, true);
		answer.append("\nExam score for " + studentName + " " + manager.getExamScore(studentName, 10));
		assertTrue(answer.toString().equals(result.toString()));
	}
	
	@Test
	public void Test2() {
		String result="Question #1 2.0 points out of 2.0\n"
				+ "Question #2 0.0 points out of 4.0\n"
				+ "Question #3 3.0 points out of 3.0\n"
				+ "\n"
				+ "Final Score: 5.0 out of9.0";
		
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		manager.addExam(10, "Midterm");
		manager.addTrueFalseQuestion(10, 1, "Abstract classes cannot have constructors.", 2, false);
		manager.addTrueFalseQuestion(10, 2, "The equals method returns a boolean.", 4, true);
		manager.addTrueFalseQuestion(10, 3, "Identifiers can start with numbers.", 3, false);

		String studentName = "Peterson,Rose";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 10, 1, false);
		manager.answerTrueFalseQuestion(studentName, 10, 2, false);
		manager.answerTrueFalseQuestion(studentName, 10, 3, false);
		answer.append(manager.getGradingReport(studentName, 10));
		assertTrue(answer.toString().equals(result));	
	}
	
	@Test
	public void Test3() {
		String result="Report for Peterson,Rose\n"
				+ "Question #1 3.0 points out of 3.0\n"
				+ "Question #2 2.0 points out of 2.0\n"
				+ "Question #3 4.0 points out of 4.0\n"
				+ "\n"
				+ "Final Score: 9.0 out of9.0\n"
				+ "Report for Sanders,Mike\n"
				+ "Question #1 0.0 points out of 3.0\n"
				+ "Question #2 2.0 points out of 2.0\n"
				+ "Question #3 4.0 points out of 4.0\n"
				+ "\n"
				+ "Final Score: 6.0 out of9.0";
		
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		manager.addExam(10, "Midterm");
		
		double points;
		
		String questionText = "Which of the following are valid ids?\n";
		questionText += "A: house   B: 674   C: age   D: &";
		points = 3;
		manager.addMultipleChoiceQuestion(10, 1, questionText, points, new String[]{"A","C"});
		
		questionText = "Which of the following methods belong to the Object class?\n";
		questionText += "A: equals   B: hashCode   C: separate   D: divide ";
		points = 2;
		manager.addMultipleChoiceQuestion(10, 2, questionText, points, new String[]{"A","B"});
		
		questionText = "Which of the following allow us to define a constant?\n";
		questionText += "A: abstract   B: equals   C: class   D: final ";
		points = 4;
		manager.addMultipleChoiceQuestion(10, 3, questionText, points, new String[]{"D"});

		String studentName = "Peterson,Rose";
		manager.addStudent(studentName);
		manager.answerMultipleChoiceQuestion(studentName, 10, 1, new String[]{"A", "C"});
		manager.answerMultipleChoiceQuestion(studentName, 10, 2, new String[]{"A", "B"});
		manager.answerMultipleChoiceQuestion(studentName, 10, 3, new String[]{"D"});
		answer.append("Report for " + studentName + "\n" + manager.getGradingReport(studentName, 10));
		
		studentName = "Sanders,Mike";
		manager.addStudent(studentName);
		manager.answerMultipleChoiceQuestion(studentName, 10, 1, new String[]{"A"});
		manager.answerMultipleChoiceQuestion(studentName, 10, 2, new String[]{"A", "B"});
		manager.answerMultipleChoiceQuestion(studentName, 10, 3, new String[]{"D"});
		answer.append("\nReport for " + studentName + "\n" + manager.getGradingReport(studentName, 10));
		assertTrue(answer.toString().equals(result.toString()));
	}
	
	@Test
	public void Test4() {
		String result="Report for Peterson,Rose\n"
				+ "Question #1 4.0 points out of 4.0\n"
				+ "Question #2 6.0 points out of 6.0\n"
				+ "\n"
				+ "Final Score: 10.0 out of10.0\n"
				+ "Report for Sanders,Laura\n"
				+ "Question #1 2.0 points out of 4.0\n"
				+ "Question #2 4.0 points out of 6.0\n"
				+ "\n"
				+ "Final Score: 6.0 out of10.0";
		
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		manager.addExam(10, "Midterm");
		double points;
		
		String questionText = "Name two types of initialization blocks.";
		points = 4;
		manager.addFillInTheBlanksQuestion(10, 1, questionText, points, new String[]{"static","non-static"});
	
		questionText = "Name the first three types of primitive types discussed in class.";
		points = 6;
		manager.addFillInTheBlanksQuestion(10, 2, questionText, points, new String[]{"int","double","boolean"});	
		
		String studentName = "Peterson,Rose";
		manager.addStudent(studentName);
		manager.answerFillInTheBlanksQuestion(studentName, 10, 1, new String[]{"static", "non-static"});
		manager.answerFillInTheBlanksQuestion(studentName, 10, 2, new String[]{"int", "double", "boolean"});
		answer.append("Report for " + studentName + "\n" + manager.getGradingReport(studentName, 10));
		
		studentName = "Sanders,Laura";
		manager.addStudent(studentName);
		manager.answerFillInTheBlanksQuestion(studentName, 10, 1, new String[]{"static"});
		manager.answerFillInTheBlanksQuestion(studentName, 10, 2, new String[]{"int", "boolean"});		
		answer.append("\nReport for " + studentName + "\n" + manager.getGradingReport(studentName, 10));
		System.out.println(answer);
		assertTrue(answer.toString().equals(result.toString()));
	}
}