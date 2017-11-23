package org.cgiar.ciat.snp.filter;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import net.sf.ngstools.variants.CalledGenomicVariant;
import net.sf.ngstools.variants.GenomicVariant;
import net.sf.ngstools.variants.io.VCFFileReader;
import net.sf.ngstools.variants.io.VCFRecord;

public class SNPcounter {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		String filename = args[0];
		VCFFileReader reader = new VCFFileReader(filename);
		List<String> sampleIds = reader.getSampleIds();
		Iterator<VCFRecord> it = reader.iterator();
		int totalSNPs = 0;

		double totalPoints = 0;
		
		int totalHomo = 0;
		int totalHetero = 0;
		double totalMiss = 0;

		int i = 0;

		List<CalledGenomicVariant> callsVariant = null;
		CalledGenomicVariant calledVar;

	
		VCFRecord record = null;
		// Basic information from the variant

		GenomicVariant variant = null;

		callsVariant = null;

		while (it.hasNext()) {
			
			record = it.next();
			// Basic information from the variant
			totalSNPs++;
			variant = record.getVariant();

			callsVariant = record.getCalls();

			for (i = 0; i < callsVariant.size(); i++) {
				calledVar = callsVariant.get(i);
				totalPoints++;
				if (calledVar.isHomozygous())
					totalHomo++;

				if (calledVar.isHeterozygous())
					totalHetero++;

				if (calledVar.isUndecided())
					totalMiss++;

			}	

		}
		
		
		System.out.println("Total SNPs"+"\t"+totalSNPs);
		
		System.out.println("Total DataPoints"+"\t"+totalPoints);
		
		System.out.println("Total MissPoints"+"\t"+totalMiss);
		
		System.out.println("% MissPoints"+"\t"+((totalMiss/totalPoints)*100));
		
		System.out.println("Total HomoPoints"+"\t"+totalHomo);
		
		System.out.println("Total HetePoints"+"\t"+totalHetero);
		
		reader.close();

	}

}
