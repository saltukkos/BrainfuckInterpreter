package ru.nsu.saltuk.operators;

import org.apache.log4j.Logger;
import ru.nsu.saltuk.virtualmachine.Context;

import java.io.IOException;

/**
 * Implementation of Brainfuck operator ','
 * read byte from System.in
 */
public class Input implements Operator {
    private final Logger log = Logger.getLogger(Input.class.getName());

    @Override
    public void execute(Context machine) {

        try {
            byte b = (byte) machine.getInputStream().read();
            log.debug("read from input " + (b + 256) % 256);
            machine.writeCurrentCell(b);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
