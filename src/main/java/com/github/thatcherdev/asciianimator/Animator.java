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
     * Constructs a new Animator.
     * <p>
     * Sets {@link #file} to the file with the name {@link inFile}, sets
     * {@link #loop} to {@link inLoop}, sets {@link #wait} to {@link inWait}, and
     * starts AnsiConsole using {@link AnsiConsole.systemInstall()} to be used for
     * cursor movement.
     * 
     * @param inFile name of file with frames to animate
     * @param inLoop if animation should loop
     * @param inWait milliseconds to wait before displaying each frame
     * @return
     */
    public Animator(String inFile, boolean inLoop, int inWait) {
        file = new File(inFile);
        wait = inWait;
        loop = inLoop;
        AnsiConsole.systemInstall();
    }

    /**
     * Starts animator.
     * <p>
     * Gets lines from file {@link #file} using {@link #getLines()}. Starts a loop
     * that continuously uses {@link #animate(ArrayList<String>)} to animate
     * {@link lines}, unless {@link #loop} is false, in which case the loop is only
     * run once.
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
     * Starts stop motion animation of {@link lines}.
     * <p>
     * Cycles though {@link lines}. If line is "[frame]", moves cursor to (0,0),
     * waits {@link #wait} milliseconds, and clears screen. Otherwise, prints line.
     * 
     * @param lines lines to animate
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
     * Gets all lines from {@link #file}.
     * 
     * @return ArrayList<String> of lines from {@link #file}
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