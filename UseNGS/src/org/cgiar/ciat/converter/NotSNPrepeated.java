package org.cgiar.ciat.converter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.ngstools.variants.io.VCFFileHandler;
import net.sf.ngstools.variants.io.VCFFileReader;

public class NotSNPrepeated {

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
		
		
		int linea = 1;
		
		String pos=null;
		
		boolean first = true;
		
		while ((str = inFile.readLine()) != null  ) {
			
			if(linea<15){
				linea++;
				continue;
		    }
				
				
			arrayCadena = str.split("\t");
			
			if(first){
					
				pos=arrayCadena[1];
				outFile.println(str);
				first=false;
				
			}else{
				
				if(pos.equals(arrayCadena[1])){
					outFileRepeats.println(str);
				}else{
					outFile.println(str);
					pos=arrayCadena[1];
				}
				
				
			}
			
			
			
						
				
			
			
							
			
		}
		
		inFile.close();
		outFile.close();
		
		outFileRepeats.close();
		
		
	}
		
		
		
	

}
