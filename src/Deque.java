package src;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Linked<Item> Deque;

    // construct an empty deque
    public Deque() {
        Deque = new Linked<>();
    }

    // is the deque empty?
    public boolean isEmpty() {
        if (Deque.count() == 0) {
            return true;
        }
        return false;
    }


    // return the number of items on the deque
    public int size() {
        return Deque.count();
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item != null) {
            if (Deque.count > 0) {
                Node n = new Node(item);
                Node p = Deque.head;
                n.setNext(p);
                p.setBack(n);
                Deque.head = n;
                Deque.count++;


            }
            else {
                Deque.add(item);

            }
        }
        else
            throw new IllegalArgumentException("Item is null");

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item != null) {
            Deque.add(item);
        }
        else {
            throw new IllegalArgumentException("item is null");
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        int count = Deque.count;
        if (count > 0) {
            if (count > 1) {
                Node<Item> n = Deque.head;
                Deque.head = Deque.head.getNext();
                Deque.head.setBack(null);
                Deque.count--;
                return n.getData();
            }
            else {
                Item m = Deque.head.getData();
                Deque.remove();
                return m;
            }
        }
        else {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
    }

    private void remove() {
        throw new UnsupportedOperationException("Depreciated");
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (Deque.count() > 0) {
            return Deque.remove();
        }
        else {
            throw new java.util.NoSuchElementException("Deque is Empty");
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        Deque.DequeIterator rand = new Deque.DequeIterator();
        return rand;
    }

    // return an iterator over items in order from front to back
    private class DequeIterator implements Iterator<Item> {
        private Node<Item> temp = Deque.head;
        int count = 0;


        public boolean hasNext() {
            if (count < Deque.count) {
                return true;
            }
            return false;
        }

        public Item next() {
            if (count < Deque.count) {
                Item n = temp.getData();
                temp = temp.getNext();
                count++;
                return n;
            }
            else {
                throw new java.util.NoSuchElementException("Deque is empty");
            }
        }


    }


    // unit testing (required)
    public static void main(String[] args) {
        // Deque queue = new Deque();
        // System.out.println("Adding to first and removing from first");
        // // adding all to first and printing first
        // for (int i = 0; i < 73; i++) {
        //     queue.addFirst(i);
        // }
        // while (queue.hasNext()) {
        //     System.out.println(queue.removeFirst());
        // }
        // System.out.println();
        // queue = new Deque();
        // // adding all to first and printing last
        // System.out.println("Adding to first and removing from last");
        // for (int i = 0; i < 73; i++) {
        //     queue.addFirst(i);
        // }
        // while (queue.hasNext()) {
        //     System.out.println(queue.removeLast());
        // }
        //
        // System.out.println();
        // // adding all to last and removing last
        // queue = new Deque();
        // System.out.println("Adding to last and removing from last");
        // for (int i = 0; i < 73; i++) {
        //     queue.addLast(i);
        // }
        // while (queue.hasNext()) {
        //     System.out.println(queue.removeLast());
        // }
        //
        // System.out.println();
        // // adding all to last and removing first
        // queue = new Deque();
        // System.out.println("Adding to last and removing from first");
        // for (int i = 0; i < 73; i++) {
        //     queue.addLast(i);
        // }
        // while (queue.hasNext()) {
        //     System.out.println(queue.removeFirst());
        // }
        //
        // System.out.println();
        // // alternating adding to first and last and removing last
        // queue = new Deque();
        // int t = 0;
        // System.out.println("Alternating adding to first and last and removing last");
        // for (int i = 0; i < 73; i++) {
        //     if (t == 0) {
        //         queue.addLast(i);
        //         t++;
        //     }
        //     else {
        //         queue.addFirst(i);
        //         t--;
        //     }
        // }
        // while (queue.hasNext()) {
        //     System.out.println(queue.removeFirst());
        // }
        //
        // System.out.println();
        // // adding to first and alternating removing last and removing first
        // queue = new Deque();
        // System.out.println("Adding all to first and alternating removing First and last ");
        // for (int i = 0; i < 73; i++) {
        //
        //     queue.addFirst(i);
        //
        // }
        // while (queue.hasNext()) {
        //     if (t == 0) {
        //         System.out.println(queue.removeFirst());
        //         t++;
        //     }
        //     else {
        //         System.out.println(queue.removeLast());
        //         t--;
        //     }
        // }
        //
        // System.out.println();
        // queue = new Deque();
        // System.out.println(
        //         "Adding 5 to first and removing 5 from First then adding 5 to last and removeing 5 from last ");
        // for (int i = 0; i < 5; i++) {
        //
        //     queue.addFirst(i);
        //
        // }
        // while (queue.hasNext()) {
        //
        //     System.out.println(queue.removeFirst());
        //     t++;
        //
        // }
        // for (int i = 5; i < 10; i++) {
        //
        //     queue.addLast(i);
        //
        // }
        // while (queue.hasNext()) {
        //
        //     System.out.println(queue.removeLast());
        //
        // }
        //
        // System.out.println();
        // queue = new Deque();
        // for (int i = 0; i < 5; i++) {
        //
        //     queue.addFirst(i);
        //
        // }
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);
        deque.addFirst(7);
        for (int i : deque) {
            System.out.print(i);
        }
        System.out.println();
        for (int i : deque) {
            System.out.print(i);
        }

        // System.out.println(queue.removeFirst());
        // System.out.println(queue.removeLast());
        // System.out.println(queue.removeFirst());
        // System.out.println(queue.removeLast());
    }
}
