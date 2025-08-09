package adomlogistics.datastructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private List<Entry<K, V>>[] table;
    private int size;

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @SuppressWarnings("unchecked")
    public HashTable() {
        table = (List<Entry<K, V>>[]) new List[DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    public void put(K key, V value) {
        int index = hash(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        table[index].add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public List<V> values() {
        List<V> allValues = new ArrayList<>();
        for (List<Entry<K, V>> bucket : table) {
            for (Entry<K, V> entry : bucket) {
                allValues.add(entry.value);
            }
        }
        return allValues;
    }
}