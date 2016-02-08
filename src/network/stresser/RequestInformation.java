package network.stresser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RequestInformation {

	private String request;
	private String expectedResponse;
	private String host;
	private int port;

	public RequestInformation(String request, String expectedResponse, String host, int port) {
		this.setRequest(request);
		this.setExpectedResponse(expectedResponse);
		this.setHost(host);
		this.setPort(port);
	}

	public RequestInformation(FilesInputGetter files) {
		this.setRequestInfoFromFile(files.getRequestFile());
		this.setExpectedResponseFromFile(files.getResponseFile());
	}

	private void setRequestInfoFromFile(String file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			this.setRequest(br.readLine());
			this.setHost(br.readLine());
			this.setPort(Integer.parseInt(br.readLine()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setExpectedResponseFromFile(String file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			this.setExpectedResponse(br.readLine());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getRequest() {
		return request;
	}

	public String getExpectedResponse() {
		return expectedResponse;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public void setExpectedResponse(String expectedResponse) {
		this.expectedResponse = expectedResponse;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
