package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Li Gongheng
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        if (key.equals(p.key)) {
            return p.value;
        } else if (key.compareTo(p.key) < 0) {
            return getHelper(key, p.left);
        } else {
            return getHelper(key, p.right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size += 1;
            return new Node(key, value);
        }
        if (key.compareTo(p.key) < 0) {
            p.left = putHelper(key, value, p.left);
        } else if (key.compareTo(p.key) > 0) {
            p.right = putHelper(key, value, p.right);
        } else if (key.equals(p.key)){
            p.value = value;
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        keySetHelper(root, keySet);
        return  keySet;
    }

    private void keySetHelper(Node p, Set<K> keySet) {
        if (p == null) {
            return;
        }
        keySet.add(p.key);
        keySetHelper(p.left, keySet);
        keySetHelper(p.right, keySet);
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        return removeHelper(root, null, LEFT, key, null, false);
    }

    private V removeHelper(Node p, Node parentNode, int childNum, K key, V value, boolean pairFlag) {
        if (p == null) {
            return null;
        }
        if (key.compareTo(p.key) < 0) {
            return removeHelper(p.left, p, LEFT, key, value, pairFlag);
        } else if (key.compareTo(p.key) > 0) {
            return removeHelper(p.right, p, RIGHT, key, value, pairFlag);
        } else if (key.equals(p.key)) {
            if (pairFlag && !value.equals(p.value)) {
                return null;
            }
            if (p.left == null && p.right == null) {
                size -= 1;
                if (parentNode == null) {
                    root = null;
                } else {
                    if (childNum == LEFT) {
                        parentNode.left = null;
                    } else if (childNum == RIGHT) {
                        parentNode.right = null;
                    }
                }
                return p.value;
            } else if (p.left != null && p.right == null) {
                size -= 1;
                if (parentNode == null) {
                    root = root.left;
                } else {
                    if (childNum == LEFT) {
                        parentNode.left = p.left;
                    } else if (childNum == RIGHT) {
                        parentNode.right = p.left;
                    }
                }
                return p.value;
            } else if (p.right != null && p.left == null) {
                size -= 1;
                if (parentNode == null) {
                    root = root.right;
                } else {
                    if (childNum == LEFT) {
                        parentNode.left = p.right;
                    } else if (childNum == RIGHT) {
                        parentNode.right = p.right;
                    }
                }
                return p.value;
            } else {
                Node replaceNode = findReplaceNode(p.left);
                remove(replaceNode.key);
                V valueToReturn = p.value;
                p.key = replaceNode.key;
                p.value = replaceNode.value;
                return valueToReturn;
            }
        }
        return null;
    }

    private Node findReplaceNode(Node p) {
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        return removeHelper(root, null, LEFT, key, value, true);
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }


    public static void main(String[] args) {
        Map61B<String, Integer> testMap = new BSTMap<>();
        testMap.put("two", 2);
        testMap.put("three", 3);
        testMap.put("Guess what", 0);
        testMap.put("one", 1);
        testMap.put("four", 4);
        testMap.put("yellow", -1);
        testMap.put("I think that's enough", -2);
        testMap.put("Capital Two, the last one", 2);
        Set<String> keySet = testMap.keySet();
        int zero = testMap.remove("Guess what");
        int three = testMap.remove("three", 3);
        testMap.remove("three");
        testMap.remove("two", 5);
        int two = testMap.remove("two");
        int negativeTwo = testMap.remove("I think that's enough");
    }
}
