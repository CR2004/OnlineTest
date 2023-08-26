package onlineTest;

import java.io.Serializable;

public class TrueFalse implements Question,Serializable{
	int examId;
	int questionNumber;
	String text;
	double points;
	boolean answer;
	public TrueFalse(int examId, int questionNumber,
		    String text, double points, boolean answer) {
		this.examId=examId;
		this.questionNumber=questionNumber;
		this.text=text;
		this.points=points;
		this.answer=answer;
	}
	public TrueFalse(TrueFalse t) {
		this.examId=t.examId;
		this.questionNumber=t.questionNumber;
		this.text=t.text;
		this.points=t.points;
		this.answer=t.answer;
	}
	public int getexamId() {
		return examId;
	}
	public int getquestionNumber() {
		return questionNumber;
	}
	public String gettext() {
		return text;
	}
	public double getpoints() {
		return points;
	}
}
