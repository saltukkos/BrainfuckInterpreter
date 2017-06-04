package ru.nsu.saltuk.virtualmachine;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * Implementation of Context interface
 */
public class ContextImpl implements Context{

    private static Logger log = Logger.getLogger(SimpleMachine.class.getName());

    private InputStream input;
    private OutputStream output;

    private byte[] program;
    private byte[] data;
    private int dataPointer;
    private int instructionPointer;

    ContextImpl(int size, InputStream input, OutputStream output){
        data = new byte[size];
        this.input = input;
        this.output = output;
    }

    @Override
    public byte readCurrentCell() {
        log.trace("Read data at " + dataPointer + " (" + (int)data[dataPointer] + ")");
        return data[dataPointer];
    }

    @Override
    public void writeCurrentCell(byte aData) {
        log.trace("Write data at " + dataPointer + " (" + aData + ")");
        data[dataPointer] = aData;
    }

    @Override
    public void moveDataPointer(int shift) throws DataPointerException {
        log.debug("Move data pointer from " + dataPointer + " on " + shift);
        dataPointer += shift;
        if (dataPointer >= data.length)
            throw new DataPointerException("try to run machine with bigger length");
        if (dataPointer < 0)
            throw new DataPointerException("wrong Data Pointer position: " + dataPointer);

    }

    @Override
    public byte readInstruction() {
        log.trace("Read instruction at " + instructionPointer + " (" + (char)program[instructionPointer] + ")");
        return program[instructionPointer];
    }

    @Override
    public void moveInstructionPointer(int shift) throws InstructionPointerException{
        log.debug("Move instruction pointer from " + instructionPointer + " on " + shift);
        instructionPointer += shift;
        if (instructionPointer >= program.length || instructionPointer < 0)
            throw new InstructionPointerException("wrong Instruction Pointer position: " + instructionPointer);
    }

    @Override
    public int getProgramLength() {
        return program.length;
    }

    @Override
    public int getInstructionPointerPosition() {
        return instructionPointer;
    }

    @Override
    public void loadNewProgram(byte[] aProgram) {
        program = aProgram;
        instructionPointer = 0;
        dataPointer = 0;
        Arrays.fill(data, (byte) 0);
    }

    @Override
    public OutputStream getOutputStream() {
        return output;
    }

    @Override
    public InputStream getInputStream() {
        return input;
    }

    public void drawState(){
        for (int i = 0; i < instructionPointer; ++i) {
            System.out.print(" ");
        }
        System.out.println('v');

        for (int i = 0; i < 300 && i < program.length; ++i) {
            System.out.print(Character.valueOf((char) program[i]).toString());
        }

        System.out.println("");
        for (int i = 0; i < dataPointer; ++i) {
            int temp = ((int) data[i] + 256) % 256;
            System.out.print(" ");
            do {
                System.out.print(" ");
                temp /= 10;
            }
            while (temp > 0);
        }

        System.out.println('v');
        for (int i = 0; i < 150 && i < data.length; ++i) {
            System.out.print(Integer.valueOf(((int) data[i] + 256) % 256).toString() + "|");
        }
        System.out.println("");
    }
}
