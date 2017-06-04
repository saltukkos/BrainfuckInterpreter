package ru.nsu.saltuk.virtualmachine;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Program context, which store current
 * state of executing program
 */
public interface Context {
    /**
     * Moves the Data Pointer in cells
     * @param shift count of move length in cells
     * (can be negative)
     * @throws DataPointerException when pointer is
     * set on forbidden position
     */
    void moveDataPointer(int shift) throws DataPointerException;

    /**
     * Set data at the Data Pointer
     * @param data value, which will be set
     * in current cell
     */
    void writeCurrentCell(byte data);

    /**
     * Read data from the cell at the Data Pointer
     * @return byte from current cell
     */
    byte readCurrentCell();

    /**
     * Read instruction at the Instruction Pointer
     * @return current brainfuck instruction
     */
    byte readInstruction();

    /**
     * Moves the Instruction dataPointer in program
     * @param shift count of move length in cells
     * @throws InstructionPointerException when pointer
     * is set on forbidden position
     */
    void moveInstructionPointer(int shift) throws InstructionPointerException;

    /**
     * @return length of current program in bytes
     */
    int getProgramLength();

    /**
     * @return current instruction pointer position
     */
    int getInstructionPointerPosition();

    /**
     * Load new program, sets instruction pointer
     * to start position, clears all data
     * @param program byte array with brainfuck commands
     */
    void loadNewProgram(byte[] program);

    /**
     * Get output stream
     * @return stream for output for operator "."
     */
    OutputStream getOutputStream();

    /**
     * Get input stream
     * @return stream for output for operator ","
     */
    InputStream getInputStream();
}
