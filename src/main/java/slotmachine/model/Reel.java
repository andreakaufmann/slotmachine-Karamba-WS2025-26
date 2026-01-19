package slotmachine.model;

import java.util.List;
import java.util.Random;

// Ein Reel (Walze) besteht aus mehreren Symbolen.
// Beim Spin wird zufällig ein Symbol ausgewählt.

public class Reel {
    private final List<Symbol> symbols;
    private final Random random = new Random();

    public Reel(List<Symbol> symbols){
        this.symbols = symbols;
    }

    // Dreht das Reel und gibt ein zufälliges Symbol zurück
    public Symbol spin(){
        int index = random.nextInt(symbols.size());
        return symbols.get(index);
    }
}

