package codemetrics.metrics;

import codemetrics.model.*;


public interface MetricCalculator {

	void calculate(JavaSourceFile js, Metrics metrics);


	//void calculator1();  

}