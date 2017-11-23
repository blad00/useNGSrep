package org.cgiar.ciat.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;


import net.sf.ngstools.variants.GenomicVariant;
import net.sf.ngstools.variants.io.VCFFileReader;
import net.sf.ngstools.variants.io.VCFRecord;

public class RegionOperations {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		countSNPsManyReg(args[0], args[1]);
		
		
		
		
	}
	
	
	public static void countSNPsManyReg(String vcfinFile, String indexFile) throws Exception{
		VCFFileReader readerVCF = new VCFFileReader(vcfinFile);
		BufferedReader inFile = new BufferedReader(new FileReader(indexFile));
		Iterator<VCFRecord> it = readerVCF.iterator();
		VCFRecord record=null;
		GenomicVariant var=null;
		
		
		
		int posini=0;
		int posfin=0;
		
		int posvar=0;
		
		String ch="";
		
		int totalSNPs=0;
		
		String str=null;
		String strArrayTmp[]=null;
		
		boolean first=true;
		
		
		while ((str = inFile.readLine()) != null) {
			
			strArrayTmp=str.split("\t");
			
			
			//Si hay regiones repetidas 
			if(ch.equals(strArrayTmp[0])&&posini==Integer.parseInt(strArrayTmp[1])&&posfin==Integer.parseInt(strArrayTmp[2])){
				System.out.println(ch+"\t"+posini+"\t"+posfin+"\t"+totalSNPs);
				continue;
			}
			
			
			
			ch=strArrayTmp[0];
			posini=Integer.parseInt(strArrayTmp[1]);
			posfin=Integer.parseInt(strArrayTmp[2]);
			
			totalSNPs=0;
			
			//para considerar el SNP actual que termino el conteo anterior
			if((posvar<=posfin)&&(posvar>=posini)&&(var!=null)&&(ch.equals(var.getSequenceName()))){
				totalSNPs++;
			}
			
			
			
			if(totalSNPs==0&&!first){
				System.out.println(ch+"\t"+posini+"\t"+posfin+"\t"+totalSNPs);
				continue;
			}
			
			
						
			while (it.hasNext()) {
				record = it.next();
				var=record.getVariant();
				posvar=var.getFirst();
				first=false;
				
				if((posvar<=posfin)&&(posvar>=posini)&&(ch.equals(var.getSequenceName()))){
					totalSNPs++;
				}else{
					break;
				}
				
			}
			
			System.out.println(ch+"\t"+posini+"\t"+posfin+"\t"+totalSNPs);
			
		
		}
		
		readerVCF.close();
		inFile.close();
	
	}

}
