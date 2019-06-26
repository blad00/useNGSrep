package org.cgiar.ciat.tareas;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import net.sf.ngstools.variants.CalledGenomicVariant;
import net.sf.ngstools.variants.GenomicVariant;
import net.sf.ngstools.variants.PhredScoreHelper;
import net.sf.ngstools.variants.SNV;
import net.sf.ngstools.variants.io.VCFFileReader;
import net.sf.ngstools.variants.io.VCFRecord;

/*
 * Print distribution of GQ of a VCF file
 */

public class ProcessGQ_DP_VCFFile {
	public static void main(String[] args) throws Exception {
		String filename = args[0];
		VCFFileReader reader = new VCFFileReader(filename);
		// Ids of the samples
		List<String> sampleIds = reader.getSampleIds();
		Iterator<VCFRecord> it = reader.iterator();
		int totalSNPs = 0;
		

		List<CalledGenomicVariant> callsVariant = null;
		CalledGenomicVariant calledVar;

		List<Integer> samplesCount = new ArrayList<Integer>();
		
		Integer itg = 0;

		
		
		HashMap<Short, Integer> mapGQ = new HashMap<Short, Integer>();
		
		double genoProb = 0;
		
		short gqProb = 0;
		
		int itmp;
		
		int totalDataPoints = 0;
		
		int i;
		
		while (it.hasNext()) {
			totalSNPs++;
			VCFRecord record = it.next();
			// Basic information from the variant

			

			callsVariant = record.getCalls();

			for (i = 0; i < callsVariant.size(); i++) {
				totalDataPoints++;
				
				calledVar = callsVariant.get(i);

				genoProb = calledVar.getGenotypeProbability();
				
				gqProb = PhredScoreHelper.calculatePhredScore(1-genoProb);
				
				if(!mapGQ.containsKey(gqProb)){
					
					mapGQ.put(gqProb, 1);
					
				}else{
					itmp = mapGQ.get(gqProb);
					itmp++;
					mapGQ.put(gqProb, itmp);
					
				}
							
				
			}

		
		}
		
		Iterator<Short> itGQset = mapGQ.keySet().iterator();
		
		while(itGQset.hasNext()){
			gqProb = itGQset.next();
			System.out.print(gqProb);
			System.out.print("\t");			
			System.out.println(mapGQ.get(gqProb));

		}
		System.out.println("Total data points\t"+totalDataPoints);
		System.out.println("Total SNPs\t"+totalSNPs);
		
				
		reader.close();

	}
}

