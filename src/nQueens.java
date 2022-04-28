import java.util.Arrays;

public class nQueens {
    public static void main(String[] args) {
        int n = 5;
        int[] board = new int[n];
        board[0] = 1;
        while (n < 15) {
            System.out.println("Solution for n = " + n + ": ");
            while (board[n - 1] == 0) {
                board = nextLegalPosition(board, n);
            }
            System.out.println(Arrays.toString(board));
            n++;
            board = new int[n];
            System.out.println("--------------------");
        }

        n = 4;
        int count = 0;
        while (n < 15) {
            while (board[n - 1] == 0 && !Arrays.equals(board, new int[n])) {
                board = nextLegalPosition(board, n);
            }
            if (Arrays.equals(board, new int[n])) {
                System.out.println("There are " + count + " solutions to the " + n + "-Queens problems.");
                count = 0;
                n++;
                board = new int[n];
                board[0] = 1;
                continue;
            }
            count++;
            board = nextLegalPosition(board, n);
        }
    }

    static boolean isLegalPosition(int[] board, int n) {
        int newPositionIndex = n - 1;
        for (int i = 0; i < n; i++) {
            if (board[i] == 0) {
                newPositionIndex = i - 1;
                break;
            }
        }
        if (board[0] == 0) return true;
        int newPosition = board[newPositionIndex];
        for (int i = 0; i < newPositionIndex; i++) {
            int diff = newPositionIndex - i;
            if ((board[i] - diff == newPosition
                    || board[i] + diff == newPosition
                    || board[i] == newPosition)) {
                return false;
            }
        }
        return true;
    }

    static int[] nextLegalPosition(int[] board, int n) {
        int newPositionIndex = n - 1;
        for (int i = 0; i < n; i++) {
            if (board[i] == 0) {
                newPositionIndex = i - 1;
                break;
            }
        }
        if (!isLegalPosition(board, n)) {
            if (board[newPositionIndex] == n - 1) return new int[n];
            board[newPositionIndex]++;
            nextLegalPosition(board, n);
        } else {
            if (newPositionIndex != n - 1) {
                newPositionIndex++;
                board[newPositionIndex] = 1;
            } else if (board[newPositionIndex] >= n) {
                board[newPositionIndex] = 0;
                newPositionIndex--;
                board[newPositionIndex]++;
            } else {
                board[newPositionIndex]++;
            }
            while (!isLegalPosition(board, n)) {
                if (board[newPositionIndex] >= n) {
                    do {
                        board[newPositionIndex] = 0;
                        newPositionIndex--;
                    } while (newPositionIndex >= 0 && board[newPositionIndex] >= n);
                }
                if (newPositionIndex < 0) return new int[n];
                board[newPositionIndex]++;
            }
            return board;
        }
        return board;
    }
}
