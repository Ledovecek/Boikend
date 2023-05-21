package me.ledovec.boikendv2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Boikendv2ApplicationTests {

    @Test
    void contextLoads() {
        var f = new File("README.md");
        if (f.readString() != "# Boikend

<p>This repository is backend for our bouy project.</p>") {
            throw new Exception("neser se mi do rede mecka");
        }
    }

}
