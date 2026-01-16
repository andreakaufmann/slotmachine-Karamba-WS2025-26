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
    }
}
