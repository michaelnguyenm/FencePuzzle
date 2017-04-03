package mlt.fencepuzzle;

/**
 * Created by timothyho on 3/26/17.
 */

public class FenceBlank extends Fence {
    public FenceBlank (int correct, int start){
        super(correct, start);

        int[] hue = new int[]{0};
        super.setDirections(hue);
    }

    @Override
    public int getFenceType() {
        return 0;
    }


}
