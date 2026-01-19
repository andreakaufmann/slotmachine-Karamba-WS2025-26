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

    public Symbol[] playRound(int betAmount){
        if(!player.hasEnoughCredits(betAmount)){
            return null;
        }
        player.decreaseBalance(betAmount); // Einsatz wird abgezogen

        //Walzen drehen
        Symbol[] result = spinReels();

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

