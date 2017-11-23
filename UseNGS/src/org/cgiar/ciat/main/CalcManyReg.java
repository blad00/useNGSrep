package org.cgiar.ciat.main;

import java.io.File;

import java.util.Arrays;

public class CalcManyReg {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String seqNames=args[0];//seq Names
		
		String pathSet1=args[1];
		String namesSet1=args[2];
		
		String pathSet2=args[3];
		String namesSet2=args[4];
						
		String argSet1[]=namesSet1.split(",");
		
		String argSet2[]=namesSet2.split(",");
		
		String params[]=new String[3];
		
		Arrays.sort(argSet1);
		Arrays.sort(argSet2);
		
		int i,j;
		
		for(i=0;i<argSet1.length;i++){
			
			for(j=0;j<argSet2.length;j++){
				params[0]=seqNames;
				params[1]=pathSet1+File.separator+argSet1[i];
				
				params[2]=pathSet2+File.separator+argSet2[j];
				
				GenomicRegionSetsComparator.main(params);
				
			}
			
			
		}
		
		
		
		
	}

}
