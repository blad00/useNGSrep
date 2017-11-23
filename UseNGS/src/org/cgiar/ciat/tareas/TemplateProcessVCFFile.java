package org.cgiar.ciat.tareas;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.ngstools.variants.CalledGenomicVariant;
import net.sf.ngstools.variants.GenomicVariant;
import net.sf.ngstools.variants.SNV;
import net.sf.ngstools.variants.io.VCFFileReader;
import net.sf.ngstools.variants.io.VCFRecord;

public class TemplateProcessVCFFile {
	public static void main(String[] args) throws Exception {
		String filename = args[0];
		VCFFileReader reader = new VCFFileReader(filename);
		// Ids of the samples
		List<String> sampleIds = reader.getSampleIds();
		Iterator<VCFRecord> it = reader.iterator();
		int totalSNPs = 0;
		int totalGenotyped = 0;
		int totalsampleGenotyped = 0;
		int totalHomo = 0;
		int totalHetero = 0;
		int lessHalfGenSample = 0;
		int i = 0;
		int halfSizeSamples = sampleIds.size() / 2;

		List<CalledGenomicVariant> callsVariant = null;
		CalledGenomicVariant calledVar;

		List<Integer> samplesCount = new ArrayList<Integer>();
		int itmp;
		Integer itg = 0;

		for (i = 0; i < sampleIds.size(); i++) {

			samplesCount.add(itg);

		}

		while (it.hasNext()) {
			totalSNPs++;
			VCFRecord record = it.next();
			// Basic information from the variant

			GenomicVariant variant = record.getVariant();

			callsVariant = record.getCalls();

			for (i = 0; i < callsVariant.size(); i++) {
				calledVar = callsVariant.get(i);

				if (!calledVar.isUndecided()) {
					totalGenotyped++;
					totalsampleGenotyped++;
					itmp = samplesCount.get(i).intValue();
					samplesCount.remove(i);
					samplesCount.add(i, ++itmp);
				}else{
					continue;
				}

				if (calledVar.isHomozygous())
					totalHomo++;

				if (calledVar.isHeterozygous())
					totalHetero++;

			}

			if (totalsampleGenotyped < halfSizeSamples)
				lessHalfGenSample++;

			totalsampleGenotyped=0;
		}

		System.out.println("Total Genotipados: " + totalGenotyped);
		System.out.println("Total Homo: " + totalHomo);
		System.out.println("Total Hetero: " + totalHetero);
		System.out.println("Número de variantes que tienen menos de la mitad de las muestras genotipadas: "	+ lessHalfGenSample);

		System.out.println("Muestras con menos de la mitad de los SNPs genotipados");
		System.out.println("total SNPs: " + totalSNPs);
		for (i = 0; i < samplesCount.size(); i++) {

			if (samplesCount.get(i) < (totalSNPs / 2)) {
				System.out.println(sampleIds.get(i));
			}

		}

	}
}

// record.getVariant().
// Genotypes called for this variant. This list has the same length as the
// size of the ids of the samples. So, for example, the genotype of the sample
// with id sampleIds.get(i) for this variant is genotypeCalls.get(i)
// List<CalledGenomicVariant> genotypCalls = record.getCalls();
// Do your stuff here
// For example, if you want to go over the genotypes called for this variant
/*
 * for(int i=0;i<genotypCalls.size();i++) { CalledGenomicVariant genotypeCall =
 * genotypCalls.get(i); //And then you can do whatever you need
 * 
 * }
 */
