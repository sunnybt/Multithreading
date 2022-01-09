package Locks;

public class CustomReentrantLock {

	private int lockHoldCount;
	private long IdOfThreadCurrentlyHoldingLock;
	
	CustomReentrantLock(){
      	lockHoldCount=0;
	}

	public synchronized void lock() {
		// Acquires the lock if it is not held by another thread.
		// And sets lock hold count to 1.
		if (lockHoldCount == 0) {
			lockHoldCount++;
			IdOfThreadCurrentlyHoldingLock = Thread.currentThread().getId();
		}
		// If current thread already holds lock then lock hold
		// count is increased by 1.
		else if (lockHoldCount > 0 && IdOfThreadCurrentlyHoldingLock == Thread.currentThread().getId()) {
			lockHoldCount++;
		}
		// If the lock is held by another thread then the current
		// thread waits for another thread to release lock.
		else {
			try {
				wait();
				lockHoldCount++;
				IdOfThreadCurrentlyHoldingLock = Thread.currentThread().getId();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void unlock() {
		// current thread is not holding the lock, throw
		// IllegalMonitorStateException.
		if (lockHoldCount == 0)
			throw new IllegalMonitorStateException();

		lockHoldCount--; // decrement lock hold count by 1

		// if lockHoldCount is 0, lock is released, and
		// one waiting thread is notified.
		if (lockHoldCount == 0)
			notify();
	}

	public synchronized boolean tryLock() {
		// Acquires the lock if it is not held by another thread and
		// returns true
		if (lockHoldCount == 0) {
			lock();
			return true;
		}
		// If lock is held by another thread then method return false.
		else
			return false;
	}
}