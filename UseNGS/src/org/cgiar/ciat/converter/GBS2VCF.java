package org.cgiar.ciat.converter;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;

import net.sf.ngstools.variants.io.VCFFileHandler;

public class GBS2VCF {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
		PrintStream outFile = new PrintStream(args[1]);
		
		String str=null;
		String geno=null;
		String header[]=null;
		String arrayCadena[]=null;
		String arrayPosTmp[]=null;
		
		String samplesID[]=null;
		
		String alleles[]=null;
		
		
		
		VCFFileHandler vHandler = new VCFFileHandler();
		
		int i=0;
		
		int pos=0;
		int posSNP=0;
		
		
		// sacar los IDs de las muestras
		header = inFile.readLine().split("\t");
		
		
		samplesID = takeSampleID(header);		
		
		vHandler.printHeader(outFile, samplesID);		
				
		
		while ((str = inFile.readLine()) != null  ) {
			
			arrayCadena = str.split("\t");
			
			//Chr
			//If has ||
			
			
			//removing all ambiguos SNP
/*			if(arrayCadena[5].contains("|")||arrayCadena[5].equals("")||arrayCadena[2].equals("")){
				continue;
			}*/
			
			//All ambiguos except unknown alleles --> made up G A
			
			if(arrayCadena[5].contains("|")||arrayCadena[5].equals("")){
				continue;
			}
			
			
			// never enter
			if(arrayCadena[5].contains("|")){
				arrayPosTmp=arrayCadena[5].split("\\|");
				outFile.print(arrayPosTmp[0]);
				outFile.print("\t");
			}else{
				if(arrayCadena[5].equals("")){
					outFile.print("UNK");
					outFile.print("\t");
				}else{
					outFile.print(arrayCadena[5]);
					outFile.print("\t");
				}
			}
			
					
			//Pos
			//If has ||
			
			if(arrayCadena[4].contains("|")){
				arrayPosTmp=arrayCadena[4].split("\\|");
				outFile.print(arrayPosTmp[0]);
				outFile.print("\t");
			}else{
				//determinando la posicion exacta del SNP si no es un silicon
				pos=Integer.parseInt(arrayCadena[4]);
				if(!arrayCadena[2].equals("")){
					arrayPosTmp = arrayCadena[2].split(":");
					posSNP=Integer.parseInt(arrayPosTmp[0]);
					posSNP++;// base 1
					
					pos=pos+posSNP;
				}
				
				
				
								
				outFile.print(pos);
				outFile.print("\t");
			}

			
			arrayPosTmp = arrayCadena[0].split(":");
			
			//SNP ID
			outFile.print(arrayPosTmp[0]);
			outFile.print("\t");
		
			//Alleles and SNP ID
			
			if(arrayPosTmp.length==2){
				// There are alleles
				alleles=arrayPosTmp[1].split(">");
				//REF
				outFile.print(alleles[0]);
				outFile.print("\t");
				
				//ALT
				outFile.print(alleles[1]);
				outFile.print("\t");
								
			}else if (arrayPosTmp.length==1){
				// No Alleles
				outFile.print("G\tA\t");
				
			}
			
			//QUAL
			outFile.print("255");
			outFile.print("\t");
			
			//FILTER
			outFile.print(".");
			outFile.print("\t");
			
			//INFO
			outFile.print(".");
			outFile.print("\t");
			
			//FORMAT
			outFile.print("GT");
			
			
			//GENOTYPES
			
			for(i=9;i<arrayCadena.length;i++){
				
				outFile.print("\t");
				geno=arrayCadena[i];
				
				if(geno.equals("-")){
					outFile.print("./.");
					
				}else if(geno.equals("0")){
					outFile.print("0/0");
					
				}else if(geno.equals("1")){
					outFile.print("1/1");
					
				} 	
				
			}
			
			outFile.println();
			
		}
		
		inFile.close();
		outFile.close();
		
		
	}
	
	
	public static String [] takeSampleID(String firstLine[]){
		
		ArrayList<String> sampleNames = new ArrayList<String>();
		
			
		for(int i=9;i<firstLine.length;i++){
			sampleNames.add(firstLine[i]);			
		}
		
		return sampleNames.toArray(new String[0]); 
				
	}
	
	
	
	

}
