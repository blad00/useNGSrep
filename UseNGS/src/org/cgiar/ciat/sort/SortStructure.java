package org.cgiar.ciat.sort;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;


public class SortStructure {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
		BufferedReader listFile = new BufferedReader(new FileReader(args[1]));
		PrintWriter outFile = new PrintWriter(new FileOutputStream(args[2]));
		
		
		String str="";
		String arrayTmp[]=null;
		HashMap<String, String> map;
		
		
		
		map =new HashMap<String, String>();
		
		
		
		while ((str = inFile.readLine()) != null) {
			
			arrayTmp=str.split(" ");
			
			map.put(arrayTmp[0], str);
//			System.out.println(arrayTmp[0]);
				
		}
		
		inFile.close();
		
		
		while ((str = listFile.readLine()) != null) {
			
			outFile.println(map.get(str));
					
		}
		
			
		listFile.close();
		outFile.close();
		
		
	}

}
