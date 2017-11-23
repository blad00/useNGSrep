package org.cgiar.ciat.converter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MissMatch {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader inFileVCF1 = new BufferedReader(new FileReader(args[0]));
		
		BufferedReader inFileVCF2 = new BufferedReader(new FileReader(args[1]));
		
		String str1=null;
		String cadena1[]=null;
		
		String str2=null;
		String cadena2[]=null;
		
		
		for (int i=0;i<=41;i++){
			str1 = inFileVCF1.readLine();
		}
		
		str2 = inFileVCF2.readLine();
			
		
		
		
		while ((str1 = inFileVCF1.readLine()) != null  ) {
			str2 = inFileVCF2.readLine();
			
			cadena1 = str1.split("\t");
			
			cadena2 = str2.split("\t");
				
			if(!(cadena1[0].equals(cadena2[2])&&cadena1[1].equals(cadena2[3]))){
				System.out.println(cadena1[0]+"VCF "+cadena1[1]);
				System.out.println(cadena2[2]+"HAP "+cadena1[3]);
				break;
			}
			
		}
		
		inFileVCF1.close();
		inFileVCF2.close();
		
	}
		
		
	

}
