package org.cgiar.ciat.tareas;

import java.io.FileInputStream;
import java.util.ArrayList;

import net.sf.samtools.SAMFileHeader;
import net.sf.samtools.SAMFileReader;
import net.sf.samtools.SAMRecord;
import net.sf.samtools.SAMRecordIterator;

public class BamCounter {

	public static void main(String[] args) throws Exception{
		

		String filename = args[0];
		FileInputStream fis = new FileInputStream(filename);
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
		
		ArrayList<String> refNames = null;
		
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
				//print
				
				
				//clean
				currentNumRefs=0;
				qname=alnRecord.getReadName();
				refNames.clear();
				//add new one
				refNames.add(alnRecord.getReferenceName());
				currentNumRefs=1;
			}
		
			
		}
		System.out.println();
		System.out.println("Total alineamientos: "+numTotalAligment);
		System.out.println("Total alineamientos Validos: "+numValidAligment);
		
		fis.close();
		
		
	
		// TODO Auto-generated method stub

	}

}
