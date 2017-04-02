package mlt.fencepuzzle;

import java.util.ArrayList;

/**
 * Created by Laura Yu on 3/29/2017.
 */

public class Level {
    //read from external file to generate a puzzle given a level id

    //later on we can have this auto-generate puzzles

    public Puzzle puzzle;
    private int levelID;
    private int size;
    public Level(){
        //I actually am not sure what to do here

        //read external file and call createPuzzle
    }

    public Level(int levelIDin){
        //read external file and call createPuzzle
        ArrayList<int[]> info = getPuzzleInfo(levelID);
        levelID = levelIDin;
        size = info.get(0).length;
        puzzle = createPuzzle(info.get(0), info.get(1), info.get(2));
    }

    public ArrayList<int[]> getPuzzleInfo(int id){
        //TODO: change so that you actually read
        ArrayList<int[]> info = new ArrayList<int[]>();
        int[] pieces = {};
        int[] correct = {};
        int[] start = {};
        info.add(pieces);
        info.add(correct);
        info.add(start);
        return info;
    }

    public Puzzle createPuzzle(int[] pieces, int[] correct, int[] start){
        return new Puzzle(pieces, correct, start);
    }

    public int getLevelID() {
        return levelID;
    }
}
