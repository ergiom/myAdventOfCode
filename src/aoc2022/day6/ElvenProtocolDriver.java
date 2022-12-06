package aoc2022.day6;

import java.io.BufferedReader;
import java.io.IOException;

public class ElvenProtocolDriver {
    int distinctChars;
    ElvenProtocolDriver(int distinctChars) {
        this.distinctChars = distinctChars;
    }

    public int skipToMessage(BufferedReader reader) throws IOException {
        ElvenProtocolSkipper skipper = new ElvenProtocolSkipper(distinctChars);
        while (! skipper.full()) {
            skipper.write((char) reader.read());
        }

        while (!skipper.messageStart()) {
            skipper.write((char) reader.read());
        }

        return skipper.getWrites();
    }
}
