/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradingfun;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author samuraipanzer
 */
public class GradeParser {
  private CSVParser parser = null;
  private Map<String, Double> parsedData = null;

  public GradeParser(File file) {
	try {
	  this.parser = CSVParser.parse(file, Charset.defaultCharset(), CSVFormat.RFC4180);
	} catch (IOException ex) {
	  Logger.getLogger(GradeParser.class.getName()).log(Level.SEVERE, null, ex);
	}
  }
  
  public Map<String, Double> getParsedData() {
	return parsedData;
  }

  public void setParser(CSVParser parser) {
	this.parser = parser;
  }

  public CSVParser getParser() {
	return parser;
  }
  
  public void parse(){
	Map<String, Double> data = new HashMap<String, Double>();
	for (CSVRecord csvRecord : this.parser) {
	  if(csvRecord.getRecordNumber() > 1){
		String lastName = csvRecord.get(0);
		String firstName = csvRecord.get(1);
		String score = csvRecord.get(6);
		Double dblScore = null;
		try{
		  dblScore = new Double(score);
		}catch(NumberFormatException ex){
		  dblScore = 0.0;
		}
		data.put(firstName + " " + lastName, dblScore);
	  }
	}
	this.parsedData = data;
	try {
	  this.parser.close();
	} catch (IOException ex) {
	  Logger.getLogger(GradeParser.class.getName()).log(Level.SEVERE, null, ex);
	}
  }
}
