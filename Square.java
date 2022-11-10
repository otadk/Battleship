public class Square {

    private int row = 0;                  //An integer denoting the row position, inclusive of 0.
    private int column = 0;               //An integer denoting the column position, inclusive of 0.
    private boolean is_ship = false;      //An attribute to indicate whether the square has a ship in it. Default false because we have not set it.
    private Battleship battleship = null; //A reference to a battleship if one is currently on the Square. Default false because we have not set it.
    private boolean has_shut = false;     //An attribute to indicate whether the player has fired a shot at the square. Default false because we have not set it.

    //A constructor of a Square object.
    public Square (int row, int column) {
        this.row = row;                   //Set row.
        this.column = column;             //Set column.
    }

    //Row setter.
    public void setRow(int row) {
        this.row = row;                   //Set row.
    }

    //Column setter.
    public void setColumn(int column) {
        this.column = column;             //Set column.
    }

    //A method to set this square as a ship square.
    public void setShip() throws Exception {
        if (this.is_ship == true) {
            throw new Exception();        //If this square is already a ship square, throw an exception.
        }
        this.is_ship = true;              //Set this square as a ship square
    }

    //A method to set a battleship to this square.
    public void setBattleship(Battleship battleship) throws Exception {
        if (battleship == null) {
            throw new Exception();        //If this square alreay has a battleship, throw an exception.
        }
        this.battleship = battleship;     //Set this battleship to this square.
    }


    //a method to set this square has been shut.
    public void setShut() throws Exception {
        if (this.has_shut == true) {
            throw new Exception();        //If this square has already been shut, throw an exception.
        }
        this.has_shut = true;             //Set this square as a ship square.
    }

    //Row getter.
    public int getRow() {
        return this.row;                  //Get row.
    }

    //Column getter.
    public int getColumn() {
        return this.column;               //Get column.
    }

    //Battleship getter.
    public Battleship getBattleship() throws Exception {
        if (this.battleship == null) {
            throw new Exception();        //If this square do not refer to a battleship, throw an exception.
        }

        return this.battleship;           //Get battleship.
    }

    //Get is_ship.
    public boolean isShip() {
        return this.is_ship;              //Return if this is a ship.
    }

    //Get has_shut
    public boolean hasShut() {
        return this.has_shut;             //Return if this has been shut.
    }

    //Transit this square to String
    public String toString() {
        if (this.has_shut == true) {
            if (this.is_ship == true) {
                return "X";               //If this square has been shut and is a ship, it should be X.
            }
            return "O";                   //If this square has been shut and is not a ship, it should be O.
        } else {
            if (this.is_ship == true) return "S";
            return "-";                   //If this square has not been shut, it should be -.
        }
    }
}
