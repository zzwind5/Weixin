package cn.wltc.framework.util.thread;


public class WorkThread extends Thread {
	private boolean _is_running = true;

	public void run() {
		while (_is_running) {
			Task t = ThreadPool.getInstance().getActiveTask();
			if (t != null) {
				t.run();
			}
		}
	}
}
