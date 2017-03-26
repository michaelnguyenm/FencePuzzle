package mlt.fencepuzzle;

/**
 * Created by timothyho on 3/26/17.
 */

public class Fence {

    //Assuming rotation is clockwise
    public enum Orientation {
        NONE, UP, LEFT, DOWN, RIGHT, VERTICAL, HORIZONTAL
    }

    public enum FenceType{
        BLANK, STRAIGHT, CURVED, DOUBLE
    }

    private Orientation currentOrientation;
    private Orientation correctOrientation;
    private FenceType typeFence;
    private boolean movable;
    private int xPos;
    private int yPos;

    public Fence(Orientation currentOrientation, FenceType typeFence, int xPos, int yPos){
        this.currentOrientation = currentOrientation;
        this.correctOrientation = currentOrientation;
        this.typeFence = typeFence;
        if(typeFence == FenceType.BLANK){
            movable = false;
        }
        this.xPos = xPos;
        this.yPos = yPos;
    }


    public Orientation getOrientation(){
        return currentOrientation;
    }

    public void setOrientation(Orientation newOrientation){
        currentOrientation = newOrientation;
    }

    public FenceType getTypeFence(){
        return typeFence;
    }

    public boolean canMove(){
        return movable;
    }

    public int getXPos(){
        return xPos;
    }

    public int getYPos(){
        return yPos;
    }

    public boolean solutionChecker(){
        return currentOrientation == correctOrientation;
    }
}
