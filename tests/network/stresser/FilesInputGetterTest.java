package network.stresser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class FilesInputGetterTest {
	private FilesInputGetter filesInputGetter;

	@Before
	public void setUp() {
		filesInputGetter = new FilesInputGetter("example_request.txt", "example_expected_response.txt");
	}

	@Test
	public void testGetRequestFile() {
		assertEquals("resources\\example_request.txt", filesInputGetter.getRequestFile());
	}

	@Test
	public void testGetResponseFile() {
		assertEquals("resources\\example_expected_response.txt", filesInputGetter.getResponseFile());
	}

}
