package onlineTest;

import java.io.Serializable;

public class answerTrueFalse implements Answer,Serializable{
	String name;
	int examId;
	int QuestionNumber;
	boolean answer;
	public answerTrueFalse(String name,int examId,int QuestionNumber,boolean answer) {
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
