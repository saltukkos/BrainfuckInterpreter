package ru.nsu.saltuk.virtualmachine;

/**
 * Throws, when Instruction Pointer is set on incorrect
 * position during execution program
 */
public class InstructionPointerException extends VMException {
    InstructionPointerException(String error){
        super(error);
    }
}
