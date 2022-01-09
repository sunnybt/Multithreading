package Locks;

public class ReentrantLockCustomTryLockTest {
	public static void main(String[] args) {

		CustomReentrantLock lockCustom = new CustomReentrantLock();
		MyRunnable1 myRunnable = new MyRunnable1(lockCustom);
		new Thread(myRunnable, "Thread-1").start();
		new Thread(myRunnable, "Thread-2").start();

	}
}

class MyRunnable1 implements Runnable {

	CustomReentrantLock lockCustom;

	public MyRunnable1(CustomReentrantLock lockCustom) {
		this.lockCustom = lockCustom;
	}

	public void run() {

		System.out.println(Thread.currentThread().getName() + " is Waiting to acquire lock");
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (lockCustom.tryLock()) {
			System.out.println(Thread.currentThread().getName() + " has acquired lock.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println(Thread.currentThread().getName() + " didn't got lock.");

		}

	}
}
