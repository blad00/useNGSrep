package org.cgiar.ciat.test;

public class Hilo2 extends Thread {

	/**
	 * @param args
	 */
	public void run(){  
		for(int i=1;i<=5;i++){  
			try{  
				Thread.sleep(500);  
			}catch(Exception e){System.out.println(e);}  
			System.out.println(i+"b");  
		}  
	}  
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
