package codemetrics.cli;

import java.io.File;
import java.util.List;
import java.util.Map;

import codemetrics.model.JavaSourceFile;
import codemetrics.model.Metrics;
import codemetrics.output.CSVWr;
import codemetrics.output.HotspotWr;
import filedirectoryscanner.*;

public class CommandControlCenter {

    private FileDirectoryDemo scanner = new FileDirectoryDemo();
    private MetricManager manager = new MetricManager();
    private FileParser parser = new FileParser();

    public void execution(String command) {

        // ================= SCAN DIRECTORY =================
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
                System.out.println("Error during scan: " + e.getMessage());
            }
        }

        // ================= SUMMARY =================
        else if (command.equalsIgnoreCase("summarize")) {

            Map<File, Metrics> map = manager.getMetricsMap();

            if (map.isEmpty()) {
                System.out.println("No data available. Run scanDir first.");
                return;
            }

            System.out.println("\n------ Summary -------");

            map.forEach((file, metrics) -> {
                System.out.println(
                        file.getName() +
                        " | LOC: " + metrics.getLoc() +
                        " | Comments: " + metrics.getCommentCount() +
                        " | Comment %: " + String.format("%.2f", metrics.getCommentPercentage()) +
                        " | Cyclomatic: " + metrics.getCyclomaticComplexity() +
                        " | Cyclomatic %: " + String.format("%.2f", metrics.getCyclomaticPercentage()) +
                        " | Level: " + metrics.getComplexityLevel()
                );
            });
        }

        // ================= EXPORT =================
        else if (command.equalsIgnoreCase("export")) {

            Map<File, Metrics> map = manager.getMetricsMap();

            if (map.isEmpty()) {
                System.out.println("No data available. Run scanDir first.");
                return;
            }

            CSVWr.writeCSV(map);
            HotspotWr.writeHotspots(map);

            System.out.println("Export complete.");
        }

        // ================= UNKNOWN COMMAND =================
        else {
            System.out.println("Unknown command.");
            System.out.println("Available commands:");
            System.out.println("  scanDir <path>");
            System.out.println("  summarize");
            System.out.println("  export");
        }
    }
}
