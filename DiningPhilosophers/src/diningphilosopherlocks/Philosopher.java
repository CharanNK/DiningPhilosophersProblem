package diningphilosopherlocks;

import java.util.Random;

public class Philosopher implements Runnable{
	private int id;
	
	private Chopstick leftChopStick;
	private Chopstick rightChopStick;
	
	private Random random;
	private int eatingCounter;
	private boolean isFull = false;
	
	public Philosopher(int id,Chopstick lefChopstick,Chopstick rightChopStick) {
		this.id = id;
		this.leftChopStick = lefChopstick;
		this.rightChopStick = rightChopStick;
		random = new Random();
	}
	
	@Override
	public void run() {
		try {
			while(!isFull) {
				think();
				if(leftChopStick.pickUp(this, States.LEFT)) {
					if(rightChopStick.pickUp(this, States.RIGHT)) {
						eat();
						rightChopStick.putDown(this, States.RIGHT);
					}
					leftChopStick.putDown(this, States.LEFT);
				}
			}
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	public void think() throws InterruptedException{
		System.out.println(this+" is thinking..");
		Thread.sleep(this.random.nextInt(1000));
	}
	
	public void eat() throws InterruptedException{
		System.out.println(this+" is eating..");
		eatingCounter++;
		Thread.sleep(this.random.nextInt(1000));
	}
	
	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}
	
	public int getEatingCounter() {
		return this.eatingCounter;
	}
	
	@Override
	public String toString() {
		return "Philosopher "+this.id;
	}
}
