package labb_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SeekText {

	public static void main(String[] args) {
		
		Scanner keyboardInput = new Scanner(System.in);
		String userInput;
		String userPath;
		System.out.println("Enter the file name and path: ");
		userPath=keyboardInput.nextLine();
		System.out.println("Enter the text : ");
		userInput=keyboardInput.nextLine();
		
		readFile(userPath,userInput); 
		keyboardInput.close();
	}

	private static void readFile(String filePath, String sText) {
		File file = new File(filePath);
		printInfo(file, sText);
	}
	
//	A function to print information about the file
	public static void printInfo(File file,String sText) {
		if (file.isFile()) {
			Scanner scanner= null;
			
			try {
				scanner = new Scanner(file);
				int count=0;
				while(scanner.hasNext()) {
					if(scanner.next().matches(sText)) {
						count++;
					}
				}
				if (count==0){
					System.out.println("Nothing similar found");
				} else {
					System.out.println("Word : ("+ sText + ") found in the file ("+file.getAbsolutePath()+") "+count+" time(s)");
				}
			} catch (FileNotFoundException e) {
				System.out.println("No file found.");
			} finally {
				if(scanner != null) {
					scanner.close();
				}
			}
		} else if(file.isDirectory()) {
			File[] dirList = file.listFiles();
			if(dirList!=null) {
				for (int i=0; i< dirList.length; i++) {
					File f= dirList[i];
					printInfo(f,sText);
				}
			} else {
				System.out.println("It is an unreadable destination");
			}
		} else {
			System.out.println("Something different than a file or a directory, probably not exist");
		}
	}
}

