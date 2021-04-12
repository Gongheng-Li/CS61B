package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Li Gongheng
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return buckets[hash(key)].get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        Map61B<K, V> mapToAdd = buckets[hash(key)];
        if (!mapToAdd.containsKey(key)) {
            size += 1;
        }
        mapToAdd.put(key, value);
        if (loadFactor() > MAX_LF) {
            resize(2 * buckets.length);
        }
    }

    private void resize(int capacity) {
        ArrayMap<K, V>[] tempMap = buckets;
        buckets = new ArrayMap[capacity];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayMap<>();
        }
        for (ArrayMap<K, V> bucket : tempMap) {
            for (K key : bucket) {
                buckets[hash(key)].put(key, bucket.get(key));
            }
        }
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
        for (ArrayMap<K, V> bucket : buckets) {
            for (K key : bucket) {
                keySet.add(key);
            }
        }
        return keySet;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        Map61B<K, V> targetMap = buckets[hash(key)];
        if (targetMap.containsKey(key)) {
            size -= 1;
            return targetMap.remove(key);
        } else {
            return null;
        }
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        Map61B<K, V> targetMap = buckets[hash(key)];
        if (targetMap.containsKey(key)) {
            size -= 1;
            return targetMap.remove(key, value);
        } else {
            return null;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public static void main(String[] args) {
        Map61B<String, Integer> testMap = new MyHashMap<>();
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
        boolean checkRemove = testMap.containsKey("Guess what");
        int three = testMap.remove("three", 3);
        testMap.remove("three");
        testMap.remove("two", 5);
        int two = testMap.remove("two");
        int negativeTwo = testMap.remove("I think that's enough");
    }
}
