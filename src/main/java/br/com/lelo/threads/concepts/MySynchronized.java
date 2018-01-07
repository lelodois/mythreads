package br.com.lelo.threads.concepts;

class MySynchronizedApp {

	private int count = 0;

	private synchronized void addCount() {
		count++;
	}

	public void doWork() throws InterruptedException {

		Thread firstThread = new Thread(() -> {
			for (int index = 0; index < 10000; index++) {
				addCount();
			}
		});

		Thread secondThread = new Thread(() -> {
			for (int index = 0; index < 10000; index++) {
				addCount();
			}
		});

		firstThread.start();
		secondThread.start();

		firstThread.join();
		secondThread.join();

		System.out.println("Count is: " + count);
	}
}

public class MySynchronized {
	public static void main(String[] args) throws InterruptedException {
		new MySynchronizedApp().doWork();
	}
}
