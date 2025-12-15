package filedirectoryscanner;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileDirectoryDemo {
	
	
	    public List<File> scanDir(String filePath) throws IOException{
		
		//filePath=sc.nextLine();
		
		File directory = new File(filePath);
		
		if(!directory.exists()) {
			throw new IOException("Directory not found "+ directory);
		
		}
		if(!directory.isDirectory()) {
			throw new IOException("Path is not directory "+directory);
		}
		
		List<File> javaFiles = new ArrayList <>();
			
		Files.walk(directory.toPath())
		             .filter(path -> Files.isRegularFile(path))
		             .filter(path -> path.toString().endsWith(".java"))
		             .forEach(path -> javaFiles.add(path.toFile()));
		return javaFiles;
	}

}