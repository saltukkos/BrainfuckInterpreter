package ru.nsu.saltuk.virtualmachine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.util.Properties;

public class SimpleMachineTest {

    private static SimpleMachine machine;
    private static InputStream in;
    private static OutputStream out;
    private static InputStream config;

    @BeforeClass
    public static void init() throws IOException {
        config = SimpleMachineTest.class.getResourceAsStream("/config.properties");
        Assert.assertTrue(config.markSupported());

        config.mark(config.available() + 1);
    }

    @Before
    public void reset() throws IOException{
        config.reset();
    }

    @Test(expected = DataPointerException.class)
    public void infiniteLoop() throws IOException, VMException {
        in = new ByteArrayInputStream(new byte[0]);
        out = new ByteArrayOutputStream(0);

        machine = new SimpleMachine(1000, config, in, out);

        machine.executeProgram("+[>+]".getBytes());
    }

    @Test(expected = InstructionPointerException.class)
    public void unmatchedOpenBracket() throws IOException, VMException {
        in = new ByteArrayInputStream(new byte[0]);
        out = new ByteArrayOutputStream(0);
        machine = new SimpleMachine(1000, config, in, out);

        machine.executeProgram("[".getBytes());
    }

    @Test(expected = InstructionPointerException.class)
    public void unmatchedCloseBracket() throws IOException, VMException {
        in = new ByteArrayInputStream(new byte[0]);
        out = new ByteArrayOutputStream(0);
        machine = new SimpleMachine(1000, config, in, out);

        machine.executeProgram("+]".getBytes());
    }

    @Test(expected = DataPointerException.class)
    public void negativeDataPosition() throws IOException, VMException {
        in = new ByteArrayInputStream(new byte[0]);
        out = new ByteArrayOutputStream(0);
        machine = new SimpleMachine(1000, config, in, out);

        machine.executeProgram("<".getBytes());
    }

    @Test(expected = DataPointerException.class)
    public void infiniteDataPosition() throws IOException, VMException {
        in = new ByteArrayInputStream(new byte[0]);
        out = new ByteArrayOutputStream(0);
        machine = new SimpleMachine(1000, config, in, out);

        machine.executeProgram("+[>+]".getBytes());
    }

    @Test(expected = VMException.class)
    public void wrongSize() throws IOException, VMException {
        in = new ByteArrayInputStream(new byte[0]);
        out = new ByteArrayOutputStream(0);
        machine = new SimpleMachine(1000, config, in, out);

        machine = new SimpleMachine(-1, config, in, out);
    }

    @Test
    public void inputOutput() throws IOException, VMException {
        in = new ByteArrayInputStream(new byte[]{0x1, 0x2, 0x3, 0x4, 0x5, 0x0});
        out = new ByteArrayOutputStream(10);

        machine = new SimpleMachine(1000, config, in, out);
        machine.debug(true);
        machine.executeProgram(">,[>,]<[<]>[.>]".getBytes());
        Assert.assertEquals(new String(new byte[]{0x1, 0x2, 0x3, 0x4, 0x5}), out.toString());
    }

    @Test
    public void inputOutputException() throws IOException, VMException {
        out = new FileOutputStream("out.txt");
        out.close();
        in = new FileInputStream("out.txt");
        in.close();

        machine = new SimpleMachine(1000, config, in, out);
        machine.executeProgram(",.".getBytes());
    }

    @Test
    public void emptyProgram() throws IOException, VMException {
        machine = new SimpleMachine(1000, config, in, out);
        machine.executeProgram("".getBytes());
    }

    @Test(expected = VMException.class)
    public void incorrectConfig() throws IOException, VMException {
        ByteArrayOutputStream config = new ByteArrayOutputStream();

        Properties prop = new Properties();
        prop.setProperty(".", "ru.nsu.saltuk.factory.TestClass");
        prop.store(config, "description");

        machine = new SimpleMachine(1000, new ByteArrayInputStream(config.toByteArray()), in, out);
        machine.executeProgram(".".getBytes());
    }

    @Test
    public void programFrom() throws IOException, VMException {
        OutputStream temp = new FileOutputStream("test");
        temp.write("+.+?+.-.".getBytes());
        temp.close();

        out = new ByteArrayOutputStream(10);
        machine = new SimpleMachine(1000, config, in, out);
        machine.executeProgram("test");
        Assert.assertEquals(new String(new byte[]{0x1, 0x3, 0x2}), out.toString());
    }

    @Test
    public void sortTest() throws IOException, VMException {
        in = new ByteArrayInputStream(new byte[]{0x30, 0x36, 0x31, 0x37, 0x39, 0x32, 0x38, 0x33, 0x35, 0x34, 0x0});
        out = new ByteArrayOutputStream(10);

        machine = new SimpleMachine(1000, config, in, out);
        machine.executeProgram(">,[>,]+[<[-<]>[>]>[<-[>]<.>>]<<+]".getBytes());
        Assert.assertEquals(new String(new byte[]{0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39}), out.toString());
    }

    @Test
    public void factTest() throws IOException, VMException {
        out = new ByteArrayOutputStream(1000);

        machine = new SimpleMachine(30000, config, null, out);
        machine.executeProgram("++++++>>++++++++++>>>+>+<<<<<<[>>>>>>>>>+[-[<<<<<[+<<<<<]>>[[-]>[<<+>+>-]<[>+<-]<[>+<-[>+<-[>+<-[>+<-[>+<-[>+<-[>+<-[>+<-[>+<-[>[-]>>>>+>+<<<<<<-[>+<-]]]]]]]]]]]>[<+>-]+>>>>>]<<<<<[<<<<<]>>>>>>>[>>>>>]++[-<<<<<]>>>>>>-]+>>>>>]<[>++<-]<<<<[<[>+<-]<<<<]>>[->[-]++++++[<++++++++>-]>>>>]<<<<<[<[>+>+<<-]>.<<<<<]>.<<-]".getBytes());
        Assert.assertEquals("1\n1\n2\n6\n24\n120\n", out.toString());
    }

}
