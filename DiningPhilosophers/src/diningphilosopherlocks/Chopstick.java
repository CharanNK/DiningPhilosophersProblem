package diningphilosopherlocks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
	private int id;
	private Lock lock;
	
	public Chopstick(int id) {
		this.id = id;
		lock = new ReentrantLock();
	}
	
	
	public boolean pickUp(Philosopher philosopher,States state) throws InterruptedException {
		if(this.lock.tryLock(10,TimeUnit.MILLISECONDS)) {
			System.out.println(philosopher+" picked up "+this);
		}
		return false;
	}
	
	public void putDown(Philosopher philosopher,States state) throws InterruptedException{
		lock.unlock();
		System.out.println(philosopher+" put down "+this);
	}
	
	@Override
	public String toString() {
		return "Chopstick "+this.id;
	}
}
