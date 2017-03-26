package mlt.fencepuzzle;

/**
 * Created by timothyho on 3/26/17.
 */

public class FenceStraight extends Fence {
    public FenceStraight(Orientation fenceOrientation, int xPos, int yPos){
        super(fenceOrientation, FenceType.STRAIGHT, xPos, yPos);
    }

    public FenceStraight(int xPos, int yPos){
        super(Orientation.VERTICAL, FenceType.STRAIGHT, xPos, yPos);
    }

    public void changeOrientation(){
        if(super.getOrientation() == Orientation.VERTICAL){
            super.setOrientation(Orientation.HORIZONTAL);
        } else {
            super.setOrientation(Orientation.VERTICAL);
        }
    }
}
