package tictactoe;

import org.junit.Test;

import static org.junit.Assert.*;


public class TicTacTest {

    @Test
    public void testBoard(){
        Player p1 = new Player("Player One", 'X');
        Player p2 = new Player("Player Two", 'O');

        TicTacToe ticTacToe = new TicTacToe(p1, p2);
        ticTacToe.initializeBoard();
        assertEquals(true, ticTacToe.getBoard().isEmpty());
        Character p1Sym = p1.getSymbol();
        Player pNull = null;
        assertEquals(9, ticTacToe.getBoard().availablePosCount());
        assertEquals(p1Sym, ticTacToe.getBoard().updateBoardWith(p1, 1, 1));
        assertEquals(p1Sym, ticTacToe.getBoard().updateBoardWith(p1, 1, 2));
        assertEquals(p1Sym, ticTacToe.getBoard().updateBoardWith(p1, 1, 3));
        assertEquals(6, ticTacToe.getBoard().availablePosCount());
        assertEquals(p1Sym, ticTacToe.getBoard().updateBoardWith(p1, 2, 1));
        assertEquals(p1Sym, ticTacToe.getBoard().updateBoardWith(p1, 2, 2));
        assertEquals(p1Sym, ticTacToe.getBoard().updateBoardWith(p1, 2, 3));
        assertEquals(p1Sym, ticTacToe.getBoard().updateBoardWith(p1, 3, 1));
        assertEquals(p1Sym, ticTacToe.getBoard().updateBoardWith(p1, 3, 2));
        assertEquals(p1Sym, ticTacToe.getBoard().updateBoardWith(p1, 3, 3));
        assertEquals(false, ticTacToe.getBoard().isEmpty());
        ticTacToe.displayBoard();
        assertEquals(false, ticTacToe.getBoard().isEmpty());
        ticTacToe.getBoard().initializeBoard();
        assertEquals(true, ticTacToe.getBoard().isEmpty());
    }

    @Test
    public void testValidate(){
        Player p1 = new Player("Gulaman", 'X');
        Player p2 = new Player("Sago", 'O');
        Player pNull = null;
        TicTacToe ticTacToe = new TicTacToe(p1, p2);
        ticTacToe.validateAllPlayers();
        ticTacToe.validatePosition(1,1);
        ticTacToe.setRoundWithPosition(1,1);

        try {
            ticTacToe.validatePosition(1,1);
        } catch (Exception e){
            //System.out.println(e.getMessage());
            //do nothing
        }

        try {
            ticTacToe.validatePosition(0,1);
        } catch (Exception e){
            //System.out.println(e.getMessage());
        }

    }

    @Test
    public void playerInfoMustNotBeBlank(){
        Player player;
        try {
            player = new Player(" ", ' ');
            fail("Player info must be handle properly.");
        } catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testMove(){
        Player p1 = new Player("Gulapa", 'X');
        Player p2 = new Player("Dante", 'O');
        TicTacToe ticTacToe = new TicTacToe(p1, p2);
        assertEquals('X', ticTacToe.getCurrentPlayer().getSymbol());
        ticTacToe.setRoundWithPosition(1,1);
        assertEquals('O', ticTacToe.getCurrentPlayer().getSymbol());
        ticTacToe.setRoundWithPosition(1,2);
        assertEquals('X', ticTacToe.getCurrentPlayer().getSymbol());
        ticTacToe.setRoundWithPosition(1,3);
        assertEquals('O', ticTacToe.getCurrentPlayer().getSymbol());
        assertEquals(6, ticTacToe.getBoard().availablePosCount());
        ticTacToe.setRoundWithPosition(2,1);
        assertEquals('X', ticTacToe.getCurrentPlayer().getSymbol());
        ticTacToe.setRoundWithPosition(2,2);
        assertEquals('O', ticTacToe.getCurrentPlayer().getSymbol());
        ticTacToe.setRoundWithPosition(2,3);
        assertEquals('X', ticTacToe.getCurrentPlayer().getSymbol());
        assertEquals(3, ticTacToe.getBoard().availablePosCount());
        ticTacToe.setRoundWithPosition(3,2);
        assertEquals('O', ticTacToe.getCurrentPlayer().getSymbol());
        ticTacToe.setRoundWithPosition(3,3);
        assertEquals('X', ticTacToe.getCurrentPlayer().getSymbol());
        assertEquals(1, ticTacToe.getBoard().availablePosCount());
        ticTacToe.displayBoard();

        try {
            //ticTacToe.setRoundWithPosition(1,1);
            assertEquals('X', ticTacToe.updateBoardWithTurn(1,1));
            assertEquals('O', ticTacToe.updateBoardWithTurn(1,2));
            assertEquals('X', ticTacToe.updateBoardWithTurn(1,3));
            assertEquals('X', ticTacToe.updateBoardWithTurn(3,1));
            assertEquals('O', ticTacToe.updateBoardWithTurn(3,2));
            assertEquals('X', ticTacToe.updateBoardWithTurn(3,3));
            assertEquals('X', ticTacToe.updateBoardWithTurn(1,1));
            assertEquals('O', ticTacToe.updateBoardWithTurn(-1,-3));
            fail("Inputs must be handled thoroughly.");
        } catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testRowWinningConditions(){
        Player p1 = new Player("PLOne", 'X');
        Player p2 = new Player("PLOne", 'O');
        TicTacToe ticTacToe = new TicTacToe(p1, p2);
        Character playerOneSymbol = p1.getSymbol();
        assertEquals(playerOneSymbol, ticTacToe.getBoard().updateBoardWith(p1, 1, 1));
        assertEquals(playerOneSymbol, ticTacToe.getBoard().updateBoardWith(p1, 1, 2));
        assertEquals(playerOneSymbol, ticTacToe.getBoard().updateBoardWith(p1, 1, 3));
        assertFalse(ticTacToe.getBoard().isEmpty());
        assertTrue(ticTacToe.checkRowWin());

        ticTacToe.initializeGame();
        assertTrue(ticTacToe.getBoard().isEmpty());

        ticTacToe.setRoundWithPosition(1,1);
        ticTacToe.setRoundWithPosition(3,1);
        ticTacToe.setRoundWithPosition(2,2);
        ticTacToe.setRoundWithPosition(3,2);
        ticTacToe.setRoundWithPosition(1,3);
        ticTacToe.setRoundWithPosition(3,3);
        assertTrue(ticTacToe.hasWinner());
        ticTacToe.displayBoard();
    }

    @Test
    public void testColumnWinningConditions(){
        Player p1 = new Player("PLOne", 'X');
        Player p2 = new Player("PLOne", 'O');
        TicTacToe ticTacToe = new TicTacToe(p1, p2);
        Character playerOneSymbol = p1.getSymbol();
        assertEquals(playerOneSymbol, ticTacToe.getBoard().updateBoardWith(p1, 1, 1));
        assertEquals(playerOneSymbol, ticTacToe.getBoard().updateBoardWith(p1, 2, 1));
        assertEquals(playerOneSymbol, ticTacToe.getBoard().updateBoardWith(p1, 3, 1));
        assertFalse(ticTacToe.getBoard().isEmpty());
        assertTrue(ticTacToe.checkColumnWin());

        ticTacToe.initializeGame();
        assertTrue(ticTacToe.getBoard().isEmpty());

        ticTacToe.setRoundWithPosition(1,1);
        ticTacToe.setRoundWithPosition(3,3);
        ticTacToe.setRoundWithPosition(2,1);
        ticTacToe.setRoundWithPosition(3,2);
        ticTacToe.setRoundWithPosition(3,1);
        assertTrue(ticTacToe.hasWinner());

        ticTacToe.initializeGame();
        assertTrue(ticTacToe.getBoard().isEmpty());

        ticTacToe.setRoundWithPosition(1,2);
        ticTacToe.setRoundWithPosition(1,1);
        ticTacToe.setRoundWithPosition(2,2);
        ticTacToe.setRoundWithPosition(3,3);
        ticTacToe.setRoundWithPosition(3,1);
        ticTacToe.setRoundWithPosition(1,3);
        ticTacToe.setRoundWithPosition(3,2);

        assertTrue(ticTacToe.hasWinner());

        ticTacToe.initializeGame();
        assertTrue(ticTacToe.getBoard().isEmpty());

        ticTacToe.setRoundWithPosition(1,3);
        ticTacToe.setRoundWithPosition(1,1);
        ticTacToe.setRoundWithPosition(2,3);
        ticTacToe.setRoundWithPosition(3,2);
        ticTacToe.setRoundWithPosition(3,3);


        assertTrue(ticTacToe.hasWinner());
        ticTacToe.displayBoard();
    }

    @Test
    public void testDiagonal(){
        Player p1 = new Player("PLOne", 'X');
        Player p2 = new Player("PLOne", 'O');
        TicTacToe ticTacToe = new TicTacToe(p1, p2);
        Character playerOneSymbol = p1.getSymbol();

        assertEquals(playerOneSymbol, ticTacToe.getBoard().updateBoardWith(p1, 1, 1));
        assertEquals(playerOneSymbol, ticTacToe.getBoard().updateBoardWith(p1, 2, 2));
        assertEquals(playerOneSymbol, ticTacToe.getBoard().updateBoardWith(p1, 3, 3));
        assertFalse(ticTacToe.getBoard().isEmpty());
        ticTacToe.displayBoard();
        assertTrue(ticTacToe.checkDiagonalWin());

        ticTacToe.initializeGame();
        assertTrue(ticTacToe.getBoard().isEmpty());

        assertEquals(playerOneSymbol, ticTacToe.getBoard().updateBoardWith(p1, 3, 1));
        assertEquals(playerOneSymbol, ticTacToe.getBoard().updateBoardWith(p1, 2, 2));
        assertEquals(playerOneSymbol, ticTacToe.getBoard().updateBoardWith(p1, 1, 3));
        assertFalse(ticTacToe.getBoard().isEmpty());
        ticTacToe.displayBoard();
        assertTrue(ticTacToe.checkDiagonalWin());
    }
}
