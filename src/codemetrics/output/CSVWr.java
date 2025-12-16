package codemetrics.output;

import codemetrics.model.Metrics;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CSVWr {

    public static void writeCSV(Map<File, Metrics> map) {

        try (PrintWriter pw = new PrintWriter(new FileWriter("metrics.csv"))) {

            // Header
            pw.println("File,LOC,Comments,Cyclomatic,CommentPercentage,ComplexityPer100LOC,ComplexityLevel");

            // Sort by Cyclomatic Complexity (descending)
            List<Map.Entry<File, Metrics>> sortedList =
                    map.entrySet()
                       .stream()
                       .sorted(Comparator.comparingInt(
                               e -> ((Entry<File, Metrics>) e).getValue().getCyclomaticComplexity()
                       ).reversed())
                       .collect(Collectors.toList());

            // Data rows
            for (Map.Entry<File, Metrics> entry : sortedList) {

                File file = entry.getKey();
                Metrics m = entry.getValue();

                pw.println(
                    file.getName() + "," +
                    m.getLoc() + "," +
                    m.getCommentCount() + "," +
                    m.getCyclomaticComplexity() + "," +
                    String.format("%.2f", m.getCommentPercentage()) + "," +
                    String.format("%.2f", m.getCyclomaticPercentage()) + "," +
                    m.getComplexityLevel()
                );
            }

            System.out.println("CSV created at: " +
                    new File("metrics.csv").getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }
}
