package com.javacodegeeks.advanced.design;

public class AnonymousClass {
    public static void main( String[] args ) {
        new Thread( () -> { /* Implementation here */ } ).start();
    }
}
