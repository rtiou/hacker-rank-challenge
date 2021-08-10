package com.ronaldotiou;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * @author Ronaldo Tiou
 * @since 10/08/2021
 */
public class LruCacheTest {

    @Test
    void lruCacheNegativeSize() {
        assertThrows(IllegalArgumentException.class, () -> new LruCache(-1));
    }

    @Test
    void lruCacheOverflowSize() {
        assertThrows(IllegalArgumentException.class, () -> new LruCache(2000));
    }

    @Test
    void emptyCacheGetElement() {
        var lruCache = new LruCache(1);
        assertEquals(-1, lruCache.get(1));
    }

    @Test
    void populatedCacheGetElement() {
        var lruCache = new LruCache(1);
        lruCache.put(1, 100);
        assertEquals(100, lruCache.get(1));
    }

    @Test
    void populateCacheWith2ElementAndFindElement() {
        var lruCache = new LruCache(2);
        lruCache.put(1, 100);
        lruCache.put(2, 200);
        lruCache.put(3, 300);

        assertEquals(-1, lruCache.get(1));
        assertEquals(200, lruCache.get(2));
        assertEquals(300, lruCache.get(3));
    }

    @Test
    void populatedCacheAddDuplicatedElements() {
        var lruCache = new LruCache(2);
        lruCache.put(1, 100);
        lruCache.put(2, 200);
        lruCache.put(3, 300);
        lruCache.put(2, 250);

        assertEquals(-1, lruCache.get(1));
        assertEquals(250, lruCache.get(2));
        assertEquals(300, lruCache.get(3));
    }
}
