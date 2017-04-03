package mlt.fencepuzzle;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Laura Yu on 3/29/2017.
 */

public class Level {
    //read from external file to generate a puzzle given a level id

    //later on we can have this auto-generate puzzles
    private static final String TAG = "Level";
    public Puzzle puzzle;
    private int levelID;
    private int size;
    JSONObject json;
    public Level(){
        //I actually am not sure what to do here

        //read external file and call createPuzzle
    }

    public Level(int levelIDin){
        //read external file and call createPuzzle

//        readJSON();

        ArrayList<int[]> info = getPuzzleInfo(levelID);
        levelID = levelIDin;
        size = info.get(0).length;
        puzzle = createPuzzle(info.get(0), info.get(1), info.get(2));
    }

    private void readJSON() {
//        String jsonString = R.raw.puzzles;
        String jsonString = "???";
        try {
            JSONObject json = new JSONObject(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<int[]> getPuzzleInfo(int levelID){
        //TODO: change so that you actually read
        ArrayList<int[]> info = new ArrayList<>();
//        int[] pieces = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
//        int[] correct = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
//        int[] start = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        int[] pieces = {0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,
                0,0,0,2,2,0,0,0,
                0,0,0,2,2,0,0,0,
                0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0};
        int[] correct = {0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,
                0,0,0,1,2,0,0,0,
                0,0,0,0,3,0,0,0,
                0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0};
        int[] start = {0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,
                0,0,0,2,2,0,0,0,
                0,0,0,2,2,0,0,0,
                0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0};

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
