package com.javacodegeeks.advanced.design;

public class FunctionalInterface {
    public void runMe( final Runnable r ) {
        r.run();
    }
    
    public static void main(String[] args) {
        new FunctionalInterface().runMe( () -> System.out.println( "Run!" ) );
    }
}
