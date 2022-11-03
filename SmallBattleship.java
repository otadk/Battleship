public class SmallBattleship extends Battleship {
    public SmallBattleship(Square[] squares) throws Exception  {

        //the size of small battle ship is 1, others are same as battleship constructor
        this.size = 1;
        this.health = 1;
        this.squares = squares;
        this.is_sunk = false;

        if (this.squares.length != 1) {
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
