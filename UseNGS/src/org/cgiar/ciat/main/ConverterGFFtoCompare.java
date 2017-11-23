package org.cgiar.ciat.main;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class ConverterGFFtoCompare {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader inFile = new BufferedReader(new FileReader("D:/Desarrollo/workspacejunoTest/PruebaIR64/ID43/ID43_bowtie2_d5_SV.gff"));
		PrintWriter outFileDel = new PrintWriter(new FileOutputStream("D:/Desarrollo/workspacejunoTest/PruebaIR64/ID43/outPut/delsID43.txt"));
		PrintWriter outFileIns = new PrintWriter(new FileOutputStream("D:/Desarrollo/workspacejunoTest/PruebaIR64/ID43/outPut/InserID43.txt"));
		String str="";
		String strArrayTmp[]=null;
		
		// primero los borrados
		
		while ((str = inFile.readLine()) != null) {
			
			strArrayTmp=str.split("\t");
			
			if(strArrayTmp[2].equals("Deletion")){
				if((Integer.parseInt(strArrayTmp[3]))<(Integer.parseInt(strArrayTmp[4]))){
					outFileDel.println(strArrayTmp[0]+"\t"+strArrayTmp[3]+"\t"+strArrayTmp[4]+"\t"+strArrayTmp[8]);
				}else{
					outFileDel.println(strArrayTmp[0]+"\t"+strArrayTmp[4]+"\t"+strArrayTmp[3]+"\t"+strArrayTmp[8]);
				}
				
			}
			
		}
		inFile.close();

		inFile = new BufferedReader(new FileReader("D:/Desarrollo/workspacejunoTest/PruebaIR64/ID43/ID43_bowtie2_d5_SV.gff"));
		
		while ((str = inFile.readLine()) != null) {
			
			strArrayTmp=str.split("\t");
			
			if(strArrayTmp[2].equals("Insertion")){
				if((Integer.parseInt(strArrayTmp[3]))<(Integer.parseInt(strArrayTmp[4]))){
					outFileIns.println(strArrayTmp[0]+"\t"+strArrayTmp[3]+"\t"+strArrayTmp[4]+"\t"+strArrayTmp[8]);
				}else{
					outFileIns.println(strArrayTmp[0]+"\t"+strArrayTmp[4]+"\t"+strArrayTmp[3]+"\t"+strArrayTmp[8]);
				}
				
			}
			
			
		}
		
		inFile.close();
		outFileDel.close();
		outFileIns.close();
	}

}
