package de.deminosa.core.utils.thread;

public abstract class SimpleThreadWorker {

	private String name;
	private boolean repeat = false;
	private int millseconds = -1;
	private int wait = -1;
	private Thread t;
	
	public SimpleThreadWorker(String name) {
		this.name = name;
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				if(wait != -1) {
					sleep(wait);
				}
				if(!repeat) {
					update();
				}
				if(millseconds != -1) {
					while (repeat) {
						update();
						sleep(millseconds);
					}
				}
			}
		});
		t.setName(name);
	}
	
	/**
	 * 
	 * @param bool
	 * @param interval in Milliseconds
	 */
	public SimpleThreadWorker setRepeat(boolean bool, int interval) {
		this.repeat = bool;
		this.millseconds = interval;
		return this;
	}
	
	public SimpleThreadWorker runTaskLater(int i) {
		if(i < 0) {
			i = 0;
		}
		this.wait = i;
		return this;
	}
	
	public void start() {
		t.start();
	}
	
	@SuppressWarnings("deprecation")
	public void stop() {
		t.stop();
	}
	
	private void sleep(int milli) {
		try {
			Thread.sleep(milli);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public abstract void update();

	public String getName() {
		return name;
	}

	public boolean isRepeat() {
		return repeat;
	}

	public int getMillseconds() {
		return millseconds;
	}

	public Thread getThread() {
		return t;
	}

}
