package test;

public class RunableTest implements Runnable {

	public static void main(String[] args) {

		for (int i = 0; i < 1000; i++) {
			System.out.println(i);
			RunableTest t = new RunableTest();
			t.run();
		}
		// HttpClientOA test = new HttpClientOA();
		// test.testOA();
	}

	public void run() {
//		HttpClientOA test = new HttpClientOA();
//		test.testOA();

		HttpClientJW jw = new HttpClientJW();
		jw.testJw();
		 
	}

}
