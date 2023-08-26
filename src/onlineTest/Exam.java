package onlineTest;

import java.io.Serializable;

public class Exam implements Serializable{
	int examId;
	String title;
	public Exam(int examId,String title) {
		this.examId=examId;
		this.title=title;
	}
	
}
