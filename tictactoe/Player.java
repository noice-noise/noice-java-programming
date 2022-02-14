package tictactoe;

public class Player {
    private String name;
    private char symbol;




    public Player(String name, char symbol) {
        validateSymbol(symbol);
        this.name = name;
        this.symbol = symbol;
    }

    public void validateSymbol(char c){
        if (c == ' '){
            throw new IllegalArgumentException("Symbol must not be space.");
        }
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return this.symbol;
    }
}
