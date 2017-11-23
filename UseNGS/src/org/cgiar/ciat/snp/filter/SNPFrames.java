package org.cgiar.ciat.snp.filter;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class SNPFrames {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args)  {
		// TODO Auto-generated method stub

		try{


			BufferedReader inFile = new BufferedReader(new FileReader(args[0]));

			PrintWriter outFile = new PrintWriter(new FileOutputStream(args[1]));

			int frameSize = Integer.parseInt(args[2]);

			int skipLines = Integer.parseInt(args[3]);

			int line = 0;


			//current line
			String str="";
			String arrayStr[]=null;

			//next line
			String strNext="";
			String arrayStrNext[]=null;


			int limitInf=1;

			int currentLimit=frameSize;

			int countSnp=0;

			String currentChr=null;

			int currentPos=1;

			int nextPos=1;

			int numFrame=1;
			
			boolean primero=true;


			outFile.println("#Frame"+"\t"+"CHR"+"\t"+"PosIni"+"\t"+"PosFin"+"\t"+"SNPsCant"+"\t"+"SNPs/KB");
			while ((str = inFile.readLine()) != null) {
				
				
				line++;
			

				
				if(line>skipLines){

					arrayStr=str.split("\t");

					if(primero){
						currentChr=arrayStr[0];
						primero=false;
					}	

					currentPos=Integer.parseInt(arrayStr[1]);
				
				
					
					if((strNext = inFile.readLine()) != null){
						line++;
						arrayStrNext=strNext.split("\t");
						nextPos=Integer.parseInt(arrayStrNext[1]);

						if(!currentChr.equals(arrayStrNext[0])){
							
							countSnp++;
							printSNPframe(numFrame,currentChr,limitInf,currentLimit,countSnp,outFile);
							currentChr=arrayStrNext[0];
							currentPos=1;
							limitInf=1;
							currentLimit=frameSize;
							countSnp=1;
							continue;

						}

					}

					if(currentPos<=currentLimit){

						countSnp++;

					}else{
						printSNPframe(numFrame,currentChr,limitInf,currentLimit,countSnp,outFile);
						limitInf=currentLimit+1;
						currentLimit+=frameSize;
						numFrame++;
						countSnp=1;
					}

					if(strNext==null){
						printSNPframe(numFrame,currentChr,limitInf,currentLimit,countSnp,outFile);
						break;
					}
					if(nextPos<=currentLimit){
						countSnp++;
					}else{
						printSNPframe(numFrame,currentChr,limitInf,currentLimit,countSnp,outFile);
						limitInf=currentLimit+1;
						currentLimit+=frameSize;
						numFrame++;
						countSnp=1;
					}


				}
				
				


			}

			if(strNext!=null){
				System.out.println("jo");
				printSNPframe(numFrame,currentChr,limitInf,currentLimit,countSnp,outFile);
			}
			
			inFile.close();
			outFile.close();
			
		}catch(Exception e){
			e.printStackTrace();

		}

	}
	
	
	public static void printSNPframe(int numFrame, String currentChr, int limitInf, int currentLimit, int countSnp, PrintWriter outFile){
		
		outFile.println(numFrame+"\t"+currentChr+"\t"+limitInf+"\t"+currentLimit+"\t"+countSnp+"\t"+countSnp/100);
		
	}



}
