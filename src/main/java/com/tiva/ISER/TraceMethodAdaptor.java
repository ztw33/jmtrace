package com.tiva.ISER;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class TraceMethodAdaptor extends MethodVisitor{
    public TraceMethodAdaptor(final MethodVisitor mv) {
        super(Opcodes.ASM8, mv);
    }

    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        if (this.mv != null) {
            this.mv.visitFieldInsn(opcode, owner, name, descriptor);
        }

    }
}
