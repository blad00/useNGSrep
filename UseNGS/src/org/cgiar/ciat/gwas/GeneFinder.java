package org.cgiar.ciat.gwas;



import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Iterator;


import net.sf.ngstools.genome.GenomicRegion;
import net.sf.ngstools.genome.GenomicRegionSortedCollection;

import net.sf.ngstools.variants.io.VCFFileReader;
import net.sf.ngstools.variants.io.VCFRecord;

public class GeneFinder {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		/* 0 VCF con picos interesantes
		   1 archivo salida
		   2 BD de genes
		   3 tamano de ventana
		*/
		
		String filename = args[0];
		VCFFileReader reader = new VCFFileReader(filename);
		
		Iterator<VCFRecord> it = reader.iterator();
		GenomicRegionSortedCollection<GenomicRegion> genomicRegions = new GenomicRegionSortedCollection<GenomicRegion>();
		GenomicRegion snpTmp=null;
		
		PrintWriter outFile = new PrintWriter(new FileOutputStream(args[1]));
		
		BufferedReader inGeneBD = new BufferedReader(new FileReader(args[2]));
		
		int window = Integer.parseInt(args[3]); 
		
		String str = null;
		
		String strGeneTmp[]=null;
		
		int posGenStart=0;
		int posGenEnd=0;
		String genSeqName=null;
						
		Iterator<GenomicRegion> itSNPs = null; 
		
		GenomicRegionSortedCollection<GenomicRegion> snpsInGene;
				
		while (it.hasNext()) {
	
			VCFRecord record = it.next();
			snpTmp=record.getVariant();
			genomicRegions.add(snpTmp);
						
		}	
		
		
		boolean firstTime=true;
		
		outFile.println("Chromosome Name	Gene Start (bp)	Gene End (bp)	Gene Name	Source	TypeDef	ID_Def	Def_Desc	GWASChr	GWASPos");
		
		while ((str = inGeneBD.readLine()) != null) {
			
			if(firstTime){
				firstTime=false;
				continue;
			}
			
			strGeneTmp = str.split("\t");
			genSeqName=strGeneTmp[0];
			posGenStart = Integer.parseInt(strGeneTmp[1])-window;
			if(posGenStart<0) posGenStart=0;
			posGenEnd = Integer.parseInt(strGeneTmp[2])+window;
			snpsInGene = genomicRegions.findSpanningRegions(genSeqName, posGenStart, posGenEnd);
			if(!snpsInGene.isEmpty()){
				
				itSNPs = snpsInGene.iterator();
				
				outFile.print(str);
				
				while(itSNPs.hasNext()){
					snpTmp = itSNPs.next();
					outFile.print("\t"+snpTmp.getSequenceName()+"\t"+snpTmp.getFirst());

				}
				outFile.println();
			}
		
		}	
	
	reader.close();
	inGeneBD.close();
	outFile.close();
	
	}
	
	

}
