public class Square {

    /*
     * square is a dot on board
     */

    private int row = 0;
    private int column = 0;
    private boolean is_ship = false;
    private boolean is_shut = false;
    private Battleship battleship = null;

    public Square (int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setShip() throws Exception {
        if (this.is_ship == true) {
            throw new Exception();
        }
        this.is_ship = true;
    }

    public void setShut() {
        this.is_shut = true;
    }

    public void setBattleship(Battleship battleship) {
        this.battleship = battleship;
    }

    public Battleship getBattleship() {
        return this.battleship;
    }

    /*
     * return the char of square
     */
    public char getPattern() {
        if (this.is_shut == true) {
            if (this.is_ship == true) {
                return 'x';
            }
            return 'o';
        } else {
            return '-';
        }
    }

    public boolean isShip() {
        return this.is_ship;
    }

    public boolean isShut() {
        return this.is_shut;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

}
