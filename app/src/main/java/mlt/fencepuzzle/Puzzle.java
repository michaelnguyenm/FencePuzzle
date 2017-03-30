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

    public Puzzle(int[] pieces, int[] correctPos, int[] start){
        fencePieces = new Fence[correctPos.length];
        createFences(pieces, correctPos, start);
        size = fencePieces.length;
        correct = checkIsCorrect();
    }

    private void createFences(int[] pieces, int[] correct, int[] start) {
        assert correct.length == start.length;

        for (int i=0; i<correct.length; i++){

            switch(pieces[i]){
                case 0:
                    fencePieces[i] = new FenceBlank(correct[i], start[i]);
                    break;
                case 1:
                    fencePieces[i] = new FenceStraight(correct[i], start[i]);
                    break;
                case 2:
                    fencePieces[i] = new FenceCurved(correct[i], start[i]);
                    break;
                case 3:
                    fencePieces[i] = new FenceDouble(correct[i], start[i]);
                    break;
                default:
                    //TODO: add some error message
                    break;
            }
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
