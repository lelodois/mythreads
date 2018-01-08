package br.com.lelo.threads.concepts;

import br.com.lelo.threads.commons.MyThreadUtils;

class MySynchronizedProcessor {

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

		MyThreadUtils.startAndJoin(firstThread, secondThread);

		System.out.println("Count is: " + count);
	}
}

public class MySynchronized {
	public static void main(String[] args) throws InterruptedException {
		new MySynchronizedProcessor().doWork();
	}
}
