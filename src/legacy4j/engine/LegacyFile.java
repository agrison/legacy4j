package legacy4j.engine;

import java.util.List;

public interface LegacyFile<T> {
	public LegacyFile<T> open();
	public void close();
	public T readNext();
	public List<T> read();
	public T convert(String line);
}
