package adomlogistics.datastructures;

public class LinkedList<T> {
    private Node head;
    private int size;

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }

    public void add(T item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public int size() {
        return size;
    }

    public void forEach(Consumer<T> action) {
        Node current = head;
        while (current != null) {
            action.accept(current.data);
            current = current.next;
        }
    }

    public interface Consumer<T> {
        void accept(T item);
    }
}