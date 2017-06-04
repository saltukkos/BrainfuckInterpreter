package ru.nsu.saltuk.virtualmachine;

/**
 * The class VMException and subclasses indicates
 * that unexpected error occurred during execution
 * the Brainfuck program
 */

public class VMException extends Exception{
    VMException(String message){
        super(message);
    }
}
