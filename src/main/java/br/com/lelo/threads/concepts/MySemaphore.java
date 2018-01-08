package br.com.lelo.threads.concepts;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import br.com.lelo.threads.commons.MyThreadUtils;

class MySemaphoreProcessor {

	private int count = 0;
	private Lock lock = new ReentrantLock();

	public void doWork() throws InterruptedException {

		Thread firstThread = new Thread(() -> {
			for (int index = 0; index < 10000; index++) {
				increment();
			}
		});

		Thread secondThread = new Thread(() -> {
			for (int index = 0; index < 10000; index++) {
				increment();
			}
		});

		MyThreadUtils.startAndJoin(firstThread, secondThread);

		System.out.println("Count is: " + count);
	}

	private void increment() {
		lock.lock();
		count++;
		lock.unlock();
	}
}

public class MySemaphore {
	public static void main(String[] args) throws InterruptedException {
		new MySemaphoreProcessor().doWork();
	}
}
