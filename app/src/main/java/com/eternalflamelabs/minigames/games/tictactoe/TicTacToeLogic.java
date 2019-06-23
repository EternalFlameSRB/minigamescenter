package com.eternalflamelabs.minigames.games.tictactoe;

import android.widget.ImageView;

class TicTacToeLogic {
    private static ImageView[] sBlocks;
    static String sWinner;
    static int[] sSet = new int[3];
    static final int CIRCLE = 0;
    static final int CROSS = 1;

    private static boolean areSameInSet(int first, int second, int third) {
        boolean value = sBlocks[first - 1].getId() == sBlocks[second - 1].getId() && sBlocks[second - 1].getId() == sBlocks[third - 1].getId();
        if (value) {
            if (sBlocks[first - 1].getId() == CIRCLE)
                sWinner = "CIRCLE";
            else
                sWinner = "CROSS";
            sSet[0] = first;
            sSet[1] = second;
            sSet[2] = third;
        }
        return value;
    }

    static boolean isCompleted(int position, ImageView[] blocks, int type) {
        TicTacToeLogic.sBlocks = blocks;
        boolean isComplete = false;
        if (type == 9) {
            switch (position) {
                case 1:
                    isComplete = areSameInSet(1, 2, 3) ||
                            areSameInSet(1, 4, 7) ||
                            areSameInSet(1, 5, 9);
                    break;
                case 2:
                    isComplete = areSameInSet(1, 2, 3) ||
                            areSameInSet(2, 5, 8);
                    break;
                case 3:
                    isComplete = areSameInSet(1, 2, 3) ||
                            areSameInSet(3, 6, 9) ||
                            areSameInSet(3, 5, 7);
                    break;
                case 4:
                    isComplete = areSameInSet(4, 5, 6) ||
                            areSameInSet(1, 4, 7);
                    break;
                case 5:
                    isComplete = areSameInSet(4, 5, 6) ||
                            areSameInSet(2, 5, 8) ||
                            areSameInSet(1, 5, 9) ||
                            areSameInSet(3, 5, 7);
                    break;
                case 6:
                    isComplete = areSameInSet(4, 5, 6) ||
                            areSameInSet(3, 6, 9);
                    break;
                case 7:
                    isComplete = areSameInSet(7, 8, 9) ||
                            areSameInSet(1, 4, 7) ||
                            areSameInSet(3, 5, 7);
                    break;
                case 8:
                    isComplete = areSameInSet(7, 8, 9) ||
                            areSameInSet(2, 5, 8);
                    break;
                case 9:
                    isComplete = areSameInSet(7, 8, 9) ||
                            areSameInSet(3, 6, 9) ||
                            areSameInSet(1, 5, 9);
                    break;
            }
        } else if (type == 16) {
            switch (position) {
                case 1:
                    isComplete = areSameInSet(1, 2, 3) ||
                            areSameInSet(1, 6, 11) ||
                            areSameInSet(1, 5, 9);
                    break;
                case 2:
                    isComplete = areSameInSet(1, 2, 3) ||
                            areSameInSet(2, 3, 4) ||
                            areSameInSet(2, 6, 10) ||
                            areSameInSet(2, 7, 12);
                    break;
                case 3:
                    isComplete = areSameInSet(1, 2, 3) ||
                            areSameInSet(2, 3, 4) ||
                            areSameInSet(3, 6, 9) ||
                            areSameInSet(3, 7, 11);
                    break;
                case 4:
                    isComplete = areSameInSet(2, 3, 4) ||
                            areSameInSet(4, 7, 10) ||
                            areSameInSet(4, 8, 12);
                    break;
                case 5:
                    isComplete = areSameInSet(1, 5, 9) ||
                            areSameInSet(5, 6, 7) ||
                            areSameInSet(5, 9, 13) ||
                            areSameInSet(5, 10, 15);
                    break;
                case 6:
                    isComplete = areSameInSet(1, 6, 11) ||
                            areSameInSet(2, 6, 10) ||
                            areSameInSet(3, 6, 9) ||
                            areSameInSet(6, 7, 9) ||
                            areSameInSet(6, 11, 16) ||
                            areSameInSet(6, 10, 14) ||
                            areSameInSet(5, 6, 7);
                    break;
                case 7:
                    isComplete = areSameInSet(2, 7, 12) ||
                            areSameInSet(3, 7, 11) ||
                            areSameInSet(4, 7, 10) ||
                            areSameInSet(6, 7, 8) ||
                            areSameInSet(7, 10, 13) ||
                            areSameInSet(7, 11, 15) ||
                            areSameInSet(5, 6, 7);
                    break;
                case 8:
                    isComplete = areSameInSet(6, 7, 8) ||
                            areSameInSet(8, 11, 4) ||
                            areSameInSet(4, 8, 12) ||
                            areSameInSet(8, 12, 16);
                    break;
                case 9:
                    isComplete = areSameInSet(1, 5, 9) ||
                            areSameInSet(5, 9, 13) ||
                            areSameInSet(3, 6, 9) ||
                            areSameInSet(9, 10, 11);
                    break;
                case 10:
                    isComplete = areSameInSet(5, 10, 15) ||
                            areSameInSet(6, 10, 14) ||
                            areSameInSet(7, 10, 13) ||
                            areSameInSet(9, 10, 11) ||
                            areSameInSet(4, 7, 10) ||
                            areSameInSet(10, 11, 12) ||
                            areSameInSet(2, 6, 10);
                    break;
                case 11:
                    isComplete = areSameInSet(1, 6, 11) ||
                            areSameInSet(3, 7, 11) ||
                            areSameInSet(9, 10, 11) ||
                            areSameInSet(6, 11, 16) ||
                            areSameInSet(7, 11, 15) ||
                            areSameInSet(9, 11, 14) ||
                            areSameInSet(10, 11, 12);
                    break;
                case 12:
                    isComplete = areSameInSet(4, 8, 12) ||
                            areSameInSet(2, 7, 12) ||
                            areSameInSet(10, 11, 12) ||
                            areSameInSet(8, 12, 16);
                    break;
                case 13:
                    isComplete = areSameInSet(5, 9, 13) ||
                            areSameInSet(7, 10, 13) ||
                            areSameInSet(13, 14, 15);
                    break;
                case 14:
                    isComplete = areSameInSet(6, 10, 14) ||
                            areSameInSet(8, 11, 14) ||
                            areSameInSet(13, 14, 15) ||
                            areSameInSet(14, 15, 16);
                    break;
                case 15:
                    isComplete = areSameInSet(13, 14, 15) ||
                            areSameInSet(5, 10, 15) ||
                            areSameInSet(7, 11, 15) ||
                            areSameInSet(14, 15, 16);
                    break;
                case 16:
                    isComplete = areSameInSet(6, 11, 16) ||
                            areSameInSet(8, 12, 16) ||
                            areSameInSet(14, 15, 16);
                    break;
            }
        } else if (type == 25) {

        }

        return isComplete;
    }
}