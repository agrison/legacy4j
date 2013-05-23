package me.grison.legacy4j.exception;

/**
 * This exception is thrown in case of any exception detected while closing a file.
 * 
 * @author alexandre
 */
public class FileCloseException extends RuntimeException {
	private static final long serialVersionUID = 1000371289549076524L;

	public FileCloseException() {
	}

	public FileCloseException(String arg0) {
		super(arg0);
	}

	public FileCloseException(Throwable arg0) {
		super(arg0);
	}

	public FileCloseException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
