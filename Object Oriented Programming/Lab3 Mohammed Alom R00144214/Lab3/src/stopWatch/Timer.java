package stopWatch;

/**
 * Name - Mohammed Jahanigr Alom Student No - R00144214 Lab No 3
 */

public class Timer implements Runnable {

	/**
	 * The number of ticks.
	 */
	private int ticks;

	/**
	 * The time interval (in milliseconds) of a tick.
	 */
	private int interval;

	/**
	 * The stopwatch gui which is notified by the timer.
	 */
	private StopWatch gui;

	/**
	 * The thread which triggers the ticks. Is null if the timer is not running.
	 */
	private Thread thread;

	/**
	 * Creates a Timer for the given interval.
	 * 
	 * @param interval
	 *            the time interval (in milliseconds) of the timer
	 */
	public Timer(int interval) {
		this.interval = interval;
	}

	/**
	 * Attachs a Gui to the timer
	 * 
	 * @param gui
	 *            the Stopwatch object to attach to the timer
	 */
	public final void attach(StopWatch gui) {
		this.gui = gui;
	}

	/**
	 * Gets the time of the timer.
	 * 
	 * @return the time (in seconds) of the timer.
	 */
	public final double getTime() {
		return ticks * interval / 1000.0;
	}

	/**
	 * Gets the time of the timer as String.
	 * 
	 * @return the time (in seconds) converted to a String.
	 */
	public final String getTimeString() {
		return String.valueOf(this.getTime());
	}

	/**
	 * Returns true if timer is running.
	 * 
	 * @return true if the timer is running, otherwise false.
	 */
	public final boolean isRunning() {
		return thread != null;
	}

	/**
	 * Starts the timer.
	 */
	public final void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.setDaemon(true);
			thread.setPriority(Thread.MAX_PRIORITY);
			thread.start();
			gui.update();
		}
	}

	/**
	 * Stops the timer.
	 */
	public final void stop() {
		if (thread != null) {
			thread = null;
		}
		gui.update();
	}

	/**
	 * Resets the time of the timer.
	 */
	public final void reset() {
		ticks = 0;
		gui.update();
	}

	/**
	 * Increments ticks at the expiration of the time interval.
	 */
	@Override
	public final void run() {
		while (thread != null) {
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// do nothing
			}
			if (thread != null) {
				ticks++;
				// System.out.println(ticks);
				gui.update();
			}
		}
	}
}
