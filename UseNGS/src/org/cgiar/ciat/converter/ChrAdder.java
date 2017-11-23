package org.cgiar.ciat.converter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;

public class ChrAdder {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
		PrintStream outFile = new PrintStream(args[1]);
		
		String str=null;
		
		//solo trabaja para VCF sin cabecera
		String arrayCadena[]=null;
		
		int iLinea=1;
		
		while ((str = inFile.readLine()) != null  ) {
					
			arrayCadena = str.split("\t");
			
			outFile.print("Chr"+arrayCadena[0]);
			
			for (int i=1;i<arrayCadena.length;i++){
				
				outFile.print("\t"+arrayCadena[i]);
				
			}
			
			outFile.println();
			
		}
		
		inFile.close();
		outFile.close();
	}

}
