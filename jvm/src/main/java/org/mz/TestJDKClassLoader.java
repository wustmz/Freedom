package org.mz;

import sun.misc.Launcher;

import java.net.URL;

/**
 * 类加载器
 */
public class TestJDKClassLoader {
    public static void main(String[] args) {
        //对于App ClassLoader与Extension ClassLoader都是sun.misc.Launcher里的一个内部类，
        //但是BootStrapClassLoader是一个由C++编写的类，涉及到虚拟机本地实现细节，
        //开发者无法直接获取到启动类加载器的引用，所以不能直接通过引用进行操作。
        //类加载器是C++生成的，所以打印出来是null，涉及到跨语言调用
        //sun.misc.Launcher
        System.out.println(String.class.getClassLoader());
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader().getClass().getName());
        System.out.println(TestJDKClassLoader.class.getClassLoader().getClass().getName());

        System.out.println();
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassloader = appClassLoader.getParent();
        ClassLoader bootstrapLoader = extClassloader.getParent();
        //appClassLoader.loadClass("")
        System.out.println("the bootstrapLoader : " + bootstrapLoader);
        System.out.println("the extClassloader : " + extClassloader);
        System.out.println("the appClassLoader : " + appClassLoader);

        System.out.println();
        System.out.println("bootstrapLoader加载以下文件：");
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url);
        }

        System.out.println();
        System.out.println("extClassloader加载以下文件：");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println();
        System.out.println("appClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.class.path"));
        Launcher launcher = Launcher.getLauncher();

    }
}
