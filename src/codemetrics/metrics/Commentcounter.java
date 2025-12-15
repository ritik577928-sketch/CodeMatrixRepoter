package codemetrics.metrics;

import codemetrics.model.*;

public class Commentcounter implements MetricCalculator{

	@Override
	public void calculate(JavaSourceFile js, Metrics metrics) {
		
	
		boolean inBlockComment = false; // we are creating a boolean flag it will tell that weather the code 
		                                                     // ia inside the multi line comment or not if yes(/*)than true or whwn it reach the 
		                                                   // the */ than false
		
		for (String line : js.getLines()) {  // js is an  object of javaSourceFile
			String trimmed =line.trim(); 
			//inside a block comment
			if(inBlockComment) {
				metrics.addComment(); // method of matrics
				if (trimmed.contains("*/")) {
					inBlockComment = false;
				}
				continue;
			}
			//single line comment
			if (trimmed.startsWith("//")) {
				metrics.addComment();
				continue;
			}
			//block  comment starts
			if (trimmed.startsWith("/*")) {
				metrics.addComment();
			if(!trimmed.contains("*/")) {
                    inBlockComment = true; // in line it will true for multiline comment
			}
			continue;
			}
			if (trimmed.contains("/")&& trimmed.contains("/")) {
				metrics.addComment();
			}
			else if (trimmed.contains("/*")) {
				metrics.addComment();
			}
		}
		
	}
		

}