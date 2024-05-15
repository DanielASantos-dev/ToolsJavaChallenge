package org.example.core.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class NSUGenerateTest {

    @Test
    void generateNSU(){
        var nsu = NSUGenerator.generateNSU();
        assertNotNull(nsu);
    }
}
