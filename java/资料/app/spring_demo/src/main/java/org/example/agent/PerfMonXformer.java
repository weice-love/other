package org.example.agent;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/3/10 20:54
 */
public class PerfMonXformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] transformer = null;
        System.out.println("Transforming " + className);
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = null;
        try {
            ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
            if (!ctClass.isInterface()) {
                CtBehavior[] declaredBehaviors = ctClass.getDeclaredBehaviors();
                CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
                for (int i = 0; i < declaredMethods.length; i++) {
                    CtMethod declaredMethod = declaredMethods[i];
                    if (!declaredMethod.isEmpty()) {
                        System.out.println("进行方法增强！！！" + declaredMethod.getLongName() + ", empty: " + declaredMethod.isEmpty());
                        // 修改方法字节码
                        declaredMethod.insertBefore("System.out.println(\"=========================================\");");
                        declaredMethod.insertAfter("System.out.println(\"该方法总共用时: \" + (System.nanoTime()) / 100000);");
                    }
                    transformer = ctClass.toBytecode();
                }
//                for (int i = 0; i < declaredBehaviors.length; i++) {
//                    CtBehavior declaredBehavior = declaredBehaviors[i];
//                    if (!declaredBehavior.isEmpty()) {
//                        System.out.println("进行方法增强！！！" + declaredBehavior.getLongName() + ", empty: " + declaredBehavior.isEmpty());
//                        // 修改方法字节码
//                        declaredBehavior.insertBefore("long startTime = System.nanoTime();");
//                        declaredBehavior.insertAfter("System.out.println(\"该方法总共用时: \" + (System.nanoTime() - startTime) / 100000);");
//                    }
//                    transformer = ctClass.toBytecode();
//                }
            }
        } catch (IOException | CannotCompileException e) {
            System.out.println("解析异常: " + e.getMessage());
        } finally {
            if (ctClass != null) {
                ctClass.detach();
            }
        }
        return transformer;
    }
}
