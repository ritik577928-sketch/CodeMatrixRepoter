package filedirectoryscanner;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import codemetrics.metrics.*;
import codemetrics.model.JavaSourceFile;
import codemetrics.model.Metrics;


public class MetricManager {
	
	private Map<File, Metrics> metricsMap = new HashMap<>();
	private FileParser parser = new FileParser();
	
	private List<MetricCalculator> calculators = List.of(
			new CyclomaticCalculator(),
			new LineCounter(),
			new Commentcounter()
			);
	
	public void runCalculators(List<JavaSourceFile> parsedFiles) throws Exception {
		for (JavaSourceFile js : parsedFiles) {
			
			Metrics metrics = new Metrics();
			for (MetricCalculator calc : calculators) {
				calc.calculate(js, metrics);
			}
			metricsMap.put(js.getFile(), metrics) ;
				
			}
		}
	public Map<File,Metrics> getMetricsMap(){
		return metricsMap;
	}
	
	}