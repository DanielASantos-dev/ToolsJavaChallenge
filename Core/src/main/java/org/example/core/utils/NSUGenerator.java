package org.example.core.utils;

import java.util.concurrent.atomic.AtomicLong;

public class NSUGenerator {
    private static final AtomicLong sequence = new AtomicLong(System.currentTimeMillis());

    public static String generateNSU() {
        return Long.toString(sequence.incrementAndGet());
    }
}
