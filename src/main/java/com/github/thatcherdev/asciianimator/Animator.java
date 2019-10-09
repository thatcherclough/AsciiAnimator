package com.github.thatcherdev.asciianimator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class Animator {

    private File file;
    private boolean loop;
    private int wait;

    /**
     * Construct a new Animator.
     * <p>
     * Construct a new Animator and setup AnsiConsole to be used for cursor
     * movement.
     * 
     * @param inFile name of file with frames to animate
     * @param inLoop if animation should loop
     * @param inWait milliseconds to wait before displaying next frame
     * @return
     */
    public Animator(String inFile, boolean inLoop, int inWait) {
        file = new File(inFile);
        wait = inWait;
        loop = inLoop;
        AnsiConsole.systemInstall();
    }

    /**
     * Start animator.
     * <p>
     * Get lines from file {@link file}. Use {@link #animate(ArrayList<String>)} to
     * start animation with these lines.
     * 
     * @throws FileNotFoundException
     * @throws InterruptedException
     */
    public void start() throws FileNotFoundException, InterruptedException {
        ArrayList<String> lines = getLines();
        while (true) {
            animate(lines);
            if (!loop)
                break;
        }
    }

    /**
     * Start animation of {@link #lines}.
     * <p>
     * Cycle though {@link #lines}. Either display line or move cursor to (0,0),
     * wait {@link #wait} milliseconds, and clear screen.
     * 
     * @param lines lines from animation file
     * @throws InterruptedException
     */
    private void animate(ArrayList<String> lines) throws InterruptedException {
        for (int k = 0; k < lines.size(); k++) {
            String line = lines.get(k);
            if (line.startsWith("[frame]")) {
                System.out.print(Ansi.ansi().cursor(0, 0));
                Thread.sleep(wait);
                System.out.print(Ansi.ansi().eraseScreen());
            } else
                System.out.println(line);
        }
    }

    /**
     * Get all lines from {@link #file}.
     * 
     * @return ArrayList<String> lines from {@link #file}.
     * @throws FileNotFoundException
     */
    private ArrayList<String> getLines() throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<String>();
        Scanner in = new Scanner(file);
        while (in.hasNextLine())
            lines.add(in.nextLine());
        in.close();
        return lines;
    }
}
