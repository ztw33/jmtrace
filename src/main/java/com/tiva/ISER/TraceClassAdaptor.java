package com.tiva.ISER;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class TraceClassAdaptor extends ClassVisitor {
    public TraceClassAdaptor(final ClassVisitor cv) {
        super(Opcodes.ASM8, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        return this.cv != null ? new TraceMethodAdaptor(this.cv.visitMethod(access, name, descriptor, signature, exceptions)) : null;
    }
}
