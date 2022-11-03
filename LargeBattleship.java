public class LargeBattleship extends Battleship {
    public LargeBattleship(Square[] squares) throws Exception  {
        
        //the size of large battle ship is 3, others are same as battleship constructor
        this.size = 3;
        this.health = 3;
        this.squares = squares;
        this.is_sunk = false;

        if (this.squares.length != 3) {
            throw new Exception();
        }

        for (int i = 0; i < this.squares.length; ++i) {
            try {
                this.squares[i].setShip();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
