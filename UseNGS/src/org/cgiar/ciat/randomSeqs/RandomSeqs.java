package org.cgiar.ciat.randomSeqs;

import java.io.IOException;
import java.util.Random;

import net.sf.ngstools.genome.Genome;
import net.sf.ngstools.genome.GenomeAssembly;
import net.sf.ngstools.sequences.io.FastaSequencesHandler;

public class RandomSeqs {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String fastaDir=args[0];
		
		GenomeAssembly genFile = new GenomeAssembly(fastaDir);
		
		int seqLenght=Integer.parseInt(args[1]);
		
		int cantSeq=Integer.parseInt(args[2]);
		
		
		
		System.out.println(genFile.getTotalLength());
				
		System.out.println("/////////////////////////////////////////////");
		
		long START = 1;
	    long END = genFile.getTotalLength();
//		long END = 12;
	    Random random = new Random();
	    	    	    
	    String seqRandom=null;
	    
	    int posRandom=0;
	    
	    for (int idx = 1; idx <= cantSeq; idx++){
	    	
	      posRandom=showRandomInteger(START, END, random);
	      
	      
	      System.out.println("///////////////////////////");
	      
	      System.out.println("# seq "+idx);    
	      //seqRandom=genFile.getSequenceByAbsolutePosition( posRandom, seqLenght);
	      System.out.println(seqRandom);
	      
	      if(!seqRandom.contains("N")){
	    	  System.out.println(seqRandom);
	    	  
	    	  
	      }else {
			idx--;
		  }
	        
	    }
				
	}
	
	private static int showRandomInteger(long aStart, long aEnd, Random aRandom){
	    if ( aStart > aEnd ) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    //get the range, casting to long to avoid overflow problems
	    long range = (long)aEnd - (long)aStart + 1;
	    // compute a fraction of the range, 0 <= frac < range
	    long fraction = (long)(range * aRandom.nextDouble());
	    int randomNumber =  (int)(fraction + aStart);    
	    
	    return randomNumber;
	    
	  }

}
