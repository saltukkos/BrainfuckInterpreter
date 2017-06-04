package ru.nsu.saltuk.operators;

import ru.nsu.saltuk.virtualmachine.Context;
import ru.nsu.saltuk.virtualmachine.DataPointerException;

/**
 * Implementation of Brainfuck operator '&lt;'
 * increments the data pointer
 */
public class PrevByte implements Operator {

    @Override
    public void execute(Context machine) throws DataPointerException {
        machine.moveDataPointer(-1);
    }
}