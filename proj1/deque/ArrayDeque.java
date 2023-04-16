package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {

    private T[] items = (T[]) new Object[20];
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque (T item) {
        size = 1;
        nextFirst = 9;
        nextLast = 10;
        items[nextFirst] = item;
        nextFirst -= 1;
    }

    public ArrayDeque () {
        size = 0;
        nextFirst = 9;
        nextLast = 10;
    }

    public void resize(int newSize) {
        T[] newitems = (T[]) new Object[newSize];
        int firstPos = Math.abs(newSize - size) / 2;
        System.arraycopy(items, nextFirst + 1, newitems, firstPos, size);
        items = newitems;
        nextFirst = firstPos - 1;
        nextLast = firstPos + size;
    }

    public void adjustSize() {
        if (isEmpty()) {
            resize(8);
        } else if (items.length / 4 > size && size >= 4) {
            resize (size * 2);
        }
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        size += 1;
        nextFirst -= 1;
        if (nextFirst == -1){
            resize(2 * size);
        }
    }

    public void addLast(T item) {
        items[nextLast] = item;
        size += 1;
        nextLast += 1;
        if (nextLast == items.length){
            resize(2 * size);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i += 1) {
            System.out.print(get(i));
            System.out.print(" ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst += 1;
        T returnItem = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        adjustSize();
        return returnItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast -= 1;
        T returnItem = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        adjustSize();
        return returnItem;
    }

    public T get(int index) {
        if (index < 0 || index > size - 1){
            return null;
        }
        int itemIndex = nextFirst + 1 + index;
        return items[itemIndex];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements  Iterator<T> {
        private int wizPos;
        ArrayDequeIterator() {
            wizPos = 0;
        }
        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnitem = get(wizPos);
            wizPos += 1;
            return returnitem;
        }
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> oAD = (Deque<T>) o;
        if (oAD.size() != size){
            return false;
        }
        for (int i = 0; i < size; i += 1) {
            if (!(oAD.get(i).equals(get(i)))){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        ArrayDeque<Integer> testAD = new ArrayDeque<Integer>();
        testAD.addFirst(3);
        testAD.addFirst(5);
        testAD.addLast(7);
        testAD.addLast(9);
        testAD.printDeque();
    }
}
