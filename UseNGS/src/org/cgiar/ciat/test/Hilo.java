package org.cgiar.ciat.test;

public class Hilo extends Thread {

	/**
	 * @param args
	 */

	public void run(){  
		for(int i=1;i<=5;i++){  
			try{  
				Thread.sleep(500);  
			}catch(Exception e){System.out.println(e);}  
			System.out.println(i+"a");  
		}  
	}  
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Hilo h1 = new Hilo();
		Hilo2 h2 = new Hilo2();
		Hilo2 h3 = new Hilo2();
		
		h1.start();
		
		try {
			h1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		h2.start();
		h3.start();
		
		
		
	}

}
