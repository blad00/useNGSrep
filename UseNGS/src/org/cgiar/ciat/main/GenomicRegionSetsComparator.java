package org.cgiar.ciat.main;



import java.util.Collections;
import java.util.List;

import net.sf.ngstools.genome.GenomicRegion;
import net.sf.ngstools.genome.GenomicRegionComparator;
import net.sf.ngstools.genome.SpanGenomicRegionComparator;
import net.sf.ngstools.genome.io.SimpleGenomicRegionFileHandler;
import net.sf.ngstools.sequences.SequenceNameList;
import net.sf.ngstools.sequences.io.SequenceNameListHandler;


public class GenomicRegionSetsComparator {
	public static void main(String[] args) throws Exception {
		SimpleGenomicRegionFileHandler fh = new SimpleGenomicRegionFileHandler();
		SequenceNameListHandler seqNamesHandler = new SequenceNameListHandler();
		SequenceNameList seqNames = seqNamesHandler.loadSequences(args[0]);
		GenomicRegionComparator comparator = new GenomicRegionComparator(seqNames);
		List<GenomicRegion> regionSet1 = fh.loadRegions(args[1]);
		List<GenomicRegion> regionSet2 = fh.loadRegions(args[2]);
		Collections.sort(regionSet1,comparator);
		Collections.sort(regionSet2,comparator);
		int i=0;
		int j=0;
		int totalSet1 = 0;
		int totalSet2 = 0;
		int totalIntersection = 0;
		boolean countSet1 = true;
		boolean countSet2 = true;
		while(i<regionSet1.size() || j<regionSet2.size()) {
			GenomicRegion r1 = (i<regionSet1.size())?regionSet1.get(i):null;
			GenomicRegion r2 = (j<regionSet2.size())?regionSet2.get(j):null;
			if(countSet1 && r1!=null) {
				totalSet1+=r1.length();
				countSet1 = false;
			}
			if(countSet2 && r2!=null) {
				totalSet2+=r2.length();
				countSet2 = false;
			}
			int cmp = -3;
			if(r1 == null) cmp = 3;
			else if (r2!=null) cmp = comparator.compare(r1, r2);
			if(cmp<-1) {
				i++;
				countSet1 = true;
				continue;
			}
			if(cmp>1) {
				j++;
				countSet2 = true;
				continue;
			}
			int intersection = SpanGenomicRegionComparator.getInstance().getSpanLength(r1.getFirst(), r1.getLast(), r2.getFirst(), r2.getLast());
			totalIntersection+=intersection;
			if(cmp == 0) {
				i++;
				j++;
				countSet1 = true;
				countSet2 = true;
				continue;
			}
			if(r1.getLast()<r2.getLast()) {
				i++;
				countSet1 = true;
				continue;
			} else {
				j++;
				countSet2 = true;
				continue;
			}
		}
/*		System.out.println("Total set 1: "+totalSet1);
		System.out.println("Total set 2: "+totalSet2);
		System.out.println("Total intersection: "+totalIntersection);
		System.out.println("PCT intersection set 1:"+(1.0*totalIntersection/totalSet1));
		System.out.println("PCT intersection set 2:"+(1.0*totalIntersection/totalSet2));*/
		System.out.print(args[1]+"\t"+args[2]+"\t");
		System.out.println(totalSet1+"\t"+totalSet2+"\t"+totalIntersection+"\t"+(1.0*totalIntersection/totalSet1)+"\t"+(1.0*totalIntersection/totalSet2));
	}
}

		

