package br.com.lelo.threads.concepts;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.lelo.threads.commons.MyThreadUtils;

class MyCountDownLatchProcessor implements Runnable {
	final private CountDownLatch latch;

	public MyCountDownLatchProcessor(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("Started");
		MyThreadUtils.silientSleep(3000);
		System.out.println("Finish");
		latch.countDown();
	}
}

public class MyCountDownLatch {

	public static void main(String[] args) throws InterruptedException {

		CountDownLatch latch = new CountDownLatch(1);
		ExecutorService executor = Executors.newFixedThreadPool(3);

		for (int index = 0; index < 3; index++) {
			executor.submit(new MyCountDownLatchProcessor(latch));
		}

		latch.await();

		System.out.println("Completed	");
	}

}
