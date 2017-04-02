package mlt.fencepuzzle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
/**
 * Created by Laura Yu on 3/29/2017.
 */

public class BoardView extends View{
    private static final String TAG = "BoardView";

    private FencePuzzleGame game;
    private float boardWidth;

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, " in the 2 parameter constructor");
        init(context);
    }

    public BoardView(Context context){
        super(context);
        Log.d(TAG, " in the 1 parameter constructor");
        init(context);
    }

    private void init(Context context) {
        if(context instanceof GameActivity) {
            this.game = (GameActivity) context;
            game.setBoardView(this);
            setFocusable(true);
            setFocusableInTouchMode(true);
            buildBoard();
        }
        else
            Log.e(TAG, "In BoardView constructor. context is not a GameActivity!: " + context);
    }

    private void buildBoard() {
        Log.d(TAG, "In createBoard. level ID: " + game.getLevelID());



    }

    private void drawFenceBlank(){

    }

}
