package ru.nsu.saltuk.operators;

import org.apache.log4j.Logger;
import ru.nsu.saltuk.virtualmachine.Context;

import java.io.IOException;

/**
 * Implementation of Brainfuck operator '.'
 * write byte to output stream, doesn't flush a stream
 */
public class Output implements Operator {
    private final Logger log = Logger.getLogger(Output.class.getName());

    @Override
    public void execute(Context machine) {
        try {
            int data = (machine.readCurrentCell() + 256) % 256;
            log.debug("write to output " + data);
            machine.getOutputStream().write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
