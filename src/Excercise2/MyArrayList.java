package Excercise2;


import java.util.*;

public class MyArrayList<T> implements List<T> {
    int size;                   // keeps track of the number of elements
    private T[] array;         // stores the elements

    /**
     *
     * */
    @SuppressWarnings("unchecked")
    public MyArrayList() {
        array = (T[]) new Object[10];
        size = 0;
    }

    /**
     * @param args
     * */

    public static void main(String[] args) {
        // run a few simple tests
        MyArrayList<Integer> mal = new MyArrayList<>();
        mal.add(1);
        mal.add(2);
        mal.add(3);
        System.out.println(Arrays.toString(mal.toArray()) + ", size = " + mal.size);

        mal.remove(2);
        System.out.println(Arrays.toString(mal.toArray()) + ", size = " + mal.size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        // make a copy of the array
        T[] copy = Arrays.copyOf(array, size);
        return Arrays.asList(copy).iterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T element) {
        if(size >= array.length) {
            // make a bigger array and copy over the elements
            T[] bigger = (T[]) new Object[array.length * 2];
            System.arraycopy(array, 0, bigger, 0, array.length);
            array = bigger;
        }
        array[size] = element;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object obj) {
        int index = indexOf(obj);
        if(index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for(Object object: collection) {
            if(!contains(object)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean flag = true;
        for(T element: collection) {
            flag &= add(element);
        }
        return flag;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean flag = true;
        for(Object obj: collection) {
            flag &= remove(obj);
        }
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public T set(int index, T element) {
        T old = get(index);
        array[index] = element;
        return old;
    }

    @Override
    public void add(int index, T element) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        // add the element to get the resizing
        add(element);
        // shift the elements
        for(int i = size - 1; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = element;
    }

    @Override
    public T remove(int index) {
        T element = get(index);
        for(int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return element;
    }

    @Override
    public int indexOf(Object obj) {
        for(int i = 0; i < size; i++) {
            if(equals(obj, array[i])) {
                return i;
            }
        }
        return -1;
    }

    private boolean equals(Object target, Object element) {
        if(target == null) {
            return element == null;
        } else {
            return target.equals(element);
        }
    }

    @Override
    public int lastIndexOf(Object obj) {
        for(int i = size - 1; i >= 0; i--) {
            if(equals(obj, array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        // make a copy of the array
        T[] copy = Arrays.copyOf(array, size);
        // make a list and return an iterator
        return Arrays.asList(copy).listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        // make a copy of the array
        T[] copy = Arrays.copyOf(array, size);
        // make a list and return an iterator
        return Arrays.asList(copy).listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if(fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        T[] copy = Arrays.copyOfRange(array, fromIndex, toIndex);
        return Arrays.asList(copy);
    }

}
