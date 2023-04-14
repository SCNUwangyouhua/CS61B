package deque;

import javax.sound.sampled.Line;
import java.util.Iterator;
//import java.util.Deque;
public class LinkedListDeque<Item> implements Iterable<Item>, Deque<Item> {
    private final LListNode<Item> sentinel = new LListNode(null, null, null);
    private class LListNode<Item> {
        LListNode<Item> prev;
        LListNode<Item> next;
        Item item;
        LListNode(LListNode<Item> _prev, Item _item, LListNode<Item> _next) {
            this.item = _item;
            this.prev = _prev;
            this.next = _next;
        }
    }
    private int size;
    public LinkedListDeque() {
        size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public LinkedListDeque(Item item) {
        size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        addFirst(item);
    }

    /**
     *
     * @param item the value(type: Item) that you insert to the front of the deque
     *             You can assume that item is never null
     */
    public void addFirst(Item item) {
        LListNode addNode = new LListNode(sentinel, item, sentinel.next);
        sentinel.next.prev = addNode;
        sentinel.next = addNode;
        if (size == 0){
            sentinel.prev = addNode;
        }
        size += 1;
    }

    /**
     *
     * @param item the value(type: Item) that you insert to the last of the deque
     *             You can assume that item is never null !!
     */
    public void addLast(Item item) {
        LListNode<Item> oldLastNode = sentinel.prev;
        LListNode<Item> addNode = new LListNode(oldLastNode, item, sentinel);
        sentinel.prev = addNode;
        oldLastNode.next = addNode;
        if (size == 0){
            sentinel.next = addNode;
        }
        size += 1;
    }

    /**
     *
     * @return true if deque is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *
     * @return the number of items in the deque
     */
    public int size() {
        return size;
    }

    /**
     * print the item in the deque from first to last, separated by a space
     */
    public void printDeque() {
        if (this.isEmpty()) {
            System.out.println("the deque is empty");
            return;
        }
        for (LListNode p = sentinel.next; p != sentinel; p = p.next){
            System.out.print(p.item + " ");
        }
    }

    /**
     *
     * @return the item at the front of the deque and remove it
     * if no such item exists, return null
     */
    public Item removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        Item returnItem = sentinel.next.item;
        sentinel.next.item = null;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return returnItem;
    }

    /**
     *
     * @return the item at the back of the deque and remove it
     * if no such item exists, return null
     */
    public Item removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        Item returnItem = sentinel.prev.item;
        sentinel.prev.item = null;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return returnItem;
    }

    /**
     * use iteration , not recursion 使用迭代而不是递归
     * @param index
     * @return the item at the given index, where 0 is the front, 1 is the next item and so forth.
     * If no such item exists, return null.Must not alter the deque!! 不要改变队列
     */
    public Item get(int index) {
        if (index < 0) {
            return null;
        }
        int IteraIdx = 0;
        for (LListNode<Item> p = sentinel.next; p != sentinel; p = p.next){
            if (IteraIdx != index){//if size < index , should return null
                IteraIdx += 1;
                continue;
            }
            return p.item;
        }
        return null;//return null in the last , so reduce accident
    }

    //手写LinkedListDeque的迭代器
    private class LinkedListDequeIterator implements Iterator<Item> {
        private LListNode<Item> p;
        public LinkedListDequeIterator() {
            p = sentinel.next;
        }
        public boolean hasNext() {
            return p == sentinel;
        }
        public Item next() {
            Item returnItem = p.item;
            p = p.next;
            return returnItem;
        }
    }


    //还有两个特殊函数需要实现

    /**
     * The Deque objects we'll make are iterable, so we must provide this method to return an iterator
     * @return
     */
    public Iterator<Item> iterator() {
        return new LinkedListDequeIterator();
    }

    /**
     *
     * @param o is considered equal if it is a Deque and if it contains the same contents (as governed by the generic Item's equals method)
     *          in the same order!
     *          You will need to use the "instance of" keywords for this. Read this passage -> (https://www.javatpoint.com/downcasting-with-instanceof-operator)
     * @return  true if the parameter o is equal to the Deque, otherwise false
     */
    @Override
    public boolean equals(Object o) {
         if (this == o){
             return true;
         }
         if (o == null && size != 0) {
             return false;
         }
         if (!(o instanceof LinkedListDeque)) {
             return false;
         }
         LinkedListDeque<Item> oDeque = (LinkedListDeque<Item>) o;
         if (oDeque.size() != this.size()) {
             return false;
         }

         for (int i = 0; i < size; ++i) {
             if (oDeque.get(i) != this.get(i)){
                 return false;
             }
         }
         return true;
    }

    public Item getRecurHelper(int IteraIdx, int index, LListNode<Item> IteraNode){
        if (IteraNode.item == null) {
            return null;
        }
        if (IteraIdx == index) {
            return IteraNode.item;
        }
        return getRecurHelper(IteraIdx+1, index, IteraNode.next);
    }

    /**
     * Same as get, but uses recursion
     * @param index
     * @return
     */
    public Item getRecursive(int index) {
        if (index < 0){
            return null;
        }
        Item getItem = getRecurHelper(0, index, sentinel.next);
        if (getItem == null){
            return null;
        }
        return getItem;
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> TestList = new LinkedListDeque<Integer>();
        TestList.addFirst(1);
        TestList.addLast(2);
        TestList.addFirst(3);
        TestList.addLast(4);
        TestList.printDeque();
        System.out.println();
        System.out.println(TestList.get(3));
        System.out.println(TestList.getRecursive(4));
        LinkedListDeque<Integer> TestList2 = new LinkedListDeque<>();
        TestList2.addFirst(1);
        TestList2.addLast(2);
        TestList2.addFirst(3);
        TestList2.addLast(4);
        System.out.println(TestList.equals(TestList2));
    }
}
