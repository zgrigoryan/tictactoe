import java.util.Scanner;

public class Main {
    static char[][] table = new char[3][3];
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // fill the table with underscores to indicate empty cells
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = '_';
            }
        }

        printGrid(table);

        String state = "Game not finished";
        while (state.equals("Game not finished")) {
            table = putX();
            printGrid(table);
            state = checkGameState(table);
            if (!state.equals("Game not finished")) break;

            table = putO();
            printGrid(table);
            state = checkGameState(table);
        }
        System.out.println(state);
    }

    public static char[][] putX() {
        boolean correctInput = false;
        while (!correctInput) {
            String s1 = sc.next();
            String s2 = sc.next();
            int coordinate1, coordinate2;

            try {
                coordinate1 = Integer.parseInt(s1);
                coordinate2 = Integer.parseInt(s2);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (coordinate1 < 1 || coordinate1 > 3 || coordinate2 < 1 || coordinate2 > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (table[coordinate1 - 1][coordinate2 - 1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                table[coordinate1 - 1][coordinate2 - 1] = 'X';
                correctInput = true;
            }
        }
        return table;
    }

    public static char[][] putO() {
        boolean correctInput = false;
        while (!correctInput) {
            String s1 = sc.next();
            String s2 = sc.next();
            int coordinate1, coordinate2;

            try {
                coordinate1 = Integer.parseInt(s1);
                coordinate2 = Integer.parseInt(s2);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (coordinate1 < 1 || coordinate1 > 3 || coordinate2 < 1 || coordinate2 > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (table[coordinate1 - 1][coordinate2 - 1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                table[coordinate1 - 1][coordinate2 - 1] = 'O';
                correctInput = true;
            }
        }
        return table;
    }

    public static void printGrid(char[][] grid) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static String checkGameState(char[][] grid) {
        String xIsWinning = "XXX";
        String oIsWinning = "OOO";

        // checking rows and columns
        for (int i = 0; i < 3; i++) {
            String row = String.valueOf(grid[i][0]) + grid[i][1] + grid[i][2];
            if (row.equals(xIsWinning)) return "X wins";
            if (row.equals(oIsWinning)) return "O wins";

            String column = String.valueOf(grid[0][i]) + grid[1][i] + grid[2][i];
            if (column.equals(xIsWinning)) return "X wins";
            if (column.equals(oIsWinning)) return "O wins";
        }

        // checking diagonals
        String diag1 = String.valueOf(grid[0][0]) + grid[1][1] + grid[2][2];
        String diag2 = String.valueOf(grid[0][2]) + grid[1][1] + grid[2][0];
        if (diag1.equals(xIsWinning) || diag2.equals(xIsWinning)) return "X wins";
        if (diag1.equals(oIsWinning) || diag2.equals(oIsWinning)) return "O wins";

        //  checking if there are empty cells
        boolean hasEmptyCell = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == '_') {
                    hasEmptyCell = true;
                    break;
                }
            }
        }

        // if there are no empty cells and no winner, it's a draw
        return hasEmptyCell ? "Game not finished" : "Draw";
    }
}
//