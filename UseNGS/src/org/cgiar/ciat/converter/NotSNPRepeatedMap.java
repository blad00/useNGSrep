package org.cgiar.ciat.converter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import net.sf.ngstools.variants.io.VCFFileHandler;
import net.sf.ngstools.variants.io.VCFFileReader;

public class NotSNPRepeatedMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
		PrintStream outFile = new PrintStream(args[1]);
		
		PrintStream outFileRepeats = new PrintStream(args[2]);
		
		String str=null;
		
		String arrayCadena[]=null;
		String arrayPosTmp[]=null;
		
		VCFFileReader vrReader = new VCFFileReader(args[0]);
		VCFFileHandler vHandler = new VCFFileHandler();
		
		List <String> samplesID = vrReader.getSampleIds();	
		
		vHandler.printHeader(outFile,samplesID.toArray(new String[0]));
		
		TreeSet<String> hashPositions = new TreeSet<String>();
		
			
		
		int linea = 1;
		
		String pos=null;
		
		boolean first = true;
		
		String chr = null;
		
		while ((str = inFile.readLine()) != null  ) {
			
			if(linea<15){
				linea++;
				continue;
		    }
				
				
			arrayCadena = str.split("\t");
			
			if(first){
					
				pos=arrayCadena[1];
				chr=arrayCadena[0];
				outFile.println(str);
				
				hashPositions.add(pos);
							
				first=false;
				
			}else{
				if(chr.equals(arrayCadena[0])){
				
					if(hashPositions.contains(arrayCadena[1])){
						outFileRepeats.println(str);
					}else{
						pos=arrayCadena[1];
						hashPositions.add(pos);
						outFile.println(str);
						
					}
				}else{
					hashPositions.clear();
					chr=arrayCadena[0];
				}
				
				
			}
			
		}
		
		inFile.close();
		outFile.close();
		
		outFileRepeats.close();
		
		
	}
		
		
		
	

}
