import java.util.Scanner;

/*
 * author guid: 2758639g
 * author name: Jingfei Guo
 * This is the main entrance!
 */

public class Do {
    public static void main(String[] args) {

        Scanner system_scanner = new Scanner(System.in);
        
        //get the name of players
        System.out.println(PINK + "WELCOME!!!" + BLUE + "Please input the name of Player1" + ENDCOLOR);
        String player1_name = system_scanner.nextLine();
        if (player1_name.length() == 0) {//set a default name
            player1_name = "player1";
        }
        Player player1 = new Player(player1_name);

        System.out.println(PINK + "WELCOME!!!" + BLUE + "Please input the name of Player2" + ENDCOLOR);
        String player2_name = system_scanner.nextLine();
        if (player2_name.length() == 0) {
            player2_name = "player2";
        }
        Player player2 = new Player(player2_name);

        //set a hint String for getting and putting information of the last turn
        String hint = "First turn!";

        //set turns, odd for player1, even for player2
        for (int turns = 1; turns < 1e7; ++turns) {
            
            System.out.print("\033[H\033[2J");  
            System.out.flush();//flush the terminal

            //judge turns, odd for player1, even for player2
            if ((turns & 1) == 1) {
                //turns for player1
                System.out.print(BLUE + hint + "\n" + PINK + player1 + ENDCOLOR);
            } else {
                //turns for player2
                System.out.print(BLUE + hint + "\n" + PINK + player2 + ENDCOLOR);
            }

            //get the input location
            Scanner line_scanner = new Scanner(system_scanner.nextLine());
            int row = 0;
            int column = 0;
            if (line_scanner.hasNextInt()) {
                row = line_scanner.nextInt();
                if (line_scanner.hasNextInt()) {
                    column = line_scanner.nextInt();
                    if (line_scanner.hasNextInt()) {
                        hint = RED + "Wrong input!!! Please input two integers in one line!" + ENDCOLOR;
                        continue;
                    }
                } else {
                    hint = RED + "Wrong input!!! Please input two integers in one line!" + ENDCOLOR;
                    continue;
                }
            } else {
                hint = RED + "Wrong input!!! Please input two integers in one line!" + ENDCOLOR;
                continue;
            }
            line_scanner.close();

            //make sure row and column are in range
            if (row < 0 || column < 0 || row >= 10 || column >= 10) {
                hint = "input out of the range!!!!";
                continue;
            }

            if ((turns & 1) == 1) {
                //turns for player1
                int res = player1.takeTurn(row, column);
                if (res == 1) {
                    hint = "this square has been hit!";
                } else if (res == 2) {
                    System.out.println(RED + "You win!!!!!!!" + ENDCOLOR);
                    break;
                } else if (res == 3) {
                    hint = "hit successfully!";
                } else if (res == 4) {
                    hint = "hit unsuccessfully!";
                }
            } else {
                //turns for player2
                int res = player2.takeTurn(row, column);
                if (res == 1) {
                    hint = "this square has been hit!";
                } else if (res == 2) {
                    System.out.println(RED + "You win!!!!!!!The number of turns is " + BLUE + turns + ENDCOLOR);
                    break;
                } else if (res == 3) {
                    hint = "hit successfully!";
                } else if (res == 4) {
                    hint = "hit unsuccessfully!";
                }
            }
        }

        system_scanner.close();
    }
    
    /*
	 * @these are to color the terminal
	 */
    public static String BLUE = "\033[34;1m";
	public static String PINK = "\033[35;1m";
	public static String RED = "\033[31;1m";
	public static String ENDCOLOR = "\033[0m";
}
