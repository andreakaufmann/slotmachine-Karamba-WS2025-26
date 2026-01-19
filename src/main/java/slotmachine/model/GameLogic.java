package slotmachine.model;

import java.util.Random;

public class GameLogic {
    private final Reel[] reels;
    private final Player player;
    private final Random random = new Random();

    public GameLogic(Reel[] reels, Player player){
        this.reels = reels;
        this.player = player;
    }

    public SymbolEnum[] spinReels(){
        SymbolEnum[] result = new SymbolEnum[reels.length];
        for (int i = 0; i < reels.length; i++){
            result[i] = reels[i].spin();
        }
        return result;
    }

    public int calculateWinnings(SymbolEnum[] spinResult, int betAmount){
        String first = spinResult[0].name(); // Name first Symbol

        // prüfen, ob alle Symbole gleich sind
        for (int i = 1; i < spinResult.length; i++){
            if (!spinResult[i].name().equals(first)){
                return 0;
            }
        }

        // alle Symbole gleich -> Gewinn
        int symbolValue = spinResult[0].getValue();
        return betAmount * symbolValue;
    }

    public SymbolEnum[] playRound(int betAmount){
        if(!player.hasEnoughCredits(betAmount)){
            return null;
        }
        player.decreaseBalance(betAmount); // Einsatz wird abgezogen

        //Walzen drehen
        SymbolEnum[] result = spinReels();

        // Gewinn berechnen
        int winnings = calculateWinnings(result, betAmount);

        // Gewinn hinzufügen
        if (winnings > 0){
            player.increaseBalance(winnings);
        }
        return result;
        }

    public double getPlayerBalance(){
        return player.getBalance();
    }
}

