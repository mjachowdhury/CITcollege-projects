package com.javacodegeeks.advanced.design;

public interface InterfaceWithDefaultMethods {
    void performAction();
    
    default void performDefaulAction() {
        // Implementation here
    }
    
    static void createAction() {
        // Implementation here
    }
}
