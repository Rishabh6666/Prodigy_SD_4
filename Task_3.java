import java.util.Scanner;

public class Task_3 {
    public static void main(String[] args) {
        int[][] board = new int[9][9];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Sudoku puzzle (use 0 for empty cells):");
        for (int i = 0; i < 9; i++) {
            int p = i + 1;
            System.out.println("Enter Number for " + p + " line:");
            for (int j = 0; j < 9; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        if (solve(board)) {
            System.out.println("Solved Sudoku:");
            display(board);
        } else {
            System.out.println("Cannot solve");
        }
        scanner.close();
    }

    static boolean solve(int[][] board) {
        int[] emptyPos = findEmptyPosition(board);
        if (emptyPos == null) {
            return true;
        }
        int row = emptyPos[0], col = emptyPos[1];

        for (int number = 1; number <= 9; number++) {
            if (isSafe(board, row, col, number)) {
                board[row][col] = number;
                if (solve(board)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    static int[] findEmptyPosition(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    static boolean isSafe(int[][] board, int row, int col, int num) {
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        int sqrt = (int) Math.sqrt(board.length);
        int rowStart = row - row % sqrt;
        int colStart = col - col % sqrt;
        for (int r = rowStart; r < rowStart + sqrt; r++) {
            for (int c = colStart; c < colStart + sqrt; c++) {
                if (board[r][c] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    static void display(int[][] board) {
        for (int[] row : board) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
