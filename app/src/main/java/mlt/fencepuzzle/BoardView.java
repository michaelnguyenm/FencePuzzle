package mlt.fencepuzzle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
/**
 * Created by Laura Yu on 3/29/2017.
 */

//I GET PAINTED PLS NO LOGIC ;-;
public class BoardView extends View{
    private static final String TAG = "BoardView";

    private Puzzle mPuzzle;
    private Level mLevel;
    private int boardWidth;
    private Paint mPaint;
    private int fenceWidth;

    private Bitmap mBlank;
    private Bitmap mStraight;
    private Bitmap mCurved;
    private Bitmap mDouble;

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, " in the 2 parameter constructor");
        init();
    }

    public void setPuzzle(Puzzle game) {
        mPuzzle = game;
    }

    public void setLevel(Level game) {
        mLevel = game;
    }

//    public BoardView(Context context){
//        super(context);
//        Log.d(TAG, " in the 1 parameter constructor");
//        init(context);
//    }

    private void init() {
        boardWidth = getWidth();
        fenceWidth = boardWidth/8;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // Make thick, light gray lines
        mPaint.setColor(Color.LTGRAY);
        mPaint.setStrokeWidth(6);

        mBlank = BitmapFactory.decodeResource(getResources(), R.drawable.tile_blank);
        mStraight = BitmapFactory.decodeResource(getResources(), R.drawable.tile_straight);
        mCurved = BitmapFactory.decodeResource(getResources(), R.drawable.tile_curved1);
        mDouble = BitmapFactory.decodeResource(getResources(), R.drawable.tile_double);
//****
//        mBlank = Bitmap.createScaledBitmap(
//                mBlank, fenceWidth, fenceWidth, false);
//        mStraight = Bitmap.createScaledBitmap(
//                mStraight, fenceWidth, fenceWidth, false);
//        mCurved = Bitmap.createScaledBitmap(
//                mCurved, fenceWidth, fenceWidth, false);
//        mDouble = Bitmap.createScaledBitmap(
//                mDouble, fenceWidth, fenceWidth, false);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Log.d(TAG, "In BoardView. method onDraw" );


        buildBoard(boardWidth, canvas);

    }

    private void buildBoard(int boardWidth, Canvas canvas) {
        Log.d(TAG, "In BoardView. method buildBoard" );
        fenceWidth = getWidth()/8;
        Rect drawingRect = new Rect();

//***testing
//        int col = 0;
//        int row = 0;
//        fenceWidth = getWidth()/8;
//        drawingRect.left = col * fenceWidth; // x coordinate of left side of rect
//        drawingRect.top = row * fenceWidth; // y coordinate of top of rect
//        drawingRect.right = drawingRect.left + fenceWidth; // x coordinate of right side of rect
//        drawingRect.bottom = drawingRect.top + fenceWidth; // y coordinate of bottom of rect
//        canvas.drawBitmap(mDouble, null, drawingRect, null);
//
//
//        Log.d(TAG, "In BoardView. after drawing tile" );

        //***

        for(int i=0; i<64; i++){
            int col = i % 8;
            int row = i / 8;

            // Define the boundaries of a destination rectangle for the image
            drawingRect.left = col * fenceWidth; // x coordinate of left side of rect
            drawingRect.top = row * fenceWidth; // y coordinate of top of rect
            drawingRect.right = drawingRect.left + fenceWidth; // x coordinate of right side of rect
            drawingRect.bottom = drawingRect.top + fenceWidth; // y coordinate of bottom of rect

            Fence temp = mPuzzle.getFenceAt(i);
            Log.d(TAG, "In BoardView. temp.getFenceType:" +  temp.getFenceType());

            Bitmap toDraw;

            switch (temp.getFenceType()){
                case 0:
                    toDraw = drawFenceBlank(temp);
                    break;
                case 1:
                    toDraw = drawFenceStraight(temp);
                    break;
                case 2:
                    toDraw = drawFenceCurved(temp);
                    break;
                case 3:
                    toDraw = drawFenceDouble(temp);
                    break;
                default:
                    //TODO: add some error message
                    toDraw = drawFenceBlank(temp);
                    break;
            }
            canvas.drawBitmap(toDraw, null, drawingRect, null);

        }

    }

    private Bitmap drawFenceDouble(Fence temp) {
        return mDouble;
    }

    private Bitmap drawFenceCurved(Fence temp) {
        return mCurved;
    }

    private Bitmap drawFenceStraight(Fence temp) {
        return mStraight;
    }

    private Bitmap drawFenceBlank(Fence temp){
        return mBlank;
    }


}
