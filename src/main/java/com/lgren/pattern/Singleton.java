package com.lgren.pattern;

public class Singleton {
    private Singleton() {}
    private static class HolderClass {
        private final static Singleton instance = new Singleton();
    }

}
