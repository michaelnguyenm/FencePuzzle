package mlt.fencepuzzle;

/**
 * Created by timothyho on 3/26/17.
 */

public class FenceCurved extends Fence {
    public FenceCurved (int correct, int start){
        super(correct, start);

        int[] hue = new int[]{1,2,3,4};
        super.setDirections(hue);
    }
}
