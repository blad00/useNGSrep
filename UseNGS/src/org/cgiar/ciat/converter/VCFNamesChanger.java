package org.cgiar.ciat.converter;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class VCFNamesChanger {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader inFileVCF = new BufferedReader(new FileReader(args[0]));
		BufferedReader inFileNames = new BufferedReader(new FileReader(args[1]));
		PrintWriter outFile = new PrintWriter(new FileOutputStream(args[2]));

		HashMap<String, String> mapNames = new HashMap<String, String>();
		
		String str=null;
		String arrayStr[]=null;
		
		boolean headerMod = false;
		
		while ((str = inFileNames.readLine()) != null ) {
			
			arrayStr=str.split("\t");
			mapNames.put(arrayStr[0], arrayStr[1]);
		
		}	
		
		
		while ((str = inFileVCF.readLine()) != null ) {
			
			
			
			if(headerMod){
				outFile.println(str);
				continue;
			}
			
			arrayStr=str.split("\t");
			
			
			if(arrayStr[0].equals("#CHROM")){
				
				for(int i=0;i<arrayStr.length;i++){
					
					if(i!=0)
						outFile.print("\t");
					
					if(mapNames.containsKey(arrayStr[i])){
						outFile.print(mapNames.get(arrayStr[i]));
					}else{
						outFile.print(arrayStr[i]);
					}
										
				}
				headerMod=true;
				outFile.println();
				
			}else{
				outFile.println(str);
			}
				
			
			
		}
		
		
				
		
		inFileVCF.close();
		inFileNames.close();
		outFile.close();
		
		
	}

}
