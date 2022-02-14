package tictactoe;

import java.util.Scanner;

public class TicTacToeConsole {

    TicTacToe ticTacToe;
    private Player playerOne;
    private Player playerTwo;
    private boolean isDone = false;


    public TicTacToeConsole() {

    }

    public void startGameLoop() {
        char choice = 'y';
        while (choice == 'Y' || choice == 'y'){
           System.out.println("Game Loop Started");
           initializeGame();
           checkWinnerLoop();
           displayBoard();
           choice = askYesOrNo("Enter 'Y' to play again.");
       }
        System.out.println("Thanks for playing!");
    }

    public void displayBoard(){
        ticTacToe.displayBoard();
    }

    public boolean checkWinnerLoop(){
        while (!isDone){
            setTurn();
            if (ticTacToe.checkWinAndUpdateStatus()){
                System.out.println("DONE!");
                System.out.println("WINNER: " + ticTacToe.getPlayerWinner().getName());
                updateGameStatus(true);
                return true;
            }
            else if (ticTacToe.getBoard().availablePosCount() == 0) {
                System.out.println("DRAW!!!");
                return false;
            }
            displayBoard();
        }
        return false;
    }

    public void setTurn(){
        System.out.println("SET TURN");;
        int row, col;
        while (true){
            try {
                row = askIntegerInput("Enter row: ");
                col = askIntegerInput("Enter column");
                ticTacToe.setRoundWithPosition(row, col);
                ticTacToe.checkWinAndUpdateStatus();
                break;
            } catch (Exception e){
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void updateGameStatus(boolean newStatus) {
        isDone = newStatus;
    }

    public int askIntegerInput(String question){
        Scanner sc = new Scanner(System.in);
        while (true){
            try {
                System.out.println(question);
                return sc.nextInt();
            } catch (Exception e){
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    private void initializeGame() {
        initializePlayerInfo();
        ticTacToe = new TicTacToe(playerOne, playerTwo);
        displayBoard();
    }

    public Character askYesOrNo(String question){
        Scanner sc = new Scanner(System.in);
        while (true){
            try {
                System.out.println(question);
                return sc.nextLine().charAt(0);
            } catch (Exception e){
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    private void initializePlayerInfo() {
        Player playerOne = askAndGetPlayerInfo();
        Player playerTwo = askAndGetPlayerInfo();
        setPlayerOne(playerOne);
        setPlayerTwo(playerTwo);
    }

    private Player askAndGetPlayerInfo() {
        Scanner sc = new Scanner(System.in);
        String name;
        Character symbol;
        while (true){
            try {
                System.out.println("Enter player name: ");
                name = sc.nextLine();
                System.out.println("Enter player symbol: ");
                symbol = sc.nextLine().charAt(0);
                break;
            } catch (Exception e){
                System.out.println("Invalid input. Please try again.");
            }
        }
        return new Player(name, symbol);
    }

    private void setPlayerOne(Player player) {
        this.playerOne = player;
    }

    private void setPlayerTwo(Player player) {
        this.playerTwo = player;
    }

    public static void main(String[] args) {
        TicTacToeConsole ticTacToeConsole = new TicTacToeConsole();
        ticTacToeConsole.startGameLoop();
    }
}
