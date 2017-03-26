package mlt.fencepuzzle;

/**
 * Created by timothyho on 3/26/17.
 */

public class FenceBlank extends Fence {
    public FenceBlank(int xPos, int yPos){
        super(Orientation.NONE, FenceType.BLANK, xPos, yPos);
    }
}
