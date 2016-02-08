package network.stresser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RequestInformationTest {
	private FilesInputGetter files;
	private RequestInformation requestInformation;

	@Before
	public void setUp() {
		files = new FilesInputGetter("example_request.txt", "example_expected_response.txt");
		requestInformation = new RequestInformation(files);
	}

	@Test
	public void testGetPort() {
		assertEquals(80, requestInformation.getPort());
	}

	@Test
	public void testGetHost() {
		assertEquals("Host: java.voidland.org", requestInformation.getHost());
	}

	@Test
	public void testGetRequest() {
		assertEquals("GET http://java.voidland.org/ HTTP/1.1", requestInformation.getRequest());
	}

	@Test
	public void testGetResponse() {
		assertEquals("HTTP/1.1 200 OK", requestInformation.getExpectedResponse());
	}
}
