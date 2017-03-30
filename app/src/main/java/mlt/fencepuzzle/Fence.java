package mlt.fencepuzzle;

/**
 * Created by timothyho on 3/26/17.
 */

public class Fence {

    //laura note: enums can't be inherited and altered, use this instead :P
    private int[] directions;
    private int correctDir;
    private int startDir;
    private int currDir;
    public boolean isCorrect;

    public Fence() {
    }

    public int getCorrectDir() {
        return correctDir;
    }

    public int getStartDir() {
        return startDir;
    }

    public int getCurrDir() {
        return currDir;
    }

    public void setCurrDir(int currDir) {
        this.currDir = currDir;
    }

    public Fence (int correct, int start, int[] in_directions){
        correctDir = correct;
        startDir = start;
        currDir = start;
        updateCorrect();
        directions = in_directions;
    }

    public Fence (int correct, int start){
        correctDir = correct;
        startDir = start;
        currDir = start;
        updateCorrect();
    }


    //returns the next direction (up->right->down->left->up...)
    public int getNext(){
        return directions[(currDir+1) % directions.length];
    }

    public boolean checkCorrect(){
        return currDir == correctDir;
    }

    public void updateCorrect(){
        isCorrect = checkCorrect();
    }

    public void setDirections(int[] in){
        directions = in;
    }
}
