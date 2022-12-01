package deques_and_randomized_queues;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int n;
    private Item[] RandQ;

    public RandomizedQueue() {
        RandQ = (Item[]) new Object[1];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if(item == null)
            throw new NullPointerException();
        if(n == RandQ.length)
            resize(RandQ.length * 2);
        RandQ[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if(isEmpty())
            throw new NoSuchElementException();
        int random = StdRandom.uniform(n);
        Item item = RandQ[random];
        if(random != (n - 1))
            RandQ[random] = RandQ[n - 1];
        RandQ[n - 1] = null;
        n--;
        if(n == RandQ.length / 4)
            resize(RandQ.length / 2);
        return item;
    }

    private void resize(int capacity){
        Item[] copy = (Item[])new Object[capacity];
        if (n >= 0) System.arraycopy(RandQ, 0, copy, 0, n);
        RandQ = copy;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if(isEmpty())
            throw new NoSuchElementException();
        int random = StdRandom.uniform(n);
        return RandQ[random];

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandQIterator();
    }

    private class RandQIterator implements Iterator<Item> {
        private final Item[] copy = (Item[]) new Object[RandQ.length];
        private int i = n;

        public RandQIterator() {
            System.arraycopy(RandQ, 0, copy, 0, RandQ.length);
        }

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            int random = StdRandom.uniform(i);
            Item item = copy[random];
            if (random != i - 1)
                copy[random] = copy[i -1];
            copy[i--] = null;
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
