package mlt.fencepuzzle;

/**
 * Created by Laura Yu on 3/29/2017.
 */

public class Level {
    //read from external file to generate a puzzle given a level id

    //later on we can have this auto-generate puzzles

    private Puzzle puzzle;
    public Level(){
        //I actually am not sure what to do here

        //read external file and call createPuzzle
    }

    public Level(int levelID){
        //read external file and call createPuzzle
    }

    public Puzzle createPuzzle(int[] pieces, int[] correct, int[] start){
        return new Puzzle(pieces, correct, start);
    }
}
