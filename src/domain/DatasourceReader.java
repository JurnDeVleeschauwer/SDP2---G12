package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatasourceReader {


	public static <T> List<T> readFile(File file) throws FileNotFoundException {
		FileReader reader = new FileReader(file);
		Scanner s = new Scanner(reader);
		List<T> dataToProcess = new ArrayList<>(); 

		while(s.hasNext()) {
			dataToProcess.add((T) s); 
		}
		
		
		
		
		return dataToProcess; 
		
		
		
	}
	
}

