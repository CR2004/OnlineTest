package onlineTest;

import java.io.Serializable;

public class answerFillInTheBlanks implements Answer,Serializable {
	String name;
	int examId;
	int QuestionNumber;
	String[] answer;
	public answerFillInTheBlanks(String name,int examId,int QuestionNumber,String[] answer) {
		this.name=name;
		this.examId=examId;
		this.QuestionNumber=QuestionNumber;
		this.answer=answer;
	}
	public String getName() {
		return name;
	}
	public int getexamId() {
		return examId;
	}
	public int getQuestionNumber() {
		return QuestionNumber;
	}
}
