package org.cgiar.ciat.tareas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import net.sf.ngstools.variants.CalledGenomicVariant;
import net.sf.ngstools.variants.GenomicVariant;
import net.sf.ngstools.variants.io.VCFFileReader;
import net.sf.ngstools.variants.io.VCFRecord;

public class SplitProfile {
	/**
	 * 
	 * program that goes over the VCF checking if 2 groups have completely opposite alleles, No mssing data or hetero allowed
	 */

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		String filename = args[0];
		VCFFileReader reader = new VCFFileReader(filename);
		//file group interesting
		String findGroupFile = args[1];
		BufferedReader groupFile = null;
		groupFile = new BufferedReader(new FileReader(findGroupFile));
		//file left group 
		String findLeftGroupFile = args[2];
		BufferedReader leftGroupFile = null;
		leftGroupFile = new BufferedReader(new FileReader(findLeftGroupFile));
		
		String outputFile = args[3];
		
		PrintWriter outFile = new PrintWriter(new FileOutputStream(outputFile));
	
		String str;
		
		List<String> group = new ArrayList<String>();
		List<String> leftGroup = new ArrayList<String>();
		
		
		//get the relevant group
		while ((str = groupFile.readLine()) != null) {
			group.add(str);
		}
		
		//get the left group
		while ((str = leftGroupFile.readLine()) != null) {
			leftGroup.add(str);
		}
		
		groupFile.close();
		leftGroupFile.close();
				
		//Ids of the samples
		List<String> sampleIds = reader.getSampleIds(); 
		Iterator<VCFRecord> it = reader.iterator();
		
		List<CalledGenomicVariant> callsVariant = null;
		CalledGenomicVariant calledVar;
		
		int numSamples = sampleIds.size(); 
		
		int totalHomoRef = 0;
		int totalHomoAlt = 0;
		int totalLeftHomoRef = 0;
		int totalLeftHomoAlt = 0;
		
		int locationsSample;
		
		while(it.hasNext()) {
			VCFRecord record = it.next();
			GenomicVariant variant = record.getVariant();
			callsVariant = record.getCalls();
			
			//check if the group has the same profile
			for (String sample : group) {
				locationsSample = sampleIds.indexOf(sample);
				calledVar = callsVariant.get(locationsSample);
				if(calledVar.isHomozygousReference())
					totalHomoRef++;
				if(calledVar.isHomozygous())
					totalHomoAlt++;
			}
			
			if(totalHomoRef == group.size() || totalHomoAlt == group.size()) {
				//if you are here is because the first group is all equal
				for (String sample : leftGroup) {
					locationsSample = sampleIds.indexOf(sample);
					calledVar = callsVariant.get(locationsSample);
					if(calledVar.isHomozygousReference())
						totalLeftHomoRef++;
					if(calledVar.isHomozygous())
						totalLeftHomoAlt++;
				}
				
				if(totalHomoRef == group.size() && totalLeftHomoAlt == leftGroup.size())
					outFile.println(variant.getSequenceName()+"\t"+variant.getFirst());
				
				if(totalLeftHomoRef == group.size() && totalHomoAlt == leftGroup.size())
					outFile.println(variant.getSequenceName()+"\t"+variant.getFirst());
			}
			
			//reset counters
			
			totalHomoRef = 0;
			totalHomoAlt = 0;
			totalLeftHomoRef = 0;
			totalLeftHomoAlt = 0;
			
		}
		
		
		reader.close();
		outFile.close();

	}

}
