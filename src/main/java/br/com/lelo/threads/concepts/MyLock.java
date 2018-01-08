package br.com.lelo.threads.concepts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.lelo.threads.commons.MyThreadUtils;

class MyLockProcessor {

	private List<Integer> first = new ArrayList<Integer>();
	private List<Integer> second = new ArrayList<Integer>();
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	private Random random = new Random();

	public void stageOne() {
		MyThreadUtils.silientSleep(1);
		synchronized (lock1) {
			first.add(random.nextInt(100));
		}
	}

	public void stageTwo() {
		MyThreadUtils.silientSleep(1);
		synchronized (lock2) {
			second.add(random.nextInt(100));
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
		MyThreadUtils.startAndJoin(new Thread(() -> process()), new Thread(() -> process()));
		System.out.println("first: " + first.size() + " - second: " + second.size());
		System.out.println("Time> " + Long.valueOf(System.currentTimeMillis() - start));
	}

}

public class MyLock {

	public static void main(String[] args) throws InterruptedException {
		new MyLockProcessor().main();
	}

}
