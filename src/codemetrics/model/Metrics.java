
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

    // Complexity per 100 LOC
    public double getComplexityPer100LOC() {
        if (loc == 0) return 0.0;
        return (getCyclomaticComplexity() * 100.0) / loc;
    }   
 // Complexity Percentage
    public double getComplexityPercentage() {
        if (loc == 0) return 0.0;
        double value = ((double) getCyclomaticComplexity() / loc) * 100;
        return Math.round(value * 100.0) / 100.0;
    }
    
 // LOC Percentage
    public double getLocPercentage() {
        if (commentCount == 0) return 0.0;

        double value = ((double) loc / commentCount) * 100;
        return Math.round(value * 100.0) / 100.0;
    }


}

