package network.stresser;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NetworkStresserStarter {

	private FilesInputGetter files;
	private RequestInformation info;
	private ConnectionManager connManager;
	private int requests = 1;
	private static final String DEFAULT_EXPECTED_RESPONSE = "HTTP/1.1 200 OK";

	public NetworkStresserStarter(String requestFile, String responseFile) throws UnknownHostException, IOException {
		files = new FilesInputGetter(requestFile, responseFile);
		info = new RequestInformation(files);
		connManager = new ConnectionManager(info);
	}

	public NetworkStresserStarter(String request, String host, int port) throws UnknownHostException, IOException {
		info = new RequestInformation(request, DEFAULT_EXPECTED_RESPONSE, host, port);
		connManager = new ConnectionManager(info);
	}

	public void startStresser() throws InterruptedException {
		while (true) {
			CyclicBarrier barrier = new CyclicBarrier(requests);
			NetworkStresser networkStresser = new NetworkStresser(connManager, barrier);
			ExecutorService executor = Executors.newFixedThreadPool(requests);

			for (int i = 0; i < requests; i++) {
				executor.execute(networkStresser);
			}

			executor.shutdown();
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			if (NetworkStresser.isFailed) {
				break;
			}
			requests++;
		}

		System.out.printf("Maximum successful request: %d\n", requests - 1);
		System.out.println("Max time for successful request: " + NetworkStresser.getMaxTimeToRespond() + " ms");

	}

	public String startStresserGui() throws InterruptedException {
		StringBuilder sb = new StringBuilder();
		startStresser();
		sb.append(requests - 1);
		sb.append("\n");
		sb.append((NetworkStresser.getMaxTimeToRespond() / 1000.0) + " s");
		return sb.toString();

	}

}
