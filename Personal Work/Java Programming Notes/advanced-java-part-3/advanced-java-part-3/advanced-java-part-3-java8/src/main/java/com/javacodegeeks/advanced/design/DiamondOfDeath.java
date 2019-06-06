package com.javacodegeeks.advanced.design;

public class DiamondOfDeath {
    interface A {
        default void performAction() {            
        }
    }
    
    interface B extends A {
        @Override
        default void performAction() {
        }
    }
    
    interface C extends A {
        @Override
        default void performAction() {
        }
    }
    
    // E is not compilable unless it overrides perfromAction() as well
    interface E extends B, C {        
    }
}
