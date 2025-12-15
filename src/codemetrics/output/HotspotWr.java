

package codemetrics.output;

import codemetrics.model.Metrics;
import java.io.*;
import java.util.Map;
import java.io.File;

public class HotspotWr{

    public static void writeHotspots(Map<File, Metrics> map) {

        try (PrintWriter pw = new PrintWriter(new FileWriter("hotspots.txt"))) {
            pw.println("=== HOTSPOT REPORT ===");

            for (var entry : map.entrySet()) {
                Metrics m = entry.getValue();

                if (m.getCyclomaticComplexity() > 10 || m.getLoc() > 200) {
                    pw.println(entry.getKey().getName() +
                            " → High Complexity/Size");
                }
            }

        } catch (Exception e) {
            System.out.println("Error writing hotspot file.");
        }
    }
}