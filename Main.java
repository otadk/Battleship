import java.util.Scanner;

// Author guid: 2758639g
// Author name: Jingfei Guo

public class Main {
    public static void main(String[] args) {
        Scanner system_scanner = new Scanner(System.in);                          //Declare the system.in scanner.
        System.out.println("Please input the name of Player1.");                //Get the name of player 1.
        String player1_name = system_scanner.nextLine();                          //Scan to get the name.
        if (player1_name.length() == 0) {
            player1_name = "Player1";                                             //Set a default name.
        }
        System.out.println("Input any value to start an extend mod.");          //Get the game mod (extend or not).
        Player player1 = null;
        if (system_scanner.nextLine().length() == 0) {
            player1 = new Player(player1_name, false);                    //Declare the player 1.
        } else {
            player1 = new Player(player1_name, true);                      //Declare the player 1.
        }
        System.out.println("Please input the name of Player2.");                //Get the name of player 2.
        String player2_name = system_scanner.nextLine();                          //Scan to get the name.
        if (player2_name.length() == 0) {
            player2_name = "Player2";                                             //Set a default name.
        }
        System.out.println("Input any value to start an extend mod.");          //Get the game mod (extend or not).
        Player player2 = null;
        if (system_scanner.nextLine().length() == 0) {
            player2 = new Player(player1_name, false);                    //Declare the player 2.
        } else {
            player2 = new Player(player1_name, true);                     //Declare the player 2.
        }
        String hint = "First turn!";                                              //Set a hint String for getting and putting information of the last turn.
        for (int turns = 1; turns < 1e7; ++turns) {                               //Set turns, odd for player1, even for player2.
            System.out.print("\033[H\033[2J");  
            System.out.flush();                                                   //Flush the terminal.
            if ((turns & 1) == 1) {                                               //Judge turns, odd for player1, even for player2.
                System.out.print(hint + "\n" + player1);                          //Player1 turn.
            } else {
                System.out.print(hint + "\n" + player2);                          //Player2 turn.
            }
            Scanner line_scanner = new Scanner(system_scanner.nextLine());        //Scan the input line.
            int row = 0, column = 0;                                              //Declare row and column to store the input location.
            if (line_scanner.hasNextInt()) {
                row = line_scanner.nextInt();                                     //Get the row.
                if (line_scanner.hasNextInt()) {
                    column = line_scanner.nextInt();                              //Get the column.
                    if (line_scanner.hasNextInt()) {
                        hint = "Wrong input!!! Please input two integers in one line!";
                        continue;                                                 //This line input has three input int values, so it is wrong.
                    }
                } else {
                    hint = "Wrong input!!! Please input two integers in one line!";
                    continue;                                                     //This line input has only one input int value, so it is wrong.
                }
            } else {
                hint = "Wrong input!!! Please input two integers in one line!";
                continue;                                                         //This line input does not have input int value, so it is wrong.
            }
            line_scanner.close();                                                 //Close the line scanner.
            if (row < 0 || column < 0 || row >= 10 || column >= 10) {
                hint = "Input out of the range!!!!";                              //Make sure the input row and column is in range.
                continue;
            }
            if ((turns & 1) == 1) {
                int res = player1.takeTurn(row, column);                          //Turns for player1.
                if (res == 1) {
                    hint = "This square has been shut!";                          //The return value from takeTurn is 1, which means that this square has been shut.
                } else if (res == 2) {
                    System.out.println("You win!!!!!!!");                      //The return value from takeTurn is 2, which means that this player wins.
                    break;
                } else if (res == 3) {
                    hint = "Shot successfully!";                                   //The return value from takeTurn is 3, which means that this shot is successful.
                } else if (res == 4) {
                    hint = "Shot unsuccessfully!";                                 //The return value from takeTurn is 3, which means that this shot is unsuccessful.
                }
            } else {
                int res = player2.takeTurn(row, column);                          //Turns for player2.
                if (res == 1) {
                    hint = "This square has been hit!";                          //The return value from takeTurn is 1, which means that this square has been shut.
                } else if (res == 2) {
                    System.out.println("You win!!!!!!!");                      //The return value from takeTurn is 2, which means that this player wins.
                    break;
                } else if (res == 3) {
                    hint = "Shot successfully!";                                   //The return value from takeTurn is 3, which means that this shot is successful.
                } else if (res == 4) {
                    hint = "Shot unsuccessfully!";                                 //The return value from takeTurn is 3, which means that this shot is unsuccessful.
                }
            }
        }
        system_scanner.close();                                                   //Close the system.in scanner.
    }
}
