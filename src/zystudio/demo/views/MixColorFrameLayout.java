package zystudio.demo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class MixColorFrameLayout extends FrameLayout {

    private PorterDuffXfermode myPorterDuff;
    private Paint mPaint;
    final int mFirstColor=0xFFFFCC44;
    final int mSecondColor=0xFF66AAFF;

    public MixColorFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        myPorterDuff = new PorterDuffXfermode(android.graphics.PorterDuff.Mode.MULTIPLY);
        mPaint=new Paint();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(100);
        mPaint.setColor(mSecondColor);
        canvas.drawText("WZY NIHAO HELLO", 50, 200, mPaint);
        int sc = canvas.saveLayer(50, 50, 230,250,null,Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG
                | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG | Canvas.FULL_COLOR_LAYER_SAVE_FLAG
                | Canvas.CLIP_TO_LAYER_SAVE_FLAG);
//        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setTextSize(100);
//        mPaint.setColor(mSecondColor);
        canvas.drawText("WZY NIHAO HELLO", 50, 200, mPaint);

        mPaint.setXfermode(myPorterDuff);
        mPaint.setStyle(Style.FILL);
        mPaint.setColor(mFirstColor);
        Rect rect=new Rect(50,50, 230, 250);
        canvas.drawRect(rect, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(sc);
    }
}
