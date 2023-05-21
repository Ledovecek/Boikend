package me.ledovec.boikendv2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

@SpringBootTest
class Boikendv2ApplicationTests {

    @Test
    void contextLoads() throws Exception {
        var f = new File("README.md");
        FileReader fileReader;
        fileReader = new FileReader(f);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s = bufferedReader.readLine();
        if (s != "# Boikend\n" +
                "\n" +
                "<p>This repository is backend for our bouy project.</p>") {
            throw new Exception("Readme has been confused as unseemly and inconsistent with our internal conditions, which are written in the rules of the competition.");
        }
    }

}
