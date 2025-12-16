package metrics.output;

import metrics.model.Met;

import java.io.File;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

public class PDFWr {

    public static void writePDF(Map<File, Met> metricsMap) {

        try (PDDocument document = new PDDocument()) {

            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream content =
                    new PDPageContentStream(document, page);

            PDFont font = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
            content.setFont(font, 11);

            float startY = 750;
            float rowHeight = 20;

            // Column X positions
            float colFile = 50;
            float colLOC = 300;
            float colComments = 360;
            float colCyclomatic = 450;

            // 🔹 Title
            content.beginText();
            content.newLineAtOffset(colFile, startY);
            content.showText("Code Metrics Report");
            content.endText();

            startY -= 40;

            // 🔹 Header
            content.beginText();
            content.newLineAtOffset(colFile, startY);
            content.showText("File");

            content.newLineAtOffset(colLOC - colFile, 0);
            content.showText("LOC");

            content.newLineAtOffset(colComments - colLOC, 0);
            content.showText("Comments");

            content.newLineAtOffset(colCyclomatic - colComments, 0);
            content.showText("Cyclomatic");
            content.endText();

            startY -= 10;

            // 🔹 Separator line
            content.moveTo(colFile, startY);
            content.lineTo(550, startY);
            content.stroke();

            startY -= rowHeight;

            // 🔹 Table rows
            for (Map.Entry<File, Met> entry : metricsMap.entrySet()) {

                Met m = entry.getValue();

                content.beginText();
                content.newLineAtOffset(colFile, startY);
                content.showText(entry.getKey().getName());

                content.newLineAtOffset(colLOC - colFile, 0);
                content.showText(String.valueOf(m.getLOC()));

                content.newLineAtOffset(colComments - colLOC, 0);
                content.showText(String.valueOf(m.getCommentcount()));

                content.newLineAtOffset(colCyclomatic - colComments, 0);
                content.showText(String.valueOf(m.getCyclomatricComplexity()));
                content.endText();

                startY -= rowHeight;
            }

            content.close();

            document.save("metrics.pdf");

            System.out.println("PDF created at: " +
                    new File("metrics.pdf").getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Error creating PDF: " + e.getMessage());
        }
    }
}
