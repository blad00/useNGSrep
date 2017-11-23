package org.cgiar.ciat.converter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sf.ngstools.genome.GenomeAssembly;
import net.sf.ngstools.genome.GenomicRegionComparator;
import net.sf.ngstools.sequences.DNASequence;
import net.sf.ngstools.variants.CalledGenomicVariant;
import net.sf.ngstools.variants.CalledGenomicVariantImpl;
import net.sf.ngstools.variants.CalledSNV;
import net.sf.ngstools.variants.GenomicVariantAnnotation;
import net.sf.ngstools.variants.SNV;
import net.sf.ngstools.variants.io.VCFFileHandler;
import net.sf.ngstools.variants.io.VCFRecord;


public class HapmapToVCF {
	public static void main(String[] args) throws Exception {
		String hapmapFile = args[0];
		GenomeAssembly genome = null;
		
		PrintStream outFile = new PrintStream(args[2]);
		
		if(!args[1].equals("none")) {
			String reference = args[1];
			genome = new GenomeAssembly(reference);
		}
		 
		List<VCFRecord> records = new ArrayList<VCFRecord>();
		FileInputStream fis = new FileInputStream(hapmapFile);
		BufferedReader in = new BufferedReader(new InputStreamReader(fis));
		String line = in.readLine();
		String[] items = line.split("\t| ");
		String [] sampleIds = new String [items.length-11];
		for(int i=11;i<items.length;i++) {
			sampleIds[i-11] = items[i];
		}
		line = in.readLine();
		while (line != null) {
			items = line.split("\t| ");
			String seqName = items[2];
			int pos = Integer.parseInt(items[3]);
			char a1 = Character.toUpperCase(items[1].charAt(0));
			char a2 = Character.toUpperCase(items[1].charAt(2));
			if(DNASequence.BASES_STRING.indexOf(a1)<0 || DNASequence.BASES_STRING.indexOf(a2)<0) {
				line=in.readLine();
				continue;
			}
			char refBase = a1;
			char altBase = a2;
			boolean complement = false;
			boolean inRepeat = false;
			if(genome!=null) {
				char refBaseMasked = genome.getReferenceBase(seqName, pos);
				refBase = Character.toUpperCase(refBaseMasked);
				if(DNASequence.BASES_STRING.indexOf(refBase)<0) {
					line=in.readLine();
					continue;
				}
				inRepeat = (refBase!=refBaseMasked);
				
				
				if(a1 == refBase) {
					altBase = a2;
				} else if (a2 == refBase) {
					altBase = a1;
				} else {
					//SNP in the negative strand
					complement = true;
					a1 = DNASequence.getComplement(a1);
					a2 = DNASequence.getComplement(a2);
					if(a1 == refBase) {
						altBase = a2;
					} else if (a2 == refBase) {
						altBase = a1;
					} else {
						//Alleles not consistent with reference
						System.err.println("Inconsistent alleles for  "+seqName+":"+pos+" alleles: "+items[1]+" reference: "+refBase);
						line=in.readLine();
						continue;
					}
				}
			}
			SNV snv = new SNV(seqName, pos, refBase, altBase);
			List<CalledGenomicVariant> calls = new ArrayList<CalledGenomicVariant>();
			for(int i=11;i<items.length;i++) {
				if(items[i].length()==3){
					a1 = items[i].charAt(0);
					//TODO: Check official Hapmap format for genotypes (AA A/A or both accepted)
					a2 = items[i].charAt(2);
				}else if(items[i].length()==1){
					a1 = items[i].charAt(0);
					//TODO: Check official Hapmap format for genotypes (AA A/A or both accepted)
					a2 = items[i].charAt(0);
				}else{
					a1 = items[i].charAt(0);
					//TODO: Check official Hapmap format for genotypes (AA)
					a2 = items[i].charAt(1);
				}
				if(complement) {
					a1 = DNASequence.getComplement(a1);
					a2 = DNASequence.getComplement(a2);
				}
				if(a1 == 'N') {
					calls.add(new CalledGenomicVariantImpl(snv, new byte[0]));
				} else if (a1 == a2) {
					if(a1 == refBase) {
						CalledSNV call = new CalledSNV(snv, (byte)0);
						call.setGenotypeProbability(1);
						calls.add(call);
					} else if (a1 == altBase) {
						CalledSNV call = new CalledSNV(snv, (byte)2);
						call.setGenotypeProbability(1);
						calls.add(call);
					} else {
						System.err.println("Strange genotype at SNP "+seqName+":"+pos+" sample: "+sampleIds[i-11]+" sampleColumn: "+i+"genotype: "+items[i]);
						calls.add(new CalledGenomicVariantImpl(snv, new byte[0]));
					}
				} else {
					CalledSNV call = new CalledSNV(snv, (byte)1);
					call.setGenotypeProbability(1);
					calls.add(call);
				}
				
			}
			VCFRecord record = new VCFRecord(snv, calls);
			if(inRepeat) record.addAnnotation(new GenomicVariantAnnotation(snv, GenomicVariantAnnotation.ATTRIBUTE_IN_CNV, true));
			records.add(record);
			line = in.readLine();
		}
		fis.close();
		if(genome!=null) {
			GenomicRegionComparator c1 = new GenomicRegionComparator(genome.getSequenceNames());
			VCFRecordComparator c2 = new VCFRecordComparator(c1);
			Collections.sort(records,c2);
		}
		VCFFileHandler handler = new VCFFileHandler();
		
/*		handler.printHeader(System.out, sampleIds);
		for(VCFRecord record:records) handler.saveVCFRecord(record, System.out);*/
		handler.printHeader(outFile, sampleIds);
		for(VCFRecord record:records) handler.saveVCFRecord(record, outFile);
	}
}
class VCFRecordComparator implements Comparator<VCFRecord> {
	private GenomicRegionComparator comparator;
	
	public VCFRecordComparator(GenomicRegionComparator comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public int compare(VCFRecord r1, VCFRecord r2) {
		return comparator.compare(r1.getVariant(), r2.getVariant());
	}
	
}
