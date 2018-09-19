package Thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestThreadPropogation extends Thread {

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition(); ;
	AtomicInteger atomicInteger;
	@Override
	public void run() {
		while (!interrupted()) {
			System.out.println("来劲了");
		}
	}

	public void func() {
		lock.lock();
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println(i);
			}
		}finally {
			lock.unlock();
		}

	}
	public void before(){
		lock.lock();
		try {
			System.out.println("before");
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
	public void after(){
		lock.lock();
		try {
			condition.await();
			System.out.println("after");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final int totalThread = 10;
//		CountDownLatch countDownLatch = new CountDownLatch(totalThread);
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < totalThread; i++) {
			executorService.execute(() -> {
				System.out.print("run..");
//				countDownLatch.countDown();
			});
		}
//		countDownLatch.await();
		System.out.println("end");
		executorService.shutdown();
	}
}
