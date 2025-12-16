
package codemetrics.model;


public class Metrics {

    // ---------- COMMENTS ----------
    private int commentCount = 0;

    public void addComment() {
        commentCount++;
    }

    public int getCommentCount() {
        return commentCount;
    }

    // ---------- LOC ----------
    private int loc = 0;

    public void addLoc() {
        loc++;
    }

    public int getLoc() {
        return loc;
    }

    // ---------- DECISION POINTS ----------
    private int decisionPoints = 0;

    // add ONE decision point
    public void addDecisionPoint() {
        decisionPoints++;
    }

    // add MULTIPLE decision points (for &&, ||)
    public void addDecisionPoints(int n) {
        decisionPoints += n;
    }

    public int getDecisionPoints() {
        return decisionPoints;
    }

    // ---------- DERIVED METRICS ----------

    // Cyclomatic Complexity formula
    public int getCyclomaticComplexity() {
        return 1 + decisionPoints;
    }
    public double getCommentPercentage() {
        if (loc == 0) return 0;
        return (commentCount * 100.0) / loc;
    }

    public double getCyclomaticPercentage() {
        if (loc == 0) return 0;
        return (getCyclomaticComplexity() * 100.0) / loc;
    }

    public String getComplexityLevel() {
        int c = getCyclomaticComplexity();
        if (c <= 5) return "LOW";
        else if (c <= 10) return "MEDIUM";
        else return "HIGH";
    }
}


