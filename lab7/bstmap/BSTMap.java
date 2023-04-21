package bstmap;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private class Node {
        private K key;
        private V val;
        private Node left, right;

        private Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    public BSTMap() {

    }
    private Node root;
    private int size = 0;

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(root, key);
    }

    private boolean containsKey(Node node, K key) {
        if (node == null) {
            return false;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            return containsKey(node.right, key);
        } else if (cmp < 0) {
            return containsKey(node.left, key);
        }
        return true;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);//当前查找索引为key的元素   当前在node节点上索引为 node.key 如果key大于node.key则在其右子树 反之在左子树 如果相等那就直接是这个节点
        if (cmp == 0) {
            return node.val;
        } else if (cmp > 0) {
            return get(node.right, key);
        } else if (cmp < 0) {
            return get(node.left, key);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
        size += 1;
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.val = value;
        }
        return node;
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> keys = new HashSet<>();
        keyAggregation(root, keys);
        return keys;
    }

    void keyAggregation(Node n, Set<K> set) {
        if (n != null) {//中序遍历将二叉树的值加入set中
            keyAggregation(n.left, set);
            set.add(n.key);
            keyAggregation(n.right, set);
        }
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

}
