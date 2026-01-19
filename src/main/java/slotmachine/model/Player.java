package slotmachine.model;

public class Player {

    private double balance;

    public Player(double startingBalance){
        if (startingBalance < 0){
            throw new IllegalArgumentException("Guthaben darf nicht negativ sein!");
        }
        this.balance = startingBalance;
    }

    // prüfen, ob Spieler genug Guthaben hat
    public boolean hasEnoughCredits(double amount){
        return balance >= amount;
    }

    public double getBalance(){
        return balance;
    }

    // Gewinn hinzufügen
    public void increaseBalance(double amount){
        this.balance += amount;
    }

    // Einsatz abziehen
    public void decreaseBalance(double amount){
        if(amount > balance){
            throw new IllegalArgumentException("Nicht genug Guthaben!");
        }
        balance -= amount;
    }

    public void adjustBalance(double amount) {
        double newBalance = balance + amount;
        if (newBalance < 0) {
            throw new IllegalArgumentException("Nicht genug Guthaben!");
        }
        balance = newBalance;
    }

}
