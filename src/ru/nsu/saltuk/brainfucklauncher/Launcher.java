package ru.nsu.saltuk.brainfucklauncher;

import ru.nsu.saltuk.virtualmachine.SimpleMachine;
import ru.nsu.saltuk.virtualmachine.VMException;
import ru.nsu.saltuk.virtualmachine.VMachine;

import java.io.IOException;

/**
 * Launcher for brainfuck programs,
 * gets a program in first command
 * line argument and exec it on a
 * Virtual Brainfuck Machine
 */
public class Launcher {
    public static void main(String[] args) {
        if (args.length < 1){
            System.out.println("No input program");
            System.exit(0);
        }

        VMachine bm = null;
        try{
            bm = new SimpleMachine(30000, Launcher.class.getResourceAsStream("/config.properties"), System.in, System.out);
        } catch (VMException e) {
            System.err.println("\nInternal error");
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            System.err.println("\nSome I/O trouble");
            e.printStackTrace();
            System.exit(-1);
        }

        try {
            bm.executeProgram(args[0].getBytes());
        } catch (VMException e) {
            System.err.println("\nTrouble with your program: " + e.getMessage());
            System.exit(-1);
        } catch (IOException e) {
            System.err.println("\nSome I/O trouble");
            e.printStackTrace();
            System.exit(-1);
        }
        finally {
            System.out.flush();
        }
        System.err.println("\nProgram finished correctly");
    }
}
