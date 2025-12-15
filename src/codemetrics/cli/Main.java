package codemetrics.cli;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import filedirectoryscanner.FileDirectoryDemo;
import filedirectoryscanner.FileParser;

public class Main {

	public static void main(String[] args) throws Exception {
		CommandControlCenter controller = new CommandControlCenter();
		
		Scanner sc = new Scanner(System.in);
  	  
  	  
//  	  FileDirectoryDemo fo = new FileDirectoryDemo();
//  	  FileParser fp1 = new FileParser();
  	  
  	  System.out.println("Give your file path for Scanning");
  	  String filePath =sc.nextLine();
  	  
//		fo.scanDir(filePath);
//		List<File> pfp1 = fo.scanDir(filePath);
//		fp1.parsedFilePath(pfp1);
//		
//		
//		String command = String.join(" ", args);
		controller.execution("scanDir "+filePath);
		controller.execution("summarize");
		controller.execution("export");
		sc.close();

	}

}