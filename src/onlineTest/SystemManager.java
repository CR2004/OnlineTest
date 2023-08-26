package onlineTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
public class SystemManager implements Manager, Serializable{
	ArrayList<Exam> exams=new ArrayList<Exam>();
	ArrayList<Question> questions=new ArrayList<Question>();
	ArrayList<String> students=new ArrayList<String>();
	ArrayList<Answer> answers=new ArrayList<Answer>();
	String[] letterGrades; 
	double[] cutoffs;
	public boolean addExam(int examId, String title) {
		Exam e1= new Exam(examId,title);
		boolean check=false;
		for(Exam e2:exams) {
			if(e2.examId==examId) {
				check=true;
			}
		}
		if(check==false) {
			exams.add(e1);
			return true;
		}
		else {
			return false;
		}
	}
	
	
	/**
	 * Adds a true and false question to the specified exam.  If the question
	 * already exists it is overwritten.
	 * @param examId
	 * @param questionNumber
	 * @param text Question text
	 * @param points total points
	 * @param answer expected answer
	 */
	public void addTrueFalseQuestion(int examId, int questionNumber,
									    String text, double points, boolean answer) {
		TrueFalse t1= new TrueFalse(examId,questionNumber,text,points,answer);
		int l1=questions.size();
		boolean check=false;
		int index=0;
		for(int i=0;i<l1;i++) {
			if(questions.get(i) instanceof TrueFalse) {
				if(questions.get(i).getexamId()==examId && questions.get(i).gettext().equals(text)) {
					index=i;
					check=true;
				}
			}
		}
		if(check==true) {
			questions.remove(index);
		}
		questions.add(t1);
	}
	
	
	/**
	 * Adds a multiple choice question to the specified exam.   If the question
	 * already exists it is overwritten.
	 * @param examId
	 * @param questionNumber
	 * @param text Question text
	 * @param points total points
	 * @param answer expected answer
	 */
	public void addMultipleChoiceQuestion(int examId, int questionNumber,
		    String text, double points, String[] answer) {
		MultipleChoice m= new MultipleChoice(examId,questionNumber,text,points,answer);
		int l1=questions.size();
		boolean check=false;
		int index=0;
		for(int i=0;i<l1;i++) {
			if(questions.get(i) instanceof MultipleChoice) {
				if(questions.get(i).getexamId()==examId && questions.get(i).gettext().equals(text)) {
					index=i;
					check=true;
				}
			}
		}
		if(check==true) {
			questions.remove(index);
		}
		questions.add(m);
	}
	
	
	/**
	 * Adds a fill-in-the-blanks question to the specified exam.  If the question
	 * already exits it is overwritten.  Each correct response is worth
	 * points/entries in the answer.
	 * @param examId
	 * @param questionNumber
	 * @param text Question text
	 * @param points total points
	 * @param answer expected answer
	 */
	public void addFillInTheBlanksQuestion(int examId, int questionNumber,
		    String text, double points, String[] answer) {
		FillInTheBlanks f= new FillInTheBlanks(examId,questionNumber,text,points,answer);
		int l1=questions.size();
		boolean check=false;
		int index=0;
		for(int i=0;i<l1;i++) {
			if(questions.get(i) instanceof FillInTheBlanks) {
				if(questions.get(i).getexamId()==examId && questions.get(i).gettext().equals(text)) {
					index=i;
					check=true;
				}
			}
		}
		if(check==true) {
			questions.remove(index);
		}
		questions.add(f);
	}
	
	
	/**
	 * Returns a string with the following information per question:<br />
	 * "Question Text: " followed by the question's text<br />
	 * "Points: " followed by the points for the question<br />
	 * "Correct Answer: " followed by the correct answer. <br />
	 * The format for the correct answer will be: <br /> 
	 *    a. True or false question: "True" or "False"<br />
	 *    b. Multiple choice question: [ ] enclosing the answer (each entry separated by commas) and in
	 *       sorted order. <br />
	 *    c. Fill in the blanks question: [ ] enclosing the answer (each entry separated by commas) and
	 *       in sorted order. <br />
	 * @param examId
	 * @return "Exam not found" if exam not found, otherwise the key
	 */
	public String getKey(int examId) {
		String answer="";
		for(Question q:questions) {
			if(q.getexamId()==examId) {
				answer+="Question Text: "+q.gettext();
				answer+="\n";
				answer+="Points: "+q.getpoints();
				answer+="\n";
				answer+="Correct Answer: ";
				if(q instanceof TrueFalse) {
					String bool="";
					bool+=((TrueFalse) q).answer;
					String title=convertToTitleCaseIteratingChars(bool);
					answer+=title;
					answer+="\n";
				}
				else if(q instanceof MultipleChoice) {
					//String[] answer1=((MultipleChoice) q).answer;
					String[] answer2=((MultipleChoice) q).answer;
					Arrays.sort(answer2);
					answer+="[";
					int l1=answer2.length;
					for(int i=0;i<l1-1;i++) {
						answer+=answer2[i];
						answer+=",";
					}
					answer+=answer2[l1-1];
					answer+="]";
					answer+="\n";
				}
				else if(q instanceof FillInTheBlanks) {
					//String[] answer1=((FillInTheBlanks) q).answer;
					String[] answer2=((FillInTheBlanks) q).answer;
					Arrays.sort(answer2);
					answer+="[";
					int l1=answer2.length;
					for(int i=0;i<l1-1;i++) {
						answer+=answer2[i];
						answer+=",";
					}
					answer+=answer2[l1-1];
					answer+="]";
					answer+="\n";
				}
			}
			else {
				answer+="Exam not found";
			}
			
		}
		return answer;
	}
	
	
	/**
	 * Adds the specified student to the database.  Names are specified in the format
	 * LastName,FirstName
	 * @param name
	 * @return false if student already exists.
	 */
	public boolean addStudent(String name) {
		boolean check=false;
		for(String s:students) {
			if(s.equals(name)) {
				check=true;
			}
		}
		if(check==false) {
			students.add(name);
			return true;
		}
		else {
			return false;
		}
	}
	
	
	/**
	 * Enters the question's answer into the database.
	 * @param studentName
	 * @param examId
	 * @param questionNumber
	 * @param answer
	 */
	public void answerTrueFalseQuestion(String studentName, int examId, int questionNumber, boolean answer) {
		answerTrueFalse t1=new answerTrueFalse(studentName,examId,questionNumber,answer);
		answers.add(t1);
	}
	
	
	/**
	 * Enters the question's answer into the database.
	 * @param studentName
	 * @param examId
	 * @param questionNumber
	 * @param answer
	 */
	public void answerMultipleChoiceQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		answerMultipleChoice t1=new answerMultipleChoice(studentName,examId,questionNumber,answer);
		answers.add(t1);
	}
	
	
	/**
	 * Enters the question's answer into the database.
	 * @param studentName
	 * @param examId
	 * @param questionNumber
	 * @param answer
	 */
	public void answerFillInTheBlanksQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		answerFillInTheBlanks t1=new answerFillInTheBlanks(studentName,examId,questionNumber,answer);
		answers.add(t1);
	}
	

	/**
	 * Returns the score the student got for the specified exam.
	 * @param studentName
	 * @param examId
	 * @return score
	 */
	public double getExamScore(String studentName, int examId) {
		double points=0;
		for(Question q:questions) {
			if(q.getexamId()==examId) {
				for(Answer a:answers) {
					if(a.getexamId()==examId && a.getName().equals(studentName) && a.getQuestionNumber()==q.getquestionNumber()) {
						if(a instanceof answerTrueFalse) {
							boolean answer1=((TrueFalse) q).answer;
							boolean StudentAnswer=((answerTrueFalse) a).answer;
							if(answer1==(StudentAnswer)) {
								points+=q.getpoints();
							}
						}
						else if(a instanceof answerMultipleChoice) {
							String[] answer1=((MultipleChoice) q).answer;
							String[] StudentAnswer=((answerMultipleChoice) a).answer;
							int l1=StudentAnswer.length;
							int l2=answer1.length;
							boolean check=true;
							if(l1==l2) {
								for(int i=0;i<l1;i++) {
									if(answer1[i].equals(StudentAnswer[i])==false) {
										check=false;
									}
								}
							}
							else {
								check=false;
							}
							if(check==true) {
								points+=q.getpoints();
							}
						}
						else if(a instanceof answerFillInTheBlanks) {
							String[] answer1=((FillInTheBlanks) q).answer;
							String[] StudentAnswer=((answerFillInTheBlanks) a).answer;
							int l1=StudentAnswer.length;
							int l2=answer1.length;
							for(int i=0;i<l2;i++) {
								for(int j=0;j<l1;j++) {
									if(answer1[i].equals(StudentAnswer[j])) {
										points+=q.getpoints()/l2;
									}
								}
							}
						}
					}
				}
			}
		}
		return points;
	}
	
	
	/**
	 * Generates a grading report for the specified exam.  The report will include
	 * the following information for each exam question:<br />
	 * "Question #" {questionNumber} {questionScore} " points out of " {totalQuestionPoints}<br />
	 * The report will end with the following information:<br />
	 * "Final Score: " {score} " out of " {totalExamPoints};  
	 * @param studentName
	 * @param examId
	 * @return report
	 */
	public String getGradingReport(String studentName, int examId) {
		String answer="";
		double total=0;
		for(Question q:questions) {
			if(q.getexamId()==examId) {
				total+=q.getpoints();
			}
		}
		double points=getExamScore(studentName,examId);
		for(Question q:questions) {
			if(q.getexamId()==examId) {
				double points2=0;
				for(Answer a:answers) {
					if(a.getexamId()==examId && a.getName().equals(studentName) && a.getQuestionNumber()==q.getquestionNumber()) {
						if(a instanceof answerTrueFalse) {
							boolean answer1=((TrueFalse) q).answer;
							boolean StudentAnswer=((answerTrueFalse) a).answer;
							if(answer1==(StudentAnswer)) {
								points2+=q.getpoints();
							}
						}
						else if(a instanceof answerMultipleChoice) {
							String[] answer1=((MultipleChoice) q).answer;
							String[] StudentAnswer=((answerMultipleChoice) a).answer;
							int l1=StudentAnswer.length;
							int l2=answer1.length;
							boolean check=true;
							if(l1==l2) {
								for(int i=0;i<l1;i++) {
									if(answer1[i].equals(StudentAnswer[i])==false) {
										check=false;
									}
								}
							}
							else {
								check=false;
							}
							if(check==true) {
								points2+=q.getpoints();
							}
							
						}
						else if(a instanceof answerFillInTheBlanks) {
							String[] answer1=((FillInTheBlanks) q).answer;
							String[] StudentAnswer=((answerFillInTheBlanks) a).answer;
							int l1=StudentAnswer.length;
							int l2=answer1.length;
							for(int i=0;i<l2;i++) {
								for(int j=0;j<l1;j++) {
									if(answer1[i].equals(StudentAnswer[j])) {
										points2+=q.getpoints()/l2;
									}
								}
							}
						}
					}
				}
				answer+="Question #"+q.getquestionNumber()+" "+points2+" "+"points out of "+q.getpoints();
				answer+="\n";
			}
		}
		answer+="\n";
		answer+="Final Score: "+points+" "+"out of"+total;
		return answer;
	}
	
	
	/**
	 * Sets the cutoffs for letter grades.  For example, a typical curve we will have
	 * new String[]{"A","B","C","D","F"}, new double[] {90,80,70,60,0}.  Anyone with a 90 or
	 * above gets an A, anyone with an 80 or above gets a B, etc.  Notice we can have different
	 * letter grades and cutoffs (not just the typical curve).
	 * @param letterGrades
	 * @param cutoffs
	 */
	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {
		this.letterGrades=letterGrades;
		this.cutoffs=cutoffs;
	}
	
	
	/**
	 * Computes a numeric grade (value between 0 and a 100) for the student taking
	 * into consideration all the exams.  All exams have the same weight. 
	 * @param studentName
	 * @return grade
	 */
	public double getCourseNumericGrade(String studentName) {
		double NumericGrade=0;
		double score=0;
		double Nexams=0;
		for(Exam e:exams) {
			Nexams++;
		}
		for(Exam e:exams) {
			double points=0;
			double total=0;
			points+=getExamScore(studentName,e.examId);
			for(Question q:questions) {
				if(q.getexamId()==e.examId) {
					total+=q.getpoints();
				}
			}
			score+=points/total;
		}
		NumericGrade=(score/Nexams)*100;
		return NumericGrade;
	}
	
	
	/** 
	 * Computes a letter grade based on cutoffs provided.  It is assumed the cutoffs have
	 * been set before the method is called.
	 * @param studentName
	 * @return letter grade
	 */
	public String getCourseLetterGrade(String studentName) {
		double NumericGrade=getCourseNumericGrade(studentName);
		int index = 0;
        for (int i = 0; i < cutoffs.length; i++) {
            if (NumericGrade >= cutoffs[i]) {
                index = i;
                break;
            }
        }
        return letterGrades[index];
	}
	
	
	/**
	 * Returns a listing with the grades for each student.  For each student the report will
	 * include the following information: <br />
	 * {studentName} {courseNumericGrade} {courseLetterGrade}<br />
	 * The names will appear in sorted order.
	 * @return grades
	 */
	public String getCourseGrades() {
		String answer="";
		ArrayList<String> students2=students;
		students2.sort(Comparator.naturalOrder());
		for(String s:students2) {
			answer+=s+" "+getCourseNumericGrade(s)+" "+getCourseLetterGrade(s);
			answer+="\n";
		}
		return answer;
	}
	
	
	/**
	 * Returns the maximum score (among all the students) for the specified exam.
	 * @param examId
	 * @return maximum
	 */
	public double getMaxScore(int examId) {
		double max=0;
		for(Exam e:exams) {
			if(e.examId==examId) {
				for(String s:students) {
					if(getExamScore(s,examId)>max) {
						max=getExamScore(s,examId);
					}
				}
			}
		}
		return max;
	}
	
	
	/**
	 * Returns the minimum score (among all the students) for the specified exam.
	 * @param examId
	 * @return minimum
	 */
	public double getMinScore(int examId) {
		double min=100;
		for(Exam e:exams) {
			if(e.examId==examId) {
				for(String s:students) {
					if(getExamScore(s,examId)<min) {
						min=getExamScore(s,examId);
					}
				}
			}
		}
		return min;
	}
	
	
	/**
	 * Returns the average score for the specified exam.
	 * @param examId
	 * @return average
	 */
	public double getAverageScore(int examId) {
		double NumberExams=0;
		double examscore=0;
		for(String s:students) {
			examscore+=getExamScore(s,examId);
		}
		for(String s:students) {
			NumberExams++;
		}
		return examscore/NumberExams;
	}
	
	
	/**
	 * It will serialize the Manager object and store it in the
	 * specified file.
	 */
	public void saveManager(Manager manager, String fileName) {
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream(fileName);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(manager);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in "+fileName);
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	
	/**
	 * It will return a Manager object based on the serialized data
	 * found in the specified file.
	 */
	public Manager restoreManager(String fileName){
		Manager m=null;
		try {
	         FileInputStream fileIn = new FileInputStream(fileName);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         m = (Manager) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	         return m;
	      } catch (ClassNotFoundException c) {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return m;
	      }
		return m;
	}
	public static String convertToTitleCaseIteratingChars(String text) {
	    if (text == null || text.isEmpty()) {
	        return text;
	    }

	    StringBuilder converted = new StringBuilder();

	    boolean convertNext = true;
	    for (char ch : text.toCharArray()) {
	        if (Character.isSpaceChar(ch)) {
	            convertNext = true;
	        } else if (convertNext) {
	            ch = Character.toTitleCase(ch);
	            convertNext = false;
	        } else {
	            ch = Character.toLowerCase(ch);
	        }
	        converted.append(ch);
	    }

	    return converted.toString();
	}
}
