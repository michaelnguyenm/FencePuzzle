package mlt.fencepuzzle;

import android.content.Context;
import android.util.Log;
import java.lang.String;
import java.io.InputStream;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
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

    public Level(Context context, int levelIDin){
        //read external file and call createPuzzle
        String jsonString = readJSON(context);
        JSONObject json = null;
        try{
            json = new JSONObject(jsonString);
        } catch (JSONException e){
            Log.e("LEVEL", "Unexpected JSON exception", e);
        }


        ArrayList<int[]> info = getPuzzleInfo(levelIDin, json);
        levelID = levelIDin;
        size = info.get(0).length;
        puzzle = createPuzzle(info.get(0), info.get(1), info.get(2));
    }

    private String readJSON(Context context) {
//        String jsonString = R.raw.puzzles;
        String jsonString = null;
        try {
            InputStream is = context.getAssets().open("puzzles.json");
            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public ArrayList<int[]> getPuzzleInfo(int levelID, JSONObject json){
        //TODO: change so that you actually read
        ArrayList<int[]> info = new ArrayList<>();
        int[] pieces = new int[64];
        int[] correct = new int[64];
        //int[] start = {};
        Log.d(TAG, "Level id in Level.java is:" + levelID);
//        int[] pieces = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
//        int[] correct = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int[] start = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
//        if(levelID ==1){
//        pieces =new int[] {0,0,0,0,0,0,0,0,
//                0,0,0,0,0,0,0,0,
//                0,0,0,0,0,0,0,0,
//                0,0,0,2,2,0,0,0,
//                0,0,0,2,2,0,0,0,
//                0,0,0,0,0,0,0,0,
//                0,0,0,0,0,0,0,0,
//                0,0,0,0,0,0,0,0};
//        correct = new int[] {0,0,0,0,0,0,0,0,
//                0,0,0,0,0,0,0,0,
//                0,0,0,0,0,0,0,0,
//                0,0,0,1,2,0,0,0,
//                0,0,0,0,3,0,0,0,
//                0,0,0,0,0,0,0,0,
//                0,0,0,0,0,0,0,0,
//                0,0,0,0,0,0,0,0};
//        start = new int[] {0,0,0,0,0,0,0,0,
//                0,0,0,0,0,0,0,0,
//                0,0,0,0,0,0,0,0,
//                0,0,0,2,2,0,0,0,
//                0,0,0,2,2,0,0,0,
//                0,0,0,0,0,0,0,0,
//                0,0,0,0,0,0,0,0,
//                0,0,0,0,0,0,0,0};}
//
//        if(levelID ==9){
//            pieces = new int[]
//                    {2,2,2,1,2,2,2,0,
//                    2,3,3,1,2,2,3,2,
//                    0,2,3,1,2,2,3,2,
//                    2,1,3,1,3,2,2,2,
//                    2,2,1,0,1,2,1,2,
//                    0,2,3,2,2,3,2,0,
//                    2,1,3,3,2,1,2,2,
//                    2,1,2,2,2,2,1,2};
//            correct = new int[]
//                    {1,2,1,1,2,1,2,0,
//                    0,0,1,1,3,0,0,2,
//                    0,0,0,1,2,1,1,3,
//                    1,1,1,1,0,3,0,2,
//                    0,2,0,0,0,1,1,3,
//                    0,0,0,2,0,0,2,0,
//                    1,1,1,0,2,0,0,2,
//                    0,1,3,0,3,0,1,3};
//            start = new int[]
//                    {0,0,0,0,0,0,0,0,
//                    0,0,0,0,0,0,0,0,
//                    0,0,0,0,0,0,0,0,
//                    0,0,0,0,0,0,0,0,
//                    0,0,0,0,0,0,0,0,
//                    0,0,0,0,0,0,0,0,
//                    0,0,0,0,0,0,0,0,
//                    0,0,0,0,0,0,0,0};}

        String lvl = "" + levelID;
        JSONArray arraysJson = json.optJSONArray(lvl);
        //Grab correct from json
        JSONArray jsonCorrect = null;
        try{
            jsonCorrect = arraysJson.getJSONArray(0);
        } catch(JSONException e) {
            Log.e("LEVEL", "Unexpected JSON exception", e);
        }
        //Grab pieces from JSON
        JSONArray jsonPieces = null;
        try{
            jsonPieces = arraysJson.getJSONArray(1);
        } catch(JSONException e) {
            Log.e("LEVEL", "Unexpected JSON exception", e);
        }
        for(int i = 0; i < 64; i++){
            //Populate the correct and pieces array which will be used to populate the board.
            correct[i] =  jsonCorrect.optInt(i);
            pieces[i] = jsonPieces.optInt(i);
            start[i] = 0;
        }


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
