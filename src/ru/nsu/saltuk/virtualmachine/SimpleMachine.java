package ru.nsu.saltuk.virtualmachine;

import org.apache.log4j.Logger;
import ru.nsu.saltuk.factory.Factory;
import ru.nsu.saltuk.operators.Operator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Implementation of Virtual Brainfuck Machine
 */
public class SimpleMachine implements VMachine {

    private static Logger log = Logger.getLogger(SimpleMachine.class.getName());

    private boolean debug;
    private Factory operators;
    Context context;

    /**
     * Creates a machine with specified data array size
     * @param size size of data array
     * @param config stream with operators config
     * @param input input stream for executing program
     * @param output output stream to executing program
     * @throws VMException when size in not positive
     * @throws IOException when not possible to read config file
     */
    public SimpleMachine(int size, InputStream config, InputStream input, OutputStream output) throws VMException, IOException {
        log.trace("Constructing with " + size + " " + config + " " + input + " " + output);

        if (size <= 0) {
            log.warn("Negative size, throw VMException");
            throw new VMException("forbidden size");
        }

        operators = new Factory(config);
        context = new ContextImpl(size, input, output);
    }

    public void debug(boolean active){
        log.info("Set debug to " + active);
        debug = active;
    }

    @Override
    public void executeProgram(String filename) throws IOException, VMException {
        log.info("Start executing program from file");
        byte[] temp = Files.readAllBytes(Paths.get(filename));
        log.info("Reading done, run with byte array");
        executeProgram(temp);
    }

    @Override
    public void executeProgram(byte[] program) throws VMException, IOException {
        log.info("Start executing program from byte array");
        context.loadNewProgram(program);

        while (context.getInstructionPointerPosition() < context.getProgramLength()){
            if(debug){
                drawDebug();
            }

            log.debug("executing operator " + (char)context.readInstruction());
            Object temp = operators.create(Character.valueOf((char)context.readInstruction()).toString());
            if (temp instanceof Operator) {
                log.trace("Good instruction, run");
                ((Operator) temp).execute(context);
            }
            else if (temp != null) {
                log.warn("Object in not instance of Operator, throw VMException");
                throw new VMException("Incorrect config file");
            }
            else {
                log.warn("Ignored unknown symbol");
            }
            if (context.getInstructionPointerPosition() >= context.getProgramLength() - 1){
                log.debug("Program finished");
                break;
            }
            context.moveInstructionPointer(1);
        }
    }

    private void drawDebug() {
        log.debug("Drawing debug");
        if (context instanceof ContextImpl){
            ((ContextImpl)context).drawState();
        }
    }

}
