package slotmachine.model;

import java.util.List;
import java.util.Random;

// Ein Reel (Walze) besteht aus mehreren Symbolen.
// Beim Spin wird zufällig ein Symbol ausgewählt.

public class Reel {
    private final Random random = new Random();

    public SymbolEnum spin(int betAmount) {
        SymbolEnum[] symbols = SymbolEnum.values();

        int totalWeight = 0;
        for (SymbolEnum symbol : symbols) {
            totalWeight += adjustedWeight(symbol, betAmount);
        }

        int rnd = random.nextInt(totalWeight);
        int current = 0;
        for (SymbolEnum symbol : symbols) {
            current += adjustedWeight(symbol, betAmount);
            if (rnd < current) {
                return symbol;
            }
        }
        return SymbolEnum.CHERRY; // = fallback
    }

    private int adjustedWeight(SymbolEnum symbol, int bet) {
        int weight = symbol.getBaseWeight();

        if (bet >= 5) {
            weight += symbol.getValue() / 2;
        }
        if (bet >= 10) {
            weight += symbol.getValue();
        }
        return weight;
    }

}

