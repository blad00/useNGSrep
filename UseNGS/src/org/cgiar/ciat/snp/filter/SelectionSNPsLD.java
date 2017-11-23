package org.cgiar.ciat.snp.filter;
import java.io.BufferedReader;
import java.util.HashMap;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class SelectionSNPsLD {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub


		BufferedReader reader = new BufferedReader(new FileReader("D:/DanielBio/ArchivosProcess/Cassava/ReducedQ60_MinI6_K20000_CassavaWGS_Annotated_Q40_MaskedSites_D40_NoCNVs.vcf"));
		String strTmp;

		
		
		String reStr[]=null;
		int numberSNPs = 0;
		String scaffold =null;
		int line = 0;	
		String REF =null;
		String ALT=null;
		String CNV =null;



		while((strTmp=reader.readLine()) != null){

			line ++;

			if (line >14){
				
				reStr=strTmp.split("\t");
				
				
				if(scaffold==null||(!scaffold.equals(reStr[0]))){
					System.out.println(strTmp);
					scaffold= reStr[0];
				}
				

			}else{
				System.out.println(strTmp);
			}

		}
		reader.close();
	}
}



//BufferedWriter writer = new BufferedWriter(new FileWriter(filename));


//PRINT SNPs FROM EACH ONE OF THE Scaffolds 


/*public void stripDuplicatesFromFile(String filename) {
BufferedReader reader = new BufferedReader(new FileReader(filename));
Set<String> lines = new HashSet<String>(10000); // maybe should be bigger
String line;
while ((line = reader.readLine()) != null) {
    lines.add(line);
}
reader.close();
BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
for (String unique : lines) {
    writer.write(unique);
    writer.newLine();
}
writer.close();
}*/