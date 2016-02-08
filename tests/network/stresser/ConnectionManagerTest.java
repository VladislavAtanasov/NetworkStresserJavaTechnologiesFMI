package network.stresser;

import org.junit.Test;

public class ConnectionManagerTest {
	private RequestInformation info;
	private ConnectionManager connectionManager;
	private FilesInputGetter files;

	@Test
	public void setUp() {
		files = new FilesInputGetter("example_request.txt", "example_expected_response.txt");
		info = new RequestInformation(files);
	}

	@Test(expected = NullPointerException.class)
	public void testSendRequest() throws Exception {
		connectionManager = new ConnectionManager(info);
		connectionManager.sendRequest();
	}

}
