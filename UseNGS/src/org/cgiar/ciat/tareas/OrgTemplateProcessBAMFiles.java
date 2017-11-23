package org.cgiar.ciat.tareas;


import java.io.FileInputStream;


import net.sf.samtools.SAMFileHeader;
import net.sf.samtools.SAMFileReader;
import net.sf.samtools.SAMRecord;
import net.sf.samtools.SAMRecordIterator;


public class OrgTemplateProcessBAMFiles {
	public static void main(String[] args) throws Exception {
		String filename = args[0];
		FileInputStream fis = new FileInputStream(filename);
		SAMFileReader samReader = new SAMFileReader(fis);
		//Object with the information present in the header of the sam file
		SAMFileHeader samHeader = samReader.getFileHeader();
		SAMRecordIterator it = samReader.iterator();
		
		while(it.hasNext()) {
			SAMRecord alnRecord;
			try {
				alnRecord = it.next();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				continue;
			}
			//DO your stuff here
		}
		fis.close();
	}
}
