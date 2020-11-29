package com.tiva.ISER;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class TraceMethodAdaptor extends MethodVisitor {
    public TraceMethodAdaptor(final MethodVisitor mv) {
        super(Opcodes.ASM8, mv);
    }

    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        if (this.mv != null) {
            if (opcode == Opcodes.GETSTATIC) {
                this.mv.visitLdcInsn(owner);
                this.mv.visitLdcInsn(name);
                this.mv.visitInsn(Opcodes.ICONST_0);
                this.mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/tiva/ISER/TraceLog", "printRWStatic", "(Ljava/lang/String;Ljava/lang/String;Z)V", false);
            } else if (opcode == Opcodes.PUTSTATIC) {
                this.mv.visitLdcInsn(owner);
                this.mv.visitLdcInsn(name);
                this.mv.visitInsn(Opcodes.ICONST_1);
                this.mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/tiva/ISER/TraceLog", "printRWStatic", "(Ljava/lang/String;Ljava/lang/String;Z)V", false);
            } else if (opcode == Opcodes.GETFIELD) {
                this.mv.visitInsn(Opcodes.DUP);  // duplicate top operand
                this.mv.visitLdcInsn(name);
                this.mv.visitInsn(Opcodes.ICONST_0);
                this.mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/tiva/ISER/TraceLog", "printRWField", "(Ljava/lang/Object;Ljava/lang/String;Z)V", false);
            } else if (opcode == Opcodes.PUTFIELD) {  // NOTICE: category 2 computational type(double, long)占栈中两行，需要分操作数类型讨论
                if (Type.getType(descriptor).getSize() == 1) {  // category 1 computational type
                    this.mv.visitInsn(Opcodes.DUP2);
                    this.mv.visitInsn(Opcodes.POP);
                    this.mv.visitLdcInsn(name);
                    this.mv.visitInsn(Opcodes.ICONST_1);
                    this.mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/tiva/ISER/TraceLog", "printRWField", "(Ljava/lang/Object;Ljava/lang/String;Z)V", false);
                } else if (Type.getType(descriptor).getSize() == 2) {  // category 2 computational type
                    this.mv.visitInsn(Opcodes.DUP2_X1);
                    this.mv.visitInsn(Opcodes.POP2);
                    this.mv.visitInsn(Opcodes.DUP);
                    this.mv.visitLdcInsn(name);
                    this.mv.visitInsn(Opcodes.ICONST_1);
                    this.mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/tiva/ISER/TraceLog", "printRWField", "(Ljava/lang/Object;Ljava/lang/String;Z)V", false);
                    this.mv.visitInsn(Opcodes.DUP_X2);
                    this.mv.visitInsn(Opcodes.POP);
                }
            }
            this.mv.visitFieldInsn(opcode, owner, name, descriptor);
        }

    }

    public void visitInsn(int opcode) {
        if (this.mv != null) {
            if (opcode >= Opcodes.IALOAD && opcode <= Opcodes.SALOAD) {
                this.mv.visitInsn(Opcodes.DUP2);
                this.mv.visitInsn(Opcodes.ICONST_0);
                this.mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/tiva/ISER/TraceLog", "printRWArray", "(Ljava/lang/Object;IZ)V", false);
            } else if (opcode >= Opcodes.IASTORE && opcode <= Opcodes.SASTORE) {
                if (opcode == Opcodes.LASTORE || opcode == Opcodes.DASTORE) {  // category 2 computational type
                    this.mv.visitInsn(Opcodes.DUP2_X2);
                    this.mv.visitInsn(Opcodes.POP2);
                    this.mv.visitInsn(Opcodes.DUP2);
                    this.mv.visitInsn(Opcodes.ICONST_1);
                    this.mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/tiva/ISER/TraceLog", "printRWArray", "(Ljava/lang/Object;IZ)V", false);
                    this.mv.visitInsn(Opcodes.DUP2_X2);
                    this.mv.visitInsn(Opcodes.POP2);
                } else {  // category 1 computational type
                    this.mv.visitInsn(Opcodes.DUP_X2);
                    this.mv.visitInsn(Opcodes.POP);
                    this.mv.visitInsn(Opcodes.DUP2);
                    this.mv.visitInsn(Opcodes.ICONST_1);
                    this.mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/tiva/ISER/TraceLog", "printRWArray", "(Ljava/lang/Object;IZ)V", false);
                    this.mv.visitInsn(Opcodes.DUP2_X1);
                    this.mv.visitInsn(Opcodes.POP2);
                }
            }
            this.mv.visitInsn(opcode);
        }

    }

}
