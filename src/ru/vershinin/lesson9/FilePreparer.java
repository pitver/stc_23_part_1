package ru.vershinin.lesson9;

import javax.tools.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * FilePreparer
 */
public class FilePreparer {

    /**
     * формирует SomeClass.java и компилирует его в SomeClass.class
     * @param text- тело метода doWork
     *
     */
    public static void prepare(StringBuffer text) throws Exception  {


        // создание класса
        File sourceFile = null;
        try {
            sourceFile = File.createTempFile("SomeClass", ".java",
                    new File("C:\\STC-23\\" +
                            "stc_23_part_1\\out\\production\\stc_23_part_1\\" +
                            "ru\\vershinin\\lesson9"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sourceFile.deleteOnExit();

        // подготовка источника для формирования класса doWork()
        String classname = sourceFile.getName().split("\\.")[0];
        String sourceCode = "public class " + classname + "{ public void doWork() {" +text+"}}";

        // запись в файл
        FileWriter writer = new FileWriter(sourceFile);
        writer.write(sourceCode);
        writer.close();

        // компиляция класса в рантайме c помощью javax.tools.*
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        File parentDirectory = sourceFile.getParentFile();
        System.out.println(parentDirectory);
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(parentDirectory));
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));
        compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
        fileManager.close();

        // подгрузка класслоудером
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { parentDirectory.toURI().toURL() });
        Class<?> helloClass = classLoader.loadClass(classname);

        // вызов метода рефлексией
        Method doWorkMethod = helloClass.getDeclaredMethod("doWork");
        doWorkMethod.invoke(helloClass.newInstance());
    }
}
