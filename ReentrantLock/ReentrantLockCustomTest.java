package Locks;

public class ReentrantLockCustomTest {
	public static void main(String[] args) {

		CustomReentrantLock LockCustom = new CustomReentrantLock();
		MyRunnable myRunnable = new MyRunnable(LockCustom);
		new Thread(myRunnable, "Thread-1").start();
		new Thread(myRunnable, "Thread-2").start();

	}
}

class MyRunnable implements Runnable {

	CustomReentrantLock lockCustom;

	public MyRunnable(CustomReentrantLock LockCustom) {
		this.lockCustom = LockCustom;
	}

	public void run() {

		System.out.println(Thread.currentThread().getName() + " is Waiting to acquire LockCustom");

		lockCustom.lock();

		System.out.println(Thread.currentThread().getName() + " has acquired LockCustom.");

		try {
			Thread.sleep(5000);
			System.out.println(Thread.currentThread().getName() + " is sleeping.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName() + " has released LockCustom.");

		lockCustom.unlock();
	}
}