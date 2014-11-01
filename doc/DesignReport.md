![](http://imgur.com/ElootDC)

#Síðannarverkefni
##_Design Report_

_Nemendur:_
Agla Harðardóttir (agla08@ru.is)
Freyr Bergsteinsson (freyrb12@ru.is)
Helgi Björnsson (helgib10@ru.is)
Íris Björnsdóttir (irisbjo10@ru.is)
Kristinn Darri Arinbjargarson (kristinnr09@ru.is)

_Kennari:_
Hannes Pétursson

##Game Design
![](http://imgur.com/fnPfQxa.png)

##Game core interface
``` java
public class GeitIMyllu {
    // Constructor - sets up a new game
    public GeitIMyllu();

    // Return the state of the board, as a string. Each character represents a cell
    // on the board and is either 'X', 'O' or '-'. The first three characters
    // represent the top row, the next three the middle row and the last three
    // the bottom row.
    public String boardAsString();

    // Return the owner of the cell at the given row and column
    // Returns either 'X', 'O' or '-'
    public char getCell(int row, int col);

    // Mark the cell at the given row and column with the current player's mark
    // Throws IllegalArgumentException if cell is already taken or it's game over
    public void play(int row, int col) throws IllegalArgumentException;

    // Reset the current game
    public void resetGame():

    // Returns the current state of the game
    // 1 - The game is running and it's player X's turn
    // 2 - The game is running and it's player O's turn
    // 3 - The game is over and player X won
    // 4 - The game is over and player O won
    // 5 - The game is over and it was a tie
    public int getGameState();

    // Return the number of the current turn
    public int getTurn();

    // Return the current player, 'X', 'O' or 'N' if it's currently game over
    public char getCurrentPlayer();

    // Return how many games have been played since we started
    public int getPlayedGames();

    // Return how many games player X has won since we started
    public int getGamesWonByPlayerX();

    // Return how many games player O has won since we started
    public int getGamesWonByPlayerO();

    // Return how many games ended in a tie since we started
    public int getTies();

    // Test function, entirely optional - not used except for debugging
    public static void main(String[] args);
}
```

##Web API interface

>**Mandatory API calls**
>
>
>**Optional API calls**
>
>

##Development work flow
During development we used agile methologies as taught in the first part of the course. Due to the time contrains of this assignment, we decided against using Scrum and instead used Kanban. We used Trello to visualize the work flow and performed “daily stand-ups” using Google Hangout, since the team is spread out.



