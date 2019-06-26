package org.cgiar.ciat.tareas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import net.sf.samtools.SAMFileHeader;
import net.sf.samtools.SAMFileReader;
import net.sf.samtools.SAMRecord;
import net.sf.samtools.SAMRecordIterator;

/*
 * This program is for counting different characteristics in 2 subgenomes
 * 
 */

public class BamCounter {

	public static void main(String[] args) throws Exception{
		

		String filename = args[0];
		FileInputStream fis = new FileInputStream(filename);
		PrintWriter outFile = new PrintWriter(new FileOutputStream(args[1]));
		
		SAMFileReader samReader = new SAMFileReader(fis);
		//Object with the information present in the header of the sam file
		SAMFileHeader samHeader = samReader.getFileHeader();
		SAMRecordIterator it = samReader.iterator();
		SAMRecord alnRecord;
		
		int numTotalAligment=0;
		int numValidAligment=0;
		int currentNumRefs=0;
		
		
		String chr="";
		String qname="";
		
		boolean oposSubG=false;
		boolean oposChr=false;
		
		ArrayList<String> refNames = null;
		
		outFile.println("Qname"+"\t"+"NumAlig"+"\t"+"OposSubG"+"\t"+"OposChr");
		
		while(it.hasNext()) {
			
			try {
				alnRecord = it.next();
				numTotalAligment++;
			} catch (Exception e) {
				System.err.println(e.getMessage());
				continue;
			}
			//DO your stuff here
			
			
			
			if(alnRecord.isValid()==null)
				numValidAligment++;
			
			if(numTotalAligment==1){
				qname = alnRecord.getReadName();
				refNames = new ArrayList<String>();
			}
						

			if(qname.equals(alnRecord.getReadName())){
				refNames.add(alnRecord.getReferenceName());
				currentNumRefs++;
			}else{
				//send to methods
//				if(qname.equals("SN782:897:HKNNMBCXY:1:1101:1002:26538"))
//					System.out.println("Acaaa");
				oposSubG = opositeSubgenome(refNames);
				oposChr = opositeChr(refNames);
				//print
				outFile.println(qname+"\t"+currentNumRefs/2+"\t"+oposSubG+"\t"+oposChr);			
				//clean
				currentNumRefs=0;
				refNames.clear();
				//add new one
				qname=alnRecord.getReadName();
				refNames.add(alnRecord.getReferenceName());
				currentNumRefs=1;
			}
		
			
		}
		System.out.println();
		System.out.println("Total alineamientos: "+numTotalAligment);
		System.out.println("Total alineamientos Validos: "+numValidAligment);
		
		fis.close();
		samReader.close();
		outFile.close();
		
	
		// TODO Auto-generated method stub

	}

	public static boolean opositeSubgenome(ArrayList<String> refs){
		boolean hasA = false;
		boolean hasC = false;
		
		for (String ref : refs) {
			if(ref.charAt(3)=='A')
				hasA = true;
			if(ref.charAt(3)=='C')
				hasC = true;
		}
						
		return hasA&&hasC;
	}
	
	public static boolean opositeChr(ArrayList<String> refs){
		ArrayList<Character> aChr = new ArrayList<Character>();
		ArrayList<Character> cChr = new ArrayList<Character>();
		ArrayList<Character> result = null;	
		for (String ref : refs) {
			if(ref.charAt(3)=='A')
				aChr.add(ref.charAt(5));
			if(ref.charAt(3)=='C')
				cChr.add(ref.charAt(5));
		}
		
		if(aChr.size()==0||cChr.size()==0)
			return false;
		
		result = new ArrayList<Character>(aChr);
		result.retainAll(cChr);
		
		return !result.isEmpty();
	}
	
}
