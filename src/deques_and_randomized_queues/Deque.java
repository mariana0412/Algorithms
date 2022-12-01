package deques_and_randomized_queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int n;

    private class Node {
        private Item item;
        private Node prev;
        private Node next;
    }

    public Deque() {
        n = 0;
    }
    public boolean isEmpty(){
        return n == 0;
    }

    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if(item == null)
            throw new NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        first.next = oldFirst;
        if(oldFirst != null)
            oldFirst.prev = first;
        else
            last = first;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if(item == null)
            throw new NullPointerException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        last.next = null;
        if(oldLast != null)
            oldLast.next = last;
        else
            first = last;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if(this.isEmpty())
            throw new NoSuchElementException();
        Node oldFirst = first;
        first = first.next;
        if(first != null)
            first.prev = null;
        n--;
        return oldFirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if(this.isEmpty())
            throw new NoSuchElementException();
        Node oldLast = last;
        last = last.prev;
        if(last != null)
            last.next = null;
        n--;
        return oldLast.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Item item : this)
            stringBuilder.append(item).append(" ");
        return stringBuilder.toString();
    }


}
