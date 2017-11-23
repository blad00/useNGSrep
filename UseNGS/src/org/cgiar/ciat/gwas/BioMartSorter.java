package org.cgiar.ciat.gwas;

import java.io.BufferedReader;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


import net.sf.ngstools.genome.GenomicRegionComparator;
import net.sf.ngstools.genome.GenomicRegionImpl;
import net.sf.ngstools.sequences.SequenceNameList;
import net.sf.ngstools.sequences.io.SequenceNameListHandler;

public class BioMartSorter {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
		PrintWriter outFile = new PrintWriter(new FileOutputStream(args[1]));
		String sequenceNamesFile = args[2];
		SequenceNameListHandler seqNameHandler = new SequenceNameListHandler();
		SequenceNameList sequenceNames = seqNameHandler.loadSequences(sequenceNamesFile);
		GenomicRegionComparator genCompa = new GenomicRegionComparator(sequenceNames);
		
		
		String str=null;
		String arrayStr[]=null;
		
		GenomicRegionImpl firstGene;
		GenomicRegionImpl currentGene;
		
		 
		
		
		
		ArrayList<String> sameGene = new ArrayList<String>();
		
		//headers;
		str = inFile.readLine();
		
		//first gene
		str = inFile.readLine();
		arrayStr=str.split("\t");
		firstGene = new GenomicRegionImpl(arrayStr[0], Integer.parseInt(arrayStr[1]), Integer.parseInt(arrayStr[1]));		
		sameGene.add(str);
		
		int compa;
		
		while ((str = inFile.readLine()) != null ) {
			
			arrayStr=str.split("\t");
			
			currentGene = new GenomicRegionImpl(arrayStr[0], Integer.parseInt(arrayStr[1]), Integer.parseInt(arrayStr[1]));
			
			compa = genCompa.compare(firstGene, currentGene);
			
			if(compa==0){
				sameGene.add(str);
			}else{
				
//				if(arrayStr[3].equals("LOC_Os01g01060")){
//					System.out.println("problema");
//				}
				firstGene=currentGene;
				
				
//				System.out.println(arrayStr[0]+"\t"+arrayStr[1]+"\t"+arrayStr[2]);
				printDesc(sameGene, outFile);
				
				sameGene.clear();
				
				sameGene.add(str);
				
			}
			
		}
		
		inFile.close();
		outFile.close();
		
		
	}
	
	
	public static void printDesc(ArrayList<String> sameGene, PrintWriter outFile) throws Exception {
		HashMap<String, String> genesByPFAM = new HashMap<String, String>();
		HashMap<String, String> genesByKOG = new HashMap<String, String>();
		HashMap<String, String> genesByGO = new HashMap<String, String>();
		HashMap<String, String> genesBySMART = new HashMap<String, String>();
		HashMap<String, String> genesByPANTHER = new HashMap<String, String>();
		HashMap<String, String> genesByEC = new HashMap<String, String>();
		HashMap<String, String> genesByKO = new HashMap<String, String>();
		
		Iterator<String> it = sameGene.iterator();
		String arrayTMP[]=null;
		
		
		
		while(it.hasNext()){
			arrayTMP = it.next().split("\t");
			if(arrayTMP.length<6)
				continue;
			if(!arrayTMP[5].equals("")&&arrayTMP.length>=7)
				genesByPFAM.put(arrayTMP[5], arrayTMP[6]);
			else if(!arrayTMP[5].equals(""))
				genesByPFAM.put(arrayTMP[5], "");
		}
		
		it = sameGene.iterator();
		
		while(it.hasNext()){
			arrayTMP = it.next().split("\t");
			if(arrayTMP.length<8)
				continue;
			if(!arrayTMP[7].equals("")&&arrayTMP.length>=9)
				genesByKOG.put(arrayTMP[7], arrayTMP[8]);
			else if(!arrayTMP[7].equals(""))
				genesByKOG.put(arrayTMP[7], "");
		}
		
		it = sameGene.iterator();
		
		while(it.hasNext()){
			arrayTMP = it.next().split("\t");
			if(arrayTMP.length<10)
				continue;
			if(!arrayTMP[9].equals("")&&arrayTMP.length>=11)
				genesByGO.put(arrayTMP[9], arrayTMP[10]);
			else if(!arrayTMP[9].equals(""))
				genesByGO.put(arrayTMP[9], arrayTMP[10]);
		}
		
		it = sameGene.iterator();
		
		while(it.hasNext()){
			arrayTMP = it.next().split("\t");
			if(arrayTMP.length<12)
				continue;
			if(!arrayTMP[11].equals("")&&arrayTMP.length>=13){
				genesBySMART.put(arrayTMP[11], arrayTMP[12]);
				
			}else if(!arrayTMP[11].equals(""))
				genesBySMART.put(arrayTMP[11], "");
		}
		
		it = sameGene.iterator();
		
		while(it.hasNext()){
			arrayTMP = it.next().split("\t");
			if(arrayTMP.length<14)
				continue;
			if(!arrayTMP[13].equals("")&&arrayTMP.length>=15)
				genesByPANTHER.put(arrayTMP[13], arrayTMP[14]);
			else if(!arrayTMP[13].equals(""))
				genesByPANTHER.put(arrayTMP[13], "");
		}
		
		it = sameGene.iterator();
		
		while(it.hasNext()){
			arrayTMP = it.next().split("\t");
			if(arrayTMP.length<16)
				continue;
			if(!arrayTMP[15].equals("")&&arrayTMP.length>=17)
				genesByEC.put(arrayTMP[15], arrayTMP[16]);
			else if(!arrayTMP[15].equals(""))
				genesByEC.put(arrayTMP[15], "");
		}
		
		it = sameGene.iterator();
		
		while(it.hasNext()){
			arrayTMP = it.next().split("\t");
			if(arrayTMP.length<18)
				continue;
			if(!arrayTMP[17].equals("")&&arrayTMP.length>=19)
				genesByKO.put(arrayTMP[17], arrayTMP[18]);
			else if(!arrayTMP[17].equals(""))
				genesByKO.put(arrayTMP[17], "");
		}
		
		
		
		it = sameGene.iterator();
		
		
		if((genesByPFAM.isEmpty())&&(genesByKOG.isEmpty())&&(genesByGO.isEmpty())&&(genesBySMART.isEmpty())&&(genesByPANTHER.isEmpty())&&(genesByEC.isEmpty())&&(genesByKO.isEmpty())){
			while(it.hasNext()){
				outFile.println(it.next());
			}
			return;
		}
		
		String sKey=null;
		
		it=genesByPFAM.keySet().iterator();
			
		while(it.hasNext()){
			sKey=it.next();
			outFile.println(arrayTMP[0]+"\t"+arrayTMP[1]+"\t"+arrayTMP[2]+"\t"+arrayTMP[3]+"\t"+arrayTMP[4]+"\t"+"PFAM"+"\t"+sKey+"\t"+genesByPFAM.get(sKey));
		}
		
		it=genesByKOG.keySet().iterator();
		
		while(it.hasNext()){
			sKey=it.next();
			outFile.println(arrayTMP[0]+"\t"+arrayTMP[1]+"\t"+arrayTMP[2]+"\t"+arrayTMP[3]+"\t"+arrayTMP[4]+"\t"+"KOG"+"\t"+sKey+"\t"+genesByKOG.get(sKey));
		}
		
		it=genesByGO.keySet().iterator();
		
		while(it.hasNext()){
			sKey=it.next();
			outFile.println(arrayTMP[0]+"\t"+arrayTMP[1]+"\t"+arrayTMP[2]+"\t"+arrayTMP[3]+"\t"+arrayTMP[4]+"\t"+"GO"+"\t"+sKey+"\t"+genesByGO.get(sKey));
		}
		
		it=genesBySMART.keySet().iterator();
		
		while(it.hasNext()){
			sKey=it.next();
			outFile.println(arrayTMP[0]+"\t"+arrayTMP[1]+"\t"+arrayTMP[2]+"\t"+arrayTMP[3]+"\t"+arrayTMP[4]+"\t"+"SMART"+"\t"+sKey+"\t"+genesBySMART.get(sKey));
		}
		
		it=genesByPANTHER.keySet().iterator();
		
		while(it.hasNext()){
			sKey=it.next();
			outFile.println(arrayTMP[0]+"\t"+arrayTMP[1]+"\t"+arrayTMP[2]+"\t"+arrayTMP[3]+"\t"+arrayTMP[4]+"\t"+"PANTHER"+"\t"+sKey+"\t"+genesByPANTHER.get(sKey));
		}
		
		it=genesByEC.keySet().iterator();
		
		while(it.hasNext()){
			sKey=it.next();
			outFile.println(arrayTMP[0]+"\t"+arrayTMP[1]+"\t"+arrayTMP[2]+"\t"+arrayTMP[3]+"\t"+arrayTMP[4]+"\t"+"EC"+"\t"+sKey+"\t"+genesByEC.get(sKey));
		}
		
		it=genesByKO.keySet().iterator();
		
		while(it.hasNext()){
			sKey=it.next();
			outFile.println(arrayTMP[0]+"\t"+arrayTMP[1]+"\t"+arrayTMP[2]+"\t"+arrayTMP[3]+"\t"+arrayTMP[4]+"\t"+"KO"+"\t"+sKey+"\t"+genesByKO.get(sKey));
		}
	}

}
