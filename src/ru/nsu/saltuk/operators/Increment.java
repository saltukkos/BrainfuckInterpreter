package ru.nsu.saltuk.operators;

import ru.nsu.saltuk.virtualmachine.Context;

/**
 * Implementation of Brainfuck operator '+'
 * increase the value of cell at Data Pointer
 */
public class Increment implements Operator {

    @Override
    public void execute(Context machine) {
        byte temp = machine.readCurrentCell();
        temp += 1;
        machine.writeCurrentCell(temp);
    }
}