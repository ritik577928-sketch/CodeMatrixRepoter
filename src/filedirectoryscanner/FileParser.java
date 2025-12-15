package filedirectoryscanner;

import codemetrics.model.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
	 
	public List<JavaSourceFile> parsedFilePath(List<File> files) throws Exception {
		List<JavaSourceFile> parsedFiles = new ArrayList<>();
		
		for(File file : files) {
			
			
			if(!file.exists() || !file.isFile() || file ==null) {
				continue;
			}
			parsedFiles.add(parseSingleFile(file));
		}
		return parsedFiles;
		
		
	}
	public JavaSourceFile parseSingleFile(File file) throws IOException {
		
		List<String> lines = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        

        return new JavaSourceFile(file, lines);
		}
		}
	}