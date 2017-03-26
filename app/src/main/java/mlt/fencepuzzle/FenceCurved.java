package mlt.fencepuzzle;

/**
 * Created by timothyho on 3/26/17.
 */

public class FenceCurved extends Fence {
    public FenceCurved(Orientation fenceOrientation, int xPos, int yPos){
        super(fenceOrientation, FenceType.CURVED, xPos, yPos);
    }

    public FenceCurved(int xPos, int yPos){
        super(Orientation.UP, FenceType.CURVED, xPos, yPos);
    }

    public void changeOrientation(){
        if(super.getOrientation() == Orientation.UP){
            super.setOrientation(Orientation.RIGHT);
        } else if(super.getOrientation() == Orientation.RIGHT) {
            super.setOrientation(Orientation.DOWN);
        } else if(super.getOrientation() == Orientation.DOWN){
            super.setOrientation(Orientation.LEFT);
        } else {
            super.setOrientation(Orientation.UP);
        }
    }
}
