package org.cgiar.ciat.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class ConverterDifftoCompare {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader inFile = new BufferedReader(new FileReader("out.rdiff"));
		PrintWriter outFileDel = new PrintWriter(new FileOutputStream("delsDelta.txt"));
		PrintWriter outFileIns = new PrintWriter(new FileOutputStream("InserDelta.txt"));
		
		String str="";
		String strArrayTmp[]=null;
		
		int gap=0;
		
		// primero los borrados
		
		while ((str = inFile.readLine()) != null) {
			
			strArrayTmp=str.split("\t");
			
			if(strArrayTmp[1].equals("GAP")){
				gap=Math.abs(Integer.parseInt(strArrayTmp[2])-(Integer.parseInt(strArrayTmp[3])));
				if(gap>100){
					if((Integer.parseInt(strArrayTmp[2]))<(Integer.parseInt(strArrayTmp[3]))){
						outFileDel.println(strArrayTmp[0]+"\t"+strArrayTmp[2]+"\t"+strArrayTmp[3]+"\t"+strArrayTmp[4]+"\t"+strArrayTmp[5]+"\t"+strArrayTmp[6]);
					}else{
						outFileDel.println(strArrayTmp[0]+"\t"+strArrayTmp[3]+"\t"+strArrayTmp[2]+"\t"+strArrayTmp[4]+"\t"+strArrayTmp[5]+"\t"+strArrayTmp[6]);
					}
				}
				
			}
			
		}
		
		inFile.close();

		inFile = new BufferedReader(new FileReader("out.rdiff"));
		
		while ((str = inFile.readLine()) != null) {
			
			strArrayTmp=str.split("\t");
			
				if(strArrayTmp[1].equals("DUP")||strArrayTmp[1].equals("BRK")){
					gap=Math.abs(Integer.parseInt(strArrayTmp[2])-(Integer.parseInt(strArrayTmp[3])));
					
					if(gap<500){
						
						if((Integer.parseInt(strArrayTmp[2]))<(Integer.parseInt(strArrayTmp[3]))){
							outFileIns.println(strArrayTmp[0]+"\t"+strArrayTmp[2]+"\t"+strArrayTmp[3]);
						}else{
							outFileIns.println(strArrayTmp[0]+"\t"+strArrayTmp[3]+"\t"+strArrayTmp[2]);
						}
					}
				}
			
		}
		
		inFile.close();
		outFileDel.close();
		outFileIns.close();

		
		
	}

}
