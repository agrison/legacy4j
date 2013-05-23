package me.grison.legacy4j;

import me.grison.legacy4j.engine.LegacyFile;
import me.grison.legacy4j.engine.LegacyFiles;
import me.grison.legacy4j.model.Hello;
import org.junit.Test;

import java.io.File;

public class HelloTest {
    File helloFile = new File(getClass().getResource("/hello.txt").getFile());

    @Test
    public void testHello() {
        LegacyFile<Hello> hellos = LegacyFiles.openFileReader(helloFile, Hello.class);
        for (Hello hello: hellos) {
            System.out.println("> " + hello);
        }
        hellos.close();
    }
}
