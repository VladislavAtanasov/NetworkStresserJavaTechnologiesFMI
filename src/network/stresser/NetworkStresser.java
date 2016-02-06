package network.stresser;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class NetworkStresser implements Runnable {

	private static Long maxTimeForResponse = 0L;
	private ConnectionManager connectionManager;
	public static Boolean isFailed = false;
	private CyclicBarrier barrier;

	public NetworkStresser(ConnectionManager connectionManager, CyclicBarrier barrier) {
		this.connectionManager = connectionManager;
		this.barrier = barrier;
	}

	public void stressNetwork() {
		try {
			Long startTime = System.currentTimeMillis();
			connectionManager.sendRequest();
			Long timeToRespond = System.currentTimeMillis() - startTime;
			System.out.println(Thread.currentThread().getName() + ", time: " + timeToRespond + " ms");
			synchronized (maxTimeForResponse) {
				if (timeToRespond > maxTimeForResponse) {
					maxTimeForResponse = timeToRespond;
				}
			}

		} catch (IOException | WrongResponseException e) {
			synchronized (isFailed) {
				System.out.println(e.getMessage());
				System.out.println("FAIL");
				if (!isFailed) {
					isFailed = true;
				}
			}
		}
	}

	public static long getMaxTimeToRespond() {
		return maxTimeForResponse;
	}

	@Override
	public void run() {
		try {
			this.barrier.await();
			this.stressNetwork();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
