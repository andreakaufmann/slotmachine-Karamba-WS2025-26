package slotmachine.model;

import java.util.List;
import java.util.Random;

public class Reel {
    private final List<Symbol> symbols;
    private final Random random = new Random();

    public Reel(List<Symbol> symbols){
        this.symbols = symbols;
    }

    public Symbol spin(){
        int index = random.nextInt(symbols.size());
        return symbols.get(index);
    }
}
