package org.cgiar.ciat.converter;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.PrintStream;

public class CorrectVCFfromTassel {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
		PrintStream outFile = new PrintStream(args[1]);
		
		String str=null;
		
		String header[]=null;
		String arrayCadena[]=null;
		
		int iLinea=1;
		
		while ((str = inFile.readLine()) != null  ) {
			if (iLinea > 11){
			
				arrayCadena= str.split("\t");
				
				arrayCadena[5]="255";
				
				outFile.println(array2String(arrayCadena));
				
			}else{
				outFile.println(str);
			}
			
			iLinea++;
			
		}
		
		inFile.close();
		outFile.close();
		
	}
	
	
	public static String array2String(String [] array){
		
		String fullString = array[0];
		
		for(int i=1;i<array.length;i++){
			
			fullString=fullString+"\t"+array[i];
		}
		
		
		return fullString;
	}

}
