package mlt.fencepuzzle;

/**
 * Created by timothyho on 3/26/17.
 */

public class FenceBlank extends Fence {
    public FenceBlank (int correct, int start){
        super(correct, start);

        int[] hue = new int[]{1};
        super.setDirections(hue);
    }


}
