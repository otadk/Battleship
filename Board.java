import java.util.Random;

public class Board {

    /*
     * Board is for a player
     */

    private Square[][] board;
    private int row;
    private int column;
    private Battleship[] battleships = new Battleship[6];

    /*
     * create a board with row and column
     */
    public Board(int row, int column) {

        this.row = row;
        this.column = column;
        this.board = new Square[row][column];

        //get squares
        for (int i = 0; i < this.row; ++i) {
            for (int j = 0; j < this.column; ++j) {
                this.board[i][j] = new Square(i, j);
            }
        }

        //small battle ship
        for (int i = 0; i < 3; ++i) {
            //this ship position is the left and the top of the ship, ship_pos[2] means direction
            int[] ship_pos = new int[3];
            //get the random postion
            this.randomShip(ship_pos, 1);

            //the squares of this battleship
            Square[] small_battle_ship_squares = new Square[1];
            small_battle_ship_squares[0] = this.board[ship_pos[0]][ship_pos[1]];

            //get the battleship object
            SmallBattleship small_battle_ships = null;
            try {
                small_battle_ships = new SmallBattleship(small_battle_ship_squares);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //set battleships to square
            this.board[ship_pos[0]][ship_pos[1]].setBattleship(small_battle_ships);

            //store this battleship into this class
            battleships[i] = small_battle_ships;
        }

        //medium battle ship, same as small battle ship
        for (int i = 0; i < 2; ++i) {

            int[] ship_pos = new int[3];
            this.randomShip(ship_pos, 2);

            Square[] medium_battle_ship_squares = new Square[2];
            medium_battle_ship_squares[0] = this.board[ship_pos[0]][ship_pos[1]];
            //different direction
            for (int j = 0; j < 2; ++j) {
                if (ship_pos[2] == 1) {
                    medium_battle_ship_squares[j] = this.board[ship_pos[0] + j][ship_pos[1]];
                } else {
                    medium_battle_ship_squares[j] = this.board[ship_pos[0]][ship_pos[1] + j];
                }
            }

            MediumBattleship medium_battle_ships = null;
            try {
                medium_battle_ships = new MediumBattleship(medium_battle_ship_squares);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            for (int j = 0; j < 2; ++j) {
                if (ship_pos[2] == 1) {
                    this.board[ship_pos[0] + j][ship_pos[1]].setBattleship(medium_battle_ships);
                } else {
                    this.board[ship_pos[0]][ship_pos[1] + j].setBattleship(medium_battle_ships);
                }
            }

            battleships[i + 3] = medium_battle_ships;
        }
        
        //large battle ship, same as small battle ship
        for (int i = 0; i < 1; ++i) {

            int[] ship_pos = new int[3];
            this.randomShip(ship_pos, 3);

            Square[] large_battle_ship_squares = new Square[3];
            for (int j = 0; j < 3; ++j) {
                if (ship_pos[2] == 1) {
                    large_battle_ship_squares[j] = this.board[ship_pos[0] + j][ship_pos[1]];
                } else {
                    large_battle_ship_squares[j] = this.board[ship_pos[0]][ship_pos[1] + j];
                }
            }

            LargeBattleship large_battle_ships = null;
            try {
                large_battle_ships = new LargeBattleship(large_battle_ship_squares);
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (int j = 0; j < 3; ++j) {
                if (ship_pos[2] == 1) {
                    this.board[ship_pos[0] + j][ship_pos[1]].setBattleship(large_battle_ships);
                } else {
                    this.board[ship_pos[0]][ship_pos[1] + j].setBattleship(large_battle_ships);
                }
            }

            battleships[i + 5] = large_battle_ships;
        }
    } 

    /*
     * output board as a String
     */
    public String toString() {
        String res = "";
        for (int i = 0; i < this.row; ++i) {
            for (int j = 0; j < this.column; ++j) {
                res += this.board[i][j].getPattern() + " ";
            }
            res += "\n";
        }
        return res;
    }

    /*
     * calculate a random position of a ship
     */
    public void randomShip(int[] ship_pos, int size) {

        //row is the up row. column is the left column.
        Random random = new Random();

        if (random.nextBoolean() == true) {
            //up and down towards
            boolean get_pos = false;
            while (get_pos == false) {

                //the random method
                ship_pos[0] = random.nextInt(this.row - 2 * size + 2) + size - 1;
                ship_pos[1] = random.nextInt(this.column);
                ship_pos[2] = 1;

                //in case of the position is not empty, use the loop to try
                get_pos = true;
                for (int i = 0; i < size; ++i) {
                    if (board[ship_pos[0] + i][ship_pos[1]].isShip() == true) {
                        get_pos = false;
                    }
                }
            }
        } else {
            //left and right towards
            boolean get_pos = false;
            while (get_pos == false) {

                ship_pos[1] = random.nextInt(this.column - 2 * size + 2) + size - 1;
                ship_pos[0] = random.nextInt(this.row);
                ship_pos[2] = 2;
                
                get_pos = true;
                for (int i = 0; i < size; ++i) {
                    if (board[ship_pos[0]][ship_pos[1] + i].isShip() == true) {
                        get_pos = false;
                    }
                }
            }
        }
    }

    /*
     * judge if a square in row and column has been hit
     */
    public int isHit(int row, int column) {

        if (this.board[row][column].isShut() == true) {
            return 1;//has benn hit
        }

        this.board[row][column].setShut();

        for (int i = 0; i < this.battleships.length; ++i) {
            if (this.battleships[i].isHit(row, column) == true) {
                return 2;//hit correctly
            }
        }

        return 3;//miss the hit
    }

    /*
     * judge if win
     */
    public boolean isWin() {
        for (int i = 0; i < this.battleships.length; ++i) {
            if (this.battleships[i].isSunk() == false) {
                return false;
            }
        }
        return true;
    }
}
