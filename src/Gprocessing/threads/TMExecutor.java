package Gprocessing.threads;

import java.util.concurrent.Executor;

public class TMExecutor implements Executor {
	boolean running = false;

	public void execute(Runnable r) {
		Thread currentThread = new Thread(r);
		currentThread.start();
		if (currentThread.isAlive()) {
			running = true;
		} else {
			running = true;
		}
	}
}