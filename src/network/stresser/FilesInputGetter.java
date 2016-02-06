package network.stresser;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesInputGetter {

	private Path requestFile;
	private Path responseFile;
	private static final String DEFAULT_FOLDER = "resources";

	public FilesInputGetter(String requestFile, String responseFile) {
		Path requestPath = Paths.get(DEFAULT_FOLDER, requestFile);
		Path responsePath = Paths.get(DEFAULT_FOLDER, responseFile);
		this.requestFile = requestPath;
		this.responseFile = responsePath;
	}

	public String getRequestFile() {
		return requestFile.toString();
	}

	public String getResponseFile() {
		return responseFile.toString();
	}

	public void setRequestFile(Path requestFile) {
		this.requestFile = requestFile;
	}

	public void setResponseFile(Path responseFile) {
		this.responseFile = responseFile;
	}

}
