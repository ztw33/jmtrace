package com.tiva.ISER;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.Instrumentation;

public class TraceAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer((loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> {
            if (className.startsWith("sun/") || className.startsWith("java/") || className.startsWith("jdk/") ||
                    className.startsWith("javax/") || className.startsWith("oracle/") || className.startsWith("javafx/") ||
                    className.startsWith("com/oracle/") || className.startsWith("com/sun/") || className.startsWith("netscape/") ||
                    className.startsWith("com/tiva/ISER/TraceLog")) {  // 会在目标jar中插入调用TraceLog中函数的代码，需要排除，否则会递归分析造成栈溢出
                return classfileBuffer;
            }
            ClassReader cr = new ClassReader(classfileBuffer);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            ClassVisitor cv = new TraceClassAdaptor(cw);  // Adaptor: 适配器模式，TraceClassAdaptor适配ClassWriter
            cr.accept(cv, 0);
            return cw.toByteArray();
        });
    }
}
