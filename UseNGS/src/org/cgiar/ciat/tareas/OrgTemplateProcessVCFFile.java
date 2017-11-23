package org.cgiar.ciat.tareas;

import java.util.Iterator;
import java.util.List;

import net.sf.ngstools.variants.CalledGenomicVariant;
import net.sf.ngstools.variants.GenomicVariant;
import net.sf.ngstools.variants.io.VCFFileReader;
import net.sf.ngstools.variants.io.VCFRecord;


public class OrgTemplateProcessVCFFile {
	public static void main(String[] args) throws Exception {
		String filename = args[0];
		VCFFileReader reader = new VCFFileReader(filename);
		//Ids of the samples
		List<String> sampleIds = reader.getSampleIds(); 
		Iterator<VCFRecord> it = reader.iterator();
		while(it.hasNext()) {
			VCFRecord record = it.next();
			//Basic information from the variant
			GenomicVariant variant = record.getVariant();
			//Genotypes called for this variant. This list has the same length as the
			//size of the ids of the samples. So, for example, the genotype of the sample
			//with id sampleIds.get(i) for this variant is genotypeCalls.get(i)
			List<CalledGenomicVariant> genotypCalls = record.getCalls();
			//Do your stuff here
			//For example, if you want to go over the genotypes called for this variant
			for(int i=0;i<genotypCalls.size();i++) {
				CalledGenomicVariant genotypeCall = genotypCalls.get(i);
				//And then you can do whatever you need
				
			}
			
			
		}
	}
}
