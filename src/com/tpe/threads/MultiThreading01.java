package com.tpe.threads;

public class MultiThreading01 {

	public static void main(String[] args) throws InterruptedException {

		long startTime= System.currentTimeMillis();
		CounterWithoutMultiThread counter1=new CounterWithoutMultiThread(1); //2
		CounterWithoutMultiThread counter2=new CounterWithoutMultiThread(2);
		
		counter1.countMe(); //bunu calistitip 5 sn sonra digerine geciyor
		
		System.out.println("----------------------------");
		
		counter2.countMe();
		
		long endTime=System.currentTimeMillis();
		
		System.out.println("WithoutMultiThread Elapsed Time:"+(endTime-startTime));
		
		
		System.out.println("**************************************************");
		
		long startTime2= System.currentTimeMillis(); //4
		CounterWithMultiThread counter3=new CounterWithMultiThread(1);
		CounterWithMultiThread counter4=new CounterWithMultiThread(2);
		
		counter3.start(); //stat'ı calistirinca run methodu calisiyor 
		
		System.out.println("----------------------------");
		
		counter4.start();//ama calismanin bbitmesini beklemeden bu calismaya basliyor
		
		//Thread.sleep(6000);
		
		//join--Waits for this thread to die. 
		counter3.join(); //bunlari ekleme sebebim counter3 threadi bitene kadar bunu beklet
		counter4.join();//endTime'in dogru cikmasi icin
		
		long endTime2=System.currentTimeMillis();
		
		System.out.println("WithMultiThread Elapsed Time:"+(endTime2-startTime2));
		
		
		
		
	}

}

class CounterWithoutMultiThread{ //1
	private int threadNum;
	
	public CounterWithoutMultiThread(int threadNum) {
		this.threadNum=threadNum;
	}
	
	public void countMe() throws InterruptedException {
		for (int i = 1; i <=10; i++) {
			Thread.sleep(500); //main thread'i durduruyor burda
			
			System.out.println("i"+i+"Thread number:"+threadNum);
		}
	}
}


class CounterWithMultiThread extends Thread{
	private int threadNum;
	
	@Override
	public void run() {
	    try {
			countMe();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public CounterWithMultiThread(int threadNum) {//3
		this.threadNum=threadNum;
	}
	
	public void countMe() throws InterruptedException {
		for (int i = 1; i <=10; i++) {
			Thread.sleep(500);
			
			System.out.println("i"+i+"Thread number:"+threadNum);
		}
	}
}


