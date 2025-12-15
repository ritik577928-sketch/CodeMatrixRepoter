package codemetrics.cli;

import java.io.File;
import java.util.List;
import java.util.Map;

import codemetrics.model.JavaSourceFile;
import codemetrics.model.Metrics;
import codemetrics.output.CSVWriter;
import codemetrics.output.HotspotWr;
import filedirectoryscanner.*;

public class CommandControlCenter {

    private FileDirectoryDemo scanner = new FileDirectoryDemo();
    private MetricManager manager = new MetricManager();
    private FileParser parser = new FileParser();

    public void execution(String command) {

        if (command.startsWith("scanDir")) {

            String[] parts = command.split(" ", 2);
            if (parts.length < 2) {
                System.out.println("Usage: scanDir <directory_path>");
                return;
            }

            String path = parts[1].trim(); 
            System.out.println("Scanning Directory : " + path);

            try {
                // 1. Scan directory
                List<File> fileList = scanner.scanDir(path);

                // 2. Parse files
                List<JavaSourceFile> parsedFiles = parser.parsedFilePath(fileList);

                // 3. Run metrics
                manager.runCalculators(parsedFiles);

                System.out.println("Scan Completed. Files analyzed: " + parsedFiles.size());

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        else if (command.equals("summarize")) {

            Map<File, Metrics> map = manager.getMetricsMap();
            System.out.println("\n------ Summary -------");

            map.forEach((file, metrics) -> {
                System.out.println(
                    file.getName()
                    + " ---> Complexity: " + metrics.getCyclomaticComplexity()
                    + ",===> Comments: " + metrics.getCommentCount()
                    + ",----> LOC: " + metrics.getLoc()
                );
            });
        }

        else if (command.equals("export")) {
            CSVWriter.writeCSV(manager.getMetricsMap());
            HotspotWr.writeHotspots(manager.getMetricsMap());
            System.out.println("Export complete.");
        }

        else {
            System.out.println("Unknown Export.");
        }
    }
}
