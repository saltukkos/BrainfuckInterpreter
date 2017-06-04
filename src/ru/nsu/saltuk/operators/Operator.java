package ru.nsu.saltuk.operators;

import ru.nsu.saltuk.virtualmachine.Context;
import ru.nsu.saltuk.virtualmachine.VMException;

/**
 * Interface for all commands
 */
public interface Operator {
    void execute(Context context) throws VMException;
}
