package codemetrics.metrics;

import codemetrics.model.*;

public class Commentcounter implements MetricCalculator {

    @Override
    public void calculate(JavaSourceFile js, Metrics metrics) {

        boolean inBlockComment = false;

        for (String line : js.getLines()) {
            String trimmed = line.trim();

            // Already inside block comment
            if (inBlockComment) {
                metrics.addComment();
                if (trimmed.contains("*/")) {
                    inBlockComment = false;
                }
                continue;
            }

            // Single-line comment
            if (trimmed.startsWith("//")) {
                metrics.addComment();
                continue;
            }

            // Block comment starts
            if (trimmed.startsWith("/*")) {
                metrics.addComment();
                if (!trimmed.contains("*/")) {
                    inBlockComment = true;
                }
                continue;
            }

            // Inline comment (code + // comment)
            if (trimmed.contains("//")) {
                metrics.addComment();
            }
        }
    }
}
