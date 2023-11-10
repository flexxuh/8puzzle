/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
package src;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] lis;
    private int len = 10;
    private int count = 0;
    private boolean shuffled = false;


    // construct an empty randomized queue

    public RandomizedQueue() {
        lis = (Item[]) new Object[10];
    }

    private RandomizedQueue(Item[] lis, int count) {
        this.lis = (Item[]) new Object[lis.length];
        int t = 0;
        for (int i = 0; i < lis.length; i++) {
            if (lis[i] != null)
                this.lis[t++] = lis[i];
        }
        len = lis.length;
        this.count = count;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (item != null) {
            shuffled = false;
            if (count != len) {
                lis[count++] = item;
            }
            else {
                len *= 2;
                Item[] temp = (Item[]) new Object[len];
                int t = 0;
                for (int i = 0; i < lis.length; i++) {
                    if (lis[i] != null) {
                        temp[t++] = lis[i];
                    }
                }
                count = t;
                lis = temp;
                lis[count++] = item;
            }
        }
        else {
            throw new IllegalArgumentException("Item was null");
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (!this.isEmpty()) {
            if (!shuffled) {
                StdRandom.shuffle(lis);
                int t = 0;
                Item[] temp = (Item[]) new Object[len];
                for (int i = 0; i < len; i++) {
                    if (lis[i] != null)
                        temp[t++] = lis[i];
                }
                count = t;
                lis = temp;
                shuffled = true;
            }
            Item p = lis[--count];
            lis[count] = null;
            if (count <= len / 3 && count > 10) {
                Item[] temp = (Item[]) new Object[count];
                len = count;
                int t = 0;
                for (Item i : lis) {
                    if (i != null) {
                        temp[t++] = i;
                    }
                }
                lis = temp;
            }
            return p;

        }
        else {
            throw new java.util.NoSuchElementException("Random Queue is Empty");
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (!this.isEmpty()) {
            if (shuffled) {
                Item p = dequeue();
                enqueue(p);
                shuffled = false;
                return p;
            }
            else {
                StdRandom.shuffle(lis);
                int t = 0;
                Item[] temp = (Item[]) new Object[len];
                for (int i = 0; i < len; i++) {
                    if (lis[i] != null)
                        temp[t++] = lis[i];
                }
                count = t;
                lis = temp;
                Item p = dequeue();
                enqueue(p);
                shuffled = true;
                return p;
            }
        }
        else {
            throw new java.util.NoSuchElementException("Rand Queue is Empty");
        }
    }


    public Iterator<Item> iterator() {
        randomIterator rand = new randomIterator();
        return rand;
    }

    private class randomIterator implements Iterator<Item> {
        private RandomizedQueue<Item> tLis = new RandomizedQueue<>(lis, count);

        public boolean hasNext() {
            if (tLis.count > 0) {
                return true;
            }
            return false;

        }

        public Item next() {
            if (!tLis.isEmpty()) {
                Item p = tLis.dequeue();
                return p;
            }
            else {
                throw new java.util.NoSuchElementException("Deque is empty");
            }
        }


    }

    // return an independent iterator over items in random order


    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        int count = 50;
        int num;
        for (int i = 0; i < count; i++) {
            num = StdRandom.uniformInt(2);
            if (num == 0) {
                queue.enqueue(i);
            }
            else {
                for (int p : queue) {
                    System.out.print(p + " ");
                }
                System.out.println();
            }
        }
        // queue.enqueue(21);
        // queue.enqueue(3);
        // for (int i : queue) {
        //     System.out.print(i + " ");
        // }
        // System.out.println();
        // queue.enqueue(3);
        // queue.enqueue(28);
        // queue.enqueue(13);
        // queue.enqueue(17);
        // queue.enqueue(31);
        // for (int i : queue) {
        //     System.out.print(i + " ");
        // }
        // System.out.println();
        // queue.enqueue(5);
        // for (int i : queue) {
        //     System.out.print(i + " ");
        // }
        // System.out.println();

    }
}

