package mlt.fencepuzzle;
import java.lang.Math;
/**
 * Created by timothyho on 3/26/17.
 */

public class Board {
    private Fence[][] board;

    //This enum is for the answer key
    enum AnswerKeyFence{
        BLANK, STRAIGHT_VERTICAL, STRAIGHT_HORIZONTAL,
        CURVED_UP, CURVED_RIGHT, CURVED_DOWN, CURVED_LEFT,
        DOUBLE_LEFT, DOUBLE_RIGHT;
    }


    public Board(int[] answerKey){
        int keyLength = answerKey.length;
        double size = Math.sqrt(keyLength);
        int intSize = (int) size;
        board = new Fence[intSize][intSize];
        boardHelper(answerKey);
    }

    private void boardHelper(int[] answerKey){
        //variables to keep track of row and col index in board
        int fenceRow = 0;
        int fenceCol = 0;
		/*Fence object*/
        Fence f = null;
        int boardSize = (int) Math.sqrt(answerKey.length);
        for(int key = 0; key < answerKey.length; key++){
            //divide current key position by length of board to get current row
            fenceRow = key / boardSize;
            //mod current key position by length of board to get current column
            fenceCol = key % boardSize;
			/*Current type of fence that is needed*/
            int fenceType = answerKey[key];
			/*Determine which type of fence that is needed
			 * Try to change to switch
			 */
            if(fenceType == AnswerKeyFence.BLANK.ordinal()){
                f = new FenceBlank(fenceRow, fenceCol);
            } else if (fenceType == AnswerKeyFence.STRAIGHT_VERTICAL.ordinal()){
                f = new FenceStraight(Fence.Orientation.VERTICAL, fenceRow, fenceCol);
            } else if (fenceType == AnswerKeyFence.STRAIGHT_HORIZONTAL.ordinal()){
                f = new FenceStraight(Fence.Orientation.HORIZONTAL, fenceRow, fenceCol);
            } else if (fenceType == AnswerKeyFence.CURVED_UP.ordinal()){
                f = new FenceCurved(Fence.Orientation.UP, fenceRow, fenceCol);
            } else if (fenceType == AnswerKeyFence.CURVED_DOWN.ordinal()){
                f = new FenceCurved(Fence.Orientation.DOWN, fenceRow, fenceCol);
            } else if (fenceType == AnswerKeyFence.CURVED_LEFT.ordinal()){
                f = new FenceCurved(Fence.Orientation.LEFT, fenceRow, fenceCol);
            } else if (fenceType == AnswerKeyFence.CURVED_RIGHT.ordinal()){
                f = new FenceCurved(Fence.Orientation.RIGHT, fenceRow, fenceCol);
            } else if (fenceType == AnswerKeyFence.DOUBLE_LEFT.ordinal()){
                f = new FenceDouble(Fence.Orientation.LEFT, fenceRow, fenceCol);
            } else if (fenceType == AnswerKeyFence.DOUBLE_RIGHT.ordinal()){
                f = new FenceDouble(Fence.Orientation.RIGHT, fenceRow, fenceCol);
            } else {
                System.out.println("Uh oh");
            }
            if(f != null){
				/*At this point we have a piece and we can insert into the board*/
                board[fenceRow][fenceCol] = f;
            }

        }
    }

    public boolean changeOrientation(int row, int col){
        int fenceRow = board.length, fenceCol = board.length;
        if(fenceRow >= 0 && fenceCol >= 0 && fenceRow < row && fenceCol < col){
            if(board[row][col] != null){
                board[row][col].changeOrientation();
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean checkWin(){
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board.length; c++){
                if(!board[r][c].solutionChecker()){
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard(){
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board.length; c++){
                System.out.print(board[r][c].getTypeFence().name() + " " + board[r][c].getOrientation().name());
                if(c < board.length - 1){
                    System.out.print(" , ");
                }
            }
            System.out.println("");
        }
    }

    public void randomizeBoard(){
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board.length; c++){
                int randomize = (int) (Math.random() * 5);
                for(int i = 0; i < randomize; i++){
                    board[r][c].changeOrientation();
                }
            }
        }
    }
}
