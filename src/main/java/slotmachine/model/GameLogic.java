package slotmachine.model;

import java.util.Random;

// Zentrale Spiel-Logik der Slotmachine
// Verantwortlich für das Drehen der Walzen, Gewinnberechnung & Spielerguthaben
public class GameLogic {
    private final Reel[] reels; // Walzen
    private final Player player; // Spielerobjekt
    private final Random random = new Random();


    public GameLogic(Reel[] reels, Player player){
        this.reels = reels;
        this.player = player;
    }

    // Dreht Walzen und liefert Ergebnis zurück
    public SymbolEnum[] spinReels(int betAmount){
        SymbolEnum[] result = new SymbolEnum[reels.length];
        for (int i = 0; i < reels.length; i++){
            result[i] = reels[i].spin(betAmount);
        }
        return result;
    }

    // Berechnet den Gewinn der Runde
    public int calculateWinnings(SymbolEnum[] spinResult, int betAmount){
        String first = spinResult[0].name(); // Name first Symbol

        // prüfen, ob alle Symbole gleich sind
        // Gewinn nur dann, wenn alle Symbole gleich sind
        for (int i = 1; i < spinResult.length; i++){
            if (!spinResult[i].name().equals(first)){
                return 0;
            }
        }

        // alle Symbole gleich -> Gewinn
        // Gewinn = Einsatz * Symbolwert
        int symbolValue = spinResult[0].getValue();
        return betAmount * symbolValue;
    }

    // Führt eine komplette Runde aus
    public SymbolEnum[] playRound(int betAmount){
        if(!player.hasEnoughCredits(betAmount)){
            return null; // kein Spiel möglich, da Spieler keine Credits
        }
        player.decreaseBalance(betAmount); // Einsatz wird abgezogen

        //Walzen drehen
        SymbolEnum[] result = spinReels(betAmount);

        // Gewinn berechnen
        int winnings = calculateWinnings(result, betAmount);

        // Gewinn addieren
        if (winnings > 0){
            player.increaseBalance(winnings);
        }
        return result;
        }

        // aktuelles Guthaben
    public double getPlayerBalance(){
        return player.getBalance();
    }
}

