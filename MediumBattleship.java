public class MediumBattleship extends Battleship {
    public MediumBattleship(Square[] squares) throws Exception  {

        //the size of medium battle ship is 2, others are same as battleship constructor
        this.size = 2;
        this.health = 2;
        this.squares = squares;
        this.is_sunk = false;

        if (this.squares.length != 2) {
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
