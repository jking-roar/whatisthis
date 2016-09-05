package impl.bad;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class BadSet implements Set {
    @Override
    public int size() {
        return 4;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return true;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Object next() {
                return 3;
            }

            @Override
            public void remove() {

            }
        };
    }

    @Override
    public Object[] toArray() {
        return new Object[] {3, 3 ,3 ,3};
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[] {3, 3, 3, 3};
    }

    public static void main(String[] args) {
        BadSet x = new BadSet();
        System.out.println(x);
    }
}
