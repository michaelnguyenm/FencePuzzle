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
        if(this.getOrientation() == Orientation.UP){
            this.setOrientation(Orientation.RIGHT);
        } else if(this.getOrientation() == Orientation.RIGHT) {
            this.setOrientation(Orientation.DOWN);
        } else if(this.getOrientation() == Orientation.DOWN){
            this.setOrientation(Orientation.LEFT);
        } else {
            this.setOrientation(Orientation.UP);
        }
    }
}
