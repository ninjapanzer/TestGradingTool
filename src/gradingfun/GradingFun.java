/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradingfun;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author samuraipanzer
 */
public class GradingFun {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
	DecimalFormat formatter = new DecimalFormat("#0.0%");
	Map<String, Integer> fudgeDistribution = new HashMap<String, Integer>();
	Map<String, Integer> unfudgeDistribution = new HashMap<String, Integer>();
	File file = new File("src/gc_15FA_CPT_163_27_column_2015-09-17-16-52-14.csv");
	int pointPadding = 60;
	int totalPoints = 120;
	GradeParser gp = new GradeParser(file);
	gp.parse();
	Map<String, Double> data = gp.getParsedData();
	
	for (Map.Entry<String, Double> entry : data.entrySet()) {
	  Double fudgedScore = (entry.getValue() - pointPadding)/pointPadding;
	  Double unfudgedScore = (entry.getValue())/totalPoints;
	  String fudgeLetterGrade = (new LetterGrader(fudgedScore*100)).getLetterGrade();
	  String unfudgeLetterGrade = (new LetterGrader(unfudgedScore*100)).getLetterGrade();
	  if(!fudgeDistribution.containsKey(fudgeLetterGrade)){
		fudgeDistribution.put(fudgeLetterGrade, 1);
	  }else{
		fudgeDistribution.put(fudgeLetterGrade, fudgeDistribution.get(fudgeLetterGrade)+1);
	  }
	  if(!unfudgeDistribution.containsKey(fudgeLetterGrade)){
		unfudgeDistribution.put(fudgeLetterGrade, 1);
	  }else{
		unfudgeDistribution.put(unfudgeLetterGrade, unfudgeDistribution.get(fudgeLetterGrade)+1);
	  }
	  System.out.println("Student : " + entry.getKey()
		  + "\n\t Scored: \t "+ formatter.format(fudgedScore) + "\t" + fudgeLetterGrade
		  + "\n\t Fudged to: \t " + formatter.format(unfudgedScore) + "\t" + unfudgeLetterGrade);
	}
	System.out.println("Fudge Distribution:\t"+fudgeDistribution);
	System.out.println("unFudge Distribution:\t"+unfudgeDistribution);
  }
  
}
