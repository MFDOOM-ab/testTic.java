import java.util.Scanner;

public class testTic {
//pre-reqs for code
public String[][] board;
static String p1 = "X";
static String p2 = "O";

public testTic()
{
//2d Array that makes the board 3x3
        board = new String[3][3];
}
public void boardprint()
{
        //Contents of the Board
        System.out.println();
        System.out.println("    0     1     2");
        System.out.println(" ------------------");
        for (int i = 0; i < board.length; i++) {
                for(int n = 0; n < board.length; n++) {
                        System.out.print(n);
                }
                System.out.print("| ");
                for (int j = 0; j < board[i].length; j++) {
                        if (board[i][j] == null) { //fills empty slots in array with -
                                System.out.print(" - ");
                        } else {
                                System.out.print(board[i][j]);
                        }

                        if (j<2) {
                                System.out.print(" | ");
                        } else {
                                System.out.println();
                                System.out.println(" ------------------");
                        }
                }

        }

        System.out.println();
}
//checking for a winner
public Boolean winnercheckmethod(String play) {
        //temp values
        int playInRow = 0;
        int player1 = 0;
        int player2 = 0;
        int[] boardwin = new int[board[0].length];

        for (int i = 0; i < board.length; i++) {
                playInRow = 0;
                for (int j = 0; j < board[i].length; j++) {
                        if (null == board[i][j]) {
                                continue;
                        }
                        if (board[i][j].equals(play)) {
                                playInRow++;
                                boardwin[j]++;
                                if (i == j) {
                                        player1++;
                                } else if (2 == i + j) {
                                        player2++;
                                }
                        }

                }
                if (playInRow == 3) {
                        return true;
                }
        }
        if (3 == player1 || 3 == player2) {
                return true;
        }
        for (int i = 0; i < boardwin.length; i++) {
                if (boardwin[i] == 3) {
                        return true;
                }
        }
        return false;
}
//making move
public void change_move(Scanner input, String play) {
        int row;
        int col;
        Boolean flag = false;
        while(!flag) {
                row = -1;
                col = -1;
                System.out.print("Enter the row index(0-2):");
                if (input.hasNextInt()) {
                        row = input.nextInt();
                }
                System.out.print("Enter the column index(0-2):");
                if (input.hasNextInt()) {
                        col = input.nextInt();
                }
                else {
                        input.nextLine();
                        System.out.println("Values must be between 0 and 2");
                        continue;
                }

                if ((row < 0) || (row > 2) || (col < 0) || (col > 2)) {
                        System.out.println("Both inputs must be integers between 0 and 2.");
                        continue;
                }

                else if (board[row][col] != null ) { //Checks if the spot is already filled
                        System.out.println("Pick another spot");
                        continue;
                }
                else {
                        board[row][col] = play;
                        return;
                }
        }
        return;
}

public static void main(String arg[]){
        testTic object = new testTic();
        Scanner input = new Scanner(System.in);

        int ch = 0;
        System.out.println("Welcome to play TicTacToe game!We have the following 3X3 empty board");
        object.boardprint();
        while (ch < 9) { //Moves can make maxiumum is 9
                object.change_move(input, object.p1);
                ch++;
                if (ch > 4) {
                        if (object.winnercheckmethod(p1)) {
                                System.out.println("Player 1 Wins!");
                                break;
                        }
                }
                object.boardprint();
                object.change_move(input, object.p2);
                ch++;
                if (ch > 4) {
                        if (object.winnercheckmethod(p2)) {
                                System.out.println("Player 2 Wins!");
                                break;
                        }
                }
                object.boardprint();
        }
}
}
