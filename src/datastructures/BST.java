package adomlogistics.datastructures;

public class BST<K extends Comparable<K>, V> {
    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) return new Node(key, value);
        
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value;
        
        return node;
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node.value;
    }

    public void inOrderTraversal(Visitor<V> visitor) {
        inOrderTraversal(root, visitor);
    }

    private void inOrderTraversal(Node node, Visitor<V> visitor) {
        if (node != null) {
            inOrderTraversal(node.left, visitor);
            visitor.visit(node.value);
            inOrderTraversal(node.right, visitor);
        }
    }

    public interface Visitor<V> {
        void visit(V value);
    }
}