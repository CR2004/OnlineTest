package onlineTest;

import java.io.Serializable;

public class MultipleChoice implements Question,Serializable{
	int examId;
	int questionNumber;
	String text;
	double points;
	String[] answer;
	public MultipleChoice(int examId, int questionNumber,
		    String text, double points, String[] answer) {
		this.examId=examId;
		this.questionNumber=questionNumber;
		this.text=text;
		this.points=points;
		this.answer=answer;
	}
	public MultipleChoice(MultipleChoice m) {
		this.examId=m.examId;
		this.questionNumber=m.questionNumber;
		this.text=m.text;
		this.points=m.points;
		this.answer=m.answer;
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
