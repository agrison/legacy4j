package legacy4j.engine;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import legacy4j.annotation.FixedLengthRecord;

/**
 * This represents a legacy fixed file length.
 * 
 * @author Alexandre Grison <a.grison@gmail.com>
 */
public class FixedLengthFile<T> extends LegacyFileImpl<T> implements Iterator<T>, Iterable<T> {
    private Class<T> clazz;
    int currentLine = -1;
    T current = null;

    public FixedLengthFile(String file, Class<T> clazz) {
        setFile(file);
        this.clazz = clazz;
        open();
    }

    @Override
    public List<T> read() {
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = nextLine()) != null) {
            lines.add(line);
        }
        return null;
    }

    @Override
    public T readNext() {
        ++currentLine;
        String line = nextLine();
        if (line == null)
            return null;

        FixedLengthRecord flr = clazz.getAnnotation(FixedLengthRecord.class);
        if (flr != null) {
            if (currentLine < flr.ignoreFirst() - 1)
                return readNext();
            if (line.matches(flr.ignoreMatching()))
                return readNext();
            if ("".equals(line) && flr.ignoreEmpty())
                return readNext();
        }
        if (line != null && !"".equals(line.trim())) {
            return convert(line);
        }
        return null;
    }

    @Override
    public T convert(String line) {
        return RecordConverter.fromString(clazz, line);
    }

    @Override
    public boolean hasNext() {
        current = null;
        ++currentLine;
        String line = nextLine();
        if (line == null)
            return false;

        FixedLengthRecord flr = clazz.getAnnotation(FixedLengthRecord.class);
        if (flr != null) {
            if (currentLine < flr.ignoreFirst() - 1)
                return hasNext();
            if (line.matches(flr.ignoreMatching()))
                return hasNext();
            if ("".equals(line) && flr.ignoreEmpty())
                return hasNext();
        }
        if (!"".equals(line.trim())) {
            current = convert(line);
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        return current;
    }

    @Override
    public void remove() {
        throw new IllegalStateException("Remove is not implemented!");
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }
}
