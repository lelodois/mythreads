package br.com.lelo.threads.concepts;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import br.com.lelo.threads.commons.MyThreadUtils;

class MyBlockingQueueProducer {

	private BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	private Random random = new Random();

	public void producer() {
		try {
			while (true) {
				System.out.println("Inserting....");
				queue.put(random.nextInt(100));
				System.out.println("Inserted");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void consumer() {
		try {
			while (true) {
				MyThreadUtils.silientSleep(100);
				if (random.nextInt(10) == 0) {
					Integer taked;
					taked = queue.take();
					System.out.println(taked + " size in queue: " + queue.size());
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class MyBlockingQueue {

	public static void main(String[] args) {
		MyBlockingQueueProducer myBlockingQueue = new MyBlockingQueueProducer();
		MyThreadUtils.startAndJoin(new Thread(() -> myBlockingQueue.producer()), new Thread(() -> myBlockingQueue.consumer()));
	}

}
