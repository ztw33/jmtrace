package com.tiva.ISER;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ClassVisitor;

public class TraceAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Enter Trace Agent...");
        inst.addTransformer((loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> {
            if (className.startsWith("java") || className.startsWith("sun")) {
                return classfileBuffer;
            }
            System.out.println("loaded class: " + className);
            ClassReader cr = new ClassReader(classfileBuffer);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            ClassVisitor cv = new TraceClassAdaptor(cw);  // Adaptor: 适配器模式，TraceClassAdaptor适配ClassWriter
            cr.accept(cv, 0);
            return cw.toByteArray();
        });
    }
}
