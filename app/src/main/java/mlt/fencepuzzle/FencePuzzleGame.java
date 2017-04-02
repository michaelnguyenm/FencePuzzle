package mlt.fencepuzzle;

/**
 * Created by Laura Yu on 3/29/2017.
 */

public class FencePuzzleGame {
    //uhhh I get painted I think?
    //I store move logic, like turns taken, maybe undo button?
    //I don't deal with the instructions or settings buttons, so don't get me confused with the activity screen

    //I will assume the puzzle has been created
    private int turns;

    private Level level;

    public FencePuzzleGame(int levelID){
        level = new Level(levelID);
        turns =0;
    }

    private void movePiece(int piecePos){
        level.puzzle.movePiece(piecePos);
    }

    public int getLevelID(){
        return level.getLevelID();
    }

}
