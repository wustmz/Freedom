package org.mz.algorithms.frequent.slidingPuzzle;

import java.util.Arrays;

public class Node {
    int[][] board;
    String boardString;
    int zero_r;
    int zero_c;
    int depth;

    Node(int[][] B, int r, int c, int d) {
        board = B;
        boardString = Arrays.deepToString(board);
        zero_r = r;
        zero_c = c;
        depth = d;
    }
}
