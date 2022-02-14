package tictactoe;

public class TicTacToe {

    private Board board;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;
    private boolean draw;
    private boolean hasWinner;
    private Player playerWinner;

    public TicTacToe(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.board = new Board();
        initializeGame();
    }

    public void initializeGame(){
        setWinStatus(false);
        setDrawStatus(false);
        setCurrentPlayer(playerOne);
        setPlayerWinner(null);
        initializeBoard();
    }

    public void setRoundWithPosition(int row, int col){
        updateBoardWithTurn(row, col);
        checkWinAndUpdateStatus();
        if (!hasWinner)
            nextPlayerTurn();
    }

    public char updateBoardWithTurn(int row, int col) {
        try {
            invalidTurnRequest();
            validatePosition(row, col);
            board.updateBoardWith(currentPlayer, row, col);
            return currentPlayer.getSymbol();
        } catch (Exception e){
            throw e;
        }
    }

    public void invalidTurnRequest(){
        if (hasWinner()){
            throw new IllegalStateException("Game has ended.");
        }
    }


    public void nextPlayerTurn(){
        if (currentPlayer == playerOne){
            setCurrentPlayer(playerTwo);
        } else {
            setCurrentPlayer(playerOne);
        }
    }



    public void setCurrentPlayer(Player player){
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }


    public void validatePosition(int row, int col){
        int boardCapacity = getBoard().getMaxDimension();
        if (board.availablePosCount() <= 0){
            throw new IllegalStateException("No available positions in the board.");
        }

        if ((row > boardCapacity || row <= 0) || (col > boardCapacity || col <= 0)) {
            throw new IllegalArgumentException("Invalid selected position");
        }

        board.validateUnoccupiedPosition(row, col);
    }

    public void validateAllPlayers(){
        validatePlayer(playerOne);
        validatePlayer(playerTwo);
    }

    public void validatePlayer(Player player){
        if (player == null) {
            throw new IllegalArgumentException("Player is null.");
        }
    }

    public void updateWinStatus(){
        setWinStatus(true);
        setPlayerWinner(currentPlayer);
    }

    private void setWinStatus(boolean status){
        this.hasWinner = status;
    }

    public boolean hasWinner(){
        return this.hasWinner;
    }

    public void setPlayerWinner(Player playerWinner){
        this.playerWinner = playerWinner;
    }

    public Player getPlayerWinner(){
        return this.playerWinner;
    }

    public boolean checkRowWin(){
        int playerRowStreak = 0;

        for (int row = 0; row < Board.MAX_DIMENSION; row++) {
            for (int col = 0; col < Board.MAX_DIMENSION; col++) {
                if (board.getPositionOwner(row, col) == currentPlayer.getSymbol()){
                    playerRowStreak++;
                }
            }
            if (playerRowStreak == Board.MAX_DIMENSION){
                return true;
            }
            playerRowStreak = 0;
        }
        return false;
    }

    public boolean checkWinAndUpdateStatus(){
        if (checkRowWin() || checkColumnWin() || checkDiagonalWin()){
            updateWinStatus();
            return true;
        }
        checkDraw();
        return false;
    }

    public boolean checkColumnWin() {
        int playerColumnStreak = 0;

        for (int col = 0; col < Board.MAX_DIMENSION; col++) {
            for (int row = 0; row < Board.MAX_DIMENSION; row++) {
                if (board.getPositionOwner(row, col) == currentPlayer.getSymbol()){
                    playerColumnStreak++;
                }
            }
            if (playerColumnStreak == Board.MAX_DIMENSION){
                return true;
            }

            playerColumnStreak = 0;
        }
        return false;
    }

    public boolean checkDiagonalWin() {
        return checkLeftToRightDiagonal() || checkRightToLeftDiagonal();
    }

    public boolean checkRightToLeftDiagonal(){
        int playerDiagonalStreak = 0;
        for (int rowAndColumn = 0; rowAndColumn < Board.MAX_DIMENSION; rowAndColumn++) {
            if (board.getPositionOwner(rowAndColumn, rowAndColumn) == currentPlayer.getSymbol()){
                playerDiagonalStreak++;
            }
        }
        if (playerDiagonalStreak == Board.MAX_DIMENSION){
            return true;
        }
        return false;
    }

    public boolean checkLeftToRightDiagonal(){
        int playerDiagonalStreak = 0;
        int boardMax = Board.MAX_DIMENSION;

        for (int col = 0, row = Board.MAX_DIMENSION - 1; col < Board.MAX_DIMENSION; col++, row--) {
            if (board.getPositionOwner(row, col) == currentPlayer.getSymbol()){
                playerDiagonalStreak++;
            }
        }
        if (playerDiagonalStreak == Board.MAX_DIMENSION){
            return true;
        }
        return false;
    }

    public void setDrawStatus(boolean status){
        draw = status;
    }

    public boolean checkDraw(){
        if(board.availablePosCount() == 0){
            draw = true;
            return true;
        }
        return false;
    }

    public boolean isDraw() {
        return draw == true;
    }


    public void initializeBoard() {
        this.board = new Board();
    }


    public void displayBoard() {
        board.displayBoard();
    }

    public Board getBoard() {
        return this.board;
    }

}