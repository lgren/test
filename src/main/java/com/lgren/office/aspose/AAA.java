package com.lgren.office.aspose;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import lombok.SneakyThrows;

public class AAA {

  @SneakyThrows
  public static void main(String[] args) {
    ClassPool.getDefault().insertClassPath("/Users/lgren/Project/Java/0My/test/lib/aspose-words-21.10-jdk17.jar");
    CtClass zzZJJClass = ClassPool.getDefault().getCtClass("com.aspose.words.zzwH");
    CtMethod zznj = zzZJJClass.getDeclaredMethod("zznj");
    // CtMethod zzZ4t = zzZJJClass.getDeclaredMethod("zzZ4m");
    zznj.setBody("{return true;}");
    // zzZ4t.setBody("{return 1;}");
    zzZJJClass.writeFile("/Users/lgren/Project/Java/0My/test/lib/out");
  }
}