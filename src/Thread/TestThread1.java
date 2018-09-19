package Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestThread1 implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		System.out.println("另外一种线程开启模式");
		return 123;
	}

	public static void main(String[] args) {
		TestThread1 th = new TestThread1();
		FutureTask<Integer> ft = new FutureTask<Integer>(th);
		Thread t1 = new Thread();
		t1.start();
		try {
			System.out.println(ft.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
