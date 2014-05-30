package control;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
	private GameTimeListener listener;
	private Timer timer;
	private long delay;
	private long sec;

	public GameTimer() {
		delay = 1;
	}

	/**
	 * 
	 * 設定傾聽timer事件
	 * 
	 * @param li
	 */

	public void addTimeListener(GameTimeListener li) {
		listener = li;
	}

	/**
	 * 
	 * 啟動TIMER
	 * 
	 * @param s
	 */

	public void startTimer(int s) {
		if (timer == null) {
			timer = new Timer();
			sec = s;
			TimerTask task = new TimerTask() {
				public void run() {
					sec -= delay;
					if (listener != null) {
						listener.onChange(sec);
					}
					if (sec <= 0) {
						stopTimer();
						if (listener != null) {
							listener.timeOut();
						}
					}
				}
			};
			long delaySec = delay * 1000;
			timer.schedule(task, delaySec, delaySec);
		}
	}

	/**
	 * 
	 * 停止TIMER
	 * 
	 * @param s
	 */
	public void stopTimer() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}

//	public static void main(String[] args) {
//
//		// 範例
//
//		GameTimer timer = new GameTimer();
//		// 傾聽計時器timeout事件(可選的事件，不實作也可以使用timer
//		timer.addTimeListener(new GameTimeListener() {
//			@Override
//			public void timeOut() {
//				// 處理TimeOut事件
//				System.out.println("it is to late");
//			}
//
//			@Override
//			public void onChange(long sec) {
//				System.out.println("sec=>" + sec);
//			}
//		});
//		timer.startTimer(1);
//	}

}
