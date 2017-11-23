package org.cgiar.ciat.converter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;

public class GBS2Fasta {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
		PrintStream outFile = new PrintStream(args[1]);
		
		String str=null;
		
		String arrayCadena[]=null;
		String arrayPosTmp[]=null;
		
		inFile.readLine();
		
		while ((str = inFile.readLine()) != null  ) {
			
			arrayCadena = str.split("\t");
			
			arrayPosTmp = arrayCadena[0].split(":");
			
			//SNP ID
			
			outFile.println(">"+arrayPosTmp[0]+" "+arrayCadena[5]+" "+arrayCadena[4]);
			
			outFile.println(arrayCadena[6]);
							
			
		}
		
		inFile.close();
		outFile.close();
		
		
		
	}

}
