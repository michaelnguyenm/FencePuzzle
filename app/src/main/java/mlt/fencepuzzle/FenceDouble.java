package mlt.fencepuzzle;

/**
 * Created by timothyho on 3/26/17.
 */

public class FenceDouble extends Fence {
    public FenceDouble(Orientation fenceOrientation, int xPos, int yPos){
        super(fenceOrientation, FenceType.CURVED, xPos, yPos);
    }

    public FenceDouble(int xPos, int yPos){
        super(Orientation.LEFT, FenceType.CURVED, xPos, yPos);
    }

    public void changeOrientation(){
        if(super.getOrientation() == Orientation.LEFT){
            super.setOrientation(Orientation.RIGHT);
        } else {
            super.setOrientation(Orientation.LEFT);
        }
    }
}
