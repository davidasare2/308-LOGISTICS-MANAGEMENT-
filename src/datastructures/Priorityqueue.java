package adomlogistics.datastructures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PriorityQueue<T> {
    private final List<T> heap;
    private final Comparator<T> comparator;

    public PriorityQueue(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    public void enqueue(T item) {
        heap.add(item);
        siftUp(heap.size() - 1);
    }

    public T dequeue() {
        if (isEmpty()) return null;
        
        T result = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        
        if (!isEmpty()) {
            heap.set(0, last);
            siftDown(0);
        }
        
        return result;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (comparator.compare(heap.get(index), heap.get(parent)) >= 0) break;
            swap(index, parent);
            index = parent;
        }
    }

    private void siftDown(int index) {
        int smallest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < heap.size() && comparator.compare(heap.get(left), heap.get(smallest)) < 0) {
            smallest = left;
        }

        if (right < heap.size() && comparator.compare(heap.get(right), heap.get(smallest)) < 0) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            siftDown(smallest);
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}