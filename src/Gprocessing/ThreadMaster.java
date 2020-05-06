package Gprocessing;

import static Gprocessing.Graphics.*;
import static Gprocessing.Engine.*;

public class ThreadMaster {

	TMExecutor exec = new TMExecutor();

	ThreadMaster(Runnable r) {
		exec.execute(r);
	}
}

//t = new ThreadMaster(new Runnable() {
//	public void run() {
//		createHeightMapValues();
//	}
//});