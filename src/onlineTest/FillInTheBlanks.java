package onlineTest;

import java.io.Serializable;

public class FillInTheBlanks implements Question,Serializable {
	int examId;
	int questionNumber;
	String text;
	double points;
	String[] answer;
	public FillInTheBlanks(int examId, int questionNumber,
		    String text, double points, String[] answer) {
		this.examId=examId;
		this.questionNumber=questionNumber;
		this.text=text;
		this.points=points;
		this.answer=answer;
	}
	public FillInTheBlanks(FillInTheBlanks f) {
		this.examId=f.examId;
		this.questionNumber=f.questionNumber;
		this.text=f.text;
		this.points=f.points;
		this.answer=f.answer;
	}
	public String[] sortedanswer(String[] answer) {
		String[] answer1=answer;
		int l1=answer.length;
		for(int i=0;i<l1-1;i++) {
			if(answer1[i].compareTo(answer1[i+1])>0) {
				String temp=answer1[i];
				answer1[i]=answer1[i+1];
				answer1[i+1]=temp;
			}
		}
		return answer1;
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
