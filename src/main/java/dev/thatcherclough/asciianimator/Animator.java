package dev.thatcherclough.asciianimator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class Animator {

	public boolean stop = false;
	private final File file;
	private final boolean loop;
	private final boolean clear;
	private final int wait;

	/**
	 * Constructs a new Animator.
	 * <p>
	 * Sets necessary values and starts AnsiConsole.
	 *
	 * @param file name of file with frames to animate
	 * @param loop if animation should loop
	 * @param clear if screen should be cleared after every frame
	 * @param wait milliseconds to wait before displaying each frame
	 */
	public Animator(String file, boolean loop, boolean clear, int wait) {
		this.file = new File(file);
		this.wait = wait;
		this.loop = loop;
		this.clear = clear;
		AnsiConsole.systemInstall();
	}

	/**
	 * Starts animator.
	 * <p>
	 * Gets lines from file {@link #file} using {@link #getLines()}. Starts a loop
	 * that continuously uses {@link #animate(ArrayList<String>)} to animate
	 * {@code lines}, unless {@link #loop} is false, in which case the loop is only
	 * run once.
	 *
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	public void start() throws FileNotFoundException, InterruptedException {
		ArrayList<String> lines = getLines();
		while (true) {
			animate(lines);
			if (!loop || stop)
				break;
		}
	}

	/**
	 * Starts stop motion animation of {@code lines}.
	 * <p>
	 * Cycles though {@code lines}. If line is "[frame]", moves cursor to (0,0),
	 * waits {@link #wait} milliseconds, and clears screen if {@link #clear} is true. Otherwise, prints line.
	 *
	 * @param lines lines to animate
	 * @throws InterruptedException
	 */
	private void animate(ArrayList<String> lines) throws InterruptedException {
		for (String line : lines) {
			if (stop)
				break;
			else if (line.startsWith("[frame]")) {
				System.out.print(Ansi.ansi().cursor(0, 0));
				Thread.sleep(wait);
				if (clear)
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
		ArrayList<String> lines = new ArrayList<>();
		Scanner in = new Scanner(file);
		while (in.hasNextLine())
			lines.add(in.nextLine());
		in.close();
		return lines;
	}
}