package slotmachine.model;

public class GameLogic {
    private final Reel[] reels;
    private final Player player;

    public GameLogic(Reel[] reels, Player player){
        this.reels = reels;
        this.player = player;
    }

    public Symbol[] spinReels(){
        Symbol[] result = new Symbol[reels.length];
        for (int i = 0; i < reels.length; i++){
            result[i] = reels[i].spin();
        }
        return  result;
    }

    public int calculateWinnings(Symbol[] spinResult, int betAmount){
        String first = spinResult[0].getName(); // Name first Symbol

        // prüfen, ob alle Symbole gleich sind
        for (int i = 1; i < spinResult.length; i++){
            if (!spinResult[i].getName().equals(first)){
                return 0;
            }
        }

        // alle Symbole gleich -> Gewinn
        int symbolValue = spinResult[0].getValue();
        return betAmount * symbolValue;
    }
}
