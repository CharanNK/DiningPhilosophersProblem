package diningphilosopherlocks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulator {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = null;
		Philosopher[] philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];
		
		try {
			Chopstick[] chopsticks = new Chopstick[Constants.NUMBER_OF_CHOPSTICKS];
			executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHERS);
			
			for(int i=0;i<Constants.NUMBER_OF_CHOPSTICKS;i++) {
				chopsticks[i] = new Chopstick(i);
			}
			
			for(int i=0;i<Constants.NUMBER_OF_PHILOSOPHERS;i++) {
				philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i+1)%Constants.NUMBER_OF_CHOPSTICKS]);
				executorService.execute(philosophers[i]);
			}
			Thread.sleep(Constants.SIMULATION_RUNTIME);
			
			for(Philosopher philosopher : philosophers) {
				philosopher.setFull(true);
			}
		} finally {
			executorService.shutdown();
			
			while(!executorService.isTerminated()) {
				Thread.sleep(1000);
			}
			
			for(Philosopher philosopher: philosophers) {
				System.out.println(philosopher+" ate "+philosopher.getEatingCounter());
			}
		}
		 
	}

}
