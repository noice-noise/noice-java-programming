package tictactoe;

public class Board {

    protected static final int MAX_DIMENSION = 3;
    private Character[][] board;
    private int availablePosCount;

    public Board() {
        board = new Character[MAX_DIMENSION][MAX_DIMENSION];
        availablePosCount = MAX_DIMENSION * MAX_DIMENSION;
        initializeBoard();
    }

    public void initializeBoard(){
        for (int row = 0; row < MAX_DIMENSION; row++) {
            for (int col = 0; col < MAX_DIMENSION; col++) {
                board[row][col] = ' ';  //empty board is occupied with space
            }
        }
    }

    public boolean isEmpty(){
        for (int i = 0; i < MAX_DIMENSION; i++) {
            for (int j = 0; j < MAX_DIMENSION; j++) {
                if(!this.board[i][j].equals(' ')){
                    return false;
                }
            }
        }
        return true;
    }


    public void displayBoard() {
        for (int i = 0; i < MAX_DIMENSION; i++) {
            System.out.print(" |");
            for (int j = 0; j < MAX_DIMENSION; j++) {
                System.out.print(this.board[i][j] + "|");
            }
            System.out.println("");
        }
    }

    public Character updateBoardWith(Player player, int row, int col) {
        validateUnoccupiedPosition(row, col);
        board[row-1][col-1] = player.getSymbol();
        availablePosCount--;
        return player.getSymbol();
    }

    public int getMaxDimension(){
        return MAX_DIMENSION;
    }

    public int availablePosCount() {
        return this.availablePosCount;
    }

    public Character getPositionOwner(int row, int col){
        return this.board[row][col];
    }

    public void validateUnoccupiedPosition(int row, int col){
        if (board[row -1][col -1 ] != ' '){
            throw new IllegalCallerException("Position is already occupied.");
        }
    }

}
