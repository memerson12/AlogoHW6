import java.util.Arrays;

public class nQueens {
    public static void main(String[] args) {
        int n = 14;
        int[] board = new int[n];
//        System.out.println(isLegalPosition(board, board.length));
        int temp = 0;
        while(board[n-1] == 0) {
//            System.out.println("-----------NEW CALL-----------");
            board = nextLegalPosition(board, n);
            temp++;
//            if(temp>10)break;
        }
            System.out.println(Arrays.toString(board));
    }

    static boolean isLegalPosition(int[] board, int n) {
        int newPositionIndex = n - 1;
        for (int i = 0; i < n; i++) {
            if (board[i] == 0) {
                newPositionIndex = i-1;
                break;
            }
        }
        if(board[0] == 0) return true;
//        System.out.println("newPositionIndex = " + newPositionIndex);
        int newPosition = board[newPositionIndex];
        for (int i = 0; i < newPositionIndex; i++) {
            int diff = newPositionIndex - i;
//            System.out.println("i: " + i + " board[i]: " + board[i]);
            if ((board[i] - diff == newPosition
                    || board[i] + diff == newPosition
                    || board[i] == newPosition)) {
//                System.out.println(board[i] - diff == newPosition);
//                System.out.println(board[i] + diff == newPosition);
//                System.out.println(board[i] == newPosition);
                return false;
            }
        }
        return true;
    }

    static int[] nextLegalPosition(int[] board, int n) {
        int newPositionIndex = n - 1;
        for (int i = 0; i < n; i++) {
            if (board[i] == 0) {
                newPositionIndex = i-1;
                break;
            }
        }
        if (!isLegalPosition(board, n)) {
//            System.out.println("a " + newPositionIndex);
            if (board[newPositionIndex] == n - 1) return new int[n];
//            System.out.println("b");
            board[newPositionIndex]++;
            nextLegalPosition(board, n);
        } else if (newPositionIndex != n - 1) {
//            System.out.println(1);
            newPositionIndex++;
            board[newPositionIndex] = 1;
            while (!isLegalPosition(board, n)) {
//                System.out.println(Arrays.toString(board) + " was not legal");
                if (board[newPositionIndex] >= n) {
                    do {
                        board[newPositionIndex] = 0;
                        newPositionIndex--;
                    } while(newPositionIndex >= 0 && board[newPositionIndex] >= n);
                }
                board[newPositionIndex]++;
//                System.out.println("Checking if " + Arrays.toString(board) + " is legal");
            }
            return board;
        } else {
            System.out.println("here");
            while (!isLegalPosition(board, n) && newPositionIndex >= 0) {
                if (board[newPositionIndex] >= n - 1) {
                    board[newPositionIndex] = 0;
                    newPositionIndex--;
                }
                board[newPositionIndex]++;
            }
        }
        return board;
    }
}
