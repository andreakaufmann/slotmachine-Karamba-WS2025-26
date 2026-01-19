package slotmachine.model;

import java.util.List;
import java.util.Random;

// Ein Reel (Walze) besteht aus mehreren Symbolen.
// Beim Spin wird zufällig ein Symbol ausgewählt.

public class Reel {
    private final List<SymbolEnum> symbols;
    private final Random random = new Random();

    public Reel(List<SymbolEnum> symbols){
        this.symbols = symbols;
    }

    // Dreht das Reel und gibt ein zufälliges Symbol zurück
    public SymbolEnum spin(){
        int index = random.nextInt(symbols.size());
        return symbols.get(index);
    }
}

