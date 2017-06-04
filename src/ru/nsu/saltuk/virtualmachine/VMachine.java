package ru.nsu.saltuk.virtualmachine;

import java.io.IOException;

/**
 * Interface for Virtual Brainfuck Machine
 */
public interface VMachine {

    /**
     * Execute the Brainfuck program from file
     * @param filename name of file with program
     * @throws IOException when impossible to open program
     * or error with Out/InputStream
     * @throws VMException when error in program
     */

    void executeProgram(String filename) throws IOException, VMException;

    /**
     * Execute the given Brainfuck program
     * @param program Brainfuck program to executing
     * @throws VMException when error in program
     * @throws IOException when error with Out/InputStream
     */

    void executeProgram(byte[] program) throws VMException, IOException;
}
