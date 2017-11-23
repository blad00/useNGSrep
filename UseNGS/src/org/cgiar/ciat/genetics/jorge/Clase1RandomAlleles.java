package org.cgiar.ciat.genetics.jorge;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Clase1RandomAlleles {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char gen1 [] = {'A','B'};
		
		
		
		
		StringBuilder currentGen = new StringBuilder();
		ArrayList <String> listOffspring=new ArrayList<String>();
		String stmp;
		
		int AA=0;
		int BB=0;
		int AB=0;
		
		for (int i=0;i<1000;i++){
			
			currentGen.append(gen1[basicRandom()]); 
			currentGen.append(gen1[basicRandom()]);
			
			listOffspring.add(currentGen.toString());
			
			currentGen = new StringBuilder();
		}
		
		Iterator <String> ite = listOffspring.iterator();
		
		while(ite.hasNext()){
			stmp=ite.next();
			if(stmp.equals("AA")) AA++;
			if(stmp.equals("BB")) BB++;
			if(stmp.equals("AB")) AB++;
			if(stmp.equals("BA")) AB++;
				
		}
		
		System.out.println("AA "+AA);
		System.out.println("BB "+BB);
		System.out.println("AB "+AB);

	}
	
	
	private static int basicRandom(){
		Random rad = new Random();
		return rad.nextInt(2);
		
		
	}

}
