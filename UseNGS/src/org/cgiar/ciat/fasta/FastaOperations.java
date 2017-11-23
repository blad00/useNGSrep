package org.cgiar.ciat.fasta;

import java.io.IOException;

import net.sf.ngstools.sequences.DNAMaskedSequence;
import net.sf.ngstools.sequences.Sequence;
import net.sf.ngstools.sequences.io.FastaSequencesHandler;

public class FastaOperations {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String inFile = args[0];
		String seqName = args[1];
//		int first = Integer.parseInt(args[2]);
//		int last = Integer.parseInt(args[3]);
		FastaSequencesHandler inHandler = new FastaSequencesHandler();
		FastaSequencesHandler seqsHandler = new FastaSequencesHandler();
		inHandler.loadSequences(inFile);
		Sequence seq = inHandler.getSequence(seqName);
		seqsHandler.addSequence(seqName , new DNAMaskedSequence(seq.toString()));
		seqsHandler.saveSequences(System.out, 100);
		System.out.println(seq.length());
		
	}

}
