package org.cgiar.ciat.converter;

import java.io.BufferedReader;


import java.io.FileReader;
import java.io.PrintStream;





public class BGS2FastarConverter {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		String str=null;
		String header[]=null;
		String arrayCadena[]=null;
		String sample=null;
		String genotype=null;
		char allele1,allele2;
		boolean iupac=false;
		StringBuffer seq = null;
		int numColum;
		int line=0;
		int i;
//		FastaSequencesHandler handler = new FastaSequencesHandler();
//		DNASequence seqDNA = null;
		
		try{
			BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
			PrintStream out = new PrintStream(args[1]);
			if (args[2]!=null&&args[2].equals("I")){
				iupac=true;
			}
			
			if (args[2]!=null&&args[2].equals("N")){
				iupac=false;
			}
			
			
			header=inFile.readLine().split("\t");
			
			numColum=header.length;
			

			for(i=1;i<numColum;i++){
				inFile = new BufferedReader(new FileReader(args[0]));
				seq=new StringBuffer();
				while ((str = inFile.readLine()) != null ) {
					line++;
					if(line==1){
						sample=header[i];
						continue;
					}
					

					 
					arrayCadena=str.split("\t");
					
					genotype=arrayCadena[i];
					
					if(genotype.length()==1){
						seq.append('N');
					}else{
					
						allele1=genotype.charAt(0);
						allele2=genotype.charAt(2);
						
						if(allele1==allele2){
							seq.append(allele1);
						}else{
							if(!iupac){
								seq.append('N');
							}else{
								seq.append(translateIUPAC(allele1,allele2));
							}
	
							
						}
					}	
				}
				
				line=0;
				out.println(">"+sample);
				out.println(seq.toString());
//				seqDNA = new DNASequence(seq.toString());
//				handler.addSequence(sample, seqDNA);
				
			}
			
//			handler.saveSequences(out, 100);
			out.flush();
			out.close();
			
			inFile.close();
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		

	}
	
	
	public static char translateIUPAC(char allele1, char allele2){
		
		allele1=Character.toUpperCase(allele1);
		allele2=Character.toUpperCase(allele2);
		
		
		if((allele1=='A'&&allele2=='G')||(allele2=='A'&&allele1=='G'))
			return 'R';
		if((allele1=='C'&&allele2=='T')||(allele2=='C'&&allele1=='T'))
			return 'Y';
		if((allele1=='G'&&allele2=='C')||(allele2=='G'&&allele1=='C'))
			return 'S';
		if((allele1=='A'&&allele2=='T')||(allele2=='A'&&allele1=='T'))
			return 'W';
		if((allele1=='G'&&allele2=='T')||(allele2=='G'&&allele1=='T'))
			return 'K';
		if((allele1=='A'&&allele2=='C')||(allele2=='A'&&allele1=='C'))
			return 'M';
		
		
		
		return 'N';
	}

}
