package network.stresser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionManager {

	private RequestInformation info;

	public ConnectionManager(RequestInformation info) {
		this.info = info;
	}

	private Socket establishSocket(String hostLine, int port) throws UnknownHostException, IOException {
		String host = hostLine.substring(hostLine.indexOf(" ") + 1);
		return new Socket(host, port);
	}

	public void sendRequest() throws IOException, WrongResponseException {
		try (Socket socket = this.establishSocket(info.getHost(), info.getPort());
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
			pw.println(info.getRequest());
			pw.println(info.getHost());
			pw.println("");
			pw.flush();

			String firstLine = br.readLine();
			System.out.println(firstLine);
			if (!firstLine.equals(info.getExpectedResponse())) {
				throw new WrongResponseException("Wrong Response");
			}
		}
	}
}
