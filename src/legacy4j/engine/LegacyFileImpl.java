package legacy4j.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import legacy4j.exception.FileCloseException;
import legacy4j.exception.FileOpenException;
import legacy4j.exception.FileReadException;

public abstract class LegacyFileImpl<T> implements LegacyFile<T> {
	private File file;
	private BufferedReader reader = null;
	
	/**
	 * Set the file we will be working on.
	 * @param file the file
	 */
	protected void setFile(String file) {
		this.file = new File(file);
	}
	protected File getFile() {
		return file;
	}
	
	@Override
	public LegacyFile<T> close() {
		try {
			reader.close();
			return this;
		} catch (IOException e) {
			throw new FileCloseException(e);
		}
	}

	@Override
	public LegacyFile<T> open() {
		try {
			reader = new BufferedReader(new FileReader(file));
			return this;
		} catch (FileNotFoundException e) {
			throw new FileOpenException(e);
		}
	}
	
	protected String nextLine() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			throw new FileReadException(e);
		}
	}
}
