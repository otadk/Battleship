public class Battleship {

    protected boolean is_sunk = false;            //Whether it is sunk.
    protected int health = 2;                     //The remaining health (how many more hits it can take before sinking)
    protected int size = 2;                       //The size of the ship.
    protected Square[] squares = null;            //The sqaures this battleship takes.

    //A constructor of a Battleship object.
    public Battleship(Square[] squares) {

        this.size = squares.length;               //Set the size of the ship.
        this.is_sunk = false;                     //Set this ship is not sunk.
        this.health = this.size;                  //Set the health of the ship.
        this.squares = squares;                   //Set the squares of the ship.

        for (int i = 0; i < squares.length; ++i) {
            try {
                squares[i].setShip();              //Set the square as a ship square.
            } catch (Exception e) {
                e.printStackTrace();               //Get the exception.
            }
            try {
                squares[i].setBattleship(this);    //Set the square to refer this battleship.
            } catch (Exception e) {
                e.printStackTrace();               //Get the exception.
            }
        }
    }

    //Get the is_sunt.
    public boolean isSunk() {
        return this.is_sunk;                       //Get the is_sunk.
    }

    //Check if a shot success or not in the battleship scale.
    public boolean check(int row, int column) {
        if (this.isSunk() == true) {
            return false;                          //This battleship is already sunk.
        }
        for (int i = 0; i < this.size; ++i) {
            if (this.squares[i].getRow() == row && this.squares[i].getColumn() == column) {
                if (this.squares[i].isShip() == true && this.squares[i].hasShut() == false) {
                    try {
                        this.squares[i].setShut(); //Set that the square has been shut.
                    } catch (Exception e) {
                        e.printStackTrace();       //Get the exception.
                    }
                    this.health--;                 //Reduce the health.
                    if (this.health == 0) {
                        this.is_sunk = true;       //If the health is 0, it is sunk.
                    }
                    return true;                   //Shot successfully, and return true.
                }
            }
        }
        return false;                              //Shot unsuccessfully, and return false.
    }
}
