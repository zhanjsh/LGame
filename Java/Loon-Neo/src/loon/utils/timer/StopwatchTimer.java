package loon.utils.timer;

import loon.event.Updateable;

/**
 * 计时器(也就是俗称的秒表，需要正常计算时间的游戏都会用到)
 */
public class StopwatchTimer {

	private long from;

	private long to;

	private long lastStop;

	public static StopwatchTimer begin() {
		StopwatchTimer sw = new StopwatchTimer();
		sw.start();
		return sw;
	}

	public static StopwatchTimer make() {
		return new StopwatchTimer();
	}

	public static StopwatchTimer run(Updateable u) {
		StopwatchTimer sw = begin();
		u.action(null);
		sw.stop();
		return sw;
	}

	public long start() {
		from = currentTime();
		to = from;
		lastStop = to;
		return from;
	}

	private long currentTime() {
		return System.currentTimeMillis();
	}

	public StopwatchTimer stop() {
		lastStop = to;
		to = currentTime();
		return this;
	}

	public long getDuration() {
		return to - from;
	}

	public long getLastDuration() {
		return to - lastStop;
	}

	public long getStartTime() {
		return from;
	}

	public long getEndTime() {
		return to;
	}
}
