package org.cgiar.ciat.converter;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class VCF2RBLUP {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader inFileVCF = new BufferedReader(new FileReader(args[0]));
		PrintWriter outFile = new PrintWriter(new FileOutputStream(args[2]));
		
		String str=null;
		String arrayStr[]=null;
		
		ArrayList<String> samples = new ArrayList<String>();
		
		int i=0;
		
		boolean header=false;
		
		Iterator<String> it;
		
		while ((str = inFileVCF.readLine()) != null ) {
			
			arrayStr=str.split("\t");

			
// capturo ids			
			if(!header){
				if(arrayStr[0].equals("#CHROM")){
					for(i=9;i<arrayStr.length;i++){
						samples.add(arrayStr[i]);
					}
					header=true;
					
					it = samples.iterator();
					
					while(it.hasNext()){
						outFile.print(it.next());
					}
					
					
				}else{
					continue;
				}
			}
			
			
			
			
		}
		

		
		inFileVCF.close();
		outFile.close();
	}

}
