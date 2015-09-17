/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradingfun;

/**
 *
 * @author samuraipanzer
 */
public class LetterGrader {
  private double score;

  public LetterGrader(Double score) {
	this.score = score;
  }

  public String getLetterGrade(){
	String grade = "";
	Double score = this.score;
	if(score >= 90){
	  grade = "A";
	}else if(score >= 80){
	  grade = "B";
	}else if(score >= 70){
	  grade = "C";
	}else if(score >= 60){
	  grade = "D";
	}else{
	  grade = "F";
	}
	return grade;
  }
}
