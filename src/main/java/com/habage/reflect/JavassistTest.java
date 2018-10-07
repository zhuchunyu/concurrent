package com.habage.reflect;


import com.habage.bean.Student;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class JavassistTest {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("com.habage.bean.Student");
        CtMethod m = cc.getDeclaredMethod("getName");
        m.insertBefore("{ System.out.println(\"Hello.getName():\"); }");
        Class c = cc.toClass();
        Student h = (Student) c.newInstance();
        System.out.println(h.getName());
    }
}
