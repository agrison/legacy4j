package me.grison.legacy4j.exception;

/**
 * This exception is thrown in case of any exception detected while reading a file.
 * 
 * @author alexandre
 */
public class FileReadException extends RuntimeException {
	private static final long serialVersionUID = -6497810411909193826L;

	public FileReadException() {
	}

	public FileReadException(String arg0) {
		super(arg0);
	}

	public FileReadException(Throwable arg0) {
		super(arg0);
	}

	public FileReadException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
