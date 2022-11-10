public class Player {

    private String name = "";                    //The name of this player.
    private int score = 0;                       //The score of this player.
    private Board board = null;                  //The board of this player.

    //A constructor of a Player object.
    public Player(String name, boolean extend) {
        this.name = name;                        //Set the name of this player.
        this.board = new Board(10, 10, extend);      //Set the board of this player.
    }
    
    //Do what a player should do in his turn.
    public int takeTurn(int row, int column) {
        int res = this.board.check(row, column); //Get the result of checking if a shot success or not in the board scale.
        if (res == 1) {
            return 1;                            //This position has been shut.
        } else if (res == 2) {
            if (this.board.checkWin() == true) {
                return 2;                        //This player wins.
            }
            this.score++;                        //If this player shots successfully, his score should increase one point.
            return 3;                            //This player shots correctly but does not win.
        }
        return 4;                                //This player misses the shot.
    }

    //Transit this board to String.
    public String toString() {
        return "name :: " + this.name + "\nscore :: " + this.score + "\n" + this.board;
    }
}
