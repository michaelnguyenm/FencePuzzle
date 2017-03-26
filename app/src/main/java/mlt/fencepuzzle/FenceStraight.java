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
        if(this.getOrientation() == Orientation.VERTICAL){
            this.setOrientation(Orientation.HORIZONTAL);
        } else {
            this.setOrientation(Orientation.VERTICAL);
        }
    }
}
