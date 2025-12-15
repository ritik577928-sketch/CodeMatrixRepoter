
package codemetrics.model;

import java.io.File;
import java.util.List;

public abstract class SourceFile {
    protected File file;
    protected List<String> lines;

    public SourceFile(File f, List<String> lines) {
        this.file = f;
        this.lines = lines;
    }

    public List<String> getLines() {
        return lines;
    }

    public File getFile() {
        return file;
    }
//    @Override
//    public String toString() {
//    	return file.getName() + " (Number of lines = "+lines.size() +")"+c;
//    }
}