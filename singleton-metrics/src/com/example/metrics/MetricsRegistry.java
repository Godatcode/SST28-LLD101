package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Global metrics registry — proper thread-safe Singleton.
 *
 * Uses the Bill Pugh (static inner holder) approach for lazy init.
 * Also guards against reflection and serialization attacks.
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<String, Long> counters = new HashMap<>();

    // flag to block reflection from creating a second instance
    private static boolean alreadyCreated = false;

    // private constructor — throws if someone tries reflection
    private MetricsRegistry() {
        if (alreadyCreated) {
            throw new RuntimeException("Cannot create another instance via reflection!");
        }
        alreadyCreated = true;
    }

    // static inner class holder — JVM loads this lazily and thread-safely
    private static class Holder {
        private static final MetricsRegistry INSTANCE = new MetricsRegistry();
    }

    public static MetricsRegistry getInstance() {
        return Holder.INSTANCE;
    }

    // makes sure deserialization gives back the same singleton
    @Serial
    private Object readResolve() {
        return getInstance();
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }
}
