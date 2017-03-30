package mlt.fencepuzzle;

/**
 * Created by Laura Yu on 3/29/2017.
 */

public class Puzzle {

    private int size;
    private Fence[] fencePieces;
    private boolean correct;

    public Puzzle(){

    }

    public Puzzle(int[] correctPos, int[] start){
        fencePieces = new Fence[correctPos.length];
        createFences(correctPos, start);
        size = fencePieces.length;
        correct = checkIsCorrect();
    }

    private void createFences(int[] correct, int[] start) {
        assert correct.length == start.length;

        for (int i=0; i<correct.length; i++){
            fencePieces[i] = new Fence(correct[i], start[i]);
        }
    }

    private boolean checkIsCorrect(){
        for (Fence i : fencePieces){
            if (!i.isCorrect)
                return false;
        }
        return true;
    }

}
