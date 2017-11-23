package org.cgiar.ciat.test;

public class Tester2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    
	     int a = 60575845;	
	     int b = 13;	
	     int c = 0;

	    
	     int i;
	     
	     
	     
	     long numBits = 6;
	     
	     for (i=1; i<=100;i++){
	    	 System.out.print((a >> i));
	    	 System.out.print(" ");
	    	 System.out.print((a >>> i));
	    	 System.out.println();
	     }
	     	    
	  }

}
