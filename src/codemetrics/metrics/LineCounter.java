

package codemetrics.metrics;

import codemetrics.model.JavaSourceFile;
import codemetrics.model.Metrics;

public class LineCounter implements MetricCalculator {
	@Override	
	public void calculate(JavaSourceFile js, Metrics metrics) {
		        for (String line : js.getLines()) {
		            if (!line.trim().isEmpty()) {   // ignoring blank lines
		                metrics.addLoc();
		            }
		        }
	}
	}
	