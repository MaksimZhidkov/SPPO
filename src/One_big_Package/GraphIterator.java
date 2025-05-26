package One_big_Package;

import java.util.Iterator;

public class GraphIterator<T> implements Iterator<T> {
    private Iterator<T> iterator;

    public GraphIterator(Iterator<T> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {
        return iterator.next();
    }
}
