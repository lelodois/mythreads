package br.com.lelo.threads.concepts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class MySynchronizedLockApp {

	private List<Integer> first = new ArrayList<Integer>();
	private List<Integer> second = new ArrayList<Integer>();
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	private Random random = new Random();

	public void stageOne() {
		silientSleep(1);
		synchronized (lock1) {
			first.add(random.nextInt(100));
		}
	}

	public void stageTwo() {
		silientSleep(1);
		synchronized (lock2) {
			second.add(random.nextInt(100));
		}
	}

	private void silientSleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void process() {
		for (int i = 0; i < 100; i++) {
			stageOne();
			stageTwo();
		}
	}

	public void main() throws InterruptedException {
		long start = System.currentTimeMillis();
		start(new Thread(() -> process()), new Thread(() -> process()));
		System.out.println("first: " + first.size() + " - second: " + second.size());
		System.out.println("Time> " + Long.valueOf(System.currentTimeMillis() - start));
	}

	private void start(Thread... threads) throws InterruptedException {
		for (Thread thread : threads) {
			thread.start();
		}
		for (Thread thread : threads) {
			thread.join();
		}
	}
}

public class MySynchronizedLock {

	public static void main(String[] args) throws InterruptedException {
		new MySynchronizedLockApp().main();
	}

}
