package mlt.fencepuzzle;

/**
 * Created by timothyho on 3/26/17.
 */

public class FenceDouble extends Fence {
    public FenceDouble (int correct, int start){
        super(correct, start);

        int[] hue = new int[]{1,2};
        super.setDirections(hue);
    }
    @Override
    public int getFenceType() {
        return 3;
    }
}
