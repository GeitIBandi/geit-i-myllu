![](http://imgur.com/ElootDc)

#Síðannarverkefni
##_Design Report_

_Nemendur:_
Agla Harðardóttir (agla08@ru.is),
Freyr Bergsteinsson (freyrb12@ru.is),
Helgi Björnsson (helgib10@ru.is),
Íris Björnsdóttir (irisbjo10@ru.is) og
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
```
Mandatory API calls
The following API calls must be implemented to get at least a working version of the game

GET /api/v1/state        Returns the current state of the game
                Possible values:
1 - The game is running and it’s player X’s turn
2 - The game is running and it’s player O’s turn
3 - The game is over and player X won
4 - The game is over and player O won
5 - The game is over and it was a tie

POST /api/v1/play/<row>/<col>
Try to make a play as the current player in the cell at row
<row> and column <col>.
                Returns 200 (OK) if move was valid, otherwise a 403 (Forbidden)
                if the box was already taken or game over

POST /api/v1/reset        Wipes the current game and starts a new one.

GET /api/v1/board        Returns the state of the board as a 9 character string of three
                possible characters: X, O, and -. The first three characters
represent the boxes in the top row, etc. The character - means
that the box is free to play on.
Example: “XXO-OO-X-X” represents the board:
XXO
 OO
X X

Optional API calls
The following methods do not need to be implemented unless there's spare time

GET /api/v1/turn        Returns the current turn number

GET /api/v1/whosturn        Returns which player is currently doing.
Possible values:    X (player X’s turn)
O (player O’s turn)
N (nobody’s turn - game over)

GET /api/v1/played        Returns the number of games that have been played

GET /api/v1/won        Returns which player has won the current game, if any.
Possible values: X, O, T (for Tie), N (for None)

GET /api/v1/won/<p>        Returns the number of games player <p> has won where <p> is
                either X or O

GET /api/v1/ties        Returns the number of games that have ended in a tie
```


##Development work flow
During development we used agile methologies as taught in the first part of the course. Due to the time contrains of this assignment, we decided against using Scrum and instead used Kanban. We used Trello to visualize the work flow and performed “daily stand-ups” using Google Hangout, since the team is spread out.
![](http://imgur.com/D6dNqcd)


