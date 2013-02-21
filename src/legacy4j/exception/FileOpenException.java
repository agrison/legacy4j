package legacy4j.exception;

/**
 * This exception is thrown in case of any exception detected while opening a file.
 * 
 * @author alexandre
 */
public class FileOpenException extends RuntimeException {
	private static final long serialVersionUID = -38335434647072251L;

	public FileOpenException() {
	}

	public FileOpenException(String arg0) {
		super(arg0);
	}

	public FileOpenException(Throwable arg0) {
		super(arg0);
	}

	public FileOpenException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
