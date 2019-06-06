package com.javacodegeeks.advanced.design;

public class MultipleInterfaces implements Runnable, AutoCloseable {
    @Override
    public void run() {
        // Some implementation here
    }
    
    @Override
    public void close() throws Exception {
       // Some implementation here
    }
}
