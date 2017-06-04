package ru.nsu.saltuk.factory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.util.Properties;

public class FactoryTest {
    private static Factory factory = null;

    @BeforeClass
    public static void set() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Properties prop = new Properties();

        prop.setProperty("regular", "ru.nsu.saltuk.factory.TestClass");
        prop.setProperty("abstract", "ru.nsu.saltuk.factory.AbstractClass");
        prop.setProperty("illegal", "ru.nsu.saltuk.factory.PrivateConstructorClass");
        prop.setProperty("undefined", "abrakadabra");

        prop.store(out, "factory test");

        factory = new Factory(new ByteArrayInputStream(out.toByteArray()));
    }

    @Test
    public void normalClass(){
        Object temp = factory.create("regular");
        Assert.assertEquals(TestClass.class, temp.getClass());
    }

    @Test
    public void classNotFound(){
        Object temp = factory.create("undefined");
        Assert.assertEquals(null, temp);
    }

    @Test
    public void illegalAccess(){
        Object temp = factory.create("illegal");
        Assert.assertEquals(null, temp);
    }

    @Test
    public void instantiateFault(){
        Object temp = factory.create("abstract");
        Assert.assertEquals(null, temp);
    }

    @Test
    public void propNotFound(){
        Object temp = factory.create("blah");
        Assert.assertEquals(null, temp);
    }

}
