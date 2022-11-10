import java.util.Random;

public class Board {

    private int row = 10;                                          //The row of this board.
    private int column = 10;                                       //The column of this board.
    private Square[][] board = null;                               //Board stores the Square objects.
    private Battleship[] battleships = null;                       //The battleships in this board.

    //A constructor of a Board object.
    public Board(int row, int column, boolean extend) {

        this.row = row;                                            //Set row of this board.
        this.column = column;                                      //Set column of this board.
        this.board = new Square[row][column];                      //Declare the array of Squares.

        for (int i = 0; i < this.row; ++i) {
            for (int j = 0; j < this.column; ++j) {
                this.board[i][j] = new Square(i, j);               //Declare all square objects in the array.
            }
        }

        if (extend == false) {                                     //Judge if it is extend mod.
            this.battleships = new Battleship[5];                  //Declare this board has 5 ships.
            for (int i = 0; i < 5; ++i) {                          //Loop 5 times to generate 5 ships
                int[] ship_position = new int[3];                  //The ship position is the left and the top of the ship and ship_pos[2] means the direction.
                this.randomShip(ship_position, 2);           //Get the random ship position.
                Square[] battleship_squares = new Square[2];       //Declare the array of squares.
                for (int j = 0; j < 2; ++j) {
                    if (ship_position[2] == 1) {                   //Set the top-bottom direction squares
                        battleship_squares[j] = this.board[ship_position[0] + j][ship_position[1]];
                    } else {                                       //Set the left-right direction squares
                        battleship_squares[j] = this.board[ship_position[0]][ship_position[1] + j];
                    }
                }
                this.battleships[i] = new Battleship(battleship_squares); //Set the battleship.
            }
        } else {                                                   //Extend mod.
            this.battleships = new Battleship[6];                  //Declare this board has 6 ships.
            for (int i = 0; i < 3; ++i) {                          //Loop 3 times to generate 3 small ships.
                int[] ship_position = new int[3];                  //The ship position is the left and the top of the ship and ship_pos[2] means the direction.
                this.randomShip(ship_position, 1);           //Get the random ship position.
                Square[] small_battleship_squares = new Square[1]; //Declare the array of squares.
                for (int j = 0; j < 1; ++j) {
                    if (ship_position[2] == 1) {                   //Set the top-bottom direction squares
                        small_battleship_squares[j] = this.board[ship_position[0] + j][ship_position[1]];
                    } else {                                       //Set the left-right direction squares
                        small_battleship_squares[j] = this.board[ship_position[0]][ship_position[1] + j];
                    }
                }
                this.battleships[i] = new SmallBattleship(small_battleship_squares); //Set the battleship.
            }
            for (int i = 0; i < 2; ++i) {                          //Loop 2 times to generate 2 medium ships.
                int[] ship_position = new int[3];                  //The ship position is the left and the top of the ship and ship_pos[2] means the direction.
                this.randomShip(ship_position, 2);           //Get the random ship position.
                Square[] medium_battleship_squares = new Square[2]; //Declare the array of squares.
                for (int j = 0; j < 2; ++j) {
                    if (ship_position[2] == 1) {                   //Set the top-bottom direction squares
                        medium_battleship_squares[j] = this.board[ship_position[0] + j][ship_position[1]];
                    } else {                                       //Set the left-right direction squares
                        medium_battleship_squares[j] = this.board[ship_position[0]][ship_position[1] + j];
                    }
                }
                this.battleships[i + 3] = new MediumBattleship(medium_battleship_squares); //Set the battleship.
            }
            for (int i = 0; i < 1; ++i) {                          //Loop 1 time to generate 1 large ships.
                int[] ship_position = new int[3];                  //The ship position is the left and the top of the ship and ship_pos[2] means the direction.
                this.randomShip(ship_position, 3);           //Get the random ship position.
                Square[] large_battleship_squares = new Square[3]; //Declare the array of squares.
                for (int j = 0; j < 3; ++j) {
                    if (ship_position[2] == 1) {                   //Set the top-bottom direction squares
                        large_battleship_squares[j] = this.board[ship_position[0] + j][ship_position[1]];
                    } else {                                       //Set the left-right direction squares
                        large_battleship_squares[j] = this.board[ship_position[0]][ship_position[1] + j];
                    }
                }
                this.battleships[i + 5] = new MediumBattleship(large_battleship_squares); //Set the battleship.
            }
        }
    } 

    //Generate the position of a battleship by random methods.
    public void randomShip(int[] ship_position, int size) {
        Random random = new Random();                              //Declare a random object.
        if (random.nextBoolean() == true) {                        //This means this ship is in top-bottom direction.
            ship_position[2] = 1;                                  //Tell the constructor his ship is in top-bottom direction.
            boolean get_pos = false;                               //The get_pos is to judge if we get the valid position.
            while (get_pos == false) {                             //Do the while loop until we get the valid position.
                ship_position[0] = random.nextInt(this.row - 2 * size + 2) + size - 1; //The row range is [0, this.row - size + 1).
                ship_position[1] = random.nextInt(this.column);    //The column range is [0, this.column).
                get_pos = true;                                    //Presume we get the valid position.
                for (int i = 0; i < size; ++i) {
                    if (this.board[ship_position[0] + i][ship_position[1]].isShip() == true) {
                        get_pos = false;                           //If any square in this ship is ship, this position is invalid.
                    }
                }
            }
        } else {                                                   //This means this ship is in left-right direction.
            ship_position[2] = 0;                                  //Tell the constructor his ship is in left-right direction.
            boolean get_pos = false;                               //The get_pos is to judge if we get the valid position.
            while (get_pos == false) {                             //Do the while loop until we get the valid position.
                ship_position[0] = random.nextInt(this.row);       //The row range is [0, this.row).
                ship_position[1] = random.nextInt(this.column - 2 * size + 2) + size - 1; //The column range is [0, this.column - size + 1).
                get_pos = true;                                    //Presume we get the valid position.
                for (int i = 0; i < size; ++i) {
                    if (this.board[ship_position[0]][ship_position[1] + i].isShip() == true) {
                        get_pos = false;                           //If any square in this ship is ship, this position is invalid.
                    }
                }
            }
        }
    }

    //Check if a shot success or not in the board scale.
    public int check(int row, int column) {
        if (this.board[row][column].hasShut() == true) {
            return 1;                                              //Has been shut.
        }
        try {
            this.board[row][column].setShut();                     //Set shut.
        } catch (Exception e) {
            e.printStackTrace();                                   //Get the exception.
        }
        for (int i = 0; i < this.battleships.length; ++i) {
            if (this.battleships[i].check(row, column) == true) {
                return 2;                                          //Shot successfully.
            }
        }
        return 3;                                                  //This shot is miss.
    }

    //Check if this player has won.
    public boolean checkWin() {
        for (int i = 0; i < this.battleships.length; ++i) {
            if (this.battleships[i].isSunk() == false) {
                return false;                                      //Only if one battleship is not sunk, this player has not won.
            }
        }
        return true;                                               //Since all the ships are sunk, this player wins.
    }

    //Transit this board to String.
    public String toString() {
        String res = "";
        for (int i = 0; i < this.row; ++i) {
            for (int j = 0; j < this.column; ++j) {
                res += this.board[i][j] + " ";
            }
            res += "\n";
        }
        return res;
    }
}