package me.grison.legacy4j.engine;

import java.util.Iterator;
import java.util.List;

/**
 * A Legacy4j file.
 *
 * @author Alexandre Grison <a.grison@gmail.com>
 */
public interface LegacyFile<T> extends Iterator<T>, Iterable<T> {
	public LegacyFile<T> open();
	public LegacyFile<T> close();
	public T readNext();
	public List<T> read();
	public T convert(String line);
}
