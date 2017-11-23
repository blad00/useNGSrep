package org.cgiar.ciat.snp.filter;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class FilterBySNPS {
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String str=null;
		String arrayCadena[]=null;
		String alleleRef = null;
		String alleleAlt = null;
		String filter=null;
		String info=null;

		try{
			BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
			PrintWriter outFile = new PrintWriter(new FileOutputStream(args[1]));

			while ((str = inFile.readLine()) != null ) {

				if (str.charAt(0) != '#'){

					arrayCadena=str.split("\t");
					alleleRef = arrayCadena[3];
					alleleAlt=arrayCadena[4];
					filter = arrayCadena[6];
					

					if(alleleRef.length() == 1 && alleleAlt.length() == 1 ){
				
						//System.out.println(str);
						outFile.println(str);
					}
				}else{
					outFile.println(str);
					//System.out.println(str);
				}
			}
			inFile.close();
			outFile.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}
}