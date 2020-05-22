package ru.vershinin.lesson9;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Scanner;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

public class FilePreparer {

    public static void prepare(Scanner scan) throws Exception {


            String s=scan.nextLine();


        // создание класса
        File sourceFile = File.createTempFile("SomeClass", ".java");
        sourceFile.deleteOnExit();

        // generate the source code, using the source filename as the class name
        String classname = sourceFile.getName().split("\\.")[0];
        String sourceCode = "public class " + classname + "{ public void doWork() {" +s+"}}";

        // write the source code into the source file
        FileWriter writer = new FileWriter(sourceFile);
        writer.write(sourceCode);
        writer.close();

        // compile the source file
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        File parentDirectory = sourceFile.getParentFile();
        System.out.println(parentDirectory);
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(parentDirectory));
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));
        compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
        fileManager.close();

        // load the compiled class
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { parentDirectory.toURI().toURL() });
        Class<?> helloClass = classLoader.loadClass(classname);

        // call a method on the loaded class
        Method helloMethod = helloClass.getDeclaredMethod("doWork");
        helloMethod.invoke(helloClass.newInstance());
    }
}
