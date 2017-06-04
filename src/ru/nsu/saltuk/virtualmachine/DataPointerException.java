package ru.nsu.saltuk.virtualmachine;

/**
 * Throws, when Data Pointer is set on incorrect
 * position during execution program
 */
public class DataPointerException extends VMException {
    DataPointerException(String message){
        super(message);
    }
}
