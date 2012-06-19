package src.ru.md24inc.alembic.pervoc.control;

import src.ru.md24inc.alembic.pervoc.core.*;
import java.io.*;

public class ConsoleFileXML {
	public static void main(String args[]) throws IOException {
		// Creating Console promt for filename
		Console c = System.console();
		if (c == null) {
			System.err.println("No console.");
			System.exit(1);
		}
		String fileName = c.readLine("Enter your fileName: ");
		// Open file
		BufferedReader buff = null;
		try {
			FileReader file = new FileReader(fileName);
			buff = new BufferedReader(file);
			boolean eof = false;
			while (!eof) {
				String line = buff.readLine();
				if (line == null)
					eof = true;
				else
					// Printing file to console
					System.out.println(line);
			}
		} catch (IOException e) {
			System.out.println("Error -- " + e.toString());
		} finally {
			buff.close();
		}

		// Printing file to console

	}
}