package ru.nsu.saltuk.operators;

import ru.nsu.saltuk.virtualmachine.Context;
import ru.nsu.saltuk.virtualmachine.InstructionPointerException;

/**
 * Implementation of Brainfuck operator ']'
 * end of the cycle
 */
public class LoopEnd implements Operator {

    @Override
    public void execute(Context machine) throws InstructionPointerException {
        if (machine.readCurrentCell() == 0)
            return;

        int openCycles = 1;
        while (openCycles > 0){
            machine.moveInstructionPointer(-1);
            byte instruction = machine.readInstruction();

            if (instruction == ']')
                ++openCycles;
            else if (instruction == '[')
                --openCycles;
        }
    }
}