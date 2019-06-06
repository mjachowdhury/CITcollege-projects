package com.javacodegeeks.advanced.design;

public interface DefaultMethods extends Runnable, AutoCloseable {
    @Override
    default void run() {
        // Some implementation here
    }
    
    @Override
    default void close() throws Exception {
       // Some implementation here
    }
}
