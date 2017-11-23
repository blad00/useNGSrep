package org.cgiar.ciat.tareas;


import java.io.FileInputStream;
import java.util.List;


import net.sf.samtools.SAMFileHeader;
import net.sf.samtools.SAMFileReader;
import net.sf.samtools.SAMRecord;
import net.sf.samtools.SAMRecordIterator;


public class TemplateProcessBAMFiles {
	public static void main(String[] args) throws Exception {
		String filename = args[0];
		FileInputStream fis = new FileInputStream(filename);
		SAMFileReader samReader = new SAMFileReader(fis);
		//Object with the information present in the header of the sam file
		SAMFileHeader samHeader = samReader.getFileHeader();
		SAMRecordIterator it = samReader.iterator();
		SAMRecord alnRecord;
		
		int numTotalAligment=0;
		int numValidAligment=0;
		int numExpectedLenghtAligment=0;
		int numPositiveStrandAli=0;
		int numNegativeStrandAli=0;
		
		String chr="";
		
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
			
			if(alnRecord.getReadLength()-alnRecord.getReadNameLength()<10)
				numExpectedLenghtAligment++;
			
			
	
			if(!chr.equals(alnRecord.getReferenceName())){
				if(numTotalAligment==1){
					chr=alnRecord.getReferenceName();
					continue;
				}
				System.out.println(chr+" postivos: "+numPositiveStrandAli+", Negativos: "+numNegativeStrandAli);
				
				chr=alnRecord.getReferenceName();
				numPositiveStrandAli=0;
				numNegativeStrandAli=0;
				
			}else{
				if(alnRecord.getReadNegativeStrandFlag())
					numPositiveStrandAli++;
				else
					numNegativeStrandAli++;
			
			}
			
			
			
//			System.out.println(alnRecord.getReadLength());
//			System.out.println(alnRecord.getReadNameLength());
//			l=alnRecord.getAttributes();
//			System.out.println(alnRecord.getReadNegativeStrandFlag());
//			alnRecord.getMappingQuality();
//			alnRecord.getAlignmentEnd();
//			alnRecord.getAlignmentStart();
//			System.out.println(alnRecord.isValid());
//			System.out.println(alnRecord.getReferenceName());
			
			
		}
		System.out.println();
		System.out.println("Total alineamientos: "+numTotalAligment);
		System.out.println("Total alineamientos Validos: "+numValidAligment);
		System.out.println("Total alineamientos con Longitud esperada: "+numExpectedLenghtAligment);
		fis.close();
		
		
	}
}
