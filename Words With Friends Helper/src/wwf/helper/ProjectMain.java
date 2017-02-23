package wwf.helper;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Scanner;

public class ProjectMain
{
	public static void main(String args[]) throws IOException
	{
		int totalWords=0;
		String input;
		String buffer[];
		Scanner reader = new Scanner(System.in);
		File dbFile;
		FileInputStream dbStream;
		DataInputStream dbData;
		BufferedReader dbReader;
		
		dbFile = new File("res/wordlist");
		
		//Counting lines in database
		LineNumberReader dbLines;
		try
		{
			dbLines = new LineNumberReader(new FileReader(dbFile));
			dbLines.skip(Long.MAX_VALUE);
			totalWords=dbLines.getLineNumber()+1;
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Aborting...");
			System.exit(0);
		}
		
		System.out.println("Search a database of "+totalWords+" words.");
		
		//Creating buffer
		buffer=new String[totalWords];
		dbStream = new FileInputStream(dbFile);
		dbData=new DataInputStream(dbStream);
		dbReader=new BufferedReader(new InputStreamReader(dbData));
		
		for(int i=0;i<totalWords;i++)
			buffer[i]=dbReader.readLine();
		
		dbStream.close();
		
		//Reading input
		System.out.print("Your word : ");
		input = reader.nextLine();
		reader.close();
		
		if(isValidWord(input, buffer))
			System.out.println(input+" is a valid word!");
		else
			System.out.println(input+" is misspelled or a Proper Noun!");
	}
	
	static boolean isValidWord(String word,String buffer[])
	{
		int i=0;
		word=word.toLowerCase();
		for(i=0;i<buffer.length;i++)
		{
			System.out.println(buffer[i]);
			if(word.equals(buffer[i].toString())==true)
				return true;
		}
		return false;
	}
}