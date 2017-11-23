package org.cgiar.ciat.gwas;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import java.util.Arrays;
import java.util.List;


public class Pfilter {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		/* 0 archivostats
		   1 archivo salida
		   2 traits separados por ,
		   3 limite Pvalue
		*/
		
		
		
		BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
		PrintWriter outFile = new PrintWriter(new FileOutputStream(args[1]));
		//String strArrayBest[]=;
		
		List <String> listTraits=Arrays.asList(args[2].split(","));
		
		double limit = Double.parseDouble(args[3]);
		
		
		String str=null;
		
		String strStatsTmp[]=null;
		
		
		double pvalue=1000;
		
		while ((str = inFile.readLine()) != null) {
			
			strStatsTmp = str.split("\t");
			
			try {
				pvalue=Double.parseDouble(strStatsTmp[6]);
			} catch (Exception e) {
				// TODO: handle exception
				pvalue=1000;
			}
			
			if(listTraits.get(0).equalsIgnoreCase("none")&&(pvalue<limit)){
				outFile.println(strStatsTmp[2]+"\t"+strStatsTmp[3]+"\t"+strStatsTmp[3]+"\t"+strStatsTmp[0]+"\t"+strStatsTmp[6]);
			}else{
			
				if((listTraits.contains(strStatsTmp[0]))&&(pvalue<limit)){
						outFile.println(strStatsTmp[2]+"\t"+strStatsTmp[3]+"\t"+strStatsTmp[3]+"\t"+strStatsTmp[0]+"\t"+strStatsTmp[6]);			
				}
			}
		}
		
		inFile.close();
		outFile.close();
		
		
	}	
	
}
