package codemetrics.output;

import codemetrics.model.Metrics;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;

public class CSVWriter {

    public static void writeCSV(Map<File, Metrics> map) {

        try (PrintWriter pw = new PrintWriter(new FileWriter("metrics.csv"))) {

            // Header
            pw.println("File,LOC,Comments,Cyclomatic");

            // Data rows
            for (Map.Entry<File, Metrics> entry : map.entrySet()) {

                File file = entry.getKey();
                Metrics m = entry.getValue();

                pw.println(
                    file.getName() + "," +
                    m.getLoc() + "," +
                    m.getCommentCount() + "," +
                    m.getCyclomaticComplexity()
                );
            }

            // Print path ONCE
            System.out.println("CSV created at: " +
                    new File("metrics.csv").getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }
}