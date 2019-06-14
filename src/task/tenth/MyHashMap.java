package task.tenth;

import java.util.Arrays;
import java.util.Objects;

public class MyHashMap<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int size;
    @SuppressWarnings({"rawtypes", "unchecked"})
    private Node<K, V>[] entries = new Node[DEFAULT_INITIAL_CAPACITY];


    public void put(K key, V value) {
        if (checkSize()) {
            grow();
        }
        int index = indexBacket(key);
        Node<K, V> newNode = new Node<>(key, value);
        if (entries[index] == null) {
            entries[index] = newNode;
        } else {
            Node<K, V> entry = entries[index];
            if ((entry.getKey() == null && key == null) || entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
            entry = entry.next;
            while ((entry) != null) {
                if (key.equals(entry.next.getKey())) {
                    entry.next.setValue(value);
                    return;
                }
                entry = entry.next;
            }
            entry = newNode;
        }
        size++;
    }

    public V get(K key) {
        int index = indexBacket(key);
        Node<K, V> node = entries[index];
        while (node != null) {
            if ((key == null && index == 0) | (key.equals(node.getKey()))) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void grow() {
        Node<K, V>[] temp = (Node<K, V>[]) new Node[2 * entries.length];
        for (int i = 0; i < entries.length; i++) {
            Node<K, V> node = entries[i];
            while (node != null) {
                int newIndex = indexBacket(node.key);
                if (temp[newIndex] == null) {
                    temp[newIndex] = node;
                    node = node.next;
                } else {
                    Node<K, V> nodeSecond = temp[newIndex];
                    while (nodeSecond != null) {
                        if (nodeSecond.next == null) {
                            nodeSecond.next = node;
                        }
                        nodeSecond = nodeSecond.next;
                    }
                }
            }
        }
        entries = temp;
    }


    private boolean checkSize() {
        return (size / entries.length) >= DEFAULT_LOAD_FACTOR;
    }


    private <K> int indexBacket(K key) {
        return Math.abs(hash(key) % entries.length);
    }

    private class Node<K, V> {
        protected K key;
        protected V value;
        protected Node<K, V> next;


        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private <K> int hash(K key) {
        return (key == null) ? 0 : key.hashCode() * 17 - 5;
    }
}
