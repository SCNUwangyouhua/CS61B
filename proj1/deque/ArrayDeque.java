package deque;

import java.util.Iterator;
import java.util.Arrays;
import java.util.Objects;

public class ArrayDeque<Item> implements Iterable<Item>, Deque<Item> {

    private Item[] items = (Item[]) new Object[20];
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque (Item item) {
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
        Item[] newitems = (Item []) new Object[newSize];
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

    public void addFirst(Item item) {
        items[nextFirst] = item;
        size += 1;
        nextFirst -= 1;
        if (nextFirst == -1){
            resize(2 * size);
        }
    }

    public void addLast(Item item) {
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

    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst += 1;
        Item returnItem = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        adjustSize();
        return returnItem;
    }

    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast -= 1;
        Item returnItem = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        adjustSize();
        return returnItem;
    }

    public Item get(int index) {
        if (index < 0 || index > size - 1){
            return null;
        }
        int itemIndex = nextFirst + 1 + index;
        return items[itemIndex];
    }

    public Iterator<Item> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements  Iterator<Item> {
        private int wizPos;
        ArrayDequeIterator() {
            wizPos = 0;
        }
        public boolean hasNext() {
            return wizPos < size;
        }

        public Item next() {
            Item returnitem = get(wizPos);
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
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        ArrayDeque<Item> oAD = (ArrayDeque<Item>) o;
        if (oAD.size() != size){
            return false;
        }
        for (int i = 0; i < size; i += 1) {
            if (oAD.get(i) != get(i)){
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
