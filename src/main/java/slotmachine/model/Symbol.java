package slotmachine.model;

public class Symbol {
    private final String name;
    private final int value;

    public Symbol(String name, int value){
        this.name = name;
        this.value = value;
    }

    public String getName(){
        return name;
    }

    public int getValue(){
        return value;
    }

    @Override
    public String toString(){
        return name;
    }
}
