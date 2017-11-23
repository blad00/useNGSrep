package org.cgiar.ciat.genetics.jorge;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Clase2RandomAlleles2Genes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char gen1 [] = {'A','B'};
		
		
		
		char gen2 [] = {'C','D'};
		
		
		StringBuilder currentGen = new StringBuilder();
		ArrayList <String> listOffspring=new ArrayList<String>();
		String stmp;
		
		int AACC=0;
		int AADD=0;
		int BBCC=0;
		int BBDD=0;
		//4
		
		int AADC=0;
		//2
				
		int BACC=0;
		//2
		
		int BADC=0;
		//4
		
		int BADD=0;
		//2
		
		int BBDC=0;
		//2
		
		for (int i=0;i<1000;i++){
			currentGen.append(gen1[basicRandom()]);
			currentGen.append(gen1[basicRandom()]);
			currentGen.append(gen2[basicRandom()]);
			currentGen.append(gen2[basicRandom()]);
	
			listOffspring.add(currentGen.toString());
			
			currentGen = new StringBuilder();
		}
		
		Iterator <String> ite = listOffspring.iterator();
		
		while(ite.hasNext()){
			stmp=ite.next();
			if(stmp.equals("AACC")) AACC++;
			if(stmp.equals("AADD")) AADD++;
			if(stmp.equals("BBCC")) BBCC++;
			if(stmp.equals("BBDD")) BBDD++;
			
			if(stmp.equals("AADC")) AADC++;
			if(stmp.equals("AADC")) AADC++;
			
			if(stmp.equals("BACC")) BACC++;
			if(stmp.equals("ABCC")) BACC++;
			
			if(stmp.equals("BADC")) BADC++;
			if(stmp.equals("BACD")) BADC++;
			if(stmp.equals("ABDC")) BADC++;
			if(stmp.equals("BADC")) BADC++;
			if(stmp.equals("ABCD")) BADC++;
			
			if(stmp.equals("BADD")) BADD++;
			if(stmp.equals("ABDD")) BADD++;
			
			if(stmp.equals("BBDC")) BBDC++;
			if(stmp.equals("BBCD")) BBDC++;
			
			
				
		}
		
		System.out.println("AACC "+AACC);
		System.out.println("AADD "+AADD);
		System.out.println("BBCC "+BBCC);
		System.out.println("BBDD "+BBDD);
		
		System.out.println("AADC "+AADC);
		
		System.out.println("BACC "+BACC);
		
		System.out.println("BADC "+BADC);
		
		System.out.println("BADD "+BADD);
		
		System.out.println("BBDC "+BBDC);
		
		

	}
	
	
	private static int basicRandom(){
		Random rad = new Random();
		return rad.nextInt(2);
		
		
	}

}
