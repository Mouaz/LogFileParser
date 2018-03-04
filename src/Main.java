
import java.io.IOException;

import utilities.LogFileParser;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		LogFileParser.parseLogFileAndCreateProgram(args[0], Integer.parseInt(args[1]));
	}
}
