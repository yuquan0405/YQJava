package test;

public class ThreadTest extends Thread {

	public ThreadTest(String str){
		this.setName(str);
	}
	
	@Override
	public void run() {
//		HttpClientOA test = new HttpClientOA();
//		test.testOA();
//		super.run();
		
		HttpClientJW jw = new HttpClientJW();
		jw.testJw();
		
	}

	

	public static void main(String[] args) {

		for (int i = 0; i < 8000; i++) {
			System.out.println(i);
			ThreadTest t = new ThreadTest("ThreadTest"+i);
			t.start();
			try {
				if( i != 0 & i % 10 == 0){
					Thread.sleep(1000);
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// HttpClientOA test = new HttpClientOA();
		// test.testOA();
	}
}
