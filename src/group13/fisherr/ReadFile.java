package group13.fisherr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	
	private String path;
	
	public ReadFile(String filepath){
		path = filepath;
	}
	
	public String[] openFile() throws IOException{
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		
		int numberOfLines = readLines();
		String[] textData = new String[numberOfLines];
		
		
		
		for(int i = 0; i < numberOfLines; i++){
		
			textData[i] = textReader.readLine();
		}
		textReader.close();
		return textData;
		
		
	}
	
	public int readLines()throws IOException{
		FileReader fileToRead = new FileReader(path);
		BufferedReader bf = new BufferedReader(fileToRead);
		
		//remove any whitespace for lines
		
		
		
		String line;
		int numberOfLines = 0;
		
		while((line=bf.readLine())!=null ){
			numberOfLines++;
		}
		bf.close();
		return numberOfLines;
	}

}
