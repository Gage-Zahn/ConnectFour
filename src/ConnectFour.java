//comment for Lab 6 about git 

/*
I, Gage Zahn, confirm that the work for the
following Code: Lab 5 for COP3502C named "ConnectFour"
was solely undertaken by myself and that no help
was provided from other sources as those allowed.
 */
import java.util.Scanner;

public class ConnectFour {

    //Displays the current state of the board
    public static void printBoard(char[][] array){
        for(int i = array.length-1; i >= 0; i--){
            for(int j = 0; j < array[0].length; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Sets all positions in the board to empty ('-')
    public static void initializeBoard(char[][] array){
        for(int i = array.length-1; i >= 0; i--){
            for(int j = 0; j < array[0].length; j++){
                    array[i][j]='-';
            }
        }
    }

    //Inserts a Chip into the board at the specified column
    public static int insertChip(char[][] array, int col, char chipType){
        for(int i = 0; i < array.length; i++){
            if (array[i][col] == '-') {
                array[i][col] = chipType;
                return i;
            }
        }
        return -1;
    }

    //Checks if the chip at array[row][col] won the game
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType){
        int numSame = 1;

        //check row
        for(int i = 1; i < array.length; i++){
            if (array[i - 1][col] != array[i][col] || array[i][col] == '-' || array[i-1][col] == '-') {
                numSame = 0;
            }
            numSame++;
            if (numSame == 4) {
                return true;
            }
        }

        //check column
        numSame = 1;
        for(int i = 1; i < array[0].length; i++){
            if (array[row][i - 1] != array[row][i] ||array[row][i] == '-' || array[row][i-1] == '-') {
                numSame = 0;
            }
            numSame++;
            if (numSame == 4){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        //Initial set up
        Scanner scnr = new Scanner(System.in);
        System.out.print("What would you like the height of the board to be? ");
        int boardHeight = scnr.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        int boardLength = scnr.nextInt();
        char[][] board = new char[boardHeight][boardLength];
        initializeBoard(board);
        printBoard(board);
        System.out.println();
        System.out.println("Player 1: x");
        System.out.println("Player 2: o");
        System.out.println();

        //Game variable declarations
        int row;
        int col;
        char chipType;
        boolean turn = false;
        int maxTurns = boardHeight*boardLength;
        int numTurns = 0;

        //Main game loop
        do{
            turn = !turn;
            if (turn){
                System.out.print("Player 1: Which column would you like to choose? ");
                chipType = 'x';
            }
            else{
                System.out.print("Player 2: Which column would you like to choose? ");
                chipType = 'o';
            }
            col = scnr.nextInt();
            row = insertChip(board, col,chipType);
            printBoard(board);
            System.out.println();
            numTurns++;
        }
        while (!checkIfWinner(board, col, row, chipType) && numTurns != maxTurns);

        //Displays result of game
        if(numTurns != maxTurns) {
            if (turn) {
                System.out.println("Player 1 won the game!");
            } else {
                System.out.println("Player 2 won the game!");
            }
        }
        else
            System.out.println("Draw. Nobody wins.");
    }
}