package org.cgiar.ciat.snp.filter;


import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import net.sf.ngstools.variants.GenomicVariant;
import net.sf.ngstools.variants.io.VCFFileHandler;


public class SNPFramesVCF {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args)  {
		// TODO Auto-generated method stub

		try{

			VCFFileHandler vh = new VCFFileHandler();
			
			 
			List<GenomicVariant> lg =  vh.loadVariants(args[0]); 
			
			PrintWriter outFile = new PrintWriter(new FileOutputStream(args[1]));

			int frameSize = Integer.parseInt(args[2]);
						
			GenomicVariant vrecord = null;
			
			GenomicVariant vrecordNext = null;
									
			Iterator<GenomicVariant> iteline=  lg.iterator();
			
			int limitInf=1;

			int currentLimit=frameSize;
			int line = 0;
			int countSnp=0;

			String currentChr=null;

			int currentPos=1;

			int nextPos=1;

			int numFrame=1;
			
			boolean primero=true;


			outFile.println("#Frame"+"\t"+"CHR"+"\t"+"PosIni"+"\t"+"PosFin"+"\t"+"SNPsCant"+"\t"+"SNPs/KB");
			
			
			
			while(iteline.hasNext()){
				vrecord=iteline.next();
				line++;
				
				
				if(primero){
					currentChr=vrecord.getSequenceName();
					primero=false;
				}
				
				currentPos=vrecord.getFirst();
				
				if(iteline.hasNext()){
					vrecordNext=iteline.next();
					line++;
					
					nextPos=vrecordNext.getFirst();
					
					if(!currentChr.equals(vrecordNext.getSequenceName())){
						countSnp++;
						printSNPframe(numFrame,currentChr,limitInf,currentLimit,countSnp,outFile);
						currentChr=vrecordNext.getSequenceName();
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
				
				if(vrecordNext==null){
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
			
			if(vrecordNext!=null){
				printSNPframe(numFrame,currentChr,limitInf,currentLimit,countSnp,outFile);
			}
			
			outFile.close();
//			vrecord=lg.get(0);
//									
//			
//			System.out.println(vrecord.getSequenceName());
//			
//			System.out.println(vrecord.getFirst());
			
			
			
		}catch(Exception e){
			
			System.out.println("error");
			
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void printSNPframe(int numFrame, String currentChr, int limitInf, int currentLimit, int countSnp, PrintWriter outFile){
		
		outFile.println(numFrame+"\t"+currentChr+"\t"+limitInf+"\t"+currentLimit+"\t"+countSnp+"\t"+countSnp/100);
		
	}


}
