package com.tiva.ISER;

public class TraceLog {
    static public void printRWStatic(String owner, String name, boolean isWrite) {
        if (owner.startsWith("sun/") || owner.startsWith("java/") || owner.startsWith("jdk/") || owner.startsWith("javax/") ||
                owner.startsWith("oracle/") || owner.startsWith("javafx/") || owner.startsWith("com/oracle/") ||
                owner.startsWith("com/sun/") || owner.startsWith("netscape/") || owner.startsWith("com/tiva/ISER/TraceLog")) {
            return;
        }
        String className = owner.replace("/", ".");
        Object obj = null;
        try {
            obj = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String desc = className + "." + name;
        System.out.printf("%s %d %016x %s\n", isWrite ? "W" : "R", Thread.currentThread().getId(), (long)System.identityHashCode(obj) << 32, desc);
    }

    static public void printRWField(Object obj, String name, boolean isWrite) {
        String owner = obj.getClass().getCanonicalName();
        if (owner.startsWith("sun/") || owner.startsWith("java/") || owner.startsWith("jdk/") || owner.startsWith("javax/") ||
                owner.startsWith("oracle/") || owner.startsWith("javafx/") || owner.startsWith("com/oracle/") ||
                owner.startsWith("com/sun/") || owner.startsWith("netscape/") || owner.startsWith("com/tiva/ISER/TraceLog")) {
            return;
        }
        String desc = owner + "." + name;
        System.out.printf("%s %d %016x %s\n", isWrite ? "W" : "R", Thread.currentThread().getId(), (long)System.identityHashCode(obj) << 32, desc);
    }

    static public void printRWArray(Object arrayref, int index, boolean isWrite) {
        String desc = arrayref.getClass().getCanonicalName().replace("[]", "") + "[" + index + "]";
        System.out.printf("%s %d %016x %s\n", isWrite ? "W" : "R", Thread.currentThread().getId(), ((long)System.identityHashCode(arrayref) << 32) + index, desc);
    }
}
