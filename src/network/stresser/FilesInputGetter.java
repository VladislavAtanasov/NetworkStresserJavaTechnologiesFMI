package network.stresser;

import java.nio.file.Paths;

public class FilesInputGetter {

	private String requestFile;
	private String responseFile;
	private static final String DEFAULT_FOLDER = "resources";

	public FilesInputGetter(String requestFile, String responseFile) {
		String requestPath = Paths.get(DEFAULT_FOLDER, requestFile).toString();
		String responsePath = Paths.get(DEFAULT_FOLDER, responseFile).toString();
		this.requestFile = requestPath;
		this.responseFile = responsePath;
	}

	public String getRequestFile() {
		return requestFile.toString();
	}

	public String getResponseFile() {
		return responseFile.toString();
	}

	public void setRequestFile(String requestFile) {
		this.requestFile = requestFile;
	}

	public void setResponseFile(String responseFile) {
		this.responseFile = responseFile;
	}

}
