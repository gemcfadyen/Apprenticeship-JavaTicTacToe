package ttt;

import java.io.IOException;

/**
 * Created by Georgina on 12/10/15.
 */
public class HumanPlayer {
    private Prompt prompt;
    private final String symbol;

    public HumanPlayer(Prompt prompt, String symbol) {
        this.prompt = prompt;
        this.symbol = symbol;
    }

    public int chooseNextMoveFrom(Board board) throws IOException {
        prompt.display("Please enter the index for your next move");

        return prompt.read();
    }

    public String getSymbols() {
        return symbol;
    }
}
