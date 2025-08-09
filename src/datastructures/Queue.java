package adomlogistics.datastructures;

public class Queue<T> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        T item;
        Node next;

        Node(T item) {
            this.item = item;
        }
    }

    public void enqueue(T item) {
        Node newNode = new Node(item);
        if (last == null) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public T dequeue() {
        if (isEmpty()) return null;
        
        T item = first.item;
        first = first.next;
        if (first == null) last = null;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }
}