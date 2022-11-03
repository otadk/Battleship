public class Player {

    /*
     * Player contain all content of a player in this game
     */

    private String name = "";
    private int score = 0;
    private Board board = new Board(10, 10);//the default size is 10x10

    public Player(String name) {
        this.name = name;
    }

    /*
     * input the hit postion and return situation
     */
    public int takeTurn(int row, int column) {

        int res = this.board.isHit(row, column);
        if (res == 1) {
            return 1;//has been hit
        } else if (res == 2) {
            if (this.board.isWin() == true) {
                return 2;//win
            }
            this.score++;
            return 3;//hit correctly but do not win
        }
        return 4;//miss the hit
    }

    /*
     * output player as a String
     */
    public String toString() {
        return "name :: " + this.name + "\nscore :: " + this.score + "\n" + this.board;
    }
}
