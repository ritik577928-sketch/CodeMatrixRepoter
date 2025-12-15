package codemetrics.model;

import java.io.File;
import java.util.List;

public class JavaSourceFile extends SourceFile {

    public JavaSourceFile(File f, List<String> lines) {
        super(f, lines);
    }
}