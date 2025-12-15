package codemetrics.metrics;

import codemetrics.model.*;

public class CyclomaticCalculator implements MetricCalculator {

	@Override
	public void calculate(JavaSourceFile js, Metrics metrics) {
		for(String rawLine : js.getLines()) {
			String line = rawLine.trim();   // this method is used for removes extra space from beginning and end and also it is used before checking logic
			
			if (line.isEmpty()) { // This line is for skiping empty line
				continue;
			}
			//       if and else if
			// this will scanne file and tell that how many complexity if and else if are available
			if(line.startsWith("if(") || line.startsWith("if (") || line.startsWith("else if")) {
				metrics.addDecisionPoint();
			}
			//     Loops
			if (line.startsWith("for(") || line.startsWith("for (") ||   // here first for have not gap and second for have gap because 
				                                                                        //both loop means coder can write a code with space and without space so counting the complexity it is better desison 
				line.startsWith("while(") || line.startsWith("while (") ||
				line.contains(" while(")) {  // this will check keyword which are available in anywhere in code 
				metrics.addDecisionPoint();
				}
			
			// do while
			
			if (line.startsWith("do")) {
			}
			
			// switch case
			if (line.startsWith("case" )) {
				metrics.addDecisionPoint();
			}
			
			// catch
			if(line.startsWith("catch")) {
				metrics.addDecisionPoint();
			}
			// ternary
			if (line.contains("?")&& line.contains(":")) {
				metrics.addDecisionPoint();
			}
			// logical operator (count multiplies)
			metrics.addDecisionPoints(count("&&", line));
			metrics.addDecisionPoints(count("||", line));
			}


	}

	 private int count(String operator, String line) {
	        int count = 0;
	        int index = 0;

	        while ((index = line.indexOf(operator, index)) != -1) {
	            count++;
	            index += operator.length();
	        }
	        return count;
	    }
	
	}