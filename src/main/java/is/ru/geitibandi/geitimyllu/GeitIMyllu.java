package is.ru.geitibandi.geitimyllu;

// This class handles a game of tic-tac-toe
public class GeitIMyllu {

    /////////////// MANDATORY METHODS ///////////////
    // The following methods must be implemented to get at least a working version
    // of the game
    private char[][] board;
    private int gameState;
    private int turn;
    private int playedGames;
    private int gamesWonByPlayerX;
    private int gamesWonByPlayerO;
    private int ties;

    // Constructor - sets up a new game
    public GeitIMyllu() {
        board = new char[3][3];
        initializeBoard();
        gameState = 1;
        turn = 0;
        playedGames = 0;
        gamesWonByPlayerX = 0;
        gamesWonByPlayerO = 0;
        ties = 0;
    }

    // Initialze board...
    private void initializeBoard()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                setBoard(i, j, '-');
            }
        }
    }

    // Function to set board at i, j to given value
    private void setBoard(int row, int col, char value)
    {
        board[row][col] = value;
    }

    // Return the state of the board, as a string. Each character represents a cell
    // on the board and is either 'X', 'O' or '-'. The first three characters
    // represent the top row, the next three the middle row and the last three
    // the bottom row.
    public String boardAsString() {
        String stringBoard = "";
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                stringBoard += getCell(i, j);
            }
        }
        return stringBoard;
    }

    // Return the owner of the cell at the given row and column
    // Returns either 'X', 'O' or '-'
    public char getCell(int row, int col) {
        return (board[row][col]);
    }

    // Return next gamestate (3, 4, 5) if game is over
    private int updateGameState() {
        if (!gameWon() && boardFull()) {
            ties++;
            return 5;
        }
        else if (gameWon() && getGameState() == 1) {
            gamesWonByPlayerX++;
            return 3;
        }
        else if (gameWon() && getGameState() == 2) {
            gamesWonByPlayerO++;
            return 4;
        }
        else if (getGameState() == 1)
            return 2;
        return 1;
    }

    // Return true if same X or O in all arguments
    private boolean checkRowColDiag(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }

    // Return true if game won by full diogonal line
    public boolean checkDiagForWin() {
        return ((checkRowColDiag(getCell(0, 0), getCell(1, 1), getCell(2, 2)))
             || (checkRowColDiag(getCell(0, 2), getCell(1, 1), getCell(2, 0))));
    }

    // Return true if game won by full colum
    public boolean checkColsForWin() {
        for (int i = 0; i < 3; i++)
        {
            if (checkRowColDiag(getCell(0, i), getCell(1, i), getCell(2, i)))
            return true;
        }
        return false;
    }

    // Return true if game won by full row
    public boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++)
        {
            if (checkRowColDiag(getCell(i, 0), getCell(i, 1), getCell(i, 2)))
            return true;
        }
        return false;
    }

    // Returns true if board is full
    public boolean boardFull() {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (getCell(i, j) == '-')
                    return false;
            }
        }
        return true;
    }

    // Return true if game is won
    public boolean gameWon() {
        return (checkRowsForWin() || checkColsForWin() || checkDiagForWin());
    }

    // Returns char of player playing currently or winner
    public char playerMark() {
        if (getGameState() == 1 || getGameState() == 3)
            return 'X';
        else if (getGameState() == 2 || getGameState() == 4)
            return 'O';
        else
            return '=';
    }

    // Mark the cell at the given row and column with the current player's mark
    // Throws IllegalArgumentException if cell is already taken or it's game over
    public void play(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            throw new IllegalArgumentException("Cell not on board");
        }
            else if (getCell(row, col) != '-') {
            throw new IllegalArgumentException("Cell occupied");
        }
        else if (gameWon() || boardFull()) {
            throw new IllegalArgumentException("Game over");
        }
        else {
            setBoard(row, col, playerMark());
            setGameState(updateGameState());
            turn++;
        }
    }

    // Reset the current game
    public void resetGame() {
        initializeBoard();
        setGameState(1);
        turn = 0;
        playedGames++;
    }

    // Returns the current state of the game
    // 1 - The game is running and it's player X's turn
    // 2 - The game is running and it's player O's turn
    // 3 - The game is over and player X won
    // 4 - The game is over and player O won
    // 5 - The game is over and it was a tie
    public int getGameState() {
        return gameState;
    }

    // Set function for gameState
    private void setGameState(int value) {
        gameState = value;
    }


    /////////////// OPTIONAL METHODS ///////////////
    // The following methods do not need to be implemented unless there's spare time

    // Return the number of the current turn
    public int getTurn() {
        return turn;
    }

    // Return the current player, 'X', 'O' or 'N' if it's currently game over
    public char getCurrentPlayer() {
        if (!gameWon() && !boardFull())
            return playerMark();
        return 'N';
    }

    // Return how many games have been played since we started
    public int getPlayedGames() {
        return playedGames; 
    }

    // Return how many games player X has won since we started
    public int getGamesWonByPlayerX() {
        return gamesWonByPlayerX;
    }

    // Return how many games player O has won since we started
    public int getGamesWonByPlayerO() {
        return gamesWonByPlayerO;
    }

    // Return how many games ended in a tie since we started
    public int getTies() {
        return ties;
    }

    // Test function, entirely optional - not used except for debugging
    public static void main(String[] args) {
    }
}
