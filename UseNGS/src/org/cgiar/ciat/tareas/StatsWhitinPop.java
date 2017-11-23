package org.cgiar.ciat.tareas;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import net.sf.ngstools.variants.CalledGenomicVariant;
import net.sf.ngstools.variants.GenomicVariant;
import net.sf.ngstools.variants.io.VCFFileReader;
import net.sf.ngstools.variants.io.VCFRecord;

public class StatsWhitinPop {

	public static void main(String[] args) throws Exception {
		String filename = args[0];
		VCFFileReader reader = new VCFFileReader(filename);
		String outputFile = args[1];
		
		PrintWriter outFile = new PrintWriter(new FileOutputStream(outputFile));
		
		//Ids of the samples
		List<String> sampleIds = reader.getSampleIds(); 
		Iterator<VCFRecord> it = reader.iterator();
		
		List<CalledGenomicVariant> callsVariant = null;
		CalledGenomicVariant calledVar;
		
		int numSamples = sampleIds.size(); 
		
		int totalHomoRef = 0;
		int totalHomoAlt = 0;
		int totalHetero = 0;
		int totalNoCall = 0;
		
		outFile.println("CHROM"+"\t"+"POS"+"\t"+"#NoCalls"+"\t"+"#HomoRef"+"\t"+"#HomoAlt"+"\t"+"#Hetero"+"\t"+"HomoWhitin");
		
		boolean homoWhitin = false;
		
		int actualCalls = 0;
		
		while(it.hasNext()) {
			VCFRecord record = it.next();
			GenomicVariant variant = record.getVariant();
			//Variant info
			outFile.print(variant.getSequenceName()+"\t"+variant.getFirst()+"\t");
			
			callsVariant = record.getCalls();

			//go over the genotypes called for this variant
			for(int i=0;i<callsVariant.size();i++) {
				calledVar = callsVariant.get(i);
				
				
				
				if (calledVar.isUndecided()) 
					totalNoCall++;
				
				if (calledVar.isHomozygousReference())
					totalHomoRef++;
					
				
					
				
				if (calledVar.isHeterozygous())
					totalHetero++;
	
			}
			actualCalls = numSamples-totalNoCall;
			totalHomoAlt = numSamples-totalNoCall-totalHomoRef-totalHetero;
			if((actualCalls==totalHomoRef)||(actualCalls==totalHomoAlt)||(actualCalls==totalHetero)){
				homoWhitin = true;
			}
			
			
			
			outFile.println(totalNoCall+"\t"+totalHomoRef+"\t"+totalHomoAlt+"\t"+totalHetero+"\t"+homoWhitin);
			
			actualCalls = 0;
			homoWhitin = false;
			totalNoCall = 0;
			totalHomoRef = 0;
			totalHomoAlt = 0;
			totalHetero = 0;
			
		}
		
		reader.close();
		outFile.close();
		
	}

}

