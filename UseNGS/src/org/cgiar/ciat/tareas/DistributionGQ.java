package org.cgiar.ciat.tareas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sf.ngstools.statistics.DistributionCalculator;
import net.sf.ngstools.variants.CalledGenomicVariant;
import net.sf.ngstools.variants.PhredScoreHelper;
import net.sf.ngstools.variants.io.VCFFileReader;
import net.sf.ngstools.variants.io.VCFRecord;

public class DistributionGQ {
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

		DistributionCalculator gqDistrubution = new DistributionCalculator(0, 99, 1);
		
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
				
				gqDistrubution.processDatapoint(gqProb);
							
				
			}

		
		}
		
		
		gqDistrubution.printDistribution(System.out);
		
		
				
		reader.close();

	}
}

