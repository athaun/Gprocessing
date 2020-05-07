package Gprocessing.threads;

public class ThreadMaster {

	TMExecutor exec = new TMExecutor();

	public ThreadMaster(Runnable r) {
		exec.execute(r);
	}
}
/*
Used this code to create a new Thread
ThreadMaster foo;
foo = new ThreadMaster(new Runnable() {
	public void run() {
		// code in here
	}
});
*/