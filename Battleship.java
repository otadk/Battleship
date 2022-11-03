public class Battleship {
    
    /*
     * Battleship is the super class of SmallBattleship, MediumBattleship and LargeBattleship
     */

    protected boolean is_sunk = false;
    protected int size = 2;
    protected int health = 2;
    protected Square[] squares;

    //for sub class
    public Battleship(){}

    /*
     * input the size and squares
     * set these squares to ship
     */
    public Battleship(int size, Square[] squares) throws Exception  {

        this.size = size;
        this.is_sunk = false;
        this.health = size;
        this.squares = squares;

        if (this.size != this.squares.length) {
            throw new Exception();
        }

        //set squares
        for (int i = 0; i < this.squares.length; ++i) {
            try {
                this.squares[i].setShip();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * input row and column of the hit postion
     * judge if this square has been hit
     */
    public boolean isHit(int row, int column) {
        for (int i = 0; i < squares.length; ++i) {
            if (squares[i].getRow() == row && squares[i].getColumn() == column) {
                this.health--;
                if (this.health == 0) {
                    is_sunk = true;
                }
                return true;
            }
        }
        return false;
    }

    /*
     * return health
     */
    public int getHealth() {
        return this.health;
    }
    
    /*
     * judge if this ship is sunk
     */
    public boolean isSunk() {
        return this.is_sunk;
    }
}
