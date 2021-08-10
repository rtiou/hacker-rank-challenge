package com.ronaldotiou;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Ronaldo Tiou
 * @since 10/08/2021
 */
public class LruCache {

    private static final int MIN_CACHE_SIZE = 1;
    private static final int MAX_CACHE_SIZE = 1000;

    private final int cacheSize;

    //Deque used to store all the keys
    private final Deque<Integer> deque;

    // Map used to store the key and value
    private final HashMap<Integer, Integer> map;

    /**
     * Constructor with cache size as a parameter. The cacheSize should be between {@link #MIN_CACHE_SIZE} and {@link
     * #MAX_CACHE_SIZE}.
     *
     * @param cacheSize The cache size.
     */
    public LruCache(int cacheSize) {
        if (cacheSize >= MIN_CACHE_SIZE && cacheSize <= MAX_CACHE_SIZE) {
            this.cacheSize = cacheSize;
            this.deque = new LinkedList<>();
            this.map = new HashMap<>(cacheSize);
        } else {
            throw new IllegalArgumentException(
                    "Cache size should be between [%d] and [%d].".formatted(MIN_CACHE_SIZE, MAX_CACHE_SIZE));
        }
    }

    /**
     * Set or insert the value if the key is not already present. When the cache reached its capacity, it should
     * invalidate the least recently used item before inserting a new item.
     *
     * @param key   The key
     * @param value The value
     */
    public void put(int key, int value) {
        if (deque.contains(key)) {
            deque.remove(key);
            deque.add(key);
            map.replace(key, value);
        } else {
            if (deque.size() == cacheSize) {
                var removedKey = deque.poll();
                map.remove(removedKey);
            }

            deque.add(key);
            map.put(key, value);
        }
    }

    /**
     * Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
     *
     * @param key The key used into the search.
     * @return
     */
    public int get(int key) {
        if (deque.contains(key)) {
            deque.poll();
            deque.add(key);
        }
        return map.getOrDefault(key, -1);
    }
}
