package is.ru.geitibandi.geitimyllu;

// This class handles a game of tic-tac-toe
public class GeitIMyllu {
    
    /////////////// MANDATORY METHODS ///////////////
    // The following methods must be implemented to get at least a working version
    // of the game

    // Constructor - sets up a new game
    public GeitIMyllu() {}

    // Return the state of the board, as a string. Each character represents a cell
    // on the board and is either 'X', 'O' or '-'. The first three characters
    // represent the top row, the next three the middle row and the last three
    // the bottom row.
    public String boardAsString() { return "---------"; }

    // Return the owner of the cell at the given row and column
    // Returns either 'X', 'O' or '-'
    public char getCell(int row, int col) { return '-'; }

    // Mark the cell at the given row and column with the current player's mark
    // Throws IllegalArgumentException if cell is already taken or it's game over
    public void play(int row, int col) throws IllegalArgumentException {}

    // Reset the current game
    public void resetGame() {}

    // Returns the current state of the game
    // 1 - The game is running and it's player X's turn
    // 2 - The game is running and it's player O's turn
    // 3 - The game is over and player X won
    // 4 - The game is over and player O won
    // 5 - The game is over and it was a tie
    public int getGameState() { return 0; }


    /////////////// OPTIONAL METHODS ///////////////
    // The following methods do not need to be implemented unless there's spare time

    // Return the number of the current turn
    public int getTurn() { return 0; }

    // Return the current player, 'X', 'O' or 'N' if it's currently game over
    public char getCurrentPlayer() { return 'N'; }

    // Return how many games have been played since we started
    public int getPlayedGames() { return 0; }

    // Return how many games player X has won since we started
    public int getGamesWonByPlayerX() { return 0; }

    // Return how many games player O has won since we started
    public int getGamesWonByPlayerO() { return 0; }

    // Return how many games ended in a tie since we started
    public int getTies() { return 0; }

    // Test function, entirely optional - not used except for debugging
    public static void main(String[] args){}
}